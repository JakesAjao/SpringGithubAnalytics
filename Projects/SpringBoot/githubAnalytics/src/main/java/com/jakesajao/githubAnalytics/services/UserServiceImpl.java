package com.jakesajao.githubAnalytics.services;
import java.awt.print.Book;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import com.jakesajao.githubAnalytics.models.*;
import com.jakesajao.githubAnalytics.repositories.UserRepository;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    private HTTPConnections httpconnections = new HTTPConnections();

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
        System.out.println("Save Role as : "+registration.getRole());
        user.setRoles(Arrays.asList(new Role(registration.getRole())));
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
    @Override
    public Paged<Committer> getPage(int pageNumber, int size,String git,String repo) {
        //PageRequest request = PageRequest.of(pageNumber - 1, size, new Sort(Sort.Direction.ASC, "id"));
        //Page<Committer> committerPageOld = committerRepository.findAll(Sort.by(Sort.Direction.DESC, "id");
        System.out.println("getPage 1.");
        List<Committer> repository = httpconnections.repositoryByRepoName(git,repo);
        Page<Committer> committerPage = userService.findPaginated(PageRequest.of(pageNumber - 1, size),
                repository);
        System.out.println("getPage 2.");
        return new Paged<Committer>(committerPage, Paging.of(committerPage.getTotalPages(), pageNumber, size));
    }
//    @Override
//    public Paged<Committer> getPage(int pageNumber, int size,UserService userService) {
//        PageRequest request = PageRequest.of(pageNumber - 1, size, new Sort(Sort.Direction.ASC, "id"));
//        Page<Committer> postPage = userService.findAll(request);
//        return new Paged<>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size));
//    }
}


