package pl.zmudzin.genetics.evaluation;

import pl.zmudzin.genetics.core.*;

import java.util.function.Function;

/**
 * The evaluation function aims to assess the adaptation to the environment
 * of a given chromosome. Chromosome adaptation simply determines the
 * quality of the generated problem solution. It is important to properly
 * implement the evaluation function.
 *
 * @param <T> type of taken chromosome
 * @param <R> type of returned evaluated chromosome
 * @see Population
 * @see Chromosome
 * @see Evaluation
 * @author Piotr Żmudzin
 */
public interface Evaluator<T extends Chromosome, R extends Evaluation & Chromosome> extends Function<Population<? extends T>, Population<R>> {

    R evaluate(T chromosome);

    @Override
    default Population<R> apply(Population<? extends T> population) {
        return population.stream()
                .map(this::evaluate)
                .collect(Population.collector(ImmutablePopulation::new));
    }
}
