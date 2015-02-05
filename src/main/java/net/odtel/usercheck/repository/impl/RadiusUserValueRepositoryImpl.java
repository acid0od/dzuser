package net.odtel.usercheck.repository.impl;

import net.odtel.usercheck.domain.RadiusUserValue;
import net.odtel.usercheck.repository.RadiusUserValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository(value = "radiusUserValueRepository")
@Transactional
public class RadiusUserValueRepositoryImpl implements RadiusUserValueRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public RadiusUserValue createNewRecord(RadiusUserValue radiusUserValue) {
        Long id = nextId();
        String s = radiusUserValue.getValue().trim();
        StringBuilder builder = new StringBuilder();

        if (!s.startsWith("\"")) {
            builder.append("\"").append(s);
        }  else {
            builder.append(s);
        }

        if (!s.endsWith("\"")) {
            builder.append("\"");
        }

        this.jdbcTemplate.update(
                "insert into ruserreply (userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval) VALUES (?,?,?,?,?)",
                id,
                radiusUserValue.getUsername(),
                radiusUserValue.getAttribute().getValue(),
                radiusUserValue.getOperator().getValue(),
                builder.toString());

        return radiusUserValue;
    }

    @Override
    public Long createOrUpdate(RadiusUserValue value) {
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

    private void update(RadiusUserValue value) {
        String s = value.getValue().trim();
        StringBuilder builder = new StringBuilder();

        if (!s.startsWith("\"")) {
            builder.append("\"").append(s);
        } else {
            builder.append(s);
        }

        if (!s.endsWith("\"")) {
            builder.append("\"");
        }

        this.jdbcTemplate.update(
                "insert ruserreply set userreplyname = ? , userreplyattr = ? , userreplyop = ? , userreplyval = ? where userreplyid = ?",
                value.getUsername(),
                value.getAttribute().getValue(),
                value.getOperator().getValue(),
                builder.toString(),
                value.getId());

    }

    private void create(RadiusUserValue value) {
        String s = value.getValue().trim();
        StringBuilder builder = new StringBuilder();

        if (!s.startsWith("\"")) {
            builder.append("\"").append(s);
        }  else {
            builder.append(s);
        }

        if (!s.endsWith("\"")) {
            builder.append("\"");
        }

        this.jdbcTemplate.update(
                "insert into ruserreply (userreplyid, userreplyname, userreplyattr, userreplyop, userreplyval) VALUES (?,?,?,?,?)",
                value.getId(),
                value.getUsername(),
                value.getAttribute().getValue(),
                value.getOperator().getValue(),
                builder.toString());

    }

    private Long nextId() {
        Long id = this.jdbcTemplate.queryForObject("select nextval('ruserreply_userreplyid_seq'::regclass)", Long.class);
        return id;
    }

}
