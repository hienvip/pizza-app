package com.pycogroup.pitsa.security;

import com.pycogroup.pitsa.model.User;
import com.pycogroup.pitsa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;


@Component
public class PizzaCustomerDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public UserDetails loadUserByUsername(String requiredEntry) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("CUSTOMER"));

        Optional<User> user;

        if (requiredEntry.charAt(0) == '0') {
            user = userRepository.findByPhone(requiredEntry);
            return withUsername(user.get().getPhone())
                    .password(user.get().getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();

        } else {
            user = userRepository.findByEmail(requiredEntry);
            return withUsername(user.get().getEmail())
                    .password(user.get().getPassword())
                    .authorities(authorities)
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        }

    }

    /**
     * Extract username and roles from a validated jwt string.
     *
     * @param jwtToken jwt string
     * @return UserDetails if valid, Empty otherwise
     */
    public Optional<UserDetails> loadUserByJwtToken(String jwtToken) {
        if (jwtProvider.isValidToken(jwtToken)) {
            return Optional.of(
                    withUsername(jwtProvider.getUsername(jwtToken))
                            .password("") //token does not have password but field may not be empty
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }

}
