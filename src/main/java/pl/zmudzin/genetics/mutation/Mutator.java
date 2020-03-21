package pl.zmudzin.genetics.mutation;

import pl.zmudzin.genetics.core.*;

/**
 * The mutation function aims to make small changes to the chromosome. The
 * mutation occurs with a probability determined by the mutation probability
 * hyperparameter. Too high probability of mutation can lead to the
 * destruction of correct solutions to the problem.
 *
 * @param <T> type of taken chromosome
 * @param <R> type of returned chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Mutator<T extends Chromosome, R extends Chromosome> extends GeneticOperator<T, R> {
}
