package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
        //int[][] cyclesp1 = new int[ind1.getNumGenes()][ind1.getNumGenes()];
        //int[][] cyclesp2 = new int[ind2.getNumGenes()][ind2.getNumGenes()];
        int nrCycles=0;
        int idx = 0;


        int[] cycle = new int[ind1.getNumGenes()];
        boolean change=true,jump=false; // True-Par False-Impar

        for (int i = 0; i < child1.length; i++) {
            if(change) {
                if(!positions[0][i])
                {
                    getcycle(ind1,ind2,cycle,positions[0],i);
                    for (int j = 0; j < cycle.length; j++) {
                        for (int k = 0; k < child1.length; k++) {
                            if(cycle[j] == child1[k])
                            {
                                jump=true;
                                break;
                            }

                            else
                                jump=false;
                        }
                        if(cycle[j] != 0 && child1[j]==0 && !jump)
                            child1[j] = cycle[j];
                    }
                }

                if(!positions[1][i])
                {
                    getcycle(ind2,ind1,cycle,positions[1],i);
                    for (int j = 0; j < cycle.length; j++) {
                        if(cycle[j] != 0 && child2[j]==0 && child2[i] != cycle[j])
                            child2[j] = cycle[j];
                    }
                    change=false;
                }

            }
            else
            {
                if(!positions[0][i])
                {
                    getcycle(ind2,ind1,cycle,positions[0],i);
                    for (int j = 0; j < cycle.length; j++) {
                        for (int k = 0; k < child1.length; k++) {
                            if(cycle[j] == child1[k])
                            {
                                jump=true;
                                break;
                            }
                            else
                                jump=false;
                        }
                        if(cycle[j] != 0 && child1[j]==0 && !jump)
                            child1[j] = cycle[j];
                    }
                }


                if(!positions[1][i])
                {
                    getcycle(ind1,ind2,cycle,positions[1],i);
                    for (int j = 0; j < cycle.length; j++) {
                        if(cycle[j] != 0 && child2[j]==0 && child2[i] != cycle[j])
                            child2[j] = cycle[j];
                    }
                    change=true;
                }

            }

        }
        /*for (int i = 0; i < positions[0].length; i++) {
            idx = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
            if (!positions[0][i]) // positions[i] == false
            {
                while (positions[0][idx]) {
                    idx = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
                }
                cyclesp1[nrCycles] = getcycle(ind1, ind2, cyclesp1[i], positions[0], idx);
                nrCycles++;
            }
        }

        nrCycles=0;
        idx = 0;
        for (int i = 0; i < positions[1].length; i++) {
            idx = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
            if(!positions[1][i]) // positions[i] == false
            {
                while (positions[1][idx]) {
                    idx = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
                }
                cyclesp2[nrCycles] = getcycle(ind2, ind1, cyclesp2[i], positions[1],idx);
                nrCycles++;
            }
        }

        List<Integer> cycleList = new ArrayList<>();
        for (int i = 0; i < nrCycles; i++) {
            cycleList.add(i);
        }
        boolean change=true; // True-Par False-Impar

        for (int i = 0; i < nrCycles; i++) {
            int randomCycle = GeneticAlgorithm.random.nextInt(cycleList.size());
            if(change){
                crossover(child1,cyclesp1[i]);
                crossover(child2,cyclesp2[i]);
                change=false;
            }
            else{
                crossover(child1,cyclesp2[i]);
                crossover(child2,cyclesp1[i]);
                change=true;
            }
            cycleList.remove(randomCycle);
        }*/

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
            cycle[position] = allele;
            positions[position] = true;
            position = ind2.getIndexof(allele); //position from parent 2

        }while(position != indice);

        return cycle;
    }

    public void crossover(int[] child, int[] cycle)
    {
        for (int i = 0; i < cycle.length; i++) {
            if(child[i] == 0 & cycle[i]!=0){
                child[i] = cycle[i];
            }
        }
    }

    @Override
    public String toString(){
        return "Cycle Crossover (" + probability + ")";
    }    
}