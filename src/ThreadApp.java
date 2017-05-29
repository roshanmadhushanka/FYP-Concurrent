import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Created by Roshan on 5/2/2017.
 */
public class ThreadApp extends Thread{
    static long startTime;
    static BlockingQueue<String> bQueue;
    static BlockingQueue<Document> processQueue;

    public static void populateLinks(){
        bQueue = new LinkedBlockingDeque<>();
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
    }

    public static void crawl(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        processQueue.add(doc);
        System.out.println("Complete : " + link);
        long endTime = System.nanoTime();
        System.out.println("Took "+(endTime - startTime) / 1000000 + " ms");
    }

    public static void process(Document doc){
//        doc.
    }

    public static void withoutThreads() throws InterruptedException {
        System.out.println("Without threads");
        populateLinks();

        startTime = System.nanoTime();
        while (!bQueue.isEmpty()){
            try {
                crawl(bQueue.take());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void withThreads(){
        System.out.println("With threads");
        populateLinks();
        ExecutorService executorService = Executors.newFixedThreadPool(11);

        startTime = System.nanoTime();
        for(int i=0; i<8; i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        try {
                            while (!bQueue.isEmpty()){
                                crawl(bQueue.take());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        processQueue = new LinkedBlockingDeque<>();
//        withoutThreads();
        withThreads();
    }
}
