package com.pycogroup.pitsa.repository;

import com.pycogroup.pitsa.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phone);
}
