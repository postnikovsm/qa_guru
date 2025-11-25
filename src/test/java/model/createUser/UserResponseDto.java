package model.createUser;

import lombok.Data;

@Data
public class UserResponseDto {
    private String name;
    private String job;
    private String id;
    private String createdAt;
    private String updatedAt;
}
