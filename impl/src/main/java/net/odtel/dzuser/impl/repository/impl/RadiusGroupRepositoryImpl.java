package net.odtel.dzuser.impl.repository.impl;

import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusGroup;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.api.model.RadiusUser;
import net.odtel.dzuser.api.web.Page;
import net.odtel.dzuser.api.web.PageFactory;
import net.odtel.dzuser.impl.repository.RadiusGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "radiusGroupRepository")
@Transactional
public class RadiusGroupRepositoryImpl implements RadiusGroupRepository {
    private static final Logger log = LoggerFactory.getLogger(RadiusUserRepositoryImpl.class);

    private static String SELECT_FIND_ALL_DISTINCT = "SELECT DISTINCT groupreplyname from rgroupreply order by groupreplyname";
    private static String SELECT_FROM_RGROUP_WITH_SEARCH = "SELECT DISTINCT groupreplyname from rgroupreply where groupreplyname like ? order by groupreplyname limit ? offset ?";
    private static String SELECT_FROM_RGROUP_WITHOUT_SEARCH = "SELECT DISTINCT groupreplyname from rgroupreply order by groupreplyname limit ? offset ?";
    private static String SELECT_COUNT_ALL_DISTINCT = "SELECT count (DISTINCT groupreplyname) from rgroupreply";
    private static String SELECT_FIND_ALL = "SELECT DISTINCT groupreplyname, groupreplyid, groupreplyattr, groupreplyop, groupreplyval from rgroupreply order by groupreplyname";
    private static String SELECT_MGRPUSR_USER = "select groupname from musrgrp where username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<RadiusGroup> findAll () {

        return null;
    }

    @Override
    public List<RadiusGroup> findAllByUserName (String userName) {
        return null;
    }

    @Override
    public List<RadiusGroup> findAllDistinctByGroupreplyname () {
        List<RadiusGroup> list = this.jdbcTemplate.query(SELECT_FIND_ALL_DISTINCT,
                new RowMapper<RadiusGroup>() {
                    @Override
                    public RadiusGroup mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RadiusGroup radiusGroup = new RadiusGroup();
                        radiusGroup.setGroupname(rs.getString("groupreplyname"));
                        return radiusGroup;
                    }
                });

        return list;
    }

    @Override
    public List<String> getGroupForUser (String username) {
        List<String> userGroupList = this.jdbcTemplate.query(SELECT_MGRPUSR_USER, new Object[] {username}, new RowMapper<String>() {
            @Override
            public String mapRow (ResultSet rs, int rowNum) throws SQLException {
                return (rs.getString(1));
            }
        });
        return userGroupList;
    }

    @Override
    public void updateGroupForUser (RadiusUser radiusUser) {

        deleteGroupForUser(radiusUser.getUsername());
        if (radiusUser.getRadiusUserGroup() != null) {
            for (String groupname : radiusUser.getRadiusUserGroup()) {
                this.jdbcTemplate.update(
                        "insert into musrgrp (username, groupname, usrgrppriority) VALUES (?, ?, 0)",
                        radiusUser.getUsername(),
                        groupname);
            }
        }
    }

    @Override
    public Page<RadiusGroup> findAll (Integer pageNumber, Integer limit) {
        Page<RadiusGroup> page = new PageFactory<RadiusGroup>(pageNumber, limit, getTotalRecords()).createPage();

        List<RadiusGroup> list = this.jdbcTemplate.query(SELECT_FROM_RGROUP_WITHOUT_SEARCH,
                new Object[] {limit, page.getOffset()},
                new RowMapper<RadiusGroup>() {
                    @Override
                    public RadiusGroup mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RadiusGroup radiusGroup = new RadiusGroup();
                        radiusGroup.setGroupname(rs.getString("groupreplyname"));
                        return radiusGroup;
                    }
                });

        page.setListData(list);

        return page;
    }

    @Override
    public Page<RadiusGroup> findAllByGroupName (String groupName, Integer pageNumber, Integer limit) {
        Page<RadiusGroup> page = new PageFactory<RadiusGroup>(pageNumber, limit, getTotalRecords()).createPage();

        String stringSearch = groupName.replace("*", "%");

        List<RadiusGroup> list = this.jdbcTemplate.query(SELECT_FROM_RGROUP_WITH_SEARCH,
                new Object[] {stringSearch, limit, page.getOffset()},
                new RowMapper<RadiusGroup>() {
                    @Override
                    public RadiusGroup mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RadiusGroup radiusGroup = new RadiusGroup();
                        radiusGroup.setGroupname(rs.getString("groupreplyname"));
                        return radiusGroup;
                    }
                });

        page.setListData(list);

        return page;
    }

// --------------------- Private Methods -----------------------------------------

    private Long getTotalRecords () {
        Long total = this.jdbcTemplate.queryForObject(SELECT_COUNT_ALL_DISTINCT, new RowMapper<Long>() {
            @Override
            public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
        return total;
    }

    private void deleteGroupForUser (String username) {
        this.jdbcTemplate.update(
                "delete from musrgrp where username = ?",
                username);
    }

    private static final class RadiusGroupMapper implements RowMapper<RadiusGroup> {

        public RadiusGroup mapRow (ResultSet rs, int rowNum) throws SQLException {
            RadiusGroup radiusGroup = new RadiusGroup();
            radiusGroup.setGroupname(rs.getString("groupreplyname"));
            radiusGroup.setAttribute(RadiusAttribute.getKey(rs.getString("groupreplyattr")));
            radiusGroup.setOperator(RadiusOperation.getKey(rs.getString("groupreplyop")));
            radiusGroup.setValue(rs.getString("groupreplyval"));
            radiusGroup.setId(rs.getLong("groupreplyid"));
            return radiusGroup;
        }
    }
}
