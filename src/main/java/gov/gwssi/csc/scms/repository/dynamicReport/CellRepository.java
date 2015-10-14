package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import org.springframework.data.repository.CrudRepository;

/**
 * 报表单元格域对象仓库
 * Created by wangzishi on 15/10/11.
 */
public interface CellRepository extends CrudRepository<Cell, String> {
}
