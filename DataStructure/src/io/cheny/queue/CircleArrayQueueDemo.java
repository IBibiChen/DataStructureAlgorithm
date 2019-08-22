package io.cheny.queue;

import java.util.Scanner;

/**
 * 数组模拟环形队列 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2019/8/22
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        // 创建一个队列，设置为 4，队列的有效数据最大是 3
        CircleArrayQueue queue = new CircleArrayQueue(4);
        // 接受用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("v(variable)：查看当前内存变量的值");
            // 接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int head = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'v':
                    queue.showVariableState();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }
        System.out.println("程序退出");

    }

}

class CircleArrayQueue {
    /**
     * 数组的最大容量
     */
    private int maxSize;

    /**
     * front 变量的含义做一个调整，front 就指向队列的第一个元素
     * front 的初始值 = 0
     * 队列头
     */
    private int front;

    /**
     * rear 变量的含义做一个调整，rear 指向队列的最后一个元素的后一个位置，因为希望空出一个空间作为约定
     * rear 的初始值 = 0
     * 队列尾
     */
    private int rear;

    /**
     * 存放数据，模拟队列
     */
    private int[] arr;

    /**
     * 创建队列的构造器
     *
     * @param arrMaxSize
     */
    public CircleArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据到队列
     */
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能添加");
            return;
        }
        // 直接将数据加入
        arr[rear] = n;
        // 让 rear 后移，必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    /**
     * 获取队列的数据，出队列
     */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        // 这里需要分析出 front 是指向队列的第一个元素
        // 1. 先把 front 对应的值保存到一个临时变量
        // 2. 将 front 后移，考虑取模
        // 3. 将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    /**
     * 显示队列的所有数据
     */
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        // 思路：从 front 开始遍历，遍历多少个元素

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 求出当前队列有效数据的个数
     *
     * @return
     */
    public int size() {
        // rear = 1
        // front = 0
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 显示队列的头数据，注意不是取出数据
     */
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

    /**
     * 显示当前内存变量的值
     */
    public void showVariableState() {
        System.out.printf("front = %d，rear = %d", front, rear);
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }
}
