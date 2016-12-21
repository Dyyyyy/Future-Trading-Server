package com.wave.repository.EntityRepository;

import com.wave.model.UserContract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Json on 2016/12/11.
 */
public interface UserContractRepository extends JpaRepository<UserContract,Long> {
}
