package indi.andy.structs.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skiplist {

    //跳表节点对象
    private class Node {
        int val;

        Node right, down;

        Node(int val, Node right, Node down) {
            this.val = val;
            this.right = right;
            this.down = down;
        }
    }

    // 最大层数
    private int max;

    //最左上角的头结点
    private Node head;
    //所有头结点集合，也可以使用数组，这里为了代码简单使用List，注意所有的头结点均为虚拟节点
    private List<Node> heads;
    //随机数不解释
    private Random random;

    //构造函数初始化
    public Skiplist() {
        this.max = 64;
        this.head = new Node(0, null, null);
        this.heads = new ArrayList<Node>(this.max);
        this.heads.add(head);
        this.random = new Random();
    }


    //因为头结点为虚拟节点，所以使用do-while循环，从左到右、从上到下
    //右侧节点不为空，再分为小于目标、大于目标、等于目标三种情况处理
    public boolean search(int target) {
        Node node = head;
        do {
            if (node.right != null) {
                if (node.right.val < target) {
                    node = node.right;
                } else if (node.right.val > target) {
                    node = node.down;
                } else {
                    return true;
                }
            } else {
                node = node.down;
            }
        } while (node != null);
        return false;
    }
    //思路与search类似，先找到最下层的插入位置插入目标
    //再根据最大层数和随机概率选择是否复制最后一层
    //随机概率小于二的当前层数次方分之一才复制
    //复制是最耗时间的，但是却能提升以后操作的速度
    //所以层数越多，越要减少复制的次数
    //尝试过恒定二分之一、当前层数分之一、二的当前层数次方分之一
    //最后一种方式速度表现的效果最好
    public void add(int num) {
        Node node = head;
        do {
            if (node.right != null) {
                if (node.right.val <= num) {
                    node = node.right;
                } else if (node.down != null) {
                    node = node.down;
                } else {
                    break;
                }
            } else {
                if (node.down != null) {
                    node = node.down;
                } else {
                    break;
                }
            }
        } while (node != null);
        node.right = new Node(num, node.right, null);
        if (this.heads.size() < this.max && random.nextFloat() < 1f / Math.pow(2,this.heads.size())) {
            Node up = this.heads.get(this.heads.size() - 1);
            Node downHead = new Node(0, null, null);
            Node down = downHead;
            while (up != null) {
                up.down = down;
                if (up.right != null) {
                    down.right = new Node(up.right.val, null, null);
                }
                up = up.right;
                down = down.right;
            }
            this.heads.add(downHead);
        }
    }
    //代码基本与search相同，唯一不同是search找到第一个后即结束
    //erase要把整个表中的每一层找完才能结束
    public boolean erase(int num) {
        Node node = head;
        boolean exits = false;
        do {
            if (node.right != null) {
                if (node.right.val < num) {
                    node = node.right;
                } else if (node.right.val > num) {
                    node = node.down;
                } else {
                    node.right = node.right.right;
                    node = node.down;
                    exits = true;
                }
            } else {
                node = node.down;
            }
        } while (node != null);
        return exits;
    }


    public static void main(String[] args){
        Skiplist skiplist = new Skiplist();
        skiplist.add(1);
        System.out.println(skiplist);
    }

}
