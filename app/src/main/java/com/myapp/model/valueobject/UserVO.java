package com.myapp.model.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sf.rest.api.model.valueobject.DomainVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by renan on 23/02/16.
 */
public class UserVO extends DomainVO {

    @Size(min = 3, max = 60, message = "{user.name.size.min.max}")
    @NotNull(message = "{user.name.required}")
    @JsonProperty("name")
    private String name;

    @Size(min = 3, max = 60, message = "{user.email.size.min.max}")
    @NotNull(message = "{user.email.required}")
    @JsonProperty("email")
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
