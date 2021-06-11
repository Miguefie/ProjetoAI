package ga.selectionmethods;

import algorithms.Individual;
import algorithms.Problem;
import ga.Population;

public class RankBased<I extends Individual, P extends Problem<I>> extends SelectionMethod<I, P> {


    public RankBased(int popSize) {
        super(popSize);
    }

    @Override
    public Population<I, P> run(Population<I, P> original) {
        Population<I, P> result = new Population<>(original.getSize());
        I bestIndividual = original.getBest();

        for (int i = 0; i < popSize; i++) {
            for (int j = 0; j < popSize; j++) {
                
                if(original.getIndividual(i).compareTo(original.getIndividual(j)) > 0)
                {

                }
            }
        }

        result.addIndividual((I)bestIndividual.clone());

        return result;
    }


/*
    private I rankBased (Population<I, P> population)
    {
        for (int i = 0; i < popSize; i++) {

            if(population.getIndividual(i).compareTo(population.getIndividual(i-1)) > 0)
            {

            }
        }

        return
    }
*/

    @Override
    public String toString(){
        return "Rank Based Selection";
    }

}
