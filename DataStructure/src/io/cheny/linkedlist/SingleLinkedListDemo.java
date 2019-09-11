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
        // 删除一个节点
        // singleLinkedList.delete(1);
        // singleLinkedList.delete(4);
        // singleLinkedList.delete(2);
        // singleLinkedList.delete(3);
        // System.out.println("删除后的链表情况~~");

        // 显示
        singleLinkedList.list();
        // 测试 求单链表中有效节点的个数
        System.out.println("有效的节点个数 = " + getLength(singleLinkedList.getHead()));
        // 测试 查找单链表中的倒数第 k 个节点
        System.out.println("倒数第 2 个节点 = " + findIndexNode(singleLinkedList.getHead(), 2));
        // 测试单链表的反转
        System.out.println("反转单链表~~");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();
    }

    /**
     * 获取单链表有效节点的个数(如果是带头节点的链表，需求不统计头结点)
     *
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head) {
        HeroNode temp = head.next;
        // 空链表
        if (temp == null) {
            return 0;
        }
        int length = 0;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第 k 个节点【新浪面试题】
     * 1. 编写一个方法，接收 head 节点，同时接收一个 index
     * 2. index 表示是倒数第 index 个节点
     * 3. 先把链表从头到尾遍历，得到链表的总长度 length
     * 4. 得到 length 后，从链表的第一个开始遍历(length - index)个，就可以得到
     * 5. 如果找到了，则返回改节点，否则返回 null
     *
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findIndexNode(HeroNode head, int index) {
        HeroNode temp = head.next;
        // 判断链表如果为空，返回 null
        if (temp == null) {
            return null;
        }
        // 遍历得到链表的长度(节点个数)
        int length = getLength(head);
        // 遍历 length - index 位置，就是倒数的第 k 个节点
        // 校验 index
        if (index < 0 || index > length) {
            return null;
        }
        // 3    3 - 1 = 2
        for (int i = 0; i < length - index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * 单链表的反转
     * 1. 先定义一个 reverseHead
     * 2. 从头到尾遍历原来的链表，每遍历一个节点，就将其取出，并放在新的 reverseHead 的最前端
     * 3. 原来的链表的 head.next = reverseHead.next
     *
     * @param head
     */
    public static void reverseList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        // 定义一个辅助指针(变量)，帮助我们遍历原来的链表
        HeroNode cur = head.next;
        // 指向当前节点 cur 的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            // 先暂时保存当前节点的下一个节点
            next = cur.next;
            // 将 cur 的下一个节点指向新的链表的最前端
            cur.next = reverseHead.next;
            // 将 cur 放到新的链表上
            reverseHead.next = cur;
            // 让 cur 后移
            cur = next;
        }
        head.next = reverseHead.next;
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

    public HeroNode getHead() {
        return head;
    }

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
     * 根据编号删除节点
     * 思路
     * 1. head 不能动，因此需要一个 temp 辅助节点找到待删除节点的前一个节点
     * 2. 在比较时，是 temp.next.no 和 需要删除的节点的 no 比较
     *
     * @param no
     */
    public void delete(Integer no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no.equals(no)) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
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