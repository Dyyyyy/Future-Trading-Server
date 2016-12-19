package com.wave.repository.EntityRepository;

import com.wave.model.ContractItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Json on 2016/12/11.
 */
public interface ContractItemRepository extends JpaRepository<ContractItem,Long>{
    List<ContractItem> findByContractObject(String contractObject);
}
