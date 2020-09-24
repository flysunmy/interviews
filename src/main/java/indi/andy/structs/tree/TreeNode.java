package indi.andy.structs.tree;

import java.util.*;

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



    /***
     *
     * @Method : symmetry
     * @Description : 转换成轴对称二叉树
     * @param root :
     * @return : indi.andy.structs.tree.TreeNode
     * @author : wuzichao
     * @CreateDate : 2020-09-21 18:08:24
     *
     */
    static TreeNode symmetry(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        if (root.left != null) {
            symmetry(root.left);
        }
        if (root.right != null) {
            symmetry(root.right);
        }
        return root;
    }


    /***
     *
     * @Method : createBST
     * @Description : 有序数组构建二叉排序树   bst中序遍历是有序的
     * @param start :
     * @param end :
     * @return : indi.andy.structs.tree.TreeNode
     * @author : wuzichao
     * @CreateDate : 2020-09-22 11:18:04
     *
     */
    static TreeNode createBST(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0 || end < start) {
            return null;
        }
        int mid = (start + end) >> 1;
        TreeNode node = new TreeNode(arr[mid]);
        node.left = createBST(arr, start, mid - 1);
        node.right = createBST(arr, mid + 1, end);
        return node;
    }

    /***
     *
     * @Method : validateBST
     * @Description :  验证二叉树
     * @param root :
     * @return : boolean
     * @author : wuzichao
     * @CreateDate : 2020-09-22 11:31:35
     *
     */
    static boolean validateBST(TreeNode root) {
        if (root == null) {
            return false;
        }

        // 二叉搜索树的中序遍历是一个递增序列，所以我们只需要把这个中序遍历保存下来，然后判断这是个递增序列即可
        ArrayList<Integer> list = new ArrayList<>();
        midOrderToList(root, list);

        if (list == null || list.size() == 0) {
            return false;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }

        return true;

    }

    static void midOrderToList(TreeNode root, List<Integer> list) {

        if (root == null) {
            return ;
        }
        if (root.left != null) {
            midOrderToList(root.left, list);
        }
        list.add(root.value);
        if (root.right != null) {
            midOrderToList(root.right, list);
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

     /*   levelOrder(root);
        TreeNode symmetry = symmetry(root);
        System.out.println();
        levelOrder(symmetry);*/


        int[] arr = {4, 6, 8, 10,12,14,16};
        TreeNode bst = createBST(arr, 0, arr.length - 1);

        System.out.println(validateBST(bst));
        System.out.println(validateBST(root));
    }


}
