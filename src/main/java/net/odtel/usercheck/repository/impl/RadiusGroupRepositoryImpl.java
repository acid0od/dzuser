package net.odtel.usercheck.repository.impl;

import net.odtel.usercheck.domain.RadiusAttribute;
import net.odtel.usercheck.domain.RadiusGroup;
import net.odtel.usercheck.domain.RadiusOperation;
import net.odtel.usercheck.domain.RadiusUser;
import net.odtel.usercheck.repository.RadiusGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RadiusGroupRepositoryImpl implements RadiusGroupRepository {
    private static final Logger log = LoggerFactory.getLogger(RadiusUserRepositoryImpl.class);

    private static String SELECT_FIND_ALL_DISTINCT = "SELECT DISTINCT groupreplyname from rgroupreply order by groupreplyname";
    private static String SELECT_FIND_ALL = "SELECT DISTINCT groupreplyname, groupreplyid, groupreplyattr, groupreplyop, groupreplyval from rgroupreply order by groupreplyname";
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RadiusGroup> findAll() {

        return null;
    }

    @Override
    public List<RadiusGroup> findAllDistinctByGroupreplyname() {
        List<RadiusGroup> list = this.jdbcTemplate.query(SELECT_FIND_ALL_DISTINCT,
                new RowMapper<RadiusGroup>() {
                    @Override
                    public RadiusGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
                        RadiusGroup radiusGroup = new RadiusGroup();
                        radiusGroup.setGroupname(rs.getString("groupreplyname"));
                        return radiusGroup;
                    }
                });
        return list;
    }

    @Override
    public List<RadiusGroup> findAllByUserName() {
        return null;
    }

    private static final class RadiusGroupMapper implements RowMapper<RadiusGroup> {

        public RadiusGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
            RadiusGroup radiusGroup = new RadiusGroup();
            radiusGroup.setGroupname(rs.getString("groupreplyname"));
            radiusGroup.setAttribute(RadiusAttribute.valueOf(rs.getString("groupreplyattr")));
            radiusGroup.setOperator(RadiusOperation.valueOf(rs.getString("groupreplyop")));
            radiusGroup.setValue(rs.getString("groupreplyval"));
            radiusGroup.setId(rs.getLong("groupreplyid"));
            return radiusGroup;
        }
    }
}
