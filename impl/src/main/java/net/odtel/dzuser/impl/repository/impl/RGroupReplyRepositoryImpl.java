package net.odtel.dzuser.impl.repository.impl;

import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RadiusAttribute;
import net.odtel.dzuser.api.model.RadiusOperation;
import net.odtel.dzuser.impl.repository.RGroupReplyRepository;
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
import java.util.Collection;
import java.util.List;

@Repository(value = "rGroupReplyRepository")
@Transactional
public class RGroupReplyRepositoryImpl implements RGroupReplyRepository {

    private static String SELECT_FIND_ALL_DISTINCT_WITH_LIMIT = "SELECT DISTINCT groupreplyname as name from rgroupreply order by groupreplyname limit ? offset ?";
    private static String SELECT_FIND_ALL_DISTINCT = "SELECT DISTINCT groupreplyname as name from rgroupreply order by groupreplyname";
    private static String SELECT_COUNT_ALL_DISTINCT = "SELECT count (DISTINCT groupreplyname) from rgroupreply";
    private static String SELECT_FROM_RGROUP_WITH_SEARCH = "SELECT groupreplyname, groupreplyid, groupreplyattr, groupreplyop, groupreplyval from rgroupreply where groupreplyname = ?";
    private static String SELECT_FROM_RGROUP_WITH_SEARCH_BY_USERNAME = "SELECT DISTINCT groupreplyname as groupreplyname, groupreplyid, groupreplyattr, groupreplyop, groupreplyval from rgroupreply, musrgrp where musrgrp.username = ? AND musrgrp.groupname = rgroupreply.groupreplyname";
    private static String SELECT_FROM_MUSRGRP = "select groupname from musrgrp where musrgrp.username = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<RGroupReply> findGroupreplynameDistinct (Pageable pageable) {
        List<RGroupReply> list = this.jdbcTemplate.query(SELECT_FIND_ALL_DISTINCT_WITH_LIMIT,
                new Object[] {pageable.getPageSize(), pageable.getOffset()},
                new RowMapper<RGroupReply>() {
                    @Override
                    public RGroupReply mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RGroupReply rGroupReply = new RGroupReply();
                        rGroupReply.setGroupreplyname(rs.getString("name"));
                        return rGroupReply;
                    }
                });
        return (Page) new PageImpl<RGroupReply>(list, pageable, getTotalRecords());
    }

    @Override
    public List<RGroupReply> findByGroupreplyname (String name) {
        return this.jdbcTemplate.query(SELECT_FROM_RGROUP_WITH_SEARCH, new Object[] {name}, new RGroupReplyMapper());
    }

    @Override
    public void save (RGroupReply groupReply) {
        if (groupReply.getId() != null && groupReply.getId() > 0L) {
            update(groupReply);
        } else {
            createWithId(groupReply);
        }
    }

    @Override
    public void delete (String groupReplyName) {
        this.jdbcTemplate.update("delete from rgroupreply where groupreplyname = ?", groupReplyName);
    }

    @Override
    public void delete (Long groupReplyId) {
        this.jdbcTemplate.update("delete from rgroupreply where groupreplyid = ?", groupReplyId);
    }

    private void createWithId (RGroupReply groupReply) {
        Long id = nextId();
        this.jdbcTemplate.update("insert into rgroupreply VALUES (?,?,?,?,?)", id,
                groupReply.getGroupreplyname(),
                groupReply.getGroupreplyattr().getValue(),
                groupReply.getGroupreplyop().getValue(),
                StringUtils.setQuates(groupReply.getGroupreplyval()));
    }

    private void update (RGroupReply groupReply) {
        this.jdbcTemplate.update(
                "update rgroupreply set groupreplyname = ?, groupreplyattr = ?, groupreplyop = ?, groupreplyval = ? where groupreplyid = ?",
                groupReply.getGroupreplyname(),
                groupReply.getGroupreplyattr().getValue(),
                groupReply.getGroupreplyop().getValue(),
                StringUtils.setQuates(groupReply.getGroupreplyval()),
                groupReply.getId());
    }

    public Long nextId () {
        Long id = this.jdbcTemplate.queryForObject("select max(groupreplyid) from rgroupreply", Long.class) + 1L;
        return id;
    }

    @Override
    public List<RGroupReply> findGroupByUsername (String name) {
        return this.jdbcTemplate.query(SELECT_FROM_RGROUP_WITH_SEARCH_BY_USERNAME, new Object[] {name}, new RGroupReplyMapper());
    }

    @Override
    public List<String> findGroupByUsernameFromMusrgrp (String name) {
        return this.jdbcTemplate.query(SELECT_FROM_MUSRGRP, new Object[] {name},
                new RowMapper<String>() {
                    @Override
                    public String mapRow (ResultSet rs, int rowNum) throws SQLException {
                        return rs.getString("groupname");
                    }
                });
    }

    @Override
    public Collection<RGroupReply> findAllDistinctByGroupreplyname () {
        List<RGroupReply> list = this.jdbcTemplate.query(SELECT_FIND_ALL_DISTINCT,
                new RowMapper<RGroupReply>() {
                    @Override
                    public RGroupReply mapRow (ResultSet rs, int rowNum) throws SQLException {
                        RGroupReply rGroupReply = new RGroupReply();
                        rGroupReply.setGroupreplyname(rs.getString("name"));

                        return rGroupReply;
                    }
                });
        return list;
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

    private static final class RGroupReplyMapper implements RowMapper<RGroupReply> {

        public RGroupReply mapRow (ResultSet rs, int rowNum) throws SQLException {
            RGroupReply rGroupReply = new RGroupReply();
            rGroupReply.setGroupreplyname(rs.getString("groupreplyname").trim());
            rGroupReply.setGroupreplyattr(RadiusAttribute.getKey((rs.getString("groupreplyattr")).trim()));
            rGroupReply.setGroupreplyop(RadiusOperation.getKey((rs.getString("groupreplyop")).trim()));
            rGroupReply.setGroupreplyval(rs.getString("groupreplyval"));
            rGroupReply.setId(rs.getLong("groupreplyid"));
            return rGroupReply;
        }
    }
}
