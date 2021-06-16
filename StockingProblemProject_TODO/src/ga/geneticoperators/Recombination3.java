package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.HashSet;

public class Recombination3<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    public Recombination3(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO Cycle Crossover
        int[] child1 = new int[ind1.getNumGenes()];
        int[] child2 = new int[ind1.getNumGenes()];
        boolean[][] positions = new boolean[2][ind1.getNumGenes()];
        int[][] cycles = new int[Integer.MAX_VALUE][ind1.getNumGenes()];
        int nrCycles=0;

        for (int i = 0; i < positions[0].length; i++) {
            if(!positions[0][i]) // positions[i] == false
            {
                cycles[nrCycles] = getcycle(ind1, ind2, cycles[i], positions[0], i);
                nrCycles++;
            }

        }

        for (int i = 0; i < positions[1].length; i++) {

            if(!positions[1][i]) // positions[i] == false
            {
                cycles[nrCycles] = getcycle(ind2, ind1, cycles[i], positions[1], i);
                nrCycles++;
            }

        }


    }

    public int[] getcycle (I ind1, I ind2, int[] cycle, boolean[] positions, int indice)
    {
        int position = indice;
        int i=0;
        do {
            int allele = ind1.getGene(position); //allele parent 1
            position = ind2.getIndexof(allele); //position from parent 2
            cycle[i] = allele;
            i++;
            positions[ind1.getIndexof(allele)] = true;

        }while(ind1.getGene(position) != cycle[0]);

        return cycle;
    }

    @Override
    public String toString(){
        return "Cycle Crossover";
    }    
}