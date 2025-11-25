package model.loginUser;

import lombok.Data;

@Data
public class UserLoginRequestDto {
    private String email;
    private String password;
}
