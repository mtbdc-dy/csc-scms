package gov.gwssi.csc.scms.service.warning;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.warning.Warning;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by tianjing on 2015/7/22.
 */
public class WarningServiceTest extends UnitTestBase {

//    @Autowired
//    WarningService warningService;

    @Before
    public void setUp() throws Exception {
//        warningService = getBean("warningService");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetWarningById() throws Exception {
        WarningService warningService = getBean("WarningService");
        Warning warning = warningService.getWarningByStudentId("2");
        System.out.println("warning.getAddReason() = " + warning.getAddReason());
    }

    @Test
    public void testGetWarningByFilter() throws Exception {

    }

    @Test
    public void testSaveWarning() throws Exception {

    }

    @Test
    public void testGetWarningAndStu() throws Exception {

    }

    @Test
    public void testDeleteWarningById() throws Exception {

    }
}