package com.wave;
import com.wave.cache.TradeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Json on 2016/12/13.
 */
@Component
public class WebInitialConfig implements CommandLineRunner {
    @Autowired
    TradeCache tradeCache;
    @Override
    public void run(String... strings) throws Exception {
        tradeCache.init();

	//other initial work
	//test git push
    }
}
