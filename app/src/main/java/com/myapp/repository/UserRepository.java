package com.myapp.repository;

import com.myapp.model.User;
import com.sf.rest.api.data.BaseJpaRepository;

/**
 * Created by renan on 23/02/16.
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {
}
