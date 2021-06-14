package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

import java.util.ArrayList;

public class Recombination2<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private ArrayList<Integer> SegmentBuilder;
    private int[] child1, child2;
    private int cut1, cut2;

    public Recombination2(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        SegmentBuilder = new ArrayList<Integer>();

        //inicializar arrays dos filhos, com o mesmo tamanho dos pais
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];

        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes()); //ponto de corte inicial é escolhido aleatóriamente

        //ponto de corte final é criado garantindo que cut1!=cut2 e cut1<cut2
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        } while (cut1 == cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        /*
        crossOver(child1,ind2,ind1);
        crossOver(child2,ind1,ind2);
        */
        crossOver(child1,ind1,ind2);
        crossOver(child2,ind2,ind1);

        //temos de substitiur arrays aux (child 1 e 2) pelo array do individuo
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }

    }

    public void crossOver(int[] filho, I ind1, I ind2){

    }

    @Override
    public String toString() {
        return "Order recombination (" + probability + ")";
    }
}