# genetics

This library provides a set of interfaces that allow easy use of the genetic algorithm and implementations of several of the most popular genetic operators.

## Usage

```
var algorithm = GeneticAlgorithmImpl.<Chromosome, EvaluatedChromosome>builder()
    .initializer(initializer)
    .evaluator(evaluator)
    .flow(
        selector
            .andThen(crossover)
            .andThen(mutator)
            .andThen(evaluator)
    )
    .build();

BasicStatistics statistics = algoirthm
    .generations(100)
    .collect(Statistics.collector(BasicStatistics::new));
```

## Example of a generated solution to a problem of a traveling salesman

```
int[][] graph = {
	{0, 1, 2, 1, 2, 3, 2, 3, 4},
	{1, 0, 1, 2, 1, 2, 3, 2, 3},
	{2, 1, 0, 3, 2, 1, 4, 3, 2},
	{1, 2, 3, 0, 1, 2, 1, 2, 3},
	{2, 1, 2, 1, 0, 1, 2, 1, 2},
	{3, 2, 1, 2, 1, 0, 3, 2, 1},
	{2, 3, 4, 1, 2, 3, 0, 1, 2},
	{3, 2, 3, 2, 1, 2, 1, 0, 1},
	{4, 3, 2, 3, 2, 1, 2, 1, 0}
}
```

```
Gen.		Best 		Avg.  		Route (best)      
1   		-14.0		-17.64		0 -> 2 -> 1 -> 7 -> 4 -> 8 -> 5 -> 3 -> 6 -> 0		
2   		-14.0		-17.18		0 -> 4 -> 6 -> 7 -> 8 -> 2 -> 1 -> 5 -> 3 -> 0		
3   		-12.0		-16.82		0 -> 3 -> 4 -> 5 -> 8 -> 2 -> 1 -> 7 -> 6 -> 0		
4   		-10.0		-16.72		0 -> 3 -> 6 -> 7 -> 8 -> 4 -> 5 -> 2 -> 1 -> 0		
5   		-10.0		-16.26		0 -> 1 -> 2 -> 5 -> 8 -> 6 -> 7 -> 4 -> 3 -> 0		
6   		-10.0		-16.14		0 -> 1 -> 2 -> 5 -> 8 -> 6 -> 7 -> 4 -> 3 -> 0		
7   		-10.0		-15.92		0 -> 1 -> 2 -> 5 -> 8 -> 6 -> 7 -> 4 -> 3 -> 0		
8   		-10.0		-15.82		0 -> 1 -> 2 -> 5 -> 8 -> 6 -> 7 -> 4 -> 3 -> 0		
9   		-10.0		-15.54		0 -> 1 -> 2 -> 5 -> 8 -> 7 -> 6 -> 3 -> 4 -> 0		
10  		-10.0		-15.78		0 -> 3 -> 6 -> 7 -> 8 -> 5 -> 2 -> 4 -> 1 -> 0	
```