package com.pycogroup.pitsa.service;

import com.pycogroup.pitsa.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserService service;
    @Test
    public void signup() {
        Optional<User> user = service.signUpToUser("john123@gmail.com", "012345678", "john", "doe","dummypassword");
        assertThat(user.get().getPassword(), not("dummypassword"));
        System.out.println("Encoded Password = " + user.get().getPassword());
    }

}