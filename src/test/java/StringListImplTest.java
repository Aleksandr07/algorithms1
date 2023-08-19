import exceptions.ElementNotFoundException;
import exceptions.StringListOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private static StringList stringList;

    @BeforeEach
    void setUp() {
        stringList = new StringListImpl(5);
    }

    @Test
    void shouldAddItemToTheEnd() {
        stringList.add("25");
        String expected = "25";
        String[] expectedArray = {"25"};
        assertArrayEquals(expectedArray, stringList.toArray());
        assertEquals(expected, stringList.get(0));
    }

    @Test
    void shouldThrowStringListOutOfBoundException() {
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.add("1"));
    }


    @Test
    void shouldAddItemWithIndex() {
        stringList.add("1");
        stringList.add("2");
        stringList.add(0, "23");
        stringList.add("3");
        String expected = "23";
        String[] expectedArray = {"23", "1", "2", "3"};
        assertArrayEquals(expectedArray, stringList.toArray());
        assertEquals(expected, stringList.get(0));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenAddItemWhenIndexMoreThenFactSize() {
        stringList.add("1");
        stringList.add("1");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.add(4, "1"));
    }
    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenAddItem() {
        stringList.add("1");
        stringList.add("1");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.add(7, "1"));
    }

    @Test
    void shouldSetItem() {
        stringList.add("1");
        stringList.add("2");
        String[] expected = {"1", "24"};
        stringList.set(1, "24");
        assertArrayEquals(expected, stringList.toArray());
        assertEquals("24", stringList.get(1));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenSetItemWhenIndexMoreThenFactSize() {
        stringList.add("1");
        stringList.add("1");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.set(4, "23"));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenSetItem() {
        stringList.add("1");
        stringList.add("1");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.set(7, "1"));
    }

    @Test
    void shouldRemoveElementSearchElement() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        assertEquals("2", stringList.remove("2"));
        String[] expected = {"1", "3", "4"};
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void shouldThrowElementNotFoundException() {
        stringList.add("1");
        assertThrows(ElementNotFoundException.class, () -> stringList.remove("5"));
         assertThrows(ElementNotFoundException.class, () -> stringList.remove(4));
    }

    @Test
    void shouldRemoveElementSearchIndex() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        assertEquals("2", stringList.remove(1));
        String[] expected = {"1", "3", "4"};
        assertArrayEquals(expected, stringList.toArray());
    }

    @Test
    void shouldContains() {
        stringList.add("3");
        stringList.add("2");
        assertTrue(stringList.contains("3"));
        assertFalse(stringList.contains("25"));
    }

    @Test
    void shouldIndexOf() {
        stringList.add("1");
        stringList.add("1");
        int expected = 0;
        assertEquals(expected, stringList.indexOf("1"));
        assertEquals(-1, stringList.indexOf("4"));
    }

    @Test
    void shouldLastIndexOf() {
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        int expected = 2;
        assertEquals(expected, stringList.lastIndexOf("1"));
        assertEquals(-1, stringList.lastIndexOf("4"));
    }

    @Test
    void shouldGet() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        String expected = "2";
        assertEquals(expected, stringList.get(1));
    }

    @Test
    void shouldThrowStringListOutOfBoundsExceptionWhenGetItem() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        assertThrows(StringListOutOfBoundsException.class, () -> stringList.get(3));
    }

    @Test
    void shouldEqualsStringLists() {
        StringList otherList = new StringListImpl(5);
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        otherList.add("1");
        otherList.add("2");
        otherList.add("3");
        assertTrue(stringList.equals(otherList));
        otherList.add("4");
        assertFalse(stringList.equals(otherList));
    }

    @Test
    void size() {
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        assertEquals(3, stringList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(stringList.isEmpty());
        stringList.add("1");
        assertFalse(stringList.isEmpty());
    }

    @Test
    void clear() {
        stringList.add("1");
        stringList.add("1");
        stringList.add("1");
        assertFalse(stringList.isEmpty());
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void toArray() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
        String[] expected = {"1", "2", "3", "4", "5"};
        assertArrayEquals(expected, stringList.toArray());
    }
}