package br.com.rbarbioni.app;

import br.com.rbarbioni.app.model.Application;
import br.com.rbarbioni.app.model.valueobject.ApplicationVO;
import br.com.rbarbioni.app.repository.ApplicationRepository;
import com.rbarbioni.core.base.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by renan on 20/02/16.
 */
@RestController
@RequestMapping("/application")
public class ApplicationController extends ControllerBase<Application, ApplicationVO> {

    @Autowired
    public ApplicationController(ApplicationRepository applicationRepository) {
        super(applicationRepository);
    }

}
