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
        int[] child2 = new int[ind1.getNumGenes()];
        /*boolean[]

        int position = 0;
        do {
            int allele = ind1.getGene(position); //allele parent 1
            position = ind2.getIndexof(allele); //position from parent 2

        }while();





            int positionAllele = ind2.getIndexof(ind1.getGene(i));
            int allele = ind1.getGene(positionAllele);
            cycle1[i]=allele;

            positionAllele = ind1.getIndexof(ind2.getGene(i));
            allele = ind2.getGene(positionAllele);
            cycle2[i]=allele;*/


    }

    @Override
    public String toString(){
        return "Cycle Crossover";
    }    
}