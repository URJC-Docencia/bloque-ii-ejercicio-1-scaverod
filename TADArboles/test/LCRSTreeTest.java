import material.Position;
import org.junit.*;

import static org.junit.Assert.*;
/**
 * This class is a test class for the LCRSTree class.
 * It includes various test methods to test the functionalities of the LCRSTree class.
 */
public class LCRSTreeTest {



    private LCRSTree<Integer> tree;

    @Before
    public void setUp() throws Exception {
        tree = new LCRSTree<>();
    }

    public void setTree() {

        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);

        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);

        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);

        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);
    }

    @Test
    public void testSize() {
        Position<Integer> p = this.tree.addRoot(100);
        this.tree.add(200, p);
        Position<Integer> h = this.tree.add(300, p);
        this.tree.add(400, h);
        this.tree.add(500, h);
        assertEquals(this.tree.size(), 5);
    }


    @Test
    public void testSize2() {
        this.setTree();
        assertEquals(this.tree.size(), 12);
    }

    @Test
    public void testRoot() {
        this.setTree();
        Integer a = this.tree.root().getElement();
        boolean b = (a == 1);
        assertTrue(b);

    }

    @Test
    public void testIsEmpty() {
        assertTrue(this.tree.isEmpty());
    }

    @Test
    public void testIsEmpty2() {
        Position<Integer> p = this.tree.addRoot(2);
        this.tree.add(3, p);
        assertFalse(this.tree.isEmpty());
    }


/*	public void testParent() {
		this.setTree();

		try {
			Position<Integer> p = this.tree.root();
			this.tree.parent(p);
		} catch (BoundaryViolationException e) {
			assertTrue(true);
		}

	}*/


    @Test
    public void testParent2() {
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);
        assertEquals(p2, tree.parent(p3));
    }

    @Test
    public void testParent3() {
        this.setTree();

        try {
            this.tree.parent(null);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    public void testPositions() {
        Position<Integer> p = this.tree.addRoot(100);
        this.tree.add(200, p);
        this.tree.add(300, p);
        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "100200300");
    }


    @Test
    public void testRemove() {
        Position<Integer> p = this.tree.addRoot(100);
        Position<Integer> q = this.tree.add(200, p);
        Position<Integer> h = this.tree.add(300, p);
        this.tree.add(400, h);
        this.tree.add(500, h);
        this.tree.remove(h);
        assertEquals(this.tree.size(), 2);

    }

    @Test
    public void testRemove2() {
        this.setTree();
        this.tree.remove(this.tree.root());
        assertEquals(this.tree.size(), 0);
    }


    @Test
    public void testRemove3() {
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);

        this.tree.remove(p2);

        StringBuilder s = new StringBuilder();
        for (Position<Integer> pos : this.tree) {
            s.append(pos.getElement());
        }
        assertEquals(s.toString(), "12345");
    }


    @Test
    public void testGetUnmodifiableChildren() {
        Position<Integer> p = this.tree.addRoot(100);
        this.tree.add(200, p);
        this.tree.add(300, p);
        Iterable<? extends Position<Integer>> l = this.tree.children(p);
        try {
            l.iterator().remove();
            fail("The children collection has been modified");
        } catch (Exception e) {
            assertTrue(true);
        }
    }


    @Test
    public void testGetChildren() {
        Position<Integer> p = this.tree.addRoot(100);
        this.tree.add(200, p);
        this.tree.add(300, p);

        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree.children(p)) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "200300");
    }


    @Test
    public void testGetChildren2() {
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);

        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree.children(p3)) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "9101112");
    }


    @Test
    public void testIterator() {
        this.setTree();

        StringBuilder s = new StringBuilder();
        for (Position<Integer> pos : this.tree) {
            s.append(pos.getElement());
        }
        assertEquals(s.toString(), "123456789101112");
    }



    @Test
    public void testIteratorPreOrder(){
        this.setTree();
        StringBuilder s = new StringBuilder();
        var it = this.tree.iteratorPreOrder();
        while(it.hasNext()){
            s.append(it.next().getElement());
        }
        assertEquals("123567891011124", s.toString());
    }


    @Test
    public void testIteratorPostOrder(){
        this.setTree();
        StringBuilder s = new StringBuilder();
        var it = this.tree.iteratorPostOrder();
        while(it.hasNext()){
            s.append(it.next().getElement());
        }
        assertEquals("257910111286341", s.toString());
    }

    @Test
    public void testIsRoot() {
        this.setTree();
        Integer a = this.tree.root().getElement();
        boolean b = (a == 1);
        assertTrue(b);

    }


    @Test
    public void testIsRoot2() {
        try {
            this.tree.isRoot(null);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }


    @Test
    public void testSwapElements() {
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);


        this.tree.swapElements(p, p1);
        this.tree.swapElements(p2, p3);

        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "321458769101112");
    }


    @Test
    public void testReplace() {
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);


        this.tree.replace(p, -1);
        this.tree.replace(p1, -2);
        this.tree.replace(p2, -3);
        this.tree.replace(p3, -4);

        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "-12-245-37-49101112");
    }


    @Test
    public void testAttachTree(){
        LCRSTree<Integer> tree2 = new LCRSTree<>();
        Position<Integer> p = tree.addRoot(1);
        tree.add(2, p);
        Position<Integer> p1 = tree.add(3, p);
        tree.add(4, p);
        tree.add(5, p1);
        Position<Integer> p2 = tree.add(6, p1);
        tree.add(7, p2);
        Position<Integer> p3 = tree.add(8, p2);
        tree.add(9, p3);
        tree.add(10, p3);
        tree.add(11, p3);
        tree.add(12, p3);

        Position<Integer> p4 = tree2.addRoot(100);
        tree2.add(200, p4);
        tree2.add(300, p4);
        tree2.add(400, p4);
        tree2.add(500, p4);
        tree2.add(600, p4);
        tree2.add(700, p4);
        tree2.add(800, p4);
        tree2.add(900, p4);
        tree2.add(1000, p4);
        tree2.add(1100, p4);
        tree2.add(1200, p4);
        tree2.add(1300, p4);

        this.tree.attach(p3, tree2);

        StringBuilder salida = new StringBuilder();
        for (Position<Integer> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "1234567891011121002003004005006007008009001000110012001300");


    }
}
