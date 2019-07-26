package com.example.ccq.springelasticsearch.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 异步操作类
 */
public class CommonThreadPoolUtils {
	
	private static ExecutorService executorService = Executors.newCachedThreadPool();
	
	public static List<String> execTaskes(List<Callable<String>> taskes) {
		List<FutureTask<String>> list = new ArrayList<>();
		taskes.forEach( task -> {
			FutureTask<String> futureTask = new FutureTask<String>(task);
			executorService.submit(futureTask);
			list.add(futureTask);
		});
		List<String> resultList = new ArrayList<>();
		list.forEach(item -> {
			try {
				resultList.add(Objects.toString(item.get()));
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				resultList.add(null);
			}
		});
		return resultList;
	}
}
