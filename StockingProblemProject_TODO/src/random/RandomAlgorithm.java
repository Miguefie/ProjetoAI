package random;

import algorithms.Algorithm;
import algorithms.AlgorithmEvent;
import algorithms.Individual;
import algorithms.Problem;
import ga.GeneticAlgorithm;
import ga.Population;
import ga.geneticoperators.Mutation;
import ga.geneticoperators.Recombination;
import ga.selectionmethods.SelectionMethod;
import gui.Main;
import gui.MainFrame;

import java.util.Random;

public class RandomAlgorithm<I extends Individual, P extends Problem<I>> extends Algorithm<I, P> {
    //TODO this class might require the definition of additional methods and/or attributes
    private Population<I, P> population;
    //private final int populationSize;

    public RandomAlgorithm(int maxIterations, Random rand) {
        super(maxIterations, rand);
    }

    @Override
    public I run(P problem) {

        //TODO
        population = new Population<>(100, problem); //como sei populationSize??
        globalBest = population.evaluate();
        fireIterationEnded(new AlgorithmEvent(this));

        for (int i = 0; i < maxIterations; i++) {
            globalBest = randSearch(population);
            fireIterationEnded(new AlgorithmEvent(this));
        }

        fireRunEnded(new AlgorithmEvent(this));
        return globalBest; //Não devolve melhor solução
    }

    private I randSearch(Population<I, P> population) {

        I aux = population.getIndividual(random.nextInt(population.getSize()));

        if (aux.compareTo(globalBest) > 0) {
            globalBest = (I) aux.clone();
        }

        return globalBest;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Population size:" + population.getSize() + "\n");
        sb.append("Max generations:" + maxIterations + "\n");
        return sb.toString();
    }
}
