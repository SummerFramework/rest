package com.myapp.repository;

import com.myapp.model.User;
import org.summerframework.core.data.BaseJpaRepository;

/**
 * Created by renan on 23/02/16.
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {
}
