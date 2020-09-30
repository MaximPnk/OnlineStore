package dto;

import entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Collection<Role> roles;

}