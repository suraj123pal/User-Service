package com.dracula.userservice.query.api.controller;

import com.dracula.userservice.command.api.rest.UserRestModel;
import com.dracula.userservice.query.api.query.FindUserQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class QueryController {

    @Autowired
    public QueryGateway queryGateway;

    @GetMapping
    public List<UserRestModel> getUsers()
    {
        FindUserQuery findUserQuery = new FindUserQuery();
        List<UserRestModel> query = queryGateway.query(findUserQuery, ResponseTypes
                .multipleInstancesOf(UserRestModel.class)).join();

        return query;
    }

}
