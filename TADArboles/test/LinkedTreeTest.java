import junit.framework.TestCase;
import material.Position;


public class LinkedTreeTest extends TestCase {

    private LinkedTree<String> tree = new LinkedTree<>();

    public void setTree() {

        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);

        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);

        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);

        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);
    }

    public void testSize() {
        Position<String> p = this.tree.addRoot("+");
        this.tree.add("2", p);
        Position<String> h = this.tree.add("*", p);
        this.tree.add("3", h);
        this.tree.add("5", h);
        assertEquals(this.tree.size(), 5);
    }

    public void testSize2() {
        this.setTree();
        assertEquals(this.tree.size(), 12);
    }

    public void testRoot() {
        this.setTree();
        assertEquals(this.tree.root().getElement(), "A");

    }

    public void testIsEmpty() {
        assertTrue(this.tree.isEmpty());
    }

    public void testIsEmpty2() {
        Position<String> p = this.tree.addRoot("B");
        this.tree.add("C", p);
        assertFalse(this.tree.isEmpty());
    }

    public void testParent2() {
        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);
        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);
        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);
        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);
        assertEquals(p2, tree.parent(p3));
    }

    public void testParent3() {
        this.setTree();

        try {
            this.tree.parent(null);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    public void testPositions() {
        Position<String> p = this.tree.addRoot("+");
        this.tree.add("2", p);
        this.tree.add("3", p);
        StringBuilder salida = new StringBuilder();
        for (Position<String> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "+23");
    }

    public void testRemove() {
        Position<String> p = this.tree.addRoot("+");
        this.tree.add("2", p);
        Position<String> h = this.tree.add("*", p);
        this.tree.add("3", h);
        this.tree.add("5", h);
        this.tree.remove(h);
        assertEquals(this.tree.size(), 2);

    }

    public void testAddN() {
        Position<String> p = this.tree.addRoot("R");
        this.tree.add("A", p);
        this.tree.add("C", p);
        this.tree.add("B", p, 1);
        Iterable<? extends Position<String>> children = this.tree.children(p);
        StringBuilder output = new StringBuilder();
        for (Position<String> child : children)
            output.append(child.getElement());
        assertEquals(output.toString(), "ABC");

    }


    public void testRemove2() {
        this.setTree();
        this.tree.remove(this.tree.root());
        assertEquals(this.tree.size(), 0);
    }

    public void testRemove3() {
        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);
        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);
        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);
        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);

        this.tree.remove(p2);

        StringBuilder s = new StringBuilder();
        for (Position<String> pos : this.tree) {
            s.append(pos.getElement());
        }
        assertEquals(s.toString(), "ABCDE");
    }

    public void testGetUnmodifiableChildren() {
        Position<String> p = this.tree.addRoot("+");
        this.tree.add("2", p);
        this.tree.add("3", p);
        Iterable<? extends Position<String>> l = this.tree.children(p);
        try {
            l.iterator().remove();
            fail("The children collection has been modified");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    public void testGetChildren() {
        Position<String> p = this.tree.addRoot("+");
        this.tree.add("2", p);
        this.tree.add("3", p);

        StringBuilder salida = new StringBuilder();
        for (Position<String> e : this.tree.children(p)) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "23");
    }

    public void testGetChildren2() {
        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);
        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);
        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);
        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);

        StringBuilder salida = new StringBuilder();
        for (Position<String> e : this.tree.children(p3)) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "IJKL");
    }

    public void testIterator() {
        this.setTree();

        StringBuilder s = new StringBuilder();
        for (Position<String> pos : this.tree) {
            s.append(pos.getElement());
        }
        assertEquals(s.toString(), "ABCDEFGHIJKL");
    }


    public void testIteratorPreOrder(){
        this.setTree();
        StringBuilder s = new StringBuilder();
        var it = this.tree.iteratorPreOrder();
        while(it.hasNext()){
            s.append(it.next().getElement());
        }
        assertEquals("ABCEFGHIJKLD", s.toString());
    }


    public void testIteratorPostOrder(){
        this.setTree();
        StringBuilder s = new StringBuilder();
        var it = this.tree.iteratorPostOrder();
        while(it.hasNext()){
            s.append(it.next().getElement());
        }
        assertEquals("BEGIJKLHFCDA", s.toString());
    }

    public void testIsRoot() {
        this.setTree();
        assertEquals("A", this.tree.root().getElement());

    }

    public void testIsRoot2() {
        try {
            this.tree.isRoot(null);
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    public void testSwapElements() {
        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);
        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);
        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);
        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);

        this.tree.swapElements(p, p1);
        this.tree.swapElements(p2, p3);

        StringBuilder salida = new StringBuilder();
        for (Position<String> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "CBADEHGFIJKL");
    }


    public void testReplace() {
        Position<String> p = tree.addRoot("A");
        tree.add("B", p);
        Position<String> p1 = tree.add("C", p);
        tree.add("D", p);
        tree.add("E", p1);
        Position<String> p2 = tree.add("F", p1);
        tree.add("G", p2);
        Position<String> p3 = tree.add("H", p2);
        tree.add("I", p3);
        tree.add("J", p3);
        tree.add("K", p3);
        tree.add("L", p3);

        this.tree.replace(p, "X");
        this.tree.replace(p1, "Y");
        this.tree.replace(p2, "Z");
        this.tree.replace(p3, "W");

        StringBuilder salida = new StringBuilder();
        for (Position<String> e : this.tree) {
            salida.append(e.getElement());
        }
        assertEquals(salida.toString(), "XBYDEZGWIJKL");
    }
}