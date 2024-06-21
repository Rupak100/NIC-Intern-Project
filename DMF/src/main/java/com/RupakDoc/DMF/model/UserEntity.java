package com.RupakDoc.DMF.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "client")
public class UserEntity implements UserDetails {
    private String client_id;
    private String client_secret;
    private Date created_on;
    private Date expiry_on;

    @Override
    public String getUsername() {
        return client_id;
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
        return true;
    }

    public void setUsername(String username) {
        this.client_id = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return an empty collection or a default authority
        return List.of(new SimpleGrantedAuthority("ROLE_USER")); // or Collections.emptyList()
    }

    @Override
    public String getPassword() {
        return client_secret;
    }

    public void setPassword(String password) {
        this.client_secret = password;
    }
}
