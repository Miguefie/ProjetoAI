package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
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

        //encontra ponto médio entre cut1 e cut2, porque vamos usá-lo para trocar os genes
        int mid = cut1 + ((cut2 +1) - cut1)/2;
        int endCount = cut2; //é o index do ultimo elemento a ser trocado

        //loop da troca, começa no cut1 e acaba no mid
        for (int i = cut1; i < mid ; i++) {

            int aux = ind.getGene(i);

            //trocamos 1º elemento a ser trocado(i) com ultimo elemento a ser trocado(endCount)
            ind.setGene(i,ind.getGene(endCount));
            ind.setGene(endCount,aux);

            //decrementa index do ultimo elemento a ser trocado
            endCount--;
        }
    }

    @Override
    public String toString(){
        return "Inversion mutation";
    }
}