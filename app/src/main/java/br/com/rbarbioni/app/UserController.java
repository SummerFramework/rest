package br.com.rbarbioni.app;

import br.com.rbarbioni.app.model.User;
import br.com.rbarbioni.app.model.valueobject.UserVO;
import br.com.rbarbioni.app.repository.UserRepository;
import com.rbarbioni.core.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renan on 23/02/16.
 */

@RestController
@RequestMapping( "/user")
public class UserController extends ControllerBase<User, UserVO> {

    @Autowired
    public UserController(UserRepository userRepository) {
        super(userRepository);
    }
}
