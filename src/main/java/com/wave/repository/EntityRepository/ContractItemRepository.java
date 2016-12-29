package com.wave.repository.EntityRepository;

import com.wave.model.ContractItem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Json on 2016/12/11.
 */
public interface ContractItemRepository extends JpaRepository<ContractItem,Long>{
	 @Query("select c from ContractItem c where c.contractObject = :contractObject")
    List<ContractItem> findByContractObject(@Param("contractObject") String contractObject);
    ContractItem findBysecShortName(@Param("secShortName") String secShortName);
    ContractItem findByTicker(@Param("ticker") String ticker);
    ContractItem findById(@Param("id") long id);
}
