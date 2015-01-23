package net.odtel.usercheck.repository;

import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RadiusUserRepository implements IRadiusUserRepository {

    public static final String SELECT_FROM_RUSER = "SELECT userid, username, userattr, userop, userval from ruser where username = ?";
    public static final String SELECT_FROM_RUSER_BY_ID = "SELECT userid, username, userattr, userop, userval from ruser where userid = ?";
    public static final String SELECT_FROM_RUSER_BY_ORDER = "SELECT userid, username, userattr, userop, userval from ruser where username like ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RadiusUser findOne(Long id) {
        RadiusUser user = null;
        List<RadiusUser> radiusUsers = this.jdbcTemplate.query(SELECT_FROM_RUSER_BY_ID, new Object[]{id}, new RadiusUserMapper());
        if (radiusUsers.size() > 0) {
            user = radiusUsers.get(0);
        }
        return user;
    }

    @Override
    public List<RadiusUser> findAllOfOrder(String someLogin) {
        return this.jdbcTemplate.query(SELECT_FROM_RUSER_BY_ORDER, new Object[]{someLogin}, new RadiusUserMapper());
    }

    @Override
    public List<RadiusUser> findAll(String login) {
        return this.jdbcTemplate.query(SELECT_FROM_RUSER, new Object[]{login}, new RadiusUserMapper());
    }

    @Override
    public RadiusUser create(RadiusUser radiusUser) {

        this.jdbcTemplate.update(
                "insert into ruser VALUES (?,?,?,?,?)", radiusUser.getId(),
                radiusUser.getUsername(),
                radiusUser.getPasswordType().getValue(),
                radiusUser.getPasswordOperation().getValue(),
                radiusUser.getPassword());

        return radiusUser;
    }

    @Override
    public RadiusUser createWithId(RadiusUser radiusUser) {
        return null;
    }

    @Override
    public void update(RadiusUser radiusUser) {
        this.jdbcTemplate.update(
                "update ruser set username = ?, userattr = ?, userop = ?, userval = ? where userid = ?",
                radiusUser.getUsername(),
                radiusUser.getPasswordType().getValue(),
                radiusUser.getPasswordOperation().getValue(),
                radiusUser.getPassword(),
                radiusUser.getId());

    }

    @Override
    public void delete(RadiusUser radiusUser) {
        this.jdbcTemplate.update("delete from ruser where userid = ?", radiusUser.getId());
    }

    private static final class RadiusUserMapper implements RowMapper<RadiusUser> {

        public RadiusUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            RadiusUser radiusUser = new RadiusUser();
            radiusUser.setUsername(rs.getString("username"));
            radiusUser.setPassword(rs.getString("userval"));
            radiusUser.setId(rs.getLong("userid"));
            radiusUser.setPasswordType(RadiusAttribute.CLEARTEXT_PASSWORD);
            return radiusUser;
        }
    }

}
