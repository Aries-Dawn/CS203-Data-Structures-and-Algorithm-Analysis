package lab7;

import java.util.Scanner;

public class B_Judgement {

    static class TreeNode{
        int value;
        TreeNode parent;
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
        all[0] = new TreeNode(0);
        for (int i = 1;i < all.length;i++){
            TreeNode temp = new TreeNode(sc.nextInt());
            all[i] = temp;
        }
//        int [] allNum = new int[n + 1];
//        for (int i = 1;i < n + 1;i++){
//            allNum[i] = -1;
//        }

        int[][] input = new int[n][2];
        //first one is father;second one is son
        for (int i = 1;i < n;i++){
            input[i][0] = sc.nextInt();
            input[i][1] = sc.nextInt();
        }


        for (int i = 1; i < input.length;i++){
            int father = input[i][0];
            int son = input[i][1];
            if (all[father].right != null && all[father].left != null)
                return false;
            if (all[father].left == null)
                all[father].left = all[son];
            else if (all[father].right == null)
                all[father].right = all[son];
            all[son].parent = all[father];

//            allNum[left] = 0;
//            allNum[right] = 0;
        }

        for (int i = 1;i < all.length;i++) {
            if (all[i].parent == null) {
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
                if (array[i].value == 0)
                    return false;
            }

            for (int i = 1;i < (array.length - 2) / 2;i++){
                if ((array[i].value >= array[2 * i].value && array[i].value >= array[i * 2 + 1].value) || (array[i].value <= array[i * 2].value && (array[i * 2 + 1].value == 0 ||array[i].value <= array[i * 2 + 1].value))) {
                }
                else
                    return false;




            }
            return true;
        }
    }

    private static TreeNode[] storingInArray(TreeNode root,int n){
        TreeNode[] array = new TreeNode[2 * n +  2];
        for (int i = 0;i < array.length;i++){
            array[i] = new TreeNode(0);
        }
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
//            if (current != null && current.right != null && current.left != null)
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
                head.node = null;
                head.pre = null;
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
        for (int i = 1;i <= cases; i++){
            boolean key = isCompleteBinaryTree();
            if (key)
                System.out.println("Case #" + i + ": YES");
            else
                System.out.println("Case #" + i + ": NO");

        }

    }



}