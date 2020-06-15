package lab7;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class A {




    static class TreeNode{
        int value;
        TreeNode left;
        TreeNode right;

        private TreeNode(int value){
            this.value = value;
        }
    }



    private static boolean isCompleteBinaryTree() throws IOException {
        TreeNode root = null;
        int n = in.nextInt();
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
            int left = in.nextInt();
            int right = in.nextInt();
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



    static Reader in=new Reader();

    public static void main(String[] args) throws IOException {
        PrintWriter out=new PrintWriter(System.out);





        int cases = in.nextInt();
        while (cases > 0){
            boolean key = isCompleteBinaryTree();
            if (key)
                System.out.println("Yes");
            else
                System.out.println("No");
            cases--;
        }






        out.close();
    }


    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}
