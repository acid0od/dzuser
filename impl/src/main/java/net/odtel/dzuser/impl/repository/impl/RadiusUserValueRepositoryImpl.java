package net.odtel.dzuser.impl.repository.impl;

import net.odtel.dzuser.api.model.RadiusUserValue;
import net.odtel.dzuser.impl.repository.RadiusUserValueRepository;
import net.odtel.dzuser.impl.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository(value = "radiusUserValueRepository")
@Transactional
public class RadiusUserValueRepositoryImpl implements RadiusUserValueRepository {

    public static final String INSERT_INTO_RUSERREPLY = "insert into ruserreply (userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval) VALUES (?,?,?,?,?)";
    public static final String UPDATE_RUSERREPLY = "update ruserreply set userreplyname = ? , userreplyattr = ? , userreplyop = ? , userreplyval = ? where userreplyid = ?";
    public static final String DELETE_FROM_RUSERREPLY = "delete from ruserreply where userreplyid = ?";
    public static final String NEXTVAL_RUSERREPLY = "select nextval('ruserreply_userreplyid_seq'::regclass)";
    private static final String SEARCH_USERS_VALUES_FROM_USERSREPLAY = "select userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval from ruserreply where userreplyname = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RadiusUserValue createNewRecord (RadiusUserValue radiusUserValue) {
        Long id = nextId();
        this.jdbcTemplate.update(
                INSERT_INTO_RUSERREPLY,
                id,
                radiusUserValue.getUsername(),
                radiusUserValue.getAttribute().getValue(),
                radiusUserValue.getOperator().getValue(),
                StringUtils.setQuates(radiusUserValue.getValue())
        );
        return radiusUserValue;
    }

    @Override
    public Set<Long> getSetOfUserId (String radiusUser) {
        List<Long> longs = this.jdbcTemplate.query(SEARCH_USERS_VALUES_FROM_USERSREPLAY,
                new Object[] {radiusUser},
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow (ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong("userreplyid");
                    }
                });
        Set<Long> a = new HashSet<>();
        a.addAll(longs);
        return a;
    }

    @Override
    public void remove (Long id) {
        this.jdbcTemplate.update(
                DELETE_FROM_RUSERREPLY,
                id);
    }

    @Override
    public Long createOrUpdate (RadiusUserValue value) {
        Long id = null;
        if (value.getId() != null && value.getId() > 0) {
            id = value.getId();
            update(value);
        } else {
            id = nextId();
            value.setId(id);
            create(value);
        }
        return id;
    }

    private void update (RadiusUserValue value) {
        this.jdbcTemplate.update(
                UPDATE_RUSERREPLY,
                value.getUsername(),
                value.getAttribute().getValue(),
                value.getOperator().getValue(),
                StringUtils.setQuates(value.getValue()),
                value.getId());

    }

    private void create (RadiusUserValue value) {
        this.jdbcTemplate.update(
                INSERT_INTO_RUSERREPLY,
                value.getId(),
                value.getUsername(),
                value.getAttribute().getValue(),
                value.getOperator().getValue(),
                StringUtils.setQuates(value.getValue()));
    }

    private Long nextId () {
        Long id = this.jdbcTemplate.queryForObject(NEXTVAL_RUSERREPLY, Long.class);
        return id;
    }

}
