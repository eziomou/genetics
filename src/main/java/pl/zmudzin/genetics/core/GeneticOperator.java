package pl.zmudzin.genetics.core;

import java.util.function.Function;

/**
 * In each generation, the genetic algorithm performs some transformation
 * of the population to generate a better solution to the problem. This
 * interface represents these transformations.
 *
 * @param <T> type of taken chromosome
 * @param <R> type of returned chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface GeneticOperator<T extends Chromosome, R extends Chromosome> extends Function<Population<? extends T>, Population<R>> {
}
