package com.myapp.model.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.summerframework.core.model.valueobject.DomainVO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by renan on 20/02/16.
 */
public class ApplicationVO extends DomainVO {

    @Size(min = 3, max = 60, message = "application.package_name.size.min.max")
    @NotNull(message = "application.package_name.required")
    @JsonProperty("package_name")
    private String packageName;

    @Size(min = 3, max = 60, message = "{application.name.size.min.max}")
    @NotNull(message = "application.name.required")
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
