package org.juple;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TupleTest {

    static class Foo {
        final int value;

        Foo(int value) {
            this.value = value;
        }

        public String toString() {
            return Integer.toString(value);
        }
    }

    int i;

    @BeforeEach
    void setUp() {
        this.i = 0;
    }

    @Test
    void test_Tuple() {
        Tuple tuple = new Tuple(1, 2L, "3", null);
        assertEquals(new ArrayList<>(Arrays.asList(1, 2L, "3", null)), tuple.toArrayList());
    }

    @Test
    void test_Tuple_whenObjectValue() {
        Tuple tuple = new Tuple(new Foo(1));
        assertEquals(1, ((Foo) tuple.get(0)).value);
    }

    @Test
    void test_fromList() {
        Tuple tuple = Tuple.fromList(List.of(1, 2));
        assertEquals(List.of(1, 2), tuple.toArrayList());
    }

    @Test
    void test_toArrayList() {
        Tuple tuple = new Tuple(1);
        assertEquals(new ArrayList<>(List.of(1)), tuple.toArrayList());
    }

    @Test
    void test_toArray() {
        Tuple tuple = new Tuple(1);
        assertArrayEquals(new Integer[]{1}, tuple.toArray());
    }

    @Test
    void test_get() {
        Tuple tuple = new Tuple(1);
        assertEquals(Integer.valueOf(1), tuple.get(0));
    }

    @Test
    void test_get_whenIndexOutOfBounds_thenThrowIndexOutOfBoundsException() {
        Tuple tuple = new Tuple(1);
        assertThatThrownBy(() -> tuple.get(1)).isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void test_subTuple() {
        Tuple tuple = new Tuple(1, 2, 3);
        assertEquals(List.of(1, 2), tuple.subTuple(0, 2).toArrayList());
    }

    @Test
    void test_indexOf() {
        Tuple tuple = new Tuple(1, 2);
        assertEquals(1, tuple.indexOf(2));
    }

    @Test
    void test_lastIndexOf() {
        Tuple tuple = new Tuple(1, 1, 2);
        assertEquals(1, tuple.lastIndexOf(1));
    }

    @Test
    void test_isEmpty() {
        Tuple tuple = new Tuple();
        assertTrue(tuple.isEmpty());
    }

    @Test
    void test_size() {
        Tuple tuple = new Tuple(1);
        assertEquals(1, tuple.size());
    }

    @Test
    void test_size_whenEmptyTuple_thenReturnZero() {
        Tuple tuple = new Tuple();
        assertEquals(0, tuple.size());
    }

    @Test
    void test_contains() {
        Tuple tuple = new Tuple(1);
        assertTrue(tuple.contains(1));
        assertFalse(tuple.contains(2));
    }

    @Test
    void test_count() {
        Tuple tuple = new Tuple(1, 1, null, null);
        assertEquals(2, tuple.count(1));
        assertEquals(2, tuple.count(null));
        assertEquals(0, tuple.count(2));
    }

    @Test
    void test_stream() {
        Tuple tuple = new Tuple(1, 2);
        List<Integer> collect = tuple.stream().map(v -> (Integer) v * 100).collect(Collectors.toList());
        assertEquals(List.of(100, 200), collect);
    }

    @Test
    void test_parallelStream() {
        Tuple tuple = new Tuple(1, 2);
        List<Integer> collect = tuple.parallelStream().map(v -> (Integer) v * 100).collect(Collectors.toList());
        assertEquals(List.of(100, 200), collect);
    }

    @Test
    void test_iterator() {
        Tuple tuple = new Tuple(1, 2);
        Iterator<Object> iterator = tuple.iterator();

        while(iterator.hasNext()) {
            this.i += (Integer) iterator.next();
        }

        assertEquals(3, this.i);
    }

    @Test
    void test_forEach() {
        Tuple tuple = new Tuple(1, 2);
        tuple.forEach((v) -> this.i += (Integer) v);
        assertEquals(3, this.i);
    }

    @Test
    void test_toString() {
        Tuple tuple = new Tuple(1, "2", null, new Foo(3));
        assertEquals("(1, 2, null, 3)", tuple.toString());
    }
}
