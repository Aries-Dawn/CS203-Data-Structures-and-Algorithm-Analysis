package lab7;

import java.util.Scanner;

public class A_CompleteBinaryTree {

    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        private TreeNode(int value){
            this.value = value;
        }
    }
    private static Scanner sc = new Scanner(System.in);



    private static boolean isCompleteBinaryTree(){
        TreeNode root = null;
        int n = sc.nextInt();
        TreeNode[] all = new TreeNode[n + 1];
        for (int i = 0;i < all.length;i++){
            TreeNode temp = new TreeNode(i);
            all[i] = temp;
        }
        int [] allNum = new int[n + 1];
        for (int i = 1;i < n + 1;i++){
            allNum[i] = -1;
        }



        for (int i = 1; i < all.length;i++){
            int left = sc.nextInt();
            int right = sc.nextInt();
            if (all[i].right != null && all[i].left != null)
                return false;
            if (all[i].left == null)
                all[i].left = all[left];
            if (all[i].right == null)
                all[i].right = all[right];
            allNum[left] = 0;
            allNum[right] = 0;
        }
        for (int i = 1;i < allNum.length;i++){
            if (allNum[i] == -1){
                root = all[i];
                break;
            }
        }

        TreeNode[] array = storingInArray(root,n);
        return judge(array);

    }

    private static boolean judge(TreeNode[] array){
        if (array == null)
            return false;
        else{
            boolean hasNum = false;
            for (int i = array.length - 1;i > 0;i--){
                if (!hasNum){
                    if (array[i].value == 0)
                        continue;
                    else
                        hasNum = true;
                }
                if (hasNum){
                    if (array[i].value == 0)
                        return false;
                }
            }
            return true;
        }
    }


    private static TreeNode[] storingInArray(TreeNode root,int n){
        TreeNode[] array = new TreeNode[2 * n +  2];
        int num = 1;
        Queue queue = new Queue();
        if (root == null)
            return null;
        queue.enQueue(root);
        TreeNode current;
        array[1] = root;
        while (!queue.isEmpty()){
            current = queue.deQueue();
//            System.out.println(current.value);
            if (current != null && current.left != null){
                queue.enQueue(current.left);
                array[num * 2] = current.left;
            }
            if (current != null && current.right != null){
                queue.enQueue(current.right);
                array[num * 2 + 1] = current.right;
            }
            if (current != null && current.right != null && current.left != null)
                num++;
        }
        return array;

    }

    private static class Node{

        TreeNode node;
        Node next;
        Node pre;

        private Node(TreeNode node){
            this.node = node;
        }
    }

    private static class Queue{
        Node head = new Node(null);
        Node tail = head;



        private void enQueue(TreeNode input){
            Node temp = new Node(input);
            tail.next = temp;
            temp.pre = tail;
            tail = tail.next;

        }

        private TreeNode deQueue(){
            if (!isEmpty()){
                TreeNode temp =  head.next.node;
                head = head.next;
                return temp;
            }
            else
                return null;
        }

        private boolean isEmpty(){
            if (head != null && head.next == null)
                return true;
            else
                return false;
        }
    }


    public static void main(String[] args) {

        int cases = sc.nextInt();
        while (cases > 0){
            boolean key = isCompleteBinaryTree();
            if (key)
                System.out.println("Yes");
            else
                System.out.println("No");
            cases--;
        }

    }





}