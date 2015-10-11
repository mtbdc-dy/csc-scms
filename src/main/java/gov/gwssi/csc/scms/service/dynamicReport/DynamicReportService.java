package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.domain.dynamicReport.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.*;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DynamicReportService extends DynamicReportSpecs {

    @Autowired
    @Qualifier("reportConfigurationRepository")
    private ReportConfigurationRepository reportConfigurationRepository;

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

    public Page<ReportConfiguration> getAllConfigurationsByFilter(Filter filter) {

        return reportConfigurationRepository.findAll(filterIsLike(filter), new PageRequest(0, 20));
    }

    public void deleteConfigurations(String id) {
        reportConfigurationRepository.delete(id);
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

    public String getSelectSQL(List<String> codeTableNames) {
        String selectSQL = "";
        List<List<DictTreeJson>> lists = new ArrayList<List<DictTreeJson>>();
        int[] sizes = new int[codeTableNames.size()];
        int total = 1;
        for (int i = 0; i < codeTableNames.size(); i++) {
            List<DictTreeJson> codeTables = translateDictService.getCodeTableList(codeTableNames.get(i));
            lists.add(codeTables);
            sizes[i] = codeTables.size();
            total *= sizes[i];
//            total = max > sizes[i] ? max : sizes[i];
        }
//        for (String codeTableName : codeTableNames) {
//            List<DictTreeJson> codeTables = translateDictService.getCodeTableList(codeTableName);
//            lists.add(codeTables);
//            max = max > codeTables.size() ? max : codeTables.size();
//        }
//
//        String[][] matrix = new String[codeTableNames.size()][max];
//
//        for (int i = 0; i < lists.size(); i++) {
//            List<DictTreeJson> list = lists.get(i);
//            for (DictTreeJson dictTreeJson : list) {
//                String code = dictTreeJson.getCode();
//                for (int i1 = 0; i1 < matrix.length; i1++) {
//                    for (int j1 = 0; j1 < max; j1++) {
//                        matrix[i1][j1] =
//                    }
//                }
//            }
//        }

        return selectSQL;
    }

    @Transactional
    public String generateSQL(OriginalConfiguration configuration) {
        String SQL = "\nselect \n";
        // 显示的分组列
        for (GroupCondition groupCondition : configuration.getGroupConditions()) {
            Column column = getColumn(groupCondition.getColumn());
            SQL += "  nvl(" + column.getTable().getTableEn() + "." + column.getColumnEn() + ", '-'),\n";
        }

        List<String> codeTables = new ArrayList<String>();
        for (SelectCondition selectCondition : configuration.getSelectConditions()) {
            Column column = getColumn(selectCondition.getColumn());
            Table table = column.getTable();
            if (column.getCodeTable() == null) {
                System.out.println("codeTables = " + codeTables);
                getSelectSQL(codeTables);
                codeTables.clear();
                SQL += "  nvl(count(" + table.getTableEn() + "." + column.getColumnEn() + "), 0),\n";
            } else if (selectCondition.getLevel() == 1) {
                System.out.println("codeTables = " + codeTables);
                getSelectSQL(codeTables);
                codeTables.clear();
                codeTables.add(column.getCodeTable());
            } else {
                codeTables.add(column.getCodeTable());
            }
        }

        // 显示的合计列
        SQL += "  nvl(sum(CASE WHEN 1 = 1 THEN 1 END), 0)\n";

        SQL += "  from SCMS_STUDENT\n";
        for (JoinCondition joinCondition : configuration.getJoinConditions()) {
            Table table = getTable(joinCondition.getTable());
            SQL += "  join " + table.getTableEn() + " on SCMS_STUDENT.ID = " + table.getTableEn() + ".STUDENTID\n";
        }

        if (configuration.getWhereConditions().size() > 0) {
            SQL += " where ";
            for (WhereCondition whereCondition : configuration.getWhereConditions()) {
                Column column = getColumn(whereCondition.getColumn());
                SQL += whereCondition.getLParenthese() + column.getTable().getTableEn() + "." + column.getColumnEn() + getOperator(whereCondition.getOperator()) + "'" + whereCondition.getCondition() + "'" + whereCondition.getRParenthese() + getLogic(whereCondition.getLogic());
            }
        }

        SQL += "\n";
        if (configuration.getGroupConditions().size() > 0) {
            SQL += " group by ";
            for (GroupCondition groupCondition : configuration.getGroupConditions()) {
                Column column = getColumn(groupCondition.getColumn());
                SQL += column.getTable().getTableEn() + "." + column.getColumnEn() + ", ";
            }
            SQL = SQL.substring(0, SQL.length() - 2);
            SQL += "\n";
        }

        if (configuration.getOrderConditions().size() > 0) {
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

    private String getOrder(String id) {
        String order = null;
        if (id.equals("1")) order = " ASC ";
        if (id.equals("2")) order = " DESC ";
        return order;
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
        if (column.getCodeTable() != null) { // 代码列
            List<DictTreeJson> list = translateDictService.getCodeTableList(column.getCodeTable());
            for (DictTreeJson dictTreeJson : list) { // 遍历代码值
                Integer colSpan = getColSpan(selectConditions, currentLevel, maxLevel);
                cells.add(new Cell(getId(), currentLevel, 1, colSpan, dictTreeJson.getValue()));
            }
        } else { // 非代码列
            cells.add(new Cell(getId(), 1, maxLevel, 1, column.getColumnCh()));
        }
        // 小计列
        if (selectCondition.getSumColumn()) {
            Integer rowSpan = maxLevel + 1 - currentLevel;
            cells.add(new Cell(getId(), currentLevel,rowSpan, 1, "小计"));
        }

        return cells;
    }

    private Integer getTurns(SelectCondition selectCondition, List<SelectCondition> selectConditions, Integer currentLevel) {
        Integer turns = 1;
        Column column = columnRepository.findOne(selectCondition.getColumn());
        if (column.getCodeTable() != null) {
            for (Integer level = 1; level < currentLevel; level++) {
                for (SelectCondition condition : selectConditions) {
                    if (condition.getLevel().equals(level)){
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

    private String getId() {
        return configurationRepository.newId("SEQ_D_CFG");
    }

    @Transactional
    public Configuration saveNewConfig(Configuration configuration){
        List<Cell> cells = generateHead(configuration);
        configuration.setId(getId());
        configurationRepository.save(configuration);
        for (Cell cell : cells) {
            cell.setConfig(configuration);
        }
        cellRepository.save(cells);
        configuration.setCells(cells);
        return configuration;
    }
}
