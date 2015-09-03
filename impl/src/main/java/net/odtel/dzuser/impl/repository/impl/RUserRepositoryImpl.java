package net.odtel.dzuser.impl.repository.impl;

import net.odtel.dzuser.api.model.RUser;
import net.odtel.dzuser.api.model.RUserPair;
import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.impl.repository.RUserRepository;
import net.odtel.dzuser.impl.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository(value = "rUserRepository")
@Transactional
public class RUserRepositoryImpl implements RUserRepository {

    //private static String SELECT_FIND_ALL_DISTINCT = "SELECT DISTINCT username as username from ruser order by username limit ? offset ?";
    private static String SELECT_FIND_ALL_DISTINCT = "SELECT DISTINCT ruser.username as username, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser order by username limit ? offset ?";
    private static String SELECT_LIKE_WITH_GROUP_ARRAY = "SELECT DISTINCT ruser.username as username, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser where ruser.username like ? order by groups DESC";
    private static String SELECT_COUNT_ALL_DISTINCT = "SELECT count (DISTINCT username) from ruser";
    private static String SELECT_COUNT_ALL_DISTINCT_LIKE = "SELECT count (DISTINCT username) from ruser where username like ?";
    private static String SELECT_FROM_RUSER_WITH_SEARCH = "SELECT username, userid, userattr, userop, userval, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser where username = ?";
    //private static String SELECT_FROM_RUSER_WITH_SEARCH_AND_LIMIT = "SELECT username, userid, userattr, userop, userval from ruser where username = ? limit ? offset ?";
    private static String SELECT_FROM_RUSER_WITH_SEARCH_AND_LIMIT = "SELECT username, userid, userattr, userop, userval, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser where username = ? limit ? offset ?";
    private static String SELECT_FROM_RUSER_LIKE_WITH_SEARCH_AND_LIMIT = "SELECT DISTINCT username as username , array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser where username like ? limit ? offset ?";
    private static String SELECT_FROM_RUSERREPLY_WITH_SEARCH = "SELECT userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval from ruserreply where userreplyname = ?";

    private static String SELECT_FROM_RUSER_WITH_SEARCH_BY_NAME_AND_GROUP = "SELECT DISTINCT ruser.username as username, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser, musrgrp where ruser.username = ? AND musrgrp.username = ruser.username AND musrgrp.groupname = ? ";

    private static String SELECT_FROM_RUSER_WITH_SEARCH_BY_NAME_LIKE_AND_GROUP_AND_LIMIT = "SELECT DISTINCT ruser.username as username, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser, musrgrp where ruser.username like ? AND musrgrp.username = ruser.username AND musrgrp.groupname = ? order by ruser.username limit ? offset ? ";
    private static String COUNT_FROM_RUSER_WITH_SEARCH_BY_NAME_LIKE_AND_GROUP = "SELECT count(DISTINCT ruser.username) from ruser, musrgrp where ruser.username = ? AND musrgrp.username = ruser.username AND musrgrp.groupname = ? ";

