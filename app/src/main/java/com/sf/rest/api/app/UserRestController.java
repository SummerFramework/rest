package com.sf.rest.api.app;

import com.sf.rest.api.app.model.User;
import com.sf.rest.api.app.model.valueobject.UserVO;
import com.sf.rest.api.app.repository.UserRepository;
import com.sf.rest.api.base.BaseRestController;
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
