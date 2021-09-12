package com.jakesajao.githubAnalytics.services;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    @Override
    public Optional<GitUser> validateCredentials(String email, String password) {
        return Optional.empty();
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

    public UserDetails loadUserByUsername(LoginFormDto loginFormDto) throws UsernameNotFoundException {
        GitUser user = userRepository.findByEmail(loginFormDto.getEmail());
        System.out.println("loadUserByUsername loginFormDto: " +loginFormDto);
        if (user == null) {
            System.out.println("loadUserByUsername loginFormDto: " +"NULL");
            return null;
            //throw new UsernameNotFoundException("Invalid username or password.");
        }
        List<GrantedAuthority> auth = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
//        if (username.equals("admin")) {
//            auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
//        }
        auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        String password = user.getPassword();
        return new org.springframework.security.core.userdetails.User(loginFormDto.getEmail(), password, auth);

    }


//    public GitUser loadUserByUsername(LoginFormDto loginFormDto) throws UsernameNotFoundException {
//        System.out.println("loadUserByUsername Email: " +loginFormDto);
//
//        GitUser user = userRepository.findByEmail(loginFormDto.getEmail());
//        if (user == null) {
//            System.out.println("Invalid username or password.");
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//
//        System.out.println("Valid username or password.");
//        return user;
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

//    private Collection < ? extends GrantedAuthority > mapRolesToAuthorities(Collection < Role > roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
  //  }
}

