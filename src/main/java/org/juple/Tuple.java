package org.juple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tuple {

    private final List<Object> values = new ArrayList<>();

    public Tuple(final Object... objects) {
        this.values.addAll(Arrays.asList(objects));
    }

    private void add(Object object) {
        this.values.add(object);
    }

    public static Tuple fromList(List<Object> list) {
        Tuple tuple = new Tuple();
        for(Object object : list) {
            tuple.add(object);
        }
        return tuple;
    }

    public final List<Object> toArrayList() {
        return new ArrayList<>(this.values);
    }

    public final Object[] toArray() {
        return this.values.toArray();
    }

    @SuppressWarnings("unchecked")
    public final <T> T get(final int i) {
        return (T) this.values.get(i);
    }

    public final Tuple subTuple(int fromIndex, int toIndex) {
        return Tuple.fromList(this.values.subList(fromIndex, toIndex));
    }

    public final int indexOf(Object object) {
        return this.values.indexOf(object);
    }

    public final int lastIndexOf(Object object) {
        return this.values.lastIndexOf(object);
    }

    public final boolean isEmpty() {
        return this.values.isEmpty();
    }

    public final int size() {
        return this.values.size();
    }

    public final boolean contains(final Object value) {
        return this.values.contains(value);
    }

    public final long count(final Object value) {
        if (value == null) {
            return this.values.stream().filter(Objects::isNull).count();
        }
        return this.values.stream().filter(value::equals).count();
    }

    public final Stream<Object> stream() {
        return this.values.stream();
    }

    public final Stream<Object> parallelStream() {
        return this.values.parallelStream();
    }

    public final Iterator<Object> iterator() {
        return this.values.iterator();
    }

    public final void forEach(final Consumer<? super Object> consumer) {
        this.values.forEach(consumer);
    }

    public final String toString() {
        return this.values.stream().map(String::valueOf)
            .collect(Collectors.joining(", ", "(", ")"));
    }
}
