package com.wave.repository.EntityRepository;

import com.wave.model.TradeRecord;
import com.wave.model.User;
import com.wave.model.UserContract;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Json on 2016/12/11.
 */
public interface TradeRecordRepository extends JpaRepository<TradeRecord,Long>{
	ArrayList<TradeRecord> findByHandsId(@Param("handsId") long handsId);
	ArrayList<TradeRecord> findByUser(@Param("user") User user);
}
