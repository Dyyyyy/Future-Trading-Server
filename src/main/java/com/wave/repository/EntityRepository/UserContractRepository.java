package com.wave.repository.EntityRepository;

import com.wave.model.User;
import com.wave.model.UserContract;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Json on 2016/12/11.
 */
public interface UserContractRepository extends JpaRepository<UserContract,Long> {
	ArrayList<UserContract> findByUser(@Param("user") User user);
	UserContract findById(@Param("id") long id);
}
