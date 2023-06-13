package com.ahom.hrms.entities;

import com.ahom.hrms.constant.PrefixAndSequence;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "branch_seq")
    @GenericGenerator(name = "branch_seq",
            strategy = "com.ahom.hrms.constant.PrefixAndSequence",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.INCREMENT_PARAM,value = "1"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.VALUE_PREFIX_PARAMETER, value = "ATPL_"),
                    @org.hibernate.annotations.Parameter(name = PrefixAndSequence.NUMBER_FORMAT_PARAMETER,value = "%03d")
            })

    private String id;

    @NotBlank(message = "Department name is mandatory")
    private String departmentName;

    @Column(nullable = false)
    @NotEmpty(message = "Name can not be empty")
    @Pattern(regexp = "[/^[a-zA-Z ]*$/]{1,30}",message = "sss")
    private String employeeName;


    @NotBlank(message = "user name is mandatory")
    @Column(unique = true)
    @Email
    private String userName;


    @JsonProperty()
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
            message = "Password must contain one upperCase special character and minimum length 8")
    private String password;

    @Transient
    @NotEmpty
    private String confirmPassword;

//    @Enumerated(EnumType.STRING)
//    @NotNull(message = "roles can not be null")
//    private Roles roles;
    @NotEmpty(message = "Role is mandatory")
    private String roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String roleName = "ROLE_" + roles;
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);

        return List.of(simpleGrantedAuthority);

    }

    @Override
    public String getUsername() {
        return userName;
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
}
