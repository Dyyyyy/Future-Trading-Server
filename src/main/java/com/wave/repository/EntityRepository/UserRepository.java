package com.wave.repository.EntityRepository;

import com.wave.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by Json on 2016/12/5.
 */
@Transactional
public interface UserRepository extends JpaRepository<User,Long>{
    @Query("select u from User u where u.phone_number = :phone_number")
    User findByPhoneNumber(String phone_number);
    User findByEmail(String email);
}
