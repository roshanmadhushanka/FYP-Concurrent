import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roshan on 5/27/2017.
 */
public class ProducerConsumer {
    private static BlockingQueue<String> rowQueue = new LinkedBlockingDeque<>();
    private static BlockingQueue<Document> productQueue  = new LinkedBlockingDeque<>();
    private volatile int capacity = 15;
    static Object lock = new Object();

    void produce(String threadId) throws InterruptedException, IOException {
        while(true){
            synchronized (lock) {
                while (productQueue.size() == capacity) {
                    System.out.println("Product queue is full");
                    lock.wait();
                }

                while (rowQueue.isEmpty()) {
                    System.out.println("Row queue is empty");
                    lock.wait();
                }

                String link = rowQueue.take();
                Document doc = Jsoup.connect(link).get();

                productQueue.add(doc);
                System.out.println(threadId + " Produce : " + link);
                lock.notifyAll();
            }


        }
    }

    void consume(String threadId) throws InterruptedException {
        while (true){
            synchronized (lock){
                while (productQueue.isEmpty()){
                    lock.wait(1);
                }

                Document doc = productQueue.take();
                System.out.println(threadId + " Consuming");

                lock.notifyAll();
            }
        }
    }

    public static void setRowQueue(BlockingQueue<String> queue){
        rowQueue = queue;
    }
}

/*
public class Threadexample
{
    public static void main(String[] args)
                        throws InterruptedException
    {
        // Object of a class that has both produce()
        // and consume() methods
        final PC pc = new PC();

        // Create producer thread
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pc.produce();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        // Create consumer thread
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    pc.consume();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        // Start both threads
        t1.start();
        t2.start();

        // t1 finishes before t2
        t1.join();
        t2.join();
    }
* */
