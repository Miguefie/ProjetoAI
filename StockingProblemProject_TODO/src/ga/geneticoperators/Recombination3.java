package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        int[] child1 = new int[ind1.getNumGenes()];
        int[] child2 = new int[ind2.getNumGenes()];
        int[] cycle = new int[ind1.getNumGenes()];
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind2.getGene(ind2.getIndexof(ind1.getGene(i)));

        }

        do {
            ind1.getGene(i);
        }while();
    }

    @Override
    public String toString(){
        return "Two cuts recombination (" + probability + ")";
    }    
}