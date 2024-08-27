package com.example.onlinelearning.POJO;
import com.example.onlinelearning.Entity.User;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PojoUser {
    private Long userId;

    private String username;

    private String email;

    private String password;

    public static PojoUser fromEntity(User user){
        var pojo = new PojoUser();
        pojo.setUserId(user.getUserId());
        pojo.setUsername(user.getUsername());
        pojo.setEmail(user.getEmail());
        pojo.setPassword(user.getPassword());
        return pojo;
    }

    public static User toEntity(PojoUser pojoUser){
        var user = new User();
        user.setUserId(pojoUser.getUserId());
        user.setUsername(pojoUser.getUsername());
        user.setEmail(pojoUser.getEmail());
        user.setPassword(pojoUser.getPassword());
        return user;
    }

}
