/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demothreadunsafe;

import jdk.nashorn.internal.runtime.regexp.joni.constants.Arguments;

/**
 *
 * @author andre
 */
public class DemoThreadUnsafe {
    static int x =  10 ;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // create object of unsafe counter
        ThreadUnsafeCounter badCounter = new ThreadUnsafeCounter();

        // setup thread1 to increment the badCounter 200 times
        Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    badCounter.increment();
                    try {
                        Thread.sleep(x);
                    } catch (Exception e) {
                    }
                }
            }
        });

        // setup thread2 to decrement the badCounter 200 times
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    badCounter.decrement();
                    try {
                        Thread.sleep(x);
                    } catch (Exception e) {
                    }
                }
            }
        });
        
        // run both threads
        thread1.start();
        thread2.start();

        // wait for t1 and t2 to complete.
        try {
            thread1.join();
            thread2.join();
        } catch (Exception e) {
        }
        // print final value of counter
        badCounter.printFinalCounterValue();
    }    
}
