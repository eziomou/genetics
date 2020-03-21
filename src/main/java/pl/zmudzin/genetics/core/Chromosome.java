package pl.zmudzin.genetics.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 * This interface represents a potential solution for the given problem.
 *
 * <p>Implement this interface to represent the problem.</p>
 *
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Chromosome {

    int size();

    List<? extends Gene> genes();

    Stream<? extends Gene> stream();

    static <T extends Chromosome> T of(ChromosomeFactory<T> factory, Gene... genes) {
        return factory.create(Arrays.asList(genes));
    }

    static <T extends Chromosome> Collector<Gene, List<Gene>, T> collector(ChromosomeFactory<T> factory) {
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
