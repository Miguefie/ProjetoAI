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
        //TODO Cycle Crossover
        int[] child1 = new int[ind1.getNumGenes()];
        int[] child2 = new int[ind2.getNumGenes()];
        int[] cycle1 = new int[ind1.getNumGenes()];
        int[] cycle2 = new int[ind1.getNumGenes()];
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            int positionAllele = ind2.getIndexof(ind1.getGene(i));
            int allele = ind1.getGene(positionAllele);
            cycle1[i]=allele;

            positionAllele = ind1.getIndexof(ind2.getGene(i));
            allele = ind2.getGene(positionAllele);
            cycle2[i]=allele;

        }
        child1 = cycle1;
        child2 = cycle2;
    }

    @Override
    public String toString(){
        return "Cycle Crossover (" + probability + ")";
    }    
}