package pl.zmudzin.genetics.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class provides immutable implementation of the {@code Chromosome}.
 *
 * <p>Extend this class to represent the problem.</p>
 *
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class ImmutableChromosome implements Chromosome {

    protected List<? extends Gene> genes;

    public ImmutableChromosome(Collection<? extends Gene> genes) {
        this.genes = new ArrayList<>(genes);
    }

    public int size() {
        return genes.size();
    }

    public List<? extends Gene> genes() {
        return Collections.unmodifiableList(genes);
    }

    public Stream<? extends Gene> stream() {
        return genes.stream();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ImmutableChromosome)) {
            return false;
        }
        ImmutableChromosome other = (ImmutableChromosome) obj;
        return Objects.equals(size(), other.size()) &&
                Objects.equals(genes(), other.genes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(size(), genes());
    }

    @Override
    public String toString() {
        return stream()
                .map(Gene::toString)
                .collect(Collectors.joining());
    }
}
