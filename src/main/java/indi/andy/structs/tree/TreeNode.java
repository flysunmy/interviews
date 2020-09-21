package indi.andy.structs.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }



    /***
     *
     * @Method : preOrderRecursive
     * @Description : 二叉树前序遍历 递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:17:08
     *
     */
    static void preOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " -> ");
        preOrderRecursive(root.left);
        preOrderRecursive(root.right);
    }


    /***
     *
     * @Method : preOrderNoRecursive
     * @Description : 二叉树前序遍历 非递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:16:47
     *
     */
    static void preOrderNoRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode current;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            current = stack.pop();
            System.out.print(current.value + " -> ");
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }


    /***
     *
     * @Method : midOrderRecursive
     * @Description : 二叉树中序遍历 递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:16:34
     *
     */
    static void midOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            midOrderRecursive(root.left);
        }
        System.out.print(root.value + " -> ");
        if (root.right != null) {
            midOrderRecursive(root.right);
        }

    }


    /***
     *
     * @Method : midOrderNoRecursive
     * @Description : 二叉树中序遍历 非递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:16:09
     *
     */
    static void midOrderNoRecursive(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.print(root.value + " -> ");
                root = root.right;
            }
        }

    }



    /***
     *
     * @Method : postOrderRecursive
     * @Description : 二叉树后序遍历 递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:15:29
     *
     */
    static void postOrderRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            postOrderRecursive(root.left);
        }
        if (root.right != null) {
            postOrderRecursive(root.right);
        }
        System.out.print(root.value + " -> ");

    }





    /***
     *
     * @Method : postOrderNoRecursive
     * @Description : 二叉树后序遍历 非递归
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:48:54
     *
     */
    static void postOrderNoRecursive(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            root = s1.pop();
            s2.push(root);
            if (root.left != null) {
                s1.push(root.left);
            }
            if (root.right != null) {
                s1.push(root.right);
            }
        }
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().value + " -> ");
        }
    }


    /***
     *
     * @Method : levelOrder
     * @Description : 二叉树层序遍历
     * @param root :
     * @author : wuzichao
     * @CreateDate : 2020-09-21 15:14:57
     *
     */
    static void levelOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode current;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.print(current.value + " -> ");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }


    /***
     *
     * @Method : height
     * @Description : 二叉树高度
     * @param root :
     * @return : int
     * @author : wuzichao
     * @CreateDate : 2020-09-21 16:10:33
     *
     */
    static int height(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = height(root.left);
            int right = height(root.right);
            return Math.max(left, right) + 1;
        }
    }



    public static void main(String[] args){
        TreeNode root = new TreeNode(10);
        TreeNode l1 = new TreeNode(6);
        TreeNode r1 = new TreeNode(14);
        TreeNode l1l1 = new TreeNode(4);
        TreeNode l1r1 = new TreeNode(8);
        TreeNode r1l1 = new TreeNode(12);
        TreeNode r1r1 = new TreeNode(16);
        root.left = l1;
        root.right = r1;
        l1.left = l1l1;
        l1.right = l1r1;

        r1.left = r1l1;
        r1.right = r1r1;
    }


}