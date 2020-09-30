package dto;

import valid.ValidUser;

public class ModelToEntity {

    public static UserEntity user (ValidUser user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(user.getUserName());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());

        return userEntity;
    }
}
