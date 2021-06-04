package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {


    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        int[] child1 = new int[ind1.getNumGenes()];
        int[] child2 = new int[ind2.getNumGenes()];
        int[] genome = ind1.getGenome();
        for (int i = 0; i < genome.length; i++) {
            

        }

        do {
            ind1.getGene(i)
        }while();
    }

    @Override
    public String toString() {
        return "One cut recombination (" + probability + ")";
    }
}