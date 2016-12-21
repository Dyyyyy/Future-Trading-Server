package com.wave.repository.CacheRepository;

import com.wave.cache.FuturesExchange;
import com.wave.cache.ProductFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Json on 2016/12/13.
 */
public interface ProductFutureRepository extends JpaRepository<ProductFuture,Long> {
    @Query("select future from ProductFuture future where future.futures_exchange = :futures_exchange")
    List<ProductFuture> findByExchange(@Param("futures_exchange")FuturesExchange futures_exchange);
}
