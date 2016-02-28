package com.sf.rest.api.app.model.valueobject;

import com.sf.rest.api.model.valueobject.DomainVO;

/**
 * Created by renan on 23/02/16.
 */
public class UserVO extends DomainVO {

    private String name;

    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
