package edu.stepik.krytskii.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

    private String login;

    private String password;

    private String email;

    @Override
    public String toString() {
        return "{" +
                "\n login : " + login +
                "\n password : " + password +
                "\n email : " + email +
                "\n}";
    }
}
