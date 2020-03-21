package pl.zmudzin.genetics.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class provides immutable implementation of the {@code Population}.
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class ImmutablePopulation<C extends Chromosome> implements Population<C> {

    protected List<C> chromosomes;

    public ImmutablePopulation(Collection<? extends C> chromosomes) {
        this.chromosomes = new ArrayList<>(chromosomes);
    }

    @Override
    public int size() {
        return chromosomes.size();
    }

    @Override
    public List<C> chromosomes() {
        return Collections.unmodifiableList(chromosomes);
    }

    @Override
    public Stream<C> stream() {
        return chromosomes.stream();
    }
}
