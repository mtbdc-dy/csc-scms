package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.domain.dynamicReport.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.*;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.ExcelCell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Row;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.*;
import gov.gwssi.csc.scms.service.dictionary.TranslateDictService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.io.*;
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

    private Configuration saveConfigration(Configuration configuration) throws Exception{
        configuration = saveConfig(configuration);
        if (configuration.getReportType().equals("statistics")) {
            String result = configurationRepository.generateStatisticsSQL(configuration.getId());
            if (!result.equals("1")) {
                configurationRepository.delete(configuration.getId());
                if (result.equals("4")) {
                    throw new Exception("配置失败，报表统计列数过多！");
                } else {
                    throw new Exception("配置失败！");
                }
            }
        } else {
            configurationRepository.generateQuerySQL(configuration.getId());
            List<Cell> cells = generateHead(configuration);
            setConfig(configuration, cells);
            cellRepository.save(cells);
            configuration.setCells(cells);
        }
        return configuration;
    }

    public Configuration createConfig(Configuration configuration) throws Exception {
        configuration.setId(getId());
        return saveConfigration(configuration);
    }

    public Configuration updateConfig(Configuration configuration, String id) throws Exception {
        if (configurationRepository.exists(id)) {
            configurationRepository.delete(id);
        }
        configuration.setId(id);
        return saveConfigration(configuration);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Configuration saveConfig(Configuration configuration) {
        Configuration tempConfig = new Configuration(configuration);

        configurationRepository.save(tempConfig);

        Set<JoinCondition> joins = configuration.getJoinConditions();
        List<WhereCondition> wheres = configuration.getWhereConditions();
        List<GroupCondition> groups = configuration.getGroupConditions();
        List<OrderCondition> orders = configuration.getOrderConditions();
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

    @Transactional
    public Report getReport(String id) {
        Configuration config = findOne(id);
        String sql = config.getSql();
        BaseDAO dao = getBaseDao();
        List<Map> body = dao.queryListBySql(sql);
        return new Report(config.getOrderedCells(), body);
    }

    @Transactional
    public List<ExcelCell> getExcelHeader(String id) {
        return configurationRepository.findOne(id).getExcelCells();
    }

    @Transactional
    public byte[] export(String id, OutputStream outputStream) throws IOException {
        List<ExcelCell> header = getExcelHeader(id);
        List<Row> body = getReportBody(id);
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("sheet1");
        sheet.setDisplayGridlines(false);

        createExportHeader(header, sheet, workbook);
        createExportBody(body, sheet, workbook);

        if (outputStream != null) {
            workbook.write(outputStream);
        }
        return ((HSSFWorkbook) workbook).getBytes();
    }

    @Transactional
    public byte[] export(String id) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        export(id, os);
        return os.toByteArray();
    }

    private short getColorIndex(short index, byte red, byte green, byte blue, Workbook workbook) {
        HSSFColor color;
        HSSFPalette palette = ((HSSFWorkbook) workbook).getCustomPalette();
        if (palette.findColor(red, green, blue) == null) {
            palette.setColorAtIndex(index, red, green, blue);
        }
        color = palette.findColor(red, green, blue);
        return color.getIndex();
    }

    private CellStyle createContentStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontName("微软雅黑");

        final short GREY_35_PERCENT = getColorIndex((short) 56, (byte) 166, (byte) 166, (byte) 166, workbook);
        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(GREY_35_PERCENT);
        style.setLeftBorderColor(GREY_35_PERCENT);
        style.setTopBorderColor(GREY_35_PERCENT);
        style.setRightBorderColor(GREY_35_PERCENT);

        return style;
    }

    private CellStyle createHeaderContentStyle(Workbook workbook, CellStyle contentStyle) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(contentStyle);

        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return style;
    }

    private CellStyle createBottomHeaderContentStyle(Workbook workbook, CellStyle headerContentStyle) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(headerContentStyle);
        style.setBottomBorderColor(HSSFColor.GREY_80_PERCENT.index);
        return style;
    }

    private CellStyle createColumnHeaderContentStyle(Workbook workbook, CellStyle contentStyle) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(contentStyle);

        final short GREY_15_PERCENT = getColorIndex((short) 57, (byte) 220, (byte) 220, (byte) 220, workbook);
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setBold(true);

        style.setFont(font);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(GREY_15_PERCENT);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        return style;
    }

    private CellStyle createRightColumnHeaderContentStyle(Workbook workbook, CellStyle columnHeaderContentStyle) {
        CellStyle style = workbook.createCellStyle();
        style.cloneStyleFrom(columnHeaderContentStyle);
        style.setRightBorderColor(HSSFColor.GREY_80_PERCENT.index);
        return style;
    }

    private void createExportBody(List<Row> body, Sheet sheet, Workbook workbook) {
        CellStyle contentStyle = createContentStyle(workbook);
        CellStyle columnHeaderContentStyle = createColumnHeaderContentStyle(workbook, contentStyle);
        CellStyle rightColumnHeaderContentStyle = createRightColumnHeaderContentStyle(workbook, columnHeaderContentStyle);
        Map<String, String> allCode = translateDictService.getAllCode();
        Integer columnHeaderCount = 1;
        for (Cell cell : body.get(body.size() - 1).getCells()) {
            if (cell.getValue().equals("合计") || cell.getValue().equals("-")) columnHeaderCount++;
        }
        int xOff = sheet.getPhysicalNumberOfRows(), yOff = columnHeaderCount;
        Row row;
        sheet.createFreezePane(yOff, xOff);
        for (int x = xOff; x < body.size() + xOff; x++) {
            row = body.get(x - xOff);
            for (int y = 0; y < row.getCells().size(); y++) {
                String value = row.getCells().get(y).getValue();
                String codeValue = allCode.get(value);
                value = codeValue != null ? codeValue : value;
                getCell(x, y, sheet).setCellValue(value);
                getCell(x, y, sheet).setCellStyle(contentStyle);
                if (y < yOff - 1) {
                    getCell(x, y, sheet).setCellStyle(columnHeaderContentStyle);
                }
                if (y == yOff - 1) {
                    getCell(x, y, sheet).setCellStyle(rightColumnHeaderContentStyle);
                }
            }
        }
        System.out.println(sheet.getPhysicalNumberOfRows());
    }


    private void createExportHeader(List<ExcelCell> header, Sheet sheet, Workbook workbook) {
        CellStyle contentStyle = createContentStyle(workbook);
        CellStyle headerContentStyle = createHeaderContentStyle(workbook, contentStyle);
        CellStyle bottomHeaderContentStyle = createBottomHeaderContentStyle(workbook, headerContentStyle);

        int x, y, m, n, xMax = 0, yMax = 0;
        for (ExcelCell excelCell : header) {
            x = excelCell.getFirstRow();
            y = excelCell.getFirstColumn();
            m = excelCell.getLastRow();
            n = excelCell.getLastColumn();
            xMax = m > xMax ? m : xMax;
            yMax = n > yMax ? n : yMax;
            getCell(x, y, sheet).setCellValue(excelCell.getValue());
            sheet.addMergedRegion(new CellRangeAddress(x, m, y, n));
        }
        for (x = 0; x < xMax; x++) {
            for (y = 0; y <= yMax; y++) {
                getCell(x, y, sheet).setCellStyle(headerContentStyle);
            }
        }
        for (y = 0; y <= yMax; y++) {
            getCell(xMax, y, sheet).setCellStyle(bottomHeaderContentStyle);
        }
    }

    private org.apache.poi.ss.usermodel.Cell getCell(int x, int y, Sheet sheet) {
        org.apache.poi.ss.usermodel.Row.MissingCellPolicy policy = HSSFRow.RETURN_BLANK_AS_NULL;
        org.apache.poi.ss.usermodel.Row row;
        org.apache.poi.ss.usermodel.Cell cell;
        row = sheet.getRow(x) == null ? sheet.createRow(x) : sheet.getRow(x);
        cell = row.getCell(y, policy) == null ? row.createCell(y) : row.getCell(y, policy);
        return cell;
    }
}
