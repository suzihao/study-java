package com.company.concurrent.c_026;

import java.util.concurrent.*;

/**
 * 未来：结果(作为callable的返回值) 拿到一个未来的返回值
 */
public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task = new FutureTask<>(() -> {
            //这里是一个Callable类型，等同于匿名类 new Callable(){Integer call();}
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        //阻塞
        System.out.println(task.get());

        //=======================================================

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        //System.out.println(f.get());
        System.out.println(f.isDone());

        //结束服务
        //service.shutdown();

    }

}
