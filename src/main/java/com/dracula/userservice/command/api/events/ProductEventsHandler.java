package com.dracula.userservice.command.api.events;

import com.dracula.userservice.command.api.Repository.UserRepo;
import com.dracula.userservice.command.api.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventsHandler {

    @Autowired
    public UserRepo userRepo;

    @EventHandler
    public void handle(ProductCreatedEvent productCreatedEvent)
    {
        UserEntity userEntity = new UserEntity();

        BeanUtils.copyProperties(productCreatedEvent,userEntity);

        userRepo.save(userEntity);
      log.info("User saved in repo....." + userEntity.getUserName());
    }
}

