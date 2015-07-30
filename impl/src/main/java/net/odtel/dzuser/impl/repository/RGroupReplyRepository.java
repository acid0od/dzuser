package net.odtel.dzuser.impl.repository;

import net.odtel.dzuser.api.model.RGroupReply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface RGroupReplyRepository {

    Page<RGroupReply> findGroupreplynameDistinct (Pageable pageable);

    List<RGroupReply> findByGroupreplyname (String name);

    void save (RGroupReply groupReply);

    void delete (String groupReplyName);

    void delete (Long groupReplyId);

    List<RGroupReply> findGroupByUsername (String name);

    Collection<RGroupReply> findAllDistinctByGroupreplyname ();

    List<String> findGroupByUsernameFromMusrgrp (String name);
}
