package com.sf.rest.api.app.model.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sf.rest.api.model.valueobject.DomainVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by renan on 20/02/16.
 */
public class ApplicationVO extends DomainVO {

    @Size(min = 3, max = 60, message = "{USER.SIZE.VALIDATION}")
    @NotNull(message = "package_name é obrigatório")
    @JsonProperty("package_name")
    private String packageName;

    @Size(min = 3, max = 60, message = "name deve ter no mínimo 3 e máximo 60 caracteres")
    @NotNull(message = "nome é obrigatório")
    @JsonProperty("name")
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
