package gov.gwssi.csc.scms.dao;

//import junit.framework.TestCase;
import gov.gwssi.csc.scms.MyDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import gov.gwssi.csc.scms.service.base.UnitTestBase;

import static org.junit.Assert.*;

/**
 * Created by WangZishi on 3/23/2015.
 */
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
        BaseDao baseDao = super.getBean("dao");
        baseDao.save("good");
    }

    @Test
    public void testMyDriverManager() throws Exception {
        MyDriverManager myDriverManager = super.getBean("dataSource");
        System.out.println(myDriverManager.getClass().getName());
    }
}