package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserRepositoryTest extends UnitTestBase {

    @Autowired
    UserRepository repository;
    @Before

    public void setUp() throws Exception {

    }

    @Test
    public void findByTheUsersId() throws Exception {

    }

    @Test
    public void findByUserName() throws Exception {

    }
}