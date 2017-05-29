import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roshan on 5/28/2017.
 */
public class PCApp1 {
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

        ProducerConsumer1.setRowQueue(bQueue);

        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ProducerConsumer1.produce("Thread 1");
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
                    ProducerConsumer1.produce("Thread 2");
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
                    ProducerConsumer1.produce("Thread 3");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t4 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ProducerConsumer1.consumer("Thread 4");
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
