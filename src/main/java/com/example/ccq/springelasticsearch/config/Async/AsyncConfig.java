package com.example.ccq.springelasticsearch.config.Async;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ZTY
 * @date 2019/10/11 14:18
 * 描述： 自定义Executor
 * @EnableAsync：通过在配置类或者Main类上加@EnableAsync开启对异步方法的支持。
 * @Async 可以作用在类上或者方法上，作用在类上代表这个类的所有方法都是异步方法。
 */
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    /**
     * ThreadPoolTaskExecutor 常见概念：
     * Core Pool Size : 核心线程数线程数定义了最小可以同时运行的线程数量。
     * Queue Capacity : 当新任务来的时候会先判断当前运行的线程数量是否达到核心线程数，如果达到的话，信任就会被存放在队列中。
     * Maximum Pool Size : 当队列中存放的任务达到队列容量的时候，当前可以同时运行的线程数量变为最大线程数。
     */


    private static final int CORE_POOL_SIZE = 6;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;

    public Executor taskExecutor() {
        //spring 默认配置是核心线程数大小为1，最大线程容量大小不受限制，队列容量也不受限制
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(CORE_POOL_SIZE);
        //最大线程数
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        // 队列大小
        executor.setQueueCapacity(QUEUE_CAPACITY);


        /**
         * 如果当前同时运行的线程数量达到最大线程数量时，ThreadPoolTaskExecutor 定义一些策略:
         * ThreadPoolExecutor.AbortPolicy：抛出 RejectedExecutionException来拒绝新任务的处理。
         * ThreadPoolExecutor.CallerRunsPolicy：调用执行自己的线程运行任务。您不会任务请求。但是这种策略会降低对于新任务提交速度，
         * 影响程序的整体性能。另外，这个策略喜欢增加队列容量。如果您的应用程序可以承受此延迟并且你不能任务丢弃任何一个任务请求的话，
         * 你可以选择这个策略。
         * ThreadPoolExecutor.DiscardPolicy： 不处理新任务，直接丢弃掉。
         * ThreadPoolExecutor.DiscardOldestPolicy：此策略将丢弃最早的未处理的任务请求。
         */
        // 当最大池已满时，此策略保证不会丢失任务请求，但是可能会影响应用程序整体性能。
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程名的前缀
        executor.setThreadNamePrefix("My ThreadPoolTaskExecutor-");
        executor.initialize();
        return executor;
    }


}
