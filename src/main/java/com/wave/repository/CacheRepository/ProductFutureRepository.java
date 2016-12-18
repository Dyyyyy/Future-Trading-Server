package com.wave.repository.CacheRepository;

import com.wave.cache.ProductFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Json on 2016/12/13.
 */
public interface ProductFutureRepository extends JpaRepository<ProductFuture,Long> {
    @Query("select future from ProductFuture future where future.futures_exchange_id = :futures_exchange_id")
    List<ProductFuture> findByExchange(long futures_exchange_id);
}
