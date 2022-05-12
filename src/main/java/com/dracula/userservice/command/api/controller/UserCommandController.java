package com.dracula.userservice.command.api.controller;

import com.dracula.userservice.command.api.command.CreateUserCommand;
import com.dracula.userservice.command.api.rest.UserRestModel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserCommandController {
    public final CommandGateway commandGateway;

    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody UserRestModel userRestModel) {
        CreateUserCommand userCommand = CreateUserCommand.builder()
                .userId(UUID.randomUUID().toString())
                .userName(userRestModel.getUserName())
                .password(userRestModel.getPassword())
                .build();

        commandGateway.sendAndWait(userCommand);
        return userCommand.getUserId();

    }
}