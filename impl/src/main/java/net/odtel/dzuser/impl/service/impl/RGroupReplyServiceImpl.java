package net.odtel.dzuser.impl.service.impl;

import net.odtel.dzuser.api.model.RGroupReply;
import net.odtel.dzuser.api.model.RGroupReplyPair;
import net.odtel.dzuser.impl.repository.RGroupReplyRepository;
import net.odtel.dzuser.impl.service.RGroupReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service("rGroupReplyService")
public class RGroupReplyServiceImpl implements RGroupReplyService {

    @Autowired
    private RGroupReplyRepository rGroupReplyRepository;

    @Override
    public Page<RGroupReply> findAll (int page, int size) {
        Pageable pageable = new PageRequest(page, size, new Sort(Sort.Direction.ASC, "groupreplyname"));
        return rGroupReplyRepository.findGroupreplynameDistinct(pageable);
    }

    @Override
    public RGroupReplyPair findByName (String name) {
        RGroupReplyPair pair = new RGroupReplyPair();

        List<RGroupReply> list = rGroupReplyRepository.findByGroupreplyname(name);
        if (list.size() > 0) {
            pair.setName(list.get(0).getGroupreplyname());
            pair.setList(list);
        }

        return pair;
    }

    @Override
    public void save (RGroupReplyPair pair) {
        if (pair != null && pair.getList() != null && pair.getList().size() > 0) {
            for (RGroupReply groupReply : pair.getList()) {
                groupReply.setGroupreplyname(pair.getName());
                rGroupReplyRepository.save(groupReply);
            }
        }
    }

    @Override
    public void delete (String groupReplyName) {
        rGroupReplyRepository.delete(groupReplyName);
    }

    @Override
    public void delete (Long groupReplyId) {
        rGroupReplyRepository.delete(groupReplyId);
    }

    @Override
    public Collection<RGroupReply> findAllDistinctByGroupreplyname () {
        return  rGroupReplyRepository.findAllDistinctByGroupreplyname();
    }
}
