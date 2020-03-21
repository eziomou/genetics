package pl.zmudzin.genetics.crossing;

import pl.zmudzin.genetics.core.Chromosome;
import pl.zmudzin.genetics.core.Gene;
import pl.zmudzin.genetics.core.GeneticOperator;
import pl.zmudzin.genetics.core.Population;

/**
 * The crossover function aims to exchange a specific number of features
 * between two or more individuals.
 *
 * @param <T> type of taken chromosome
 * @param <R> type of returned chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Crossover<T extends Chromosome, R extends Chromosome> extends GeneticOperator<T, R> {
}
