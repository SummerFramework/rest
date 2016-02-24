package br.com.rbarbioni.app.model;

import com.rbarbioni.core.model.DomainEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by renan on 20/02/16.
 */

@Entity
@Table( name = "Application" )
public class Application extends DomainEntity {

    @Column( name = "package_name", nullable = false, unique=true )
    private String packageName;

    @Column ( name = "name" )
    private String name;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
