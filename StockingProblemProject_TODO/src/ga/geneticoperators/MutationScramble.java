package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationScramble<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationScramble(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
        int tamGenoma = ind.getNumGenes();

        int cut1;
        int cut2;

        do {
            //escolhe 2 pontos entre 0 e o tamanho do genoma
            cut1 = GeneticAlgorithm.random.nextInt(tamGenoma);
            cut2 = GeneticAlgorithm.random.nextInt(tamGenoma);

        }while (cut1 >= cut2); //assegura que cut2 > cut1

        //loop da troca, começa no cut1 e acaba no cut2
        for (int i = cut1; i < cut2 ; i++) {

            int j1, j2 = 0;

            //assegura que valor de j1 e j2 está entre cut1 e cut2
            do {
                j1 = GeneticAlgorithm.random.nextInt(cut2+1); //é adicionado 1, pra incluir o valor de cut2
                j2 = GeneticAlgorithm.random.nextInt(cut2+1);

            }while (j1 < cut1 && j2 < cut1); //j1 e j2 são maiores que cut1

            int aux = ind.getGene(j1);

            //trocamos o elemento do j1 com o elemento do j2
            ind.setGene(ind.getGene(j1),ind.getGene(j2));
            ind.setGene(ind.getGene(j2),aux);
        }
    }

    @Override
    public String toString(){
        return "Scramble mutation (" + probability + ")";
    }
}