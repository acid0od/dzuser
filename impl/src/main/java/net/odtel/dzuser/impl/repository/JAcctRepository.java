package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.JAcct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JAcctRepository {

    Page<JAcct> viewTopLastMonth (Pageable pageable);

    Page<JAcct> viewTopLastMonth (String name, Pageable pageable);

    Page<JAcct> viewTopLastMonth (String name, Integer nasid, Pageable pageable);

    Page<JAcct> viewTopLastMonthLike (String name, Pageable pageable);

    Page<JAcct> viewTopLastMonthLike (String name, Integer nasid, Pageable pageable);
}
