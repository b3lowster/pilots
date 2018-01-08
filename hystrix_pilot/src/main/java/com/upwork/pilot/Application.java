package com.upwork.pilot;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.upwork.pilot.model.RequestParams;
import com.upwork.pilot.model.ResponseResult;
import com.upwork.pilot.service.LoggingService;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Vladimir Tsukanov
 */
public class Application {

    private static final int DEFAULT_THREADS_NUMBER = 5;
    private static final int DEFAULT_MAX_RECURSE_LEVEL = 5;
    private static final int DEFAULT_ITERATIONS_NUMBER = 10;
    private static final long DEFAULT_OPERATION_DURATION = 2000;


    public static void main(String[] args) {

        final LoggingService loggingService = new LoggingService();

        ExecutorService executor = Executors.newFixedThreadPool(DEFAULT_THREADS_NUMBER);

        for (int i = 0; i < DEFAULT_ITERATIONS_NUMBER; i++) {
            final Integer indexWrapper = i;
            executor.execute(() -> {
                new HystrixCommand<RequestParams>(setter()) {
                    @Override
                    protected RequestParams run() {
                        boolean recurseParameter = indexWrapper % 2 == 0;
                        RequestParams requestParams = new RequestParams(indexWrapper, recurseParameter,
                                DEFAULT_OPERATION_DURATION,
                                new Random().nextInt(DEFAULT_MAX_RECURSE_LEVEL),
                                ResponseResult.EMPTY);
                        System.out.println(String.format("\n Send %s", requestParams.toString()));
                        RequestParams responseParams = loggingService.log(requestParams);
                        System.out.println(String.format("\n Receive %s", responseParams.toString()));
                        return responseParams;
                    }
                }.execute();
            });
        }
        executor.shutdown();
    }
    private static HystrixCommand.Setter setter() {
        return HystrixCommand.Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("External"));
    }
}
