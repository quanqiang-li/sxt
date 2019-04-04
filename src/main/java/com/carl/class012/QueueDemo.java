package com.carl.class012;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class QueueDemo {

	public static void main(String[] args) {
		ArrayBlockingQueue<String> abq = new ArrayBlockingQueue(2);
		abq.add("a");
		abq.add("b");
		abq.add("c");
		abq.offer("d");
		LinkedBlockingDeque<String> linkedBlockingDeque = new LinkedBlockingDeque();
		linkedBlockingDeque.add("a");
		SynchronousQueue<String> sq = new SynchronousQueue<String>();
		sq.add("a");
		
		PriorityBlockingQueue<String> pbq = new PriorityBlockingQueue<String>();
		pbq.add("a");
	}
}
