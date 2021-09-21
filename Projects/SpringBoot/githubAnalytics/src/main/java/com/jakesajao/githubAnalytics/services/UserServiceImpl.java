package com.jakesajao.githubAnalytics.services;
import java.awt.print.Book;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import com.jakesajao.githubAnalytics.models.Committer;
import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Role;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        user.setRoles(Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("Email: "+ email);

        GitUser user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        //System.out.println("user.getEmail(): "+ user.getEmail());
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }
    public User getCurrentUser(Principal principal){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    private Collection <?extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Page<Committer> findPaginated(Pageable pageable,List<Committer> gitCommitterList) {

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Committer> list;

        if (gitCommitterList.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, gitCommitterList.size());
            list = gitCommitterList.subList(startItem, toIndex);
        }

        Page<Committer> committersPage
                = new PageImpl<Committer>(list, PageRequest.of(currentPage, pageSize), gitCommitterList.size());

        return committersPage;
    }
}


