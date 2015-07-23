package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.dao.statistics.DStatisticsDAO;
import gov.gwssi.csc.scms.domain.statistics.*;
import gov.gwssi.csc.scms.repository.statistics.*;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/6/9.
 * 逻辑处理流程：1、对前台组装的JSON进行解析，分别为DConfig、DTableList、DConfigCondition、DDisplay(自动生成configId,然后插入各个表形成关联关系)
 *               2、根据configId,调用存储过程p_scms_d_Statistics(avc_configid in varchar2,avc_result out varchar2)，结果输出：0：失败；1：成功；2：缺少关联条件
 *               3、调用存储过程成功，可查询动态统计查询表头配置信息表(DHeader)、动态查询统计表头合并表(DHeaderMerge)
 *               4、返回JsonBody: 几个重要的属性：1、动态查询统计配置表[sql语句]
 *                                                2、动态查询统计表头配置信息
 *                                                3、动态查询统计表头合并表
 *                                                4、统计的预览数据List
 *
 */

@Service("dStatisticsHandlerService")
public class DStatisticsHandlerService extends BaseService {
    // 结果输出：失败
    private static final String FAILURE = "0";
    // 结果输出:成功
    private static final String SUCCESS = "1";
    // 结果输出:缺少关联条件
    private static final String LOSE_RELATION_CONDITION = "2";

    @Autowired
    private DConfigService dConfigService;

    @Autowired
    private DConditionService dConditionService;

    @Autowired
    private DDisplayService dDisplayService;

    @Autowired
    private DTableListService dTableListService;

    @Autowired
    private DStatisticsDAO dStatisticsDAO;

    @Autowired
    private DConfigRepository dConfigRepository;

    @Autowired
    private DTableListRepository dTableListRepository;

    @Autowired
    private DConditionRepository dConditionRepository;

    @Autowired
    private DDisplayRepository dDisplayRepository;

    @Autowired
    private DHeaderRepository dHeaderRepository;

    @Autowired
    private DHeaderMergeRepository dHeaderMergeRepository;

    // 保存动态查询统计配置、统计表清单、条件配置、显示样式
    @Transactional
    public ReturnStatistics save(DConfig dConfig, List<DTableList> dTableList, List<DCondition> dConditions, List<DDisplay> dDisplays){

        ReturnStatistics rtnStatistics = null;

                // 生成动态查询统计配置表configId
        String configId = dStatisticsDAO.generateIdBySequence("SEQ_D_CONFIG");

        if(configId != null && !configId.equals("")){
            if(dConfig != null){
                saveDConfig(dConfig, configId);
            }
            if(dTableList != null){
                saveDTableList(configId, dTableList);
            }
            if(dConditions != null){
                saveDConditions(configId, dConditions);
            }
            if(dDisplays != null){
                saveDDisplays(configId, dDisplays);
            }

            boolean flag = invokeProcedureDStatistics(configId);

            // 调用存储过程成功
            if(flag){
                rtnStatistics = generateReturnStatistics(configId);
            }
        }

        return rtnStatistics;

    }

    //  保存动态查询配置表
    public void saveDConfig(DConfig dConfig,String configId){
            if(configId != null && !configId.equals("") && dConfig != null){
                dConfig.setId(configId);
                dConfigRepository.save(dConfig);
            }

    }

    // 保存动态查询统计表清单
    public void saveDTableList(String configId,List<DTableList> dTableList){
        if(configId != null && !configId.equals("") && dTableList != null) {
            for (DTableList dTable : dTableList) {
                String id = dStatisticsDAO.generateIdBySequence("SEQ_D_TABLELIST");
                dTable.setId(id);
                dTable.setConfigId(configId);
                dTableListRepository.save(dTable);
            }
        }
    }

    // 生成动态查询统计条件配置
    public void saveDConditions(String configId,List<DCondition> dConditions){
        if(configId != null && !configId.equals("") && dConditions != null) {
            for (DCondition dCondition : dConditions) {
                String id = dStatisticsDAO.generateIdBySequence("SEQ_D_CONDITION");
                dCondition.setId(id);
                dCondition.setConfigId(configId);
                dConditionRepository.save(dCondition);
            }
        }
    }

    // 生成动态查询统计显示样式
    public void saveDDisplays(String configId,List<DDisplay> dDisplays){
        if(configId != null && !configId.equals("") && dDisplays != null) {
            for (DDisplay dDisplay : dDisplays) {
                String id = dStatisticsDAO.generateIdBySequence("SEQ_D_DISPLAY");
                dDisplay.setId(id);
                dDisplay.setConfigId(configId);
                dDisplayRepository.save(dDisplay);
            }
        }
    }

    public boolean invokeProcedureDStatistics(String configId){
        String result = dStatisticsDAO.invokeProcedureDStatistics(configId);
        boolean flag = true;
        if(result != null && !result.equals("")){
            if(result.equals(SUCCESS)){
                  flag = true;
            }
            // 调用存储过程失败后如何处理
            if(result.equals(FAILURE)){
                  flag = false;
            }
            // 缺少关联条件后如何处理
            if(result.equals(LOSE_RELATION_CONDITION)){
                  flag = false;
            }
        }
        return flag;
    }

    /**
     *
     */
    public ReturnStatistics generateReturnStatistics(String configId){
        List<Header> headers = dStatisticsDAO.getHeaders(configId);
        List list = queryHeaderResultListBySql(configId);

        ReturnStatistics rtnStatistics = new ReturnStatistics();
        rtnStatistics.setHeaders(headers);
        rtnStatistics.setResultList(list);

        return rtnStatistics;

    }

    // 根据配置编号动态统计查询表头配置信息表
    public List<DHeader> getDHeadersByConfigId(String configId){
        return dHeaderRepository.findByConfigId(configId);
    }

    // 根据配置编号查询动态统计表头合并表
    public List<DHeaderMerge> getDHeaderMergesByConfigId(String configId){
        return dHeaderMergeRepository.findByConfigId(configId);
    }

    // 根据配置编号获取sql语句的值
    public String getConfigSQLByConfigId(String configId){
        String dConfigSQL = "";
        DConfig dConfig =  dConfigRepository.findOne(configId);
        if(dConfig != null){
            dConfigSQL = dConfig.getSql();
        }
        return dConfigSQL;
    }

    // 根据配置编号查询结果集
    public List queryHeaderResultListBySql(String configId){
        List resultList = null;
        String dConfigSQL = getConfigSQLByConfigId(configId);
        if(dConfigSQL != null && !dConfigSQL.equals("")){
            resultList = super.getBaseDao().queryListBySql(dConfigSQL);
        }
        return resultList;
    }


}
