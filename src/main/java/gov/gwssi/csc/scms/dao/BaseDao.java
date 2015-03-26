package gov.gwssi.csc.scms.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by WangZishi on 3/22/2015.
 */
@Scope
@Repository
@Component
@Service("baseDao")
public class BaseDao {

    @Autowired
    private ApplicationContext ctx;

    @Autowired(required = false)
    @Qualifier("scmsDS")
    private DataSource dataSource;

    @Autowired(required = false)
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public JdbcTemplate getJdbcTemplate(){
        return jdbcTemplate;
    }


    public void save(String data) throws SQLException {
        // dataSource.getConnection();
        // JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println(jdbcTemplate.queryForInt("select 1 from DUAL"));
        System.out.println("[BaseDao] saving: " + data);
    }

}

