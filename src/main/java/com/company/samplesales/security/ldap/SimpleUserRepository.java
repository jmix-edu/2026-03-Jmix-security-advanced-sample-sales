package com.company.samplesales.security.ldap;

import com.company.samplesales.security.FullAccessRole;
import io.jmix.core.security.UserRepository;
import io.jmix.security.role.RoleGrantedAuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class SimpleUserRepository implements UserRepository {
    private final RoleGrantedAuthorityUtils roleGrantedAuthorityUtils;

    public SimpleUserRepository(RoleGrantedAuthorityUtils roleGrantedAuthorityUtils) {
        this.roleGrantedAuthorityUtils = roleGrantedAuthorityUtils;
    }

    @Override
    public UserDetails getSystemUser() {
        GrantedAuthority authority = roleGrantedAuthorityUtils.createResourceRoleGrantedAuthority(FullAccessRole.CODE);
        return new User("system", "", Collections.singleton(authority));
    }

    @Override
    public UserDetails getAnonymousUser() {
        return new User("anonymous", "", Collections.emptyList());
    }

    @Override
    public List<? extends UserDetails> getByUsernameLike(String substring) {
        return Collections.emptyList();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("User '" + username + "' not found");
    }
}
