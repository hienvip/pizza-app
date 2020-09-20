package com.pycogroup.pitsa.repository;

import com.pycogroup.pitsa.model.Role;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, ObjectId> {
    Optional<Role> findByRoleName(String name);
}
