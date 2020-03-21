package pl.zmudzin.genetics.statistics;

import pl.zmudzin.genetics.core.*;
import pl.zmudzin.genetics.evaluation.Evaluation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

/**
 * @param <C> type of evaluated chromosome
 * @see Statistics
 * @see Population
 * @see Chromosome
 * @see Gene
 * @author Piotr Żmudzin
 */
public class BasicStatistics<C extends Evaluation & Chromosome> implements Statistics<C> {

    protected List<Population<C>> populations;

    public BasicStatistics(List<Population<C>> populations) {
        this.populations = populations;
    }

    public double getBestEvaluation() {
        return getBestChromosome().getValue();
    }

    public C getBestChromosome() {
        return populations.stream()
                .map(this::getBestChromosome)
                .max(Evaluation::compareTo)
                .orElse(null);
    }

    public double getBestEvaluation(Population<C> population) {
        return getBestChromosome(population).getValue();
    }

    public C getBestChromosome(Population<C> population) {
        return population.chromosomes().stream()
                .max(Evaluation::compareTo)
                .orElse(null);
    }

    public double getAverageEvaluation() {
        return populations.stream()
                .mapToDouble(this::getAverageEvaluation)
                .summaryStatistics()
                .getAverage();
    }

    public double getAverageEvaluation(Population<C> population) {
        return population.chromosomes().stream()
                .mapToDouble(Evaluation::getValue)
                .summaryStatistics()
                .getAverage();
    }

    @Override
    public String toString() {
        return supplyTable().toString();
    }

    protected Table supplyTable() {
        Table table = new Table(
                new Table.Row("Gen.", "Best", "Avg.")
        );
        int i = 1;

        for (Population<C> population : populations) {
            table.getRows().add(new Table.Row(i++, getBestEvaluation(population), getAverageEvaluation(population)));
        }
        return table;
    }

    public static <A extends Chromosome & Evaluation> Collector<Population<A>, List<Population<A>>, BasicStatistics<A>> collector() {
        return Collector.of(
                ArrayList::new,
                List::add,
                (list1, list2) -> {
                    list1.addAll(list2);
                    return list1;
                },
                BasicStatistics::new
        );
    }
}
