package com.github.summerframework.sample.rest;

import com.github.summerframework.sample.model.User;
import com.github.summerframework.sample.model.valueobject.UserVO;
import com.github.summerframework.sample.rest.UserRepository;
import com.github.summerframework.rest.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renan on 23/02/16.
 */

@RestController
@RequestMapping( "/user")
public class UserRestController extends BaseRestController<User, UserVO> {

    @Autowired
    public UserRestController(UserRepository jpaRepositoryBase) {
        super(jpaRepositoryBase);
    }
}
