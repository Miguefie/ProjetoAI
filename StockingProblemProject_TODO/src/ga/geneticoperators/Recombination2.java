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
        SegmentBuilder = new ArrayList<Integer>(); //ArrayList para auxiliar no crossOver

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
        crossOver(child2,ind1,ind2); //na criação do child2 os papeis do ind1 e ind2 estão invertidos
        */
        crossOver(child1,ind1,ind2);
        crossOver(child2,ind2,ind1); //na criação do child2 os papeis do ind1 e ind2 estão invertidos

        //temos de substitiur os arrays do individuo pelos arrays aux (child 1 e 2)
        //uma vez que, os filhos vão ser os novos pais
        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }

    }

    public void crossOver(int[] filho, I pai1, I pai2){

        //Genes são copiados do pai1 para o filho:
        SegmentBuilder.clear();

        int index = cut2 + 1;

        //se cutPoint2 + 1  == pai1.length add todos os elementos do pai1 diretamente no SegmentBuilder ArrayList.
        if(index == pai1.getNumGenes()) {
            for(int x = 0; x < pai1.getNumGenes(); x++){
                SegmentBuilder.add(pai1.getGene(x));
            }
        }
        //se cutPoint2 + 1  != pai1.length, o genoma do pai1 é percorrido do principio ao fim para add no SegmentBuilder
        else {
            for(index = cut2 + 1; index < pai1.getNumGenes(); index++){
                SegmentBuilder.add(pai1.getGene(index));
            }
            for(index = 0; index <= cut2; index++){
                SegmentBuilder.add(pai1.getGene(index));
            }
        }

        //System.out.println(SegmentBuilder);

        //É necessário verificar se existem elementos indesejados na sequencia de genes.
        //E se tal se confirmar, estes têm de ser removidos.
        for(int indexInSegment = cut1; indexInSegment <=cut2; indexInSegment++){
            remove_SpecifiedElement(pai2.getGene(indexInSegment));
        }

        //copia sequencia de genes do pai2 para o filho
        for(int x = cut1; x <= cut2; x++){
            filho[x] = pai2.getGene(x);
        }

        int tempIndex = 0;
        //Copia elementos do SegmentBuilder para o filho, começando para lá do ponto de corte final
        for(int y = cut2 + 1; y < filho.length; y++){

            if(y == filho.length){ break; }

            filho[y] = SegmentBuilder.get(tempIndex);
            tempIndex++;
        }

        //Copia elementos do SegmentBuilder para o filho, acabando no ponto de corte inicial
        for(int z = 0; z < cut1; z++){

            filho[z] = SegmentBuilder.get(tempIndex);
            tempIndex++;
        }

    }

    private void remove_SpecifiedElement(int elementToRemove){
        for(int index = 0; index< SegmentBuilder.size(); index++){
            if(SegmentBuilder.get(index) == elementToRemove){
                SegmentBuilder.remove(index);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Order recombination (" + probability + ")";
    }
}