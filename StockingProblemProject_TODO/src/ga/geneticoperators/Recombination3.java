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
        int[] child2 = new int[ind2.getNumGenes()];
        boolean[][] positions = new boolean[2][ind1.getNumGenes()];
        int[][] cyclesp1 = new int[ind1.getNumGenes()][ind1.getNumGenes()];
        int[][] cyclesp2 = new int[ind2.getNumGenes()][ind2.getNumGenes()];
        int nrCycles=0;

        for (int i = 0; i < positions[0].length; i++) {
            if(!positions[0][i]) // positions[i] == false
            {
                cyclesp1[nrCycles] = getcycle(ind1, ind2, cyclesp1[i], positions[0], i);
                nrCycles++;
            }

        }

        nrCycles=0;
        for (int i = 0; i < positions[1].length; i++) {
            if(!positions[1][i]) // positions[i] == false
            {
                cyclesp2[nrCycles] = getcycle(ind2, ind1, cyclesp2[i], positions[1], i);
                nrCycles++;
            }

        }

        boolean change=true;

        for (int i = 0; i < cyclesp1.length; i++) {

            if(!change){
                for (int j = 0; j < child1.length; j++) {
                    if(cyclesp2[i][j] != 0) {
                        child1[j] = cyclesp2[i][j];
                    }
                    if(cyclesp1[i][j] != 0){
                        child2[j] = cyclesp1[i][j];
                    }
                }
                change = true;
                continue;
            }

            if (change) {
                for (int j = 0; j < child1.length; j++) {

                    if (cyclesp1[i][j] != 0) {
                        child1[j] = cyclesp1[i][j];
                    }
                    if (cyclesp2[i][j] != 0) {
                        child2[j] = cyclesp2[i][j];
                    }
                }
                change = false;
                continue;
            }
        }


        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }

    }

    public int[] getcycle (I ind1, I ind2, int[] cycle, boolean[] positions, int indice)
    {
        int position = indice;
        do {
            int allele = ind1.getGene(position); //allele parent 1
            position = ind2.getIndexof(allele); //position from parent 2
            cycle[position] = allele;
            positions[ind1.getIndexof(allele)] = true;

        }while(ind1.getGene(position) != cycle[indice]);

        return cycle;
    }

    @Override
    public String toString(){
        return "Cycle Crossover (" + probability + ")";
    }    
}