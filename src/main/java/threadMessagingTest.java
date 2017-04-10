/**
 * Created by Kyle on 4/7/2017.
 */
public class threadMessagingTest {

public static void main(String[] args) {
        final Object lock = new Object();
        final Object lock2 = new Object();
        Thread tigerIsland = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lock) {
                        while(true) {
                            System.out.println("Hello World");
                            wait();
                        }
                    }

                }catch (InterruptedException e){}



            }
        });

        Thread serverTest = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    synchronized (lock) {
                        while(true) {
                            System.out.println("My name is Kyle");
                            wait();
                        }
                    }

                }catch (InterruptedException e){}



            }
        });
        tigerIsland.start();
        serverTest.start();

        for(int i = 0; i < 3 ; i++){
            synchronized (lock){
                System.out.println("in main");
                lock.notify();
            }
//           tigerIsland.holdsLock(lock);

        }

    }
}


