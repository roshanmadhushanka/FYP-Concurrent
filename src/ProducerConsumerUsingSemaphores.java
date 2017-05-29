import java.util.concurrent.Semaphore;

/**
 * Created by Roshan on 5/28/2017.
 */
public class ProducerConsumerUsingSemaphores {
    static Semaphore semaphore = new Semaphore(4);

    static class PC {
        private String name;
        public PC(String name){
            this.name = name;
        }


    }
}
