package pl.zmudzin.genetics.crossing;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @param <T> type of element
 * @author Piotr Żmudzin
 */
public class Pair<T> {

    private T first;
    private T second;

    Pair(T first, T second) {
        this.first = Objects.requireNonNull(first);
        this.second = Objects.requireNonNull(second);
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public List<T> asList() {
        return Arrays.asList(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?> other = (Pair<?>) obj;
        return Objects.equals(first, other.first) &&
                Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
