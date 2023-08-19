import exceptions.ElementNotFoundException;
import exceptions.StringListOutOfBoundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntegerListImplTest {
    private static IntegerList integerList;

    @BeforeEach
    void setUp() {
        integerList = new IntegerListImpl(5);
    }

    @Test
    void shouldAddItemToTheEnd() {
        integerList.add(25);
        Integer expected = 25;
        Integer[] expectedArray = {25};
        assertArrayEquals(expectedArray, integerList.toArray());
        assertEquals(expected, integerList.get(0));
    }

    @Test
    void shouldThrowStringListOutOfBoundException() {
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.add(1));
    }


    @Test
    void shouldAddItemWithIndex() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(0, 23);
        integerList.add(3);
        Integer expected = 23;
        Integer[] expectedArray = {23, 1, 2, 3};
        assertArrayEquals(expectedArray, integerList.toArray());
        assertEquals(expected, integerList.get(0));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenAddItemWhenIndexMoreThenFactSize() {
        integerList.add(1);
        integerList.add(1);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.add(4, 1));
    }
    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenAddItem() {
        integerList.add(1);
        integerList.add(1);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.add(7, 1));
    }

    @Test
    void shouldSetItem() {
        integerList.add(1);
        integerList.add(2);
        Integer[] expected = {1, 24};
        integerList.set(1, 24);
        assertArrayEquals(expected, integerList.toArray());
        assertEquals(24, integerList.get(1));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenSetItemWhenIndexMoreThenFactSize() {
        integerList.add(1);
        integerList.add(1);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.set(4, 23));
    }

    @Test
    void shouldThrowStringListOutOfBoundExceptionWhenSetItem() {
        integerList.add(1);
        integerList.add(1);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.set(7, 1));
    }

    @Test
    void shouldRemoveElementSearchElement() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        assertEquals(2, integerList.removeSearchItem(2));
        Integer[] expected = {1, 3, 4};
        assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void shouldThrowElementNotFoundException() {
        integerList.add(1);
        assertThrows(ElementNotFoundException.class, () -> integerList.remove(5));
        assertThrows(ElementNotFoundException.class, () -> integerList.remove(4));
    }

    @Test
    void shouldRemoveElementSearchIndex() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        assertEquals(2, integerList.remove(1));
        Integer[] expected = {1, 3, 4};
        assertArrayEquals(expected, integerList.toArray());
    }

    @Test
    void shouldContains() {
        integerList.add(3);
        integerList.add(2);
        assertTrue(integerList.contains(3));
        assertFalse(integerList.contains(25));
    }

    @Test
    void shouldIndexOf() {
        integerList.add(1);
        integerList.add(1);
        int expected = 0;
        assertEquals(expected, integerList.indexOf(1));
        assertEquals(-1, integerList.indexOf(4));
    }

    @Test
    void shouldLastIndexOf() {
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        int expected = 2;
        assertEquals(expected, integerList.lastIndexOf(1));
        assertEquals(-1, integerList.lastIndexOf(4));
    }

    @Test
    void shouldGet() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        Integer expected = 2;
        assertEquals(expected, integerList.get(1));
    }

    @Test
    void shouldThrowStringListOutOfBoundsExceptionWhenGetItem() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        assertThrows(StringListOutOfBoundsException.class, () -> integerList.get(3));
    }

    @Test
    void shouldEqualsStringLists() {
        IntegerList otherList = new IntegerListImpl(5);
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        otherList.add(1);
        otherList.add(2);
        otherList.add(3);
        assertTrue(integerList.equals(otherList));
        otherList.add(4);
        assertFalse(integerList.equals(otherList));
    }

    @Test
    void size() {
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        assertEquals(3, integerList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(integerList.isEmpty());
        integerList.add(1);
        assertFalse(integerList.isEmpty());
    }

    @Test
    void clear() {
        integerList.add(1);
        integerList.add(1);
        integerList.add(1);
        assertFalse(integerList.isEmpty());
        integerList.clear();
        assertTrue(integerList.isEmpty());
    }

    @Test
    void toArray() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        Integer[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, integerList.toArray());
    }
}
