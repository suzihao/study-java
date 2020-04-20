package com.company.concurrent.c_025;

import java.util.concurrent.LinkedTransferQueue;

/**
 * transfer队列 马上消费的队列(也即容量为0)
 */
public class T08_TransferQueue {

    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //tansfer使用环境:消费者先启动 生产者生产后先找有无消费者，若有-直接-交给消费者，不进队列。若没有消费者则阻塞（必须要处理掉）
        queue.transfer("aaa");

        /*new Thread(() -> {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

}
