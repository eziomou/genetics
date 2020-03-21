package pl.zmudzin.genetics.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * Each generation of the genetic algorithm generates one population.
 * This interface is a literal representation of the population.
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Population<C extends Chromosome> {

    int size();

    List<C> chromosomes();

    Stream<C> stream();

    @SafeVarargs
    static <P extends Population<C>, C extends Chromosome> P of(PopulationFactory<P, C> factory, C... chromosomes) {
        return factory.create(Arrays.asList(chromosomes));
    }

    static <C extends Chromosome> Collector<C, List<C>, Population<C>> collector(PopulationFactory<Population<C>, C> factory) {
        return Collector.of(
                ArrayList::new,
                List::add,
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                },
                factory::create
        );
    }
}
