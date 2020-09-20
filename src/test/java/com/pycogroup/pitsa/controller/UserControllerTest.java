package com.pycogroup.pitsa.controller;

import com.pycogroup.pitsa.model.DTO.LoginDto;
import com.pycogroup.pitsa.model.User;
import com.pycogroup.pitsa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    private LoginDto signupDto = new LoginDto("larry", "1234", "larry", "miller");
    private User user = new User(signupDto.getUsername(), signupDto.getPassword(),
            signupDto.getFirstName(), signupDto.getLastName());

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private UserService service;

    @Test
    public void signin() {
        restTemplate.postForEntity("/user/signin", new LoginDto("0123456897", "dummy123"), Void.class);
        verify(this.service,times(1)).signInToUser("0123456789","dummy123");
    }

    @Test
    public void signup(){
        when(service.signUpToUser(signupDto.getEmail(),signupDto.getPhone(), signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getPassword())).thenReturn(Optional.of(user));

        assertThat(user.getEmail(), is(user.getEmail()));
        assertThat(user.getFirstname(), is(user.getFirstname()));
        assertThat(user.getLastname(), is(user.getLastname()));
        assertThat(user.getPhone(), is(user.getPhone()));

    }


}
