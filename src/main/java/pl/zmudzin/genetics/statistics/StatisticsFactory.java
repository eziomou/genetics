package pl.zmudzin.genetics.statistics;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.evaluation.Evaluation;

import java.util.List;

/**
 * @param <S> type of statistics
 * @param <C> type of evaluated chromosome
 * @see Statistics
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface StatisticsFactory<S extends Statistics<C>, C extends Evaluation & Chromosome> {

    S create(List<Population<C>> populations);
}
