package com.jakesajao.githubAnalytics.services;

import com.jakesajao.githubAnalytics.models.Committer;
import com.jakesajao.githubAnalytics.models.GitUser;
import com.jakesajao.githubAnalytics.models.Paged;
import dto.LoginFormDto;
import dto.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;


public interface UserService extends UserDetailsService {
    GitUser findByEmail(String email);
    GitUser save(UserRegistrationDto registration);
    UserDetails loadUserByUsername(String email);
    Page<Committer> findPaginated(Pageable pageable,List<Committer> repository);
    Paged<Committer> getPage(int pageNumber, int size,String git,String repo);
}
/*

package com.jakesajao.githubAnalytics.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
There is a knowi 
@Service
public class GitUserDetails implements UserDetails {
    private GitUser user;
    public GitUserDetails(GitUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = (Set<Role>) user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
//    public String getFullName(){
//      return  user.get
//    }
}

 */