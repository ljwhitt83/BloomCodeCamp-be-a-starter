package com.hcc.entities;

import com.hcc.enums.AuthorityEnum;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="authority")
    @Enumerated(EnumType.STRING)
    private AuthorityEnum authority;

    @ManyToMany(mappedBy = "authorities")
    private Set<User> user;

    public Authority() {
    }

    public Authority(AuthorityEnum authority, Set<User> user) {
        this.authority = authority;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthority(AuthorityEnum authority) {
        this.authority = authority;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public String getAuthority() {
        return authority.name();
    }
}
