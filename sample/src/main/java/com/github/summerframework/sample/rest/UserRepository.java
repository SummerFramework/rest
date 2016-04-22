package com.github.summerframework.sample.rest;

import com.github.summerframework.sample.model.User;
import com.github.summerframework.core.data.BaseJpaRepository;

/**
 * Created by renan on 23/02/16.
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {
}
