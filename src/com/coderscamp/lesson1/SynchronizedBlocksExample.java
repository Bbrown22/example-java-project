package com.coderscamp.lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class SynchronizedBlocksExample {
	private AtomicInteger j = new AtomicInteger(0);
	
	@Test
	public void example () {
		List<CompletableFuture<Integer>> tasks = new ArrayList<>();
		
		ExecutorService pool = Executors.newCachedThreadPool();
		for ( int i=0; i <10000; i++) {
			CompletableFuture<Integer> task = CompletableFuture.supplyAsync(() -> incremrntJ(), pool);
			tasks.add(task);
			 
		}
		while (tasks.stream().filter(CompletableFuture::isDone).count() < 10000) {
			System.out.println("Number of complete threads:"
					+ tasks.stream().filter(CompletableFuture::isDone).count() + "!");
		}
		System.out.println("Number of complete threads:"
				+ tasks.stream().filter(CompletableFuture::isDone).count() + "!")
		outputJ();
	}
	private Integer incremrntJ() {
		try { Thread.sleep(500);} catch (InterruptedException e) {}
		synchronized (j) {
			j.set(j.get() + 1);
			return j.get();
		}
		
	} 
	private void outputJ() {
		System.out.println(j);
	}

}
