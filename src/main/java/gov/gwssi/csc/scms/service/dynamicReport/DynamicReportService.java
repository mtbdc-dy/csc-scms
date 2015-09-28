package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.*;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ColumnRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.ReportConfigurationRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.TableRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DynamicReportService extends DynamicReportSpecs {
    @Qualifier("reportConfigurationRepository")
    @Autowired
    private ReportConfigurationRepository reportConfigurationRepository;

    @Qualifier("tableRepository")
    @Autowired
    private TableRepository tableRepository;

    @Qualifier("columnRepository")
    @Autowired
    private ColumnRepository columnRepository;

    public Page<ReportConfiguration> getAllConfigurationsByFilter(Filter filter) {

        return reportConfigurationRepository.findAll(filterIsLike(filter), new PageRequest(0, 20));
    }

    @Transactional
    public void getColumns() {
        for (Table table : tableRepository.findAll()) {
            System.out.println(table.getColumns().size());
        }
    }

    @Transactional
    public Column getColumn(String id) {
        return columnRepository.findOne(id);
    }

    @Transactional
    public Set<Table> getTables() {
        Set<Table> tables = new HashSet<Table>((ArrayList<Table>) tableRepository.findAll());
        return tables;
    }

    @Transactional
    public Table getTable(String id) {
        Table table = getTableWithoutColumns(id);
        // 用于执行 Columns 的查询
        table.getColumns().size();
        return table;
    }

    @Transactional
    public Table getTableWithoutColumns(String id) {
        Table table = tableRepository.findOne(id);
        return table;
    }

    @Transactional
    public String generateSQL(OriginalConfiguration configuration) {
        String SQL = "select *\n";
        SQL += "  from SCMS_STUDENT\n";
        for (JoinCondition joinCondition : configuration.getJoinConditions()) {
            Table table = getTable(joinCondition.getTable());
            SQL += "  join " + table.getTableEn() + " on SCMS_STUDENT.ID = " + table.getTableEn() + ".STUDENTID\n";
        }

        if (configuration.getWhereConditions().size() > 0) {
            SQL += " where ";
            for (WhereCondition whereCondition : configuration.getWhereConditions()) {
                Column column = getColumn(whereCondition.getColumn());
                SQL += whereCondition.getlParenthese() + column.getTable().getTableEn() + "." + column.getColumnEn() + getOperator(whereCondition.getOperator()) + "'" + whereCondition.getCondition() + "'" + whereCondition.getrParenthese() + getLogic(whereCondition.getLogic());
            }
        }

        SQL += "\n";
        if (configuration.getGroupConditions().size() > 0) {
            SQL += " group by grouping sets((),(";
            for (GroupCondition groupCondition : configuration.getGroupConditions()) {
                Column column = getColumn(groupCondition.getColumn());
                SQL += column.getTable().getTableEn() + "." + column.getColumnEn() + ", ";
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL += "))\n";
        }

        if (configuration.getOrderConditions().size() > 0){
            SQL += " order by ";
            for (OrderCondition orderCondition : configuration.getOrderConditions()) {
                Column column = getColumn(orderCondition.getColumn());
                SQL += column.getTable().getTableEn() + "." + column.getColumnEn() + getOrder(orderCondition.getOrderType()) + ", ";
            }
            SQL = SQL.substring(0, SQL.length() - 2);
        }

        return SQL;
    }

    private String getOperator(String id) {
        String operator = null;
        if (id.equals("1")) operator = " = ";
        if (id.equals("2")) operator = " <> ";
        if (id.equals("3")) operator = " > ";
        if (id.equals("4")) operator = " >= ";
        if (id.equals("5")) operator = " < ";
        if (id.equals("6")) operator = " <= ";
        if (id.equals("7")) operator = " LIKE ";
        if (id.equals("8")) operator = " NOT LIKE ";
        return operator;
    }

    private String getLogic(String id) {
        String logic = "";
        if (id.equals("1")) logic = " AND ";
        if (id.equals("2")) logic = " OR ";
        return logic;
    }

    private String getOrder(String id){
        String order = null;
        if (id.equals("1")) order = " ASC ";
        if (id.equals("2")) order = " DESC ";
        return order;
    }


}