    private static String SELECT_FROM_RUSER_WITH_SEARCH_BY_GROUP_AND_LIMIT = "SELECT DISTINCT ruser.username as username, array_to_string(ARRAY(SELECT musrgrp.groupname from musrgrp WHERE musrgrp.username = ruser.username ), ', ') AS groups from ruser, musrgrp where musrgrp.username = ruser.username AND musrgrp.groupname = ? order by ruser.username limit ? offset ? ";
    private static String COUNT_FROM_RUSER_WITH_SEARCH_BY_GROUP = "SELECT count(DISTINCT ruser.username) from ruser, musrgrp where musrgrp.username = ruser.username AND musrgrp.groupname = ? ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<RUser> findUsernameDistinct (Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FIND_ALL_DISTINCT,
                new Object[] {pageable.getPageSize(), pageable.getOffset()}, new RUserNameANDGroupsMapper());
        return new PageImpl<RUser>(list, pageable, getTotalRecords());
    }

    @Override
    public List<RUser> findByUsername (String name) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_WITH_SEARCH, new Object[] {name}, new RUserMapper());
        return list;
    }

    @Override
    public Page<RUser> findByUsername (String name, Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_WITH_SEARCH_AND_LIMIT,
                new Object[] {name, pageable.getPageSize(), pageable.getOffset()}, new RUserMapper());

        return new PageImpl(list, pageable, list.size());
    }

    @Override
    public Page<RUser> findByUsernameLike (String name, Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_LIKE_WITH_SEARCH_AND_LIMIT,
                new Object[] {name, pageable.getPageSize(), pageable.getOffset()}, new RUserNameANDGroupsMapper());
        return new PageImpl(list, pageable, getTotalRecordsLike(name));
    }

    public void saveUser (RUser rUsery) {
        if (rUsery.getId() != null && rUsery.getId() > 0) {
            update(rUsery);
        } else {
            createWithId(rUsery);
        }
    }

    @Override
    public void saveAll (List<RUser> rUsers, String userName) {
        if (rUsers != null && rUsers.size() > 0) {
            for (RUser user : rUsers) {
                user.setUsername(userName);
                if (user.getTableName() == null || user.getTableName().equals("ruserreply")) {
                    saveUserAttribute(user);
                } else if (user.getTableName().equals("ruser")) {
                    saveUser(user);
                }
            }
        }
    }

    @Override
    public void save (RUser rUser) {
        if (rUser.getId() != null && rUser.getId() > 0) {
            update(rUser);
        } else {
            createWithId(rUser);
        }
    }

    public void saveUserAttribute (RUser user) {
        if (user.getId() != null && user.getId() > 0L) {
            this.jdbcTemplate.update(
                    "update ruserreply set userreplyname = ?, userreplyattr = ?, userreplyop = ?, userreplyval = ? where userreplyid = ?",
                    user.getUsername(),
                    user.getUserattr().getValue(),
                    user.getUserop().getValue(),
                    StringUtils.setQuates(user.getUserval()),
                    user.getId());
        } else {
            this.jdbcTemplate.update(
                    "insert into ruserreply (userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval) VALUES (?, ?, ?, ?, ?)",
                    nextUserAttrId(),
                    user.getUsername(),
                    user.getUserattr().getValue(),
                    user.getUserop().getValue(),
                    StringUtils.setQuates(user.getUserval()));
        }
    }

    @Override
    public void delete (String userName) {
        this.jdbcTemplate.update("delete from ruser where username = ?", userName);
    }

    @Override
    public void deleteUserAttribute (String userName) {
        this.jdbcTemplate.update("delete from ruserreply where userreplyname = ?", userName);
    }

    @Override
    public void delete (Long userId) {
        this.jdbcTemplate.update("delete from ruser where userid = ?", userId);
    }

    public Long nextId () {
        Long id = this.jdbcTemplate.queryForObject("select max(userid) from ruser", Long.class) + 1L;
        return id;
    }

    public Long nextUserAttrId () {
        Long id = this.jdbcTemplate.queryForObject("select max(userreplyid) from ruserreply", Long.class) + 1L;
        return id;
    }

    private Long getTotalRecords () {
        Long total = this.jdbcTemplate.queryForObject(SELECT_COUNT_ALL_DISTINCT, new RowMapper<Long>() {
            @Override
            public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
        return total;
    }

    private Long getTotalRecordsLike (String name) {
        Long total = this.jdbcTemplate.queryForObject(SELECT_COUNT_ALL_DISTINCT_LIKE, new Object[] {name}, new RowMapper<Long>() {
            @Override
            public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                return rs.getLong(1);
            }
        });
        return total;
    }

    private Long getTotalRecordsByNameAndGroups (String name, String group) {
        String COUNT_FROM_RUSER_WITH_SEARCH_BY_NAME_AND_GROUP = "SELECT count( DISTINCT ruser.username) from ruser, musrgrp where ruser.username = ? AND musrgrp.username = ruser.username AND musrgrp.groupname = ?";
        return this.jdbcTemplate.queryForObject(COUNT_FROM_RUSER_WITH_SEARCH_BY_NAME_AND_GROUP,
                new Object[] {name, group}, new RowMapper<Long>() {
                    @Override
                    public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong(1);
                    }
                });
    }

    private Long getTotalRecordsByNameLikeAndGroups (String name, String group) {
        return this.jdbcTemplate.queryForObject(COUNT_FROM_RUSER_WITH_SEARCH_BY_NAME_LIKE_AND_GROUP,
                new Object[] {name, group}, new RowMapper<Long>() {
                    @Override
                    public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong(1);
                    }
                });
    }

    private Long getTotalRecordsByGroups (String group) {
        return this.jdbcTemplate.queryForObject(COUNT_FROM_RUSER_WITH_SEARCH_BY_GROUP,
                new Object[] {group}, new RowMapper<Long>() {
                    @Override
                    public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong(1);
                    }
                });
    }

    @Override
    public void updateGroupForUser (RUserPair pair) {
        deleteGroupForUser(pair.getName());
        if (pair.getListGroup() != null) {
            for (String groupname : pair.getListGroup()) {
                this.jdbcTemplate.update(
                        "insert into musrgrp (username, groupname, usrgrppriority) VALUES (?, ?, 0)",
                        pair.getName(), groupname);
            }
        }
    }

    @Override
    public void deleteAllGroup (List<String> strings, String name) {
        if (strings != null && strings.size() > 0) {
            for (String grpName : strings) {
                this.jdbcTemplate.update("delete from musrgrp where username = ? and groupname = ?", name, grpName);
            }
        }
    }

    @Override
    public void addAllGroup (List<String> list, String name) {
        if (list != null && list.size() > 0) {
            for (String groupname : list) {
                this.jdbcTemplate.update(
                        "insert into musrgrp (username, groupname, usrgrppriority) VALUES (?, ?, 0)",
                        name, groupname);
            }
        }
    }

    @Override
    public void deleteUserAttribute (Long userAttrId) {
        this.jdbcTemplate.update("delete from ruserreply where userreplyid = ?", userAttrId);
    }

    @Override
    public List<RUser> findUserAttributeByUsername (String name) {
        return this.jdbcTemplate.query(SELECT_FROM_RUSERREPLY_WITH_SEARCH, new Object[] {name},
                new RowMapper<RUser>() {
                    @Override
                    public RUser mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RUser rUser = new RUser();
                        rUser.setId(rs.getLong("userreplyid"));
                        rUser.setUsername(rs.getString("userreplyname"));
                        rUser.setUserattr(RadiusAttribute.getKey(rs.getString("userreplyattr")));
                        rUser.setUserop(RadiusOperation.getKey(rs.getString("userreplyop")));
                        rUser.setUserval(rs.getString("userreplyval"));
                        rUser.setTableName("ruserreply");
                        return rUser;
                    }
                }
        );
    }

    @Override
    public Page<RUser> findByUsernameAndGroups (String name, String group, Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_WITH_SEARCH_BY_NAME_AND_GROUP,
                new Object[] {name, group},
                new RUserNameANDGroupsMapper());
        return (Page) new PageImpl(list, pageable, getTotalRecordsByNameAndGroups(name, group));

    }

    @Override
    public Page<RUser> findByUsernameLikeAndGroups (String name, String group, Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_WITH_SEARCH_BY_NAME_LIKE_AND_GROUP_AND_LIMIT,
                new Object[] {name, group,
                        pageable.getPageSize(),
                        pageable.getOffset()},
                new RUserNameANDGroupsMapper());
        return (Page) new PageImpl(list, pageable, getTotalRecordsByNameLikeAndGroups(name, group));

    }

    @Override
    public Page<RUser> findByGroups (String group, Pageable pageable) {
        List<RUser> list = this.jdbcTemplate.query(SELECT_FROM_RUSER_WITH_SEARCH_BY_GROUP_AND_LIMIT,
                new Object[] {group,
                        pageable.getPageSize(),
                        pageable.getOffset()},
                new RUserNameANDGroupsMapper());
        return new PageImpl(list, pageable, getTotalRecordsByGroups(group));

    }

    private void deleteGroupForUser (String username) {
        this.jdbcTemplate.update(
                "delete from musrgrp where username = ?",
                username);
    }

    private void createWithId (RUser rUser) {
        Long id = nextId();
        this.jdbcTemplate.update(
                "insert into ruser (userid, username, userattr, userop, userval) VALUES (?,?,?,?,?)",
                id,
                rUser.getUsername(),
                rUser.getUserattr().getValue(),
                rUser.getUserop().getValue(),
                rUser.getUserval());
    }

    private void update (RUser rUser) {
        this.jdbcTemplate.update(
                "update ruser set username = ?, userattr = ?, userop = ?, userval = ? where userid = ?",
                rUser.getUsername(),
                rUser.getUserattr().getValue(),
                rUser.getUserop().getValue(),
                rUser.getUserval(),
                rUser.getId());
    }

    private static final class RUserNameMapper implements RowMapper<RUser> {
        @Override
        public RUser mapRow (ResultSet rs, int rowNum) throws SQLException {
            RUser rUser = new RUser();
            rUser.setUsername(rs.getString("username"));
            return rUser;
        }
    }

    private static final class RUserNameANDGroupsMapper implements RowMapper<RUser> {
        @Override
        public RUser mapRow (ResultSet rs, int rowNum) throws SQLException {
            RUser rUser = new RUser();
            rUser.setUsername(rs.getString("username"));
            rUser.setGroups(rs.getString("groups"));
            return rUser;
        }
    }

    private static final class RUserMapper implements RowMapper<RUser> {

        @Override
        public RUser mapRow (ResultSet rs, int rowNum) throws SQLException {
            RUser rUser = new RUser();
            rUser.setUsername(rs.getString("username").trim());
            rUser.setUserattr(RadiusAttribute.getKey((rs.getString("userattr")).trim()));
            rUser.setUserop(RadiusOperation.getKey((rs.getString("userop")).trim()));
            rUser.setUserval(rs.getString("userval"));
            rUser.setId(rs.getLong("userid"));
            rUser.setTableName("ruser");
            rUser.setGroups(rs.getString("groups"));
            return rUser;
        }
    }
}
