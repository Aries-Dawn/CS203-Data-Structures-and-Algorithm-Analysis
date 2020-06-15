//package lab7;
//
//public class AVLTree_test {
//	public static void main(String[] args) {
//		var bbst = new BBST();
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.insert(3, "D");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.insert(0, "A");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.insert(2, "C");
//		bbst.insert(1, "B");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.insert(5, "F");
//		bbst.insert(6, "G");
//		bbst.insert(4, "E");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//
//		System.out.println("pre of 2: " + bbst.findPre(2));         // 1
//		System.out.println("post of 2: " + bbst.findPost(2));       // 3
//		System.out.println("find 1: " + bbst.find(1));              // 1,B
//		System.out.println("find 5: " + bbst.find(5));              // 5,F
//		System.out.println("find 1th large: " + bbst.findKLargest(1));  // G
//		System.out.println("find 2th large: " + bbst.findKLargest(2));  // F
//		System.out.println("find 3th large: " + bbst.findKLargest(3));  // E
//		System.out.println("find 3th large: " + bbst.findKLargest(4));  // D
//		System.out.println("find 7th large: " + bbst.findKLargest(7));  // A
//		System.out.println("find 8th large: " + bbst.findKLargest(8));  // A
//
//		System.out.println();
//
//		System.out.println("will delete: " + bbst.getMin());
//		bbst.deleteMin();
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		System.out.println("will delete: " + bbst.getMin());
//		bbst.deleteMin();
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		System.out.println("will delete: " + bbst.getMin());
//		bbst.deleteMin();
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//
//		System.out.println();
//
//		System.out.println();
//
//		bbst = new AVLTree<>();
//		bbst.insert(4, "");
//		bbst.insert(1, "");
//		bbst.insert(0, "");
//		bbst.insert(2, "");
//		bbst.insert(3, "");
//		bbst.insert(7, "");
//		bbst.insert(5, "");
//		bbst.insert(6, "");
//		bbst.insert(9, "");
//		bbst.insert(8, "");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.delete(9);
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.delete(8);
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
////		bbst.delete(6);
////		bbst.delete(7);
////		bbst.delete(0);
////		bbst.delete(3);
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		System.out.println(bbst.findKSmallest(1));
//		System.out.println(bbst.findKSmallest(2));
//		System.out.println(bbst.findKSmallest(3));
//		System.out.println(bbst.findKSmallest(5));
//		System.out.println(bbst.findKSmallest(6));
//
//
//		System.out.println();
//		System.out.println();
//
//		bbst = new AVLTree<>();
//		bbst.insert(-3, "");
//		bbst.insert(-6, "");
//		bbst.insert(3, "");
//		bbst.insert(1, "");
//		bbst.insert(7, "");
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//		bbst.delete(-3);
//		System.out.println(bbst + ",size:" + bbst.size() + ",height:" + bbst.height());
//	}
//}
