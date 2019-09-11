package io.cheny.linkedlist;

import java.util.Stack;

/**
 * 演示栈 Stack 的基本使用 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2019/9/11
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        // 入栈
        stack.push("jack");
        stack.push("tom");
        stack.push("smith");

        // 出栈
        // smith,Tom,jack
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}
