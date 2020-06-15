//package lab7;
//
//import org.junit.Test;
////
////import static org.testng.AssertJUnit.assertNull;
//
//public class AVLTreeTest {
//
//	public void initTree(BBST tree) {
//		tree.insert(16,"");
//		tree.insert(8,"");
//		tree.insert(24,"");
//		tree.insert(4,"");
//		tree.insert(12,"");
//		tree.insert(20,"");
//		tree.insert(28,"");
//	}
//
//	@Test
//	public void testLL() {
//		BBST tree = new BBST();
//		tree.insert(5,"");
//		tree.insert(3,"");
//		tree.insert(7,"");
//		assertEquals(tree.toString(), "[5,3,7]");
//		tree.insert(1,"");
//		tree.insert(4,"");
//		tree.insert(0,"");
//		assertEquals(tree.toString(), "[3,1,5,0,^,4,7]");
//	}
//
//	private void assertEquals(String toString, String s) {
//	}
//
//	@Test
//	public void testRR() {
//		BBST tree = new BBST();
//		tree.insert(5,"");
//		tree.insert(3,"");
//		tree.insert(7,"");
//		assertEquals(tree.toString(), "[5,3,7]");
//		tree.insert(8,"");
//		tree.insert(6,"");
//		tree.insert(9,"");
//		assertEquals(tree.format(), "[[7 6],[5 3],[8 2],[3 1],[6 1],^,[9 1]]");
//	}
//
//	@Test
//	public void testLR() {
//		BBST tree = new BBST();
//		initTree(tree);
//		tree.insert(2,"");
//		tree.insert(5,"");
//		tree.insert(29,"");
//		tree.insert(6, "");
//		assertEquals(tree.format(), "[[16 11],[5 6],[24 4],[4 2],[8 3],[20 1],[28 2],[2 1],^,[6 1],[12 1],^,^,^,[29 1]]");
//	}
//
//	@Test
//	public void testRL() {
//		BBST tree = new BBST();
//		initTree(tree);
//		tree.insert(2,"");
//		tree.insert(27,"");
//		tree.insert(29,"");
//		tree.insert(26, "");
//		assertEquals(tree.format(), "[[16 11],[8 4],[27 6],[4 2],[12 1],[24 3],[28 2],[2 1],^,^,^,[20 1],[26 1],^,[29 1]]");
//	}
//
//	@Test
//	public void testRemove() {
//		BBST tree = new BBST();
//		initTree(tree);
//		tree.insert(5,"");
//		tree.remove(24);
//		tree.remove(28);
//		assertEquals(tree.format(), "[[8 6],[4 2],[16 3],^,[5 1],[12 1],[20 1]]");
//
//		tree = new BBST();
//		initTree(tree);
//		tree.insert(5,"");
//		tree.remove(20);
//		tree.remove(28);
//		assertEquals(tree.format(), "[[8 6],[4 2],[16 3],^,[5 1],[12 1],[24 1]]");
//
//		tree = new BBST();
//		initTree(tree);
//		tree.insert(9,"");
//		tree.remove(24);
//		tree.remove(28);
//		assertEquals(tree.format(), "[[12 6],[8 3],[16 2],[4 1],[9 1],^,[20 1]]");
//	}
//
//	@Test
//	public void testRemove2() {
//		BBST tree = new BBST();
//		initTree(tree);
//		tree.insert(27,"");
//		tree.remove(4);
//		tree.remove(12);
//		assertEquals(tree.format(), "[[24 6],[16 3],[28 2],[8 1],[20 1],[27 1]]");
//
//		tree = new BBST();
//		initTree(tree);
//		tree.insert(27,"");
//		tree.remove(8);
//		tree.remove(12);
//		assertEquals(tree.format(), "[[24 6],[16 3],[28 2],[4 1],[20 1],[27 1]]");
//
//		tree = new BBST();
//		initTree(tree);
//		tree.insert(21,"");
//		tree.remove(8);
//		tree.remove(4);
//		assertEquals(tree.format(), "[[20 6],[16 2],[24 3],[12 1],^,[21 1],[28 1]]");
//	}
//
//	@Test
//	public void testRemove_case1() {
//		BBST tree = new BBST();
//		tree.insert(10,"");
//		tree.remove(10);
//		assertEquals(tree.format(), "[]");
//
//		tree.insert(9,"");
//		tree.remove(10);
//		assertEquals(tree.format(), "[[9 1]]");
//
//		tree.insert(10,"");
//		tree.remove(10);
//		assertEquals(tree.format(), "[[9 1]]");
//
//		tree = new BBST();
//		tree.insert(10, 8, 11);
//		tree.remove(8);
//		assertEquals(tree.format(), "[[10 2],^,[11 1]]");
//
//		tree.insert(8, 7);
//		tree.remove(7);
//		assertEquals(tree.format(), "[[10 3],[8 1],[11 1]]");
//
//		tree.insert(9);
//		tree.remove(9);
//		assertEquals(tree.format(), "[[10 3],[8 1],[11 1]]");
//	}
//
//	@Test
//	public void testRemove_case2() {
//		BBST t = new BBST();
//		initTree(t);
//		t.remove(16);
//		assertEquals(t.format(), "[[12 6],[8 2],[24 3],[4 1],^,[20 1],[28 1]]");
//		t.remove(12);
//		assertEquals(t.format(), "[[8 5],[4 1],[24 3],^,^,[20 1],[28 1]]");
//
//		t = new BBST();
//		initTree(t);
//		t.insert(1);
//		t.remove(12);
//		assertEquals(t.format(), "[[16 7],[4 3],[24 3],[1 1],[8 1],[20 1],[28 1]]");
//		assertEquals(t.root.left.parent, t.root);
//
//	}
//
////	@Test
////	public void testFindKSmallest() {
////		BBST tree = new BBST();
////		initTree(tree);
////		assertEquals(tree.findKSmallest(1).key, Integer.valueOf(4));
////		assertEquals(tree.findKSmallest(2).key, Integer.valueOf(8));
////		assertEquals(tree.findKSmallest(3).key, Integer.valueOf(12));
////		assertEquals(tree.findKSmallest(4).key, Integer.valueOf(16));
////		assertEquals(tree.findKSmallest(5).key, Integer.valueOf(20));
////		assertEquals(tree.findKSmallest(6).key, Integer.valueOf(24));
////		assertEquals(tree.findKSmallest(7).key, Integer.valueOf(28));
////	}
////
////	@Test
////	public void testFindKSmallest2() {
////		BBST tree = new BBST();
////		assertNull(tree.findKSmallest(1));
////		tree.insert(10,"");
////		assertEquals(tree.findKSmallest(1).key, Integer.valueOf(10));
////		assertEquals(tree.findKSmallest(2).key, Integer.valueOf(10));
////	}
//}