package ga.selectionmethods;

import algorithms.Individual;
import algorithms.Problem;
import ga.Population;

public class RankBased  <I extends Individual, P extends Problem<I>> extends SelectionMethod<I, P> {


    public RankBased(int popSize) {
        super(popSize);
    }

    @Override
    public Population<I, P> run(Population<I, P> original) {

        return null;
    }
}
