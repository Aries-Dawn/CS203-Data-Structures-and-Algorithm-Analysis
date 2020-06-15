package lab7;

public class BBST {

//    private Comparator<Integer> cmp;
    private static TreeNode root = null;

    private static class TreeNode{
        int value;
        int high;
        int size;
        TreeNode left;
        TreeNode right;

        TreeNode(int value){
            this.value = value;
        }

        TreeNode(int value,TreeNode left,TreeNode right){
            this.value = value;
            this.left = left;
            this.right = right;
            high = 0;
        }
    }

    void makeEmpty(){
        root = null;
    }

    private boolean isEmpty(){
        return root == null;
    }

    void insert(int added){
        root = insert(added,root);
    }

//    boolean contains(int key){
//        return contains(key,root);
//    }

    void remove(int re){
        root = remove(re,root);
    }

//    private int myCompare(int lhs,int rhs){
//        if(cmp != null)
//            return cmp.compare(lhs,rhs);
//        else
//            return ((Comparable)lhs).compareTo(rhs);
//
//    }
//
//    private boolean contains(int key,TreeNode t){
//        if (t == null)
//            return false;
//        int compareResult = myCompare(key, t.value);
//        if(compareResult < 0)
//            return contains(key, t.left);
//        else if(compareResult > 0)
//            return contains(key, t.right);
//        else
//            return true;
//    }

    private int high(TreeNode treeNode){
        return treeNode == null ? -1 : treeNode.high;
    }

    private int size(TreeNode treeNode){
        return treeNode == null ? -1 : treeNode.size;
    }

    private TreeNode findMin(TreeNode t){
        if (t == null)
            return null;
        if (t.left == null)
            return t;
        return findMin(t.left);
    }

    private TreeNode findMax(TreeNode t){
        if (t == null)
            return null;
        if (t.right == null)
            return t;
        return findMax(t.right);
    }

    private TreeNode insert(int x,TreeNode t){
        if(t == null)
            return t = new TreeNode(x);
        else if (x < t.value){
            t.left = insert(x,t.left);

            if (high(t.left) - high(t.right) == 2){

                if (x < t.left.value){
                    t = LL(t);
                }
                else{
                    t = LR(t);
                }
            }
        }else if (x > t.value){
            t.right = insert(x,t.right);

            if (high(t.right) - high(t.left) == 2){

                if (x < t.right.value)
                    t = RL(t);
                else
                    t = RR(t);
            }
        }
        else ;
        t.high = Math.max(high(t.left),high(t.right)) + 1;
        t.size = size(t.left) + size(t.right) + 1;

        return t;


    }

    void printTree(){
        printTree(root);
    }

    private void printTree(TreeNode t) {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.value );
            printTree( t.right );
        }
    }

    private TreeNode remove(int x,TreeNode t){
        if (t == null)
            return null;


        if (x < t.value){
            t.left = remove(x,t.left);

            if (high(t.right) - high(t.left) == 2){
                TreeNode currentNode = t.right;

                if (high(currentNode.left) > high(currentNode.right))
                    t = RL(t);
                else
                    t = RR(t);

            }
        }
        else if (x > t.value){
            t.right = remove(x,t.right);

            if (high(t.left) - high(t.right) == 2){
                TreeNode currentNode = t.left;

                if (high(currentNode.right) > high(currentNode.left))
                    t = LR(t);
                else
                    t = LL(t);
            }
        }
        else if (t.right != null && t.left != null){

            t.value = findMin(t.right).value;

            t.right = remove(t.value,t.right);
        }
        else
            t = (t.left != null) ? t.left : t.right;


        if (t != null) {
            t.high = Math.max(high(t.left), high(t.right)) + 1;
            t.size = size(t.left) + size(t.right) + 1;
        }
        return t;
    }

    private TreeNode LL (TreeNode x){
        TreeNode w = x.left;
        x.left = w.right;
        w.right = x;

        x.high = Math.max(high(x.left),high(x.right)) + 1;
        x.size = size(x.left) + size(x.right) + 1;
        w.high = Math.max(high(w.left),x.high) + 1;
        w.size = size(w.left) + x.size + 1;
        return w;
    }

    private TreeNode RR (TreeNode w){
        TreeNode x = w.right;

        w.right = x.left;
        x.left = w;

        x.high = Math.max(high(x.left),high(x.right));
        x.size = size(x.left) + size(x.right) + 1;
        w.high = Math.max(high(w.left),high(w.right));
        w.size = size(w.left) + size(w.right) + 1;

        return x;
    }

    private TreeNode LR (TreeNode x){
//        if (x.left.right == null)
//            System.out.println(0);
        x.left = RR(x.left);

        return LL(x);
    }

    private TreeNode RL (TreeNode x){
        x.right = LL(x.right);

        return RR(x);
    }

}
