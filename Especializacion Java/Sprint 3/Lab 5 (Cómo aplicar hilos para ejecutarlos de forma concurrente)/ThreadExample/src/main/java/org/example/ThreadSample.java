package org.example;

public class ThreadSample implements Runnable{
    private final int  threadNum;

    public ThreadSample(int threadNum){
        this.threadNum = threadNum;
    }

    @Override
    public void run(){
        for(int i = 0; i < 10; i++){
            System.out.println(STR."\{i} from thread: \{threadNum}");
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
