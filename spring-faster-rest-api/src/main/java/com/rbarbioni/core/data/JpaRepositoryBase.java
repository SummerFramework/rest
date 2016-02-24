package com.rbarbioni.core.data;

/**
 * Created by renan on 11/01/16.
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface JpaRepositoryBase<T, ID extends Serializable> extends JpaRepository<T, ID> {

    T findByUUID(String uuid);

    List<T> findActives();

    Object getDeclaredType();

}
