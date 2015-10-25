package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.domain.dynamicReport.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Row;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.*;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.*;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DynamicReportService extends DynamicReportSpecs {

    @Autowired
    @Qualifier("configurationRepository")
    private ConfigurationRepository configurationRepository;

    @Qualifier("tableRepository")
    @Autowired
    private TableRepository tableRepository;

    @Autowired
    @Qualifier("cellRepository")
    private CellRepository cellRepository;

    @Qualifier("columnRepository")
    @Autowired
    private ColumnRepository columnRepository;

    @Qualifier("translateDictService")
    @Autowired
    private TranslateDictService translateDictService;

    @Qualifier("groupConditionRepository")
    @Autowired
    private GroupConditionRepository groupConditionRepository;
    @Qualifier("selectConditionRepository")
    @Autowired
    private SelectConditionRepository selectConditionRepository;
    @Qualifier("orderConditionRepository")
    @Autowired
    private OrderConditionRepository orderConditionRepository;
    @Qualifier("joinConditionRepository")
    @Autowired
    private JoinConditionRepository joinConditionRepository;
    @Qualifier("whereConditionRepository")
    @Autowired
    private WhereConditionRepository whereConditionRepository;

    public Page<Configuration> getAllConfigurationsByFilter(Filter filter) {

        return configurationRepository.findAll(filterIsLike(filter), new PageRequest(0, 20));
    }

    public Page<Configuration> getAllConfigurationsByFilterAndUserName(Filter filter, String userName) {
        return configurationRepository.findAll(
                where(
                        where(isPublic()).and(filterIsLike(filter))
                ).or(
                        where(userNameIsLike(userName)).and(filterIsLike(filter))
                ), new PageRequest(0, 20));
    }

    public void deleteConfigurations(String id) {
        configurationRepository.delete(id);
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
    public List<Cell> generateHead(Configuration configuration) {
        List<Cell> cells = new ArrayList<Cell>();
        Integer max = getMaxHeight(configuration);

        Cell number = new Cell(getId());
        number.setRowSpan(max);
        number.setValue("#");
        cells.add(number);
        cells.addAll(getGroupCells(configuration));
        cells.addAll(getSelectCells(configuration));

        return cells;
    }

    private List<Cell> getGroupCells(Configuration configuration) {
        List<Cell> cells = new ArrayList<Cell>();
        Integer max = getMaxHeight(configuration);

        for (GroupCondition groupCondition : configuration.getGroupConditions()) {
            Column column = columnRepository.findOne(groupCondition.getColumn());
            Cell cell = new Cell(getId(), 1, max, 1, column.getColumnCh());
            cells.add(cell);
        }

        return cells;
    }

    private List<Cell> getSelectCells(Configuration configuration) {
        List<Cell> cells = new ArrayList<Cell>();
        List<SelectCondition> selectConditions = configuration.getSelectConditions();
        Integer max = getMaxHeight(configuration);
        for (Integer i = 1; i <= max; i++) {
            for (SelectCondition selectCondition : selectConditions) {
                if (selectCondition.getLevel().equals(i)) {
                    Integer turns = getTurns(selectCondition, selectConditions, i);
                    for (Integer integer = 0; integer < turns; integer++) {
                        cells.addAll(getOneLevelSelectCells(selectCondition, selectConditions, i, max));
                    }
                }
            }
        }

        return cells;
    }

    private List<Cell> getOneLevelSelectCells(
            SelectCondition selectCondition,
            List<SelectCondition> selectConditions,
            Integer currentLevel, Integer maxLevel) {
        List<Cell> cells = new ArrayList<Cell>();
        Column column = columnRepository.findOne(selectCondition.getColumn());
        if (column.getCodeTable() != null && selectCondition.getCalculateType().equals("2")) { // 代码列
            List<DictTreeJson> list = translateDictService.getCodeTableList(column.getCodeTable());
            Integer colSpan = 0;
            for (DictTreeJson dictTreeJson : list) { // 遍历代码值
                colSpan = colSpan.equals(0) ? getColSpan(selectConditions, currentLevel, maxLevel) : colSpan;
                cells.add(new Cell(getId(), currentLevel, 1, colSpan, dictTreeJson.getValue()));
            }
        } else { // 非代码列或非代码计数列
            cells.add(new Cell(getId(), 1, maxLevel, 1, column.getColumnCh()));
        }
        // 小计列
        if (selectCondition.getSumColumn()) {
            Integer rowSpan = maxLevel + 1 - currentLevel;
            cells.add(new Cell(getId(), currentLevel, rowSpan, 1, "小计"));
        }

        return cells;
    }

    private Integer getTurns(SelectCondition selectCondition, List<SelectCondition> selectConditions, Integer currentLevel) {
        Integer turns = 1;
        Column column = columnRepository.findOne(selectCondition.getColumn());
        if (column.getCodeTable() != null) {
            for (Integer level = 1; level < currentLevel; level++) {
                for (SelectCondition condition : selectConditions) {
                    if (condition.getLevel().equals(level)) {
                        Column col = columnRepository.findOne(condition.getColumn());
                        List<DictTreeJson> list = translateDictService.getCodeTableList(col.getCodeTable());
                        turns *= list.size();
                    }
                }
            }
        }
        return turns;
    }

    private Integer getColSpan(List<SelectCondition> selectConditions, Integer currentLevel, Integer maxLevel) {
        Integer colSpan = 1;
        for (Integer level = maxLevel; level > currentLevel; level--) {
            for (SelectCondition selectCondition : selectConditions) {
                if (selectCondition.getLevel().equals(level)) {
                    Column column = columnRepository.findOne(selectCondition.getColumn());
                    List<DictTreeJson> list = translateDictService.getCodeTableList(column.getCodeTable());
                    colSpan *= selectCondition.getSumColumn() ? list.size() + 1 : list.size();
                }
            }
        }
        return colSpan;
    }

    private Integer getMaxHeight(Configuration configuration) {
        Integer max = 0;
        for (SelectCondition selectCondition : configuration.getSelectConditions()) {
            Integer level = selectCondition.getLevel();
            max = max > level ? max : level;
        }
        return max;
    }

    public String getId() {
        return configurationRepository.newId("SEQ_D_CFG");
    }

    public <E extends Collection<? extends Condition>> void setIds(E collection) {
        for (Condition condition : collection) condition.setId(getId());
    }

    public <E extends Collection<? extends Condition>> void setIds(E... collections) {
        for (E collection : collections) setIds(collection);
    }

    public <E extends Collection<? extends Config>> void setConfig(Configuration config, E collection) {
        for (Config configuration : collection) configuration.setConfig(config);
    }

    public <E extends Collection<? extends Config>> void setConfig(Configuration config, E... collections) {
        for (E collection : collections) setConfig(config, collection);
    }

    public Configuration createConfig(Configuration configuration) {
        configuration.setId(getId());
        configuration = saveConfig(configuration);
        if (configuration.getReportType().equals("statistics")) {
            configurationRepository.generateStatisticsSQL(configuration.getId());
        } else {
            configurationRepository.generateQuerySQL(configuration.getId());
            List<Cell> cells = generateHead(configuration);
            setConfig(configuration, cells);
            cellRepository.save(cells);
            configuration.setCells(cells);
        }
        return configuration;
    }

    public Configuration updateConfig(Configuration configuration, String id) {
        if (configurationRepository.exists(id)) {
            configurationRepository.delete(id);
        }
        configuration.setId(id);
        configuration = saveConfig(configuration);
        if (configuration.getReportType().equals("statistics")) {
            configurationRepository.generateStatisticsSQL(configuration.getId());
        } else {
            configurationRepository.generateQuerySQL(configuration.getId());
            List<Cell> cells = generateHead(configuration);
            setConfig(configuration, cells);
            cellRepository.save(cells);
            configuration.setCells(cells);
        }
        return configuration;
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Configuration saveConfig(Configuration configuration) {
        Configuration tempConfig = new Configuration(configuration);

        configurationRepository.save(tempConfig);

        Set<JoinCondition> joins = configuration.getJoinConditions();
        List<WhereCondition> wheres = configuration.getWhereConditions();
        Set<GroupCondition> groups = configuration.getGroupConditions();
        Set<OrderCondition> orders = configuration.getOrderConditions();
        List<SelectCondition> selects = configuration.getSelectConditions();

        setIds(joins, wheres, groups, orders, selects);
        setConfig(tempConfig, joins, wheres, groups, orders, selects);

        joinConditionRepository.save(joins);
        whereConditionRepository.save(wheres);
        groupConditionRepository.save(groups);
        orderConditionRepository.save(orders);
        selectConditionRepository.save(selects);

        tempConfig.setJoinConditions(joins);
        tempConfig.setWhereConditions(wheres);
        tempConfig.setGroupConditions(groups);
        tempConfig.setOrderConditions(orders);
        tempConfig.setSelectConditions(selects);

        return tempConfig;
    }

    public Configuration findOne(String id) {
        return configurationRepository.findOne(id);
    }

    public List<Row> getReportHeader(String id) {
        Configuration config = findOne(id);
        Report report = new Report(config.getOrderedCells(), new ArrayList<Map>());
        return report.getHeader();
    }

    public List<Row> getReportBody(String id) {
        Configuration config = findOne(id);
        String sql = config.getSql();
        BaseDAO dao = getBaseDao();
        List<Map> body = dao.queryListBySql(sql);
        Report report = new Report(new ArrayList<Cell>(), body);
        return report.getBody();
    }

    public Report getReport(String id) {
        Configuration config = findOne(id);
        String sql = config.getSql();
        BaseDAO dao = getBaseDao();
        List<Map> body = dao.queryListBySql(sql);
        // TODO Body
        return new Report(config.getOrderedCells(), body);
    }
}
