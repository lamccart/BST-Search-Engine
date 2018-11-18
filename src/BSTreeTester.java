import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Iterator;
import java.util.LinkedList;

public class BSTreeTester {

    @Test
    public void testGetRoot() {
        BSTree<String> t = new BSTree<>();
        assertNull(t.getRoot());
        t.insert("a");
        assertEquals("a", t.getRoot().getKey());
        t.insert("b");
        t.insert("c");
        t.insert("d");
        assertEquals("a", t.getRoot().getKey());
    }

    @Test
    public void testGetSize() {
        BSTree<String> t = new BSTree<>();
        assertEquals(0, t.getSize());
        t.insert("a");
        assertEquals(1, t.getSize());
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        assertEquals(4, t.getSize());
    }

    @Test
    public void testInsert() {
        BSTree<String> t = new BSTree<>();
        t.insert("a");
        assertEquals(1, t.getSize());
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        assertTrue(t.findKey("c"));
        assertEquals(1, t.leafCount());
    }

    @Test
    public void testFindKey() {
        BSTree<String> t = new BSTree<>();
        t.insert("a");
        assertTrue(t.findKey("a"));
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        assertFalse(t.findKey("z"));
        assertTrue(t.findKey("c"));
    }

    @Test
    public void testInsertData() {
        BSTree<String> t = new BSTree<>();
        t.insert("a");
        t.insertData("a", "art");
        LinkedList<String> a = new LinkedList<>();
        a.add("art");
        assertEquals(a, t.findDataList("a"));
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        LinkedList<String> b = new LinkedList<>();
        b.add("boy");
        assertEquals(b, t.findDataList("b"));
        t.insertData("b", "bike");
        b.add("bike");
        assertEquals(b, t.findDataList("b"));
    }

    @Test
    public void testFindDataList() {
        BSTree<String> t = new BSTree<>();
        t.insert("a");
        t.insertData("a", "art");
        LinkedList<String> a = new LinkedList<>();
        a.add("art");
        assertEquals(a, t.findDataList("a"));
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        LinkedList<String> b = new LinkedList<>();
        b.add("boy");
        assertEquals(b, t.findDataList("b"));
        t.insertData("b", "bike");
        b.add("bike");
        assertEquals(b, t.findDataList("b"));
    }

    @Test
    public void testFindHeight() {
        BSTree<String> t = new BSTree<>();
        assertEquals(-1, t.findHeight());
        t.insert("a");
        assertEquals(0, t.findHeight());
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        assertEquals(3, t.findHeight());
    }

    @Test
    public void testLeafCount() {
        BSTree<String> t = new BSTree<>();
        assertEquals(0, t.leafCount());
        t.insert("a");
        assertEquals(1, t.leafCount());
        t.insert("b");
        t.insertData("b", "boy");
        t.insert("c");
        t.insert("d");
        assertEquals(1, t.leafCount());
    }

    @Test
    public void testIterator() {
        BSTree<String> t = new BSTree<>();
        t.insert("d");
        t.insert("b");
        t.insert("e");
        t.insert("a");
        t.insert("c");
        Iterator<String> iter = t.iterator();
        assertTrue(iter.hasNext());
        assertEquals("a", iter.next());
        assertEquals("b", iter.next());
        assertTrue(iter.hasNext());
        assertEquals("c",iter.next());
        assertTrue(iter.hasNext());
        assertEquals("d",iter.next());
        assertTrue(iter.hasNext());
        assertEquals("e",iter.next());
        assertFalse(iter.hasNext());
    }
}