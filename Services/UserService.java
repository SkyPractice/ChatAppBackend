package com.app.demo.Services;

import com.app.demo.Tables.User;
import com.app.demo.Repositories.UserRepos;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepos userRepos;

    public UserService(UserRepos userRepos){
        this.userRepos = userRepos;
    }

    public boolean existsByUsernameAndPassword(String username, String password) {
        Specification<User> userSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("userName"), username));
            predicateList.add(criteriaBuilder.equal(root.get("password"), password));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };

        return userRepos.exists(userSpecification);
    }

    public boolean existsByUsername(String username){
        return userRepos.exists((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("userName"), username));
    }

    public void deleteByUsernameAndPassword(String username, String password) {
        Specification<User> userSpecification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();
            predicateList.add(criteriaBuilder.equal(root.get("userName"), username));
            predicateList.add(criteriaBuilder.equal(root.get("password"), password));
            return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
        };

        userRepos.delete(userSpecification);
    }


    public UserRepos getUserRepos(){
        return userRepos;
    }

}
