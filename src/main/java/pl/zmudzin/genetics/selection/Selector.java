package pl.zmudzin.genetics.selection;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.evaluation.Evaluation;

/**
 * The selection function aims to select best adapted to the environment individuals.
 *
 * @param <T> type of taken evaluated chromosome
 * @param <R> type of returned chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Selector<T extends Evaluation & Chromosome, R extends Chromosome> extends GeneticOperator<T, R> {
}
