package com.jakesajao.githubAnalytics.services;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public GitUser findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public GitUser save(UserRegistrationDto registration) {
        GitUser user = new GitUser();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
       // user.setGender(registration.getGender());
        user.setMobilephone(registration.getMobilephone());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        //user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        GitUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
//                user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
                user.getPassword(),null);
    }

//    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
  //  }
}

