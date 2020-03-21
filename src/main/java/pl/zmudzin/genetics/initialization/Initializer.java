package pl.zmudzin.genetics.initialization;

import pl.zmudzin.genetics.core.*;

/**
 * The initialization function aims to generate the initial population of
 * the genetic algorithm. The correct initialization function can lead to
 * faster generation of the expected solution to the problem.
 *
 * @param <C> type of chromosome
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public interface Initializer<C extends Chromosome> {

    Population<C> initialize();
}
