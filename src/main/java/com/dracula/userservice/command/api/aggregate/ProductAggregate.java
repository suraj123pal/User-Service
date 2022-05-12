package com.dracula.userservice.command.api.aggregate;

import com.dracula.userservice.command.api.command.CreateUserCommand;
import com.dracula.userservice.command.api.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ProductAggregate {

    @AggregateIdentifier
    private String userId;
    private String userName;
    private String password;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateUserCommand createUserCommand) {
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createUserCommand,productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent)
    {
     this.userId = productCreatedEvent.getUserId();
     this.userName = productCreatedEvent.getUserName();
     this.password = productCreatedEvent.getPassword();
    }
}
