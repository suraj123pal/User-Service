package com.dracula.userservice.query.api.projection;

import com.dracula.userservice.command.api.Repository.UserRepo;
import com.dracula.userservice.command.api.entity.UserEntity;
import com.dracula.userservice.command.api.rest.UserRestModel;
import com.dracula.userservice.query.api.query.FindUserQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserProjection {

    @Autowired
    private UserRepo userRepo;

    @QueryHandler
    public List<UserRestModel> getUsers(FindUserQuery findUserQuery)
    {
        List<UserEntity> allUsers = userRepo.findAll();
        List<UserRestModel> userRestModels = allUsers.stream().map(userEntity -> UserRestModel.builder()
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .build()).collect(Collectors.toList());

        return userRestModels;
    }
}
