package gov.gwssi.csc.scms.dao.immport;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.importExcle.ImportDao;
import org.apache.commons.fileupload.FileItem;
import org.junit.Test;

import java.util.Vector;

/**
 * Created by LiZhiSheng on 2015/8/17.
 */
public class ImportDAOTest  extends UnitTestBase {
    @Test
    public void testImport() throws Exception {
        ImportDao codeTableDAO = getBean("importDao");
        FileItem fileItem = null;
        Vector<Vector<String>> productData= codeTableDAO.doExcelImport(fileItem);
        System.out.println(productData.size());

    }
}
