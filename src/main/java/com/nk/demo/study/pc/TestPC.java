package com.nk.demo.study.pc;

/**
 * 测试生产者消费者
 */
public class TestPC {
    public static void main(String[] args) {
        Container container = new Container();
        new Thread(new Producer(container)).start();
        new Thread(new Consumer(container)).start();
    }
}


class Consumer implements Runnable {
    private Container container;
    public Consumer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Chicken pop = container.pop();

        }
    }
}


class Producer implements Runnable {
    private Container container;
    public Producer(Container container) {
        this.container = container;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了：" + i);
        }
    }
}


class Chicken{
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}


class Container{
    private Chicken[] chickens = new Chicken[10];

    private int count = 0;
    public synchronized void push(Chicken chicken){
        if (count >= chickens.length){
            System.out.println("chicken length is: " + chickens.length);
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        chickens[count] = chicken;
        count++;
        this.notifyAll();
    }

    public synchronized Chicken pop(){
        if (count <= 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        Chicken chicken = chickens[count];
        System.out.println("消费了：" + chicken.id);
        this.notifyAll();
        return chicken;
    }



}
