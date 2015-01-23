package net.odtel.usercheck.test;

import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.repository.RadiusUserRepository;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Test
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RadiusUserRepositoryTest extends AbstractTestNGSpringContextTests {

    @Configuration
    public static class Context {

        @Bean
        public JdbcTemplate jdbcTemplate() {
            return new JdbcTemplate(dataSource());
        }

        @Bean(destroyMethod = "close")
        public DataSource dataSource() {
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.hsqldb.jdbcDriver");
            dataSource.setUrl("jdbc:hsqldb:hsql:/localhost/test");
            dataSource.setPassword("");
            dataSource.setUsername("saa");
            dataSource.setInitialSize(10);
            dataSource.setTestOnBorrow(true);
            return dataSource;
        }

        @Bean
        public RadiusUserRepository radiusUserRepository() {
            return new RadiusUserRepository();
        }
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RadiusUserRepository radiusUserRepository;



    @BeforeMethod
    public void init() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS ruser");
        jdbcTemplate.execute("CREATE TABLE ruser (userid integer, username varchar(30), userattr varchar(64), userop varchar(2), userval varchar(250))");
        jdbcTemplate.execute("insert into ruser VALUES (212, 'fss_ber', 'Cleartext-Password', ':=', 'dwwewwe' )");
        jdbcTemplate.execute("insert into ruser VALUES (213, 'fss_boss', 'Cleartext-Password', ':=', 'dwwewwewww' )");
        jdbcTemplate.execute("insert into ruser VALUES (214, 'boss', 'Cleartext-Password', ':=', 'dwwewww' )");
        jdbcTemplate.execute("insert into ruser VALUES (215, 'wos', 'Cleartext-Password', ':=', 'dwwewwww' )");
    }



    @Test
    public void simpleSearchByOrderTest() {
        List<RadiusUser> list = radiusUserRepository.findAllOfOrder("fss_%");
        assertEquals(list.size(),2);
    }


    @Test
    public void simpleSearchByIdTest() {
        RadiusUser user = radiusUserRepository.findOne(215L);
        assertEquals(user.getId(), new Long(215L));
    }

    @Test
    public void simpleInsertTest() {
        RadiusUser radiusUser = new RadiusUser();
        radiusUser.setPassword("password");
        radiusUser.setPasswordType(RadiusAttribute.CLEARTEXT_PASSWORD);
        radiusUser.setUsername("pptp-user");
        radiusUser.setId(1L);
        radiusUserRepository.create(radiusUser);
        RadiusUser user = radiusUserRepository.findOne(1L);
        assertEquals(user.getId(), new Long(1L));
    }

    @Test
    public void simpleUpdateTest() {
        RadiusUser user = radiusUserRepository.findOne(213L);
        assertEquals(user.getId(), new Long(213L));

        user.setPassword("12345");
        radiusUserRepository.update(user);

        RadiusUser userUpdated = radiusUserRepository.findOne(213L);
        assertEquals(userUpdated.getPassword(), "12345");

    }

    @Test
    public void simpleDeleteTest() {
        RadiusUser user = radiusUserRepository.findOne(213L);
        assertEquals(user.getId(), new Long(213L));
        radiusUserRepository.delete(user);

        List<RadiusUser> list = radiusUserRepository.findAllOfOrder("fss_%");
        assertEquals(list.size(),1);

    }


}
