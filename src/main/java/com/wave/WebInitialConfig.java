package com.wave;

import com.wave.cache.TradeCache;
import org.springframework.boot.CommandLineRunner;

/**
 * Created by Json on 2016/12/13.
 */
public class WebInitialConfig implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        TradeCache.getInstance().init();
    }
}
