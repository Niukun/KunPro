package com.nk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池配置
 * @Author: zc
 * @Date: 2025/5/12 16:20
 **/

public class ThreadPoolConfig {


    /** 核心线程池大小 */
    private int corePoolSize = 10;
    /** 最大可创建的线程数 */
    private int maxPoolSize = 10;
    /** 队列最大长度 */
    private int queueCapacity = 100000;
    /** 线程池维护线程所允许的空闲时间 */
    private int keepAliveSeconds = 30000;

    @Bean(name = "fixThreadPoolExecutor")
    public ThreadPoolTaskExecutor fixThreadPoolExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(maxPoolSize);
        executor.setCorePoolSize(corePoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        return executor;
    }

}
