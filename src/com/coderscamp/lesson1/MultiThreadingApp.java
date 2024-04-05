 package com.coderscamp.lesson1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThreadingApp {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		String message = "Starting";
		System.out.println(message);
		System.out.println("Thread-" + Thread.currentThread().getName());
//			
//		}//newFixedThreadPool() use for cpu operations = newCachedThreadPool() use for non cpu operations like IO
		ExecutorService cpuBoundTask = Executors.newSingleThreadExecutor();
		ExecutorService ioBoundTask = Executors.newCachedThreadPool();
		
		for (int i=0; i<20; i++) {
			CompletableFuture.supplyAsync(() -> new SomeTask(), ioBoundTask)
							 .thenApplyAsync(someTask -> someTask.doSomeWork(),cpuBoundTask)//this is the value
							 .thenApplyAsync(someTask -> someTask.markComplete(), ioBoundTask)// this is the boolean T/F
							 .thenAcceptAsync(dto -> System.out.println(dto), ioBoundTask);//this print the state of the boolean
			
		} 
		 
		message = "Done";
		System.out.println(message); 

	}
	 
	
}
 

//		for (int i=0; i<5; i++) {
//			SomeTask task = new SomeTask();
////			System.out.println(task);
//			
//			new Thread(task).start();