import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roshan on 5/27/2017.
 */
public class PCApp {
    static int i;
    static int j;

    public static void main(String[] args) {
        BlockingQueue<String> bQueue = new LinkedBlockingDeque<>();
        bQueue.add("http://www.divaina.com/");
        bQueue.add("http://www.bbc.co.uk/");
        bQueue.add("https://news.google.com/");
        bQueue.add("https://www.yahoo.com/news/");
        bQueue.add("http://www.lankadeepa.lk/");
        bQueue.add("http://www.divaina.com/");
        bQueue.add("http://www.bbc.co.uk/");
        bQueue.add("https://news.google.com/");
        bQueue.add("https://www.yahoo.com/news/");
        bQueue.add("http://www.lankadeepa.lk/");
        bQueue.add("http://www.divaina.com/");
        bQueue.add("http://www.bbc.co.uk/");
        bQueue.add("https://news.google.com/");
        bQueue.add("https://www.yahoo.com/news/");
        bQueue.add("http://www.lankadeepa.lk/");
        bQueue.add("http://www.divaina.com/");
        bQueue.add("http://www.bbc.co.uk/");
        bQueue.add("https://news.google.com/");
        bQueue.add("https://www.yahoo.com/news/");
        bQueue.add("http://www.lankadeepa.lk/");

        ProducerConsumer.setRowQueue(bQueue);

//        ExecutorService producerPool = Executors.newFixedThreadPool(11);
//        ExecutorService consumerPool = Executors.newFixedThreadPool(11);
//
//        for(i=0; i<5; i++){
//            producerPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        new ProducerConsumer().produce(String.valueOf(i));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        for(j=0; i<2; i++){
//            consumerPool.execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        new ProducerConsumer().consume(String.valueOf(j));
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//
//        producerPool.shutdown();
//        consumerPool.shutdown();

        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new ProducerConsumer().produce("Producer 1");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        // Create producer thread
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new ProducerConsumer().produce("Producer 2");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    new ProducerConsumer().consume("Consumer 1");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });


        t2.start();
        t1.start();
        t3.start();
    }
}
