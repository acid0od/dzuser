package net.odtel.dzuser.impl.service;

import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RGroupReplyPair;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface RGroupReplyService {

    Page<RGroupReply> findAll (int page, int size);

    RGroupReplyPair findByName (String name);

    void save (RGroupReplyPair pair);

    void delete (String groupReplyName);

    void delete (Long groupReplyId);

    Collection<RGroupReply> findAllDistinctByGroupreplyname ();
}
