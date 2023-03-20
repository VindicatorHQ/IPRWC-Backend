package com.webshop.webshopbackend.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.webshop.webshopbackend.domain.model.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    private String id;

    @Column
    @NotBlank(message="First name cannot be empty.")
    private String name;

    @Column
    @NotBlank(message="Last name cannot be empty.")
    private String lastName;

    @Column
    @NotBlank(message="Email cannot be empty.")
    @Email(message="Email is invalid.")
    private String email;

    @Column
    @NotBlank(message="Password cannot be empty.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private boolean enabled;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private Set<Order> orders = new LinkedHashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enabled;
    }
}
