package com.app.demo.Services;

import com.app.demo.Repositories.PrivateMsgRepos;
import com.app.demo.Tables.FriendRequestEntity;
import com.app.demo.Tables.PrivateMessageEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivateMsgService {

    private final PrivateMsgRepos privateMsgRepos;

    @Autowired
    public PrivateMsgService(PrivateMsgRepos privateMsgRepos){
        this.privateMsgRepos = privateMsgRepos;
    }

    public PrivateMsgRepos getPrivateMsgRepos() {
        return privateMsgRepos;
    }

    public List<PrivateMessageEntity> getPrivateMessagesByName(String sender, String to){
        Specification<PrivateMessageEntity> entitySpecification = ((root,
                                                                    query, criteriaBuilder) -> {
            List<Predicate> firstPredicateList = new ArrayList<>();
            firstPredicateList.add(criteriaBuilder.equal(root.get("sender"), sender));
            firstPredicateList.add(criteriaBuilder.equal(root.get("receiver"), to));

            List<Predicate> secondPredicateList = new ArrayList<>();
            secondPredicateList.add(criteriaBuilder.equal(root.get("sender"), to));
            secondPredicateList.add(criteriaBuilder.equal(root.get("receiver"), sender));

            Predicate firstPredicate = criteriaBuilder.and(firstPredicateList.toArray(new Predicate[0]));
            Predicate secondPredicate = criteriaBuilder.and(secondPredicateList.toArray(new Predicate[0]));

            return criteriaBuilder.or(firstPredicate, secondPredicate);
        });

        return privateMsgRepos.findAll(entitySpecification);
    }

}
