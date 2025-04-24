package com.app.demo.Services;

import com.app.demo.Repositories.FriendRequestRepos;
import com.app.demo.Tables.FriendRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import jakarta.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendRequestService {

    private final FriendRequestRepos friendRequestRepos;

    @Autowired
    public FriendRequestService(FriendRequestRepos friendRequestRepos){
        this.friendRequestRepos = friendRequestRepos;
    }

    public FriendRequestRepos getFriendRequestRepos(){
        return friendRequestRepos;
    }

    public boolean acceptedFriendRequestByName(String sender, String to){
        Specification<FriendRequestEntity> entitySpecification = ((root,
            query, criteriaBuilder) -> {
            List<Predicate> firstPredicateList = new ArrayList<>();
            firstPredicateList.add(criteriaBuilder.equal(root.get("sender"), sender));
            firstPredicateList.add(criteriaBuilder.equal(root.get("receiver"), to));
            firstPredicateList.add(criteriaBuilder.equal(root.get("accepted"), true));

            List<Predicate> secondPredicateList = new ArrayList<>();
            secondPredicateList.add(criteriaBuilder.equal(root.get("sender"), to));
            secondPredicateList.add(criteriaBuilder.equal(root.get("receiver"), sender));
            secondPredicateList.add(criteriaBuilder.equal(root.get("accepted"), true));

            Predicate firstPredicate = criteriaBuilder.and(firstPredicateList.toArray(new Predicate[0]));
            Predicate secondPredicate = criteriaBuilder.and(secondPredicateList.toArray(new Predicate[0]));

            return criteriaBuilder.or(firstPredicate, secondPredicate);
        });

        return friendRequestRepos.exists(entitySpecification);
    }
    public void deleteFriendRequestByName(String sender, String to){
        Specification<FriendRequestEntity> entitySpecification = ((root,
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

        friendRequestRepos.delete(entitySpecification);
    }
    public List<FriendRequestEntity> acceptedFriendRequestsBySingleName(String name){

        return friendRequestRepos.findAll(((root,
                query, criteriaBuilder) ->
                    criteriaBuilder.and(  criteriaBuilder.or(criteriaBuilder.equal(root.get("sender"), name),
                            criteriaBuilder.equal(root.get("receiver"), name)), criteriaBuilder.equal(root.get("accepted"), true))
                ));

    }
    public List<FriendRequestEntity> pendingFriendRequestsBySingleName(String name){
        return friendRequestRepos.findAll(((root,
                                            query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.or(criteriaBuilder.equal(root.get("sender"), name),
                        criteriaBuilder.equal(root.get("receiver"), name)), criteriaBuilder.equal(root.get("accepted"), false))
        ));
    }

}
