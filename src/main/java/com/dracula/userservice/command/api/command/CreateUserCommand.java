package com.dracula.userservice.command.api.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserCommand {
    //create product command api
    @TargetAggregateIdentifier
    private String userId;
    private String userName;
    private String password;
}
