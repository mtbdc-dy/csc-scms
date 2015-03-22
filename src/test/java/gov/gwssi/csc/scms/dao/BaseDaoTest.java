package gov.gwssi.csc.scms.dao;

//import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gov.gwssi.csc.scms.service.base.UnitTestBase;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class BaseDaoTest extends UnitTestBase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSave() throws Exception {
        BaseDao baseDao = super.getBean("baseDao");
        baseDao.save("good");
    }
}