package com.myapp;

import com.myapp.model.User;
import com.myapp.model.valueobject.UserVO;
import com.myapp.repository.UserRepository;
import org.summerframework.rest.BaseRestController;
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
