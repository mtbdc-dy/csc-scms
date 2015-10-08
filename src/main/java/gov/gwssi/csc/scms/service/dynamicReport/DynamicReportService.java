package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.domain.dynamicReport.*;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ColumnRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.ReportConfigurationRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.TableRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.tools.jconsole.Tab;

import java.util.*;

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

    @Qualifier("translateDictService")
    @Autowired
    private TranslateDictService translateDictService;

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

    public String getSelectSQL(List<String> codeTableNames) {
        String selectSQL = "";
        int[] sizes = new int[codeTableNames.size()];
        int max = 1;
        for (int i = 0; i < codeTableNames.size(); i++) {
            int codeTableSize = translateDictService.getCodeTableList(codeTableNames.get(i)).size();
            max = max > codeTableSize ? max : codeTableSize;
        }
        String[][] matrix = new String[codeTableNames.size()][max];
        for (int i = codeTableNames.size() - 1; i >= 0; i--) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = i + "";
            }
        }
        System.out.println("matrix = " + matrix);
//        List<List<String>> matrix = new ArrayList<List<String>>();
//        Map<Point, String> matrix = new HashMap<Point, String>();
//        for (int i = 0; i < codeTableNames.size(); i++) {
//            List<DictTreeJson> codeTables = translateDictService.getCodeTableList(codeTableNames.get(i));
//            for (int j = 0; j < codeTables.size(); j++) {
//                DictTreeJson codeTable = codeTables.get(j);
//                matrix.put(new Point(i, j), codeTable.getCode());
//            }
//        }
//        System.out.println("matrix = " + matrix);
//        for (String codeTableName : codeTableNames) {
//            for (DictTreeJson dictTreeJson : translateDictService.getCodeTableList(codeTableName)) {
////                dictTreeJson.getCode()
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
                SQL += whereCondition.getlParenthese() + column.getTable().getTableEn() + "." + column.getColumnEn() + getOperator(whereCondition.getOperator()) + "'" + whereCondition.getCondition() + "'" + whereCondition.getrParenthese() + getLogic(whereCondition.getLogic());
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


}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return x * 10 + y;
    }

    @Override
    public boolean equals(Object o) {
        return this.hashCode() == o.hashCode();
    }
}
