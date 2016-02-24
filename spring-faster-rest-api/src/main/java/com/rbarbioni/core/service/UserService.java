package com.rbarbioni.core.service;

import com.rbarbioni.core.base.ServiceBase;
import com.rbarbioni.core.model.User;
import com.rbarbioni.core.model.valueobject.UserVO;
import com.rbarbioni.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renan on 23/02/16.
 */

@Service
public class UserService extends ServiceBase<User, UserVO> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
