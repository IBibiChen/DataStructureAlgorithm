package io.cheny.linkedlist;

/**
 * 单链表 .
 *
 * @author BibiChen
 * @version v1.0
 * @since 2019/8/23
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        // 进行测试
        // 先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        // 创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        // 加入
        // singleLinkedList.add(hero1);
        // singleLinkedList.add(hero2);
        // singleLinkedList.add(hero4);
        // singleLinkedList.add(hero3);

        // 加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);

        singleLinkedList.list();
        // 测试修改节点的方法
        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后的信息");
        singleLinkedList.list();

        // 显示
        singleLinkedList.list();
    }

}

/**
 * 定义 SingleLikedList 来管理我们的英雄
 */
class SingleLinkedList {
    /**
     * 初始化一个头结点，头结点不要动，不存放具体的数据
     */
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 思路：当不考虑编号的顺序时
     * 1. 找到当前链表的最后节点
     * 2. 将最后节点的 next 指向 新的节点
     *
     * @param node
     */
    public void add(HeroNode node) {
        // 因为 head 节点不能动，所以定义一个辅助变量 temp
        HeroNode temp = head;
        // 变量链表，找到最后的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            // 如果没有找到最后，将 temp 后移
            temp = temp.next;
        }
        // 当退出 while 循环时，temp 就指向了链表的最后
        // 将最后节点的 next 指向 新的节点
        temp.next = node;
    }

    /**
     * 第二种方式在添加英雄时，根据排名将英雄插入到指定位置
     * (如果有这个排名，则添加失败，并给出提示)
     *
     * @param node
     */
    public void addByOrder(HeroNode node) {
        // 因为 head 节点不能动，所以定义一个辅助变量 temp
        HeroNode temp = head;
        // 因为是单链表，我们找的 temp 是位于添加位置的前一个节点，否则添加不了
        // flag 标识添加的编号是否存在，默认为 false
        boolean flag = false;
        while (true) {
            // 说明 temp 已经在链表的最后
            if (temp.next == null) {
                break;
            }
            // 位置找到，就在 temp 的后插入
            if (temp.next.no > node.no) {
                break;
            } else if (temp.next.no.equals(node.no)) {
                flag = true;
                break;
            }
            // 后移，遍历当前链表
            temp = temp.next;
        }
        if (flag) {
            System.out.printf("准备插入的英雄编号 %d 已存在，不能添加\n", node.no);
        } else {
            // 插入到链表中，temp 的后面
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 修改节点的信息，根据编号 no 来修改，即 no 编号不能改
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("链表为空~");
            return;
        }
        // 找到需要修改的节点，根据 no 编号
        // 定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            // 链表已经遍历完了
            if (temp == null) {
                break;
            }
            if (temp.no.equals(newHeroNode.no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 根据 flag 判断是否找到要修改的节点
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }

    }

    /**
     * 显示链表(遍历)
     */
    public void list() {
        // 先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为 head 节点不能动，所以定义一个辅助变量 temp
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            // 输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }

    }

}


/**
 * 定义 HeroNode，每一个 HeroNode 对象就是一个节点
 */
class HeroNode {
    /**
     * 编号
     */
    public Integer no;

    /**
     * 名称
     */
    public String name;

    /**
     * 昵称
     */
    public String nickname;

    /**
     * 指向下一个节点
     */
    public HeroNode next;

    public HeroNode(Integer no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}