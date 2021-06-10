package ga.selectionmethods;

import algorithms.Individual;
import algorithms.Problem;
import ga.Population;

import java.util.ArrayList;
import java.util.List;

public class RankBased<I extends Individual, P extends Problem<I>> extends SelectionMethod<I, P> {


    public RankBased(int popSize) {
        super(popSize);
    }

    @Override
    public Population<I, P> run(Population<I, P> original) {
        Population<I, P> result = new Population<>(original.getSize());
        I bestIndividual = original.getBest();
        List<I> selectedIndividuals = original.getIndividuals();

        for (int i = 0; i < popSize; i++) {
            int min = i;
            for (int j = i; j < popSize; j++) {
                if(selectedIndividuals.get(j).compareTo(selectedIndividuals.get(i)) < 0)
                {
                    min = j;
                }
            }
            I aux = selectedIndividuals.get(min);
            selectedIndividuals.set(min, selectedIndividuals.get(i));
            selectedIndividuals.set(i, aux);

        }
        for (int i = 0; i < popSize; i++) {
            result.addIndividual((I) selectedIndividuals.get(i).clone());
        }

        return result;
    }


    /*private I rankBased (Population<I, P> population)
    {
        for (int i = 0; i < popSize; i++) {

            if(population.getIndividual(i).compareTo(population.getIndividual(i-1)) > 0)
            {

            }
        }

        return
    }*/

    @Override
    public String toString(){
        return "Rank Based Selection";
    }

}
