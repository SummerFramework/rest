package br.com.rbarbioni.app.model;

import com.rbarbioni.core.model.DomainEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by renan on 23/02/16.
 */
@Entity
@Table(name = "User")
public class User extends DomainEntity {

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
