import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by Roshan on 5/28/2017.
 */
public class ProducerConsumer1 {
    static BlockingQueue<String> rowQueue = new LinkedBlockingDeque<>();
    static BlockingQueue<Document> productQueue  = new LinkedBlockingDeque<>();
    static int capacity = 15;
    static Object lock = new Object();

    public static void produce(String threadId) throws InterruptedException, IOException {
        while (true){
            String link = "";
            Document doc = null;

            synchronized (lock){
                if(rowQueue.isEmpty()){
                    System.out.println("Row queue is empty");
                    lock.wait();
                }

                if(productQueue.size() < capacity) {
                    link = rowQueue.take();
                } else {
                    System.out.println(threadId + " Product queue is full : items " + String.valueOf(productQueue.size()));
                    lock.wait();
                }
            }

            doc = Jsoup.connect(link).get();

            synchronized (lock){
                if(doc != null){
                    productQueue.add(doc);
                    System.out.println(threadId + " Produce : " + link);
                    lock.notify();
                }
            }
        }
    }

    public static void consumer(String threadId) throws InterruptedException {
        while (true){
            synchronized (lock){
                while (productQueue.isEmpty()){
                    System.out.println(threadId + " Product queue is empty");
                    lock.wait();
                }

                Document doc = productQueue.take();
                System.out.println(threadId + " Consuming");

                lock.notify();
            }
        }
    }

    public static void setRowQueue(BlockingQueue<String> queue){
        synchronized (lock){
            rowQueue = queue;
            lock.notify();
        }
    }
}
