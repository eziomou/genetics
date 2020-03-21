package pl.zmudzin.genetics.statistics;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

/**
 * @param <C> type of evaluated chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Statistics<C extends Evaluation & Chromosome> {

    static <S extends Statistics<C>, C extends Evaluation & Chromosome> Collector<Population<C>, List<Population<C>>, S> collector(StatisticsFactory<S, C> factory) {
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
