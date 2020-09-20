package com.pycogroup.pitsa.repo;

import com.pycogroup.pitsa.model.User;
import com.pycogroup.pitsa.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Test
    public void testFindByEmail() {

        Optional<User> user = repository.findByEmail("anTran123@gmail.com");
        if(!user.isPresent()){
            assertTrue(user.get() != null);
        }

        user = repository.findByEmail("nobody@gmail.com");
        if(!user.isPresent()){
            assertFalse(user.isPresent());
        }
    }
}
