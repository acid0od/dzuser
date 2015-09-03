package net.odtel.dzuser.impl.repository.impl;

import net.odtel.dzuser.api.model.JAcct;
import net.odtel.dzuser.impl.repository.JAcctRepository;
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

@Repository(value = "jAcctRepository")
@Transactional
public class JAcctRepositoryImpl implements JAcctRepository {

    private static final String MOUNTH = "1 month";
    private static final String WEEK = "1 week";

    private static final String SEARCH_TOP = "SELECT acctuser, sum(acctduration) AS duration, sum(acctout) AS out from jacct_ where acctstart > EXTRACT(EPOCH FROM TIMESTAMP 'now()' - interval '1 month') GROUP BY acctuser ORDER BY out DESC LIMIT ?";
    private static final String SEARCH_TOP_WITH_NAME = "SELECT acctuser, sum(acctduration) AS duration, sum(acctout) AS out from jacct_ where acctuser = ? and acctstart > EXTRACT(EPOCH FROM TIMESTAMP 'now()' - interval '1 month') GROUP BY acctuser ORDER BY out DESC LIMIT ?";
    private static final String SEARCH_TOP_WITH_NAME_AND_NAS = "SELECT acctuser, sum(acctduration) AS duration, sum(acctout) AS out from jacct_ where acctuser = ? and nasid = ? and acctstart > EXTRACT(EPOCH FROM TIMESTAMP 'now()' - interval '1 month') GROUP BY acctuser ORDER BY out DESC LIMIT ?";
    private static final String SEARCH_TOP_WITH_NAME_LIKE = "SELECT acctuser, sum(acctduration) AS duration, sum(acctout) AS OUT from jacct_ where acctuser LIKE ? and acctstart > EXTRACT(EPOCH FROM TIMESTAMP 'now()' - interval '1 month') GROUP BY acctuser ORDER BY out DESC LIMIT ?";
    private static final String SEARCH_TOP_WITH_NAME_LIKE_AND_NAS = "SELECT acctuser, sum(acctduration) AS duration, sum(acctout) AS OUT from jacct_ where acctuser LIKE ? and nasid = ? and acctstart > EXTRACT(EPOCH FROM TIMESTAMP 'now()' - interval '1 month') GROUP BY acctuser ORDER BY out DESC LIMIT ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<JAcct> viewTopLastMonth (Pageable pageable) {
        List<JAcct> list = this.jdbcTemplate.query(SEARCH_TOP,
                new Object[] {pageable.getPageSize()},
                new JAcctShortMapper());
        return new PageImpl<JAcct>(list, pageable, pageable.getPageSize());
    }

    @Override
    public Page<JAcct> viewTopLastMonth (String name, Pageable pageable) {
        List<JAcct> list = this.jdbcTemplate.query(SEARCH_TOP_WITH_NAME,
                new Object[] {name, pageable.getPageSize()},
                new JAcctShortMapper());
        return new PageImpl<JAcct>(list, pageable, pageable.getPageSize());
    }

    @Override
    public Page<JAcct> viewTopLastMonth (String name, Integer nasid, Pageable pageable) {
        List<JAcct> list = this.jdbcTemplate.query(SEARCH_TOP_WITH_NAME_AND_NAS,
                new Object[] {name, nasid, pageable.getPageSize()},
                new JAcctShortMapper());
        return new PageImpl<JAcct>(list, pageable, pageable.getPageSize());
    }

    @Override
    public Page<JAcct> viewTopLastMonthLike (String name, Pageable pageable) {
        List<JAcct> list = this.jdbcTemplate.query(SEARCH_TOP_WITH_NAME_LIKE,
                new Object[] {name, pageable.getPageSize()},
                new JAcctShortMapper());
        return new PageImpl<JAcct>(list, pageable, pageable.getPageSize());
    }

    @Override
    public Page<JAcct> viewTopLastMonthLike (String name, Integer nasid, Pageable pageable) {
        List<JAcct> list = this.jdbcTemplate.query(SEARCH_TOP_WITH_NAME_LIKE_AND_NAS,
                new Object[] {name, nasid, pageable.getPageSize()},
                new JAcctShortMapper());
        return new PageImpl<JAcct>(list, pageable, pageable.getPageSize());
    }

    private static final class JAcctShortMapper implements RowMapper<JAcct> {
        public JAcct mapRow (ResultSet rs, int rowNum) throws SQLException {
            JAcct acct = new JAcct();
            acct.setAcctuser(rs.getString("acctuser"));
            acct.setAcctduration(rs.getInt("duration"));
            acct.setAcctout(rs.getLong("out"));
            return acct;
        }
    }

}
