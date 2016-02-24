package com.rbarbioni.core.service;

import com.rbarbioni.core.base.ServiceBase;
import com.rbarbioni.core.model.Application;
import com.rbarbioni.core.model.valueobject.ApplicationVO;
import com.rbarbioni.core.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by renan on 20/02/16.
 */
@Service
public class ApplicationService extends ServiceBase<Application, ApplicationVO> {

    ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository){
        super(applicationRepository);
        this.applicationRepository = applicationRepository;
    }



}
