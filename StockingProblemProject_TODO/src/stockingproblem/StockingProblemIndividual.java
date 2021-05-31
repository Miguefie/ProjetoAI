package stockingproblem;

import algorithms.IntVectorIndividual;
import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private int[][] material; // Fenotipo
    private double nCuts;
    private double tamMaxPec; //quantas colunas gastei para colucar material?
    private char materialChar; //para mostrar no toString

    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);

        //TODO
        List<Integer> itemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemList.add(i);
        }

        Random rn = GeneticAlgorithm.random;
        Integer itemAleatorio; // Random (1...N)

        for (int i = 0; i < this.genome.length; i++) {

            if(!itemList.isEmpty()) { //para ñ dar: IllegalArgumentException: Bound must be positive

                itemAleatorio = itemList.get(rn.nextInt(itemList.size()));
                this.genome[i] = itemAleatorio.intValue(); // Adiciona o Item aleatorio ao Genotipo
                itemList.remove(itemAleatorio);
            }
        }

        //System.out.println(Arrays.toString(genome));
    }

    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);

        //TODO
        this.material = original.material;
        this.nCuts = original.nCuts;
        this.tamMaxPec = original.tamMaxPec;
        this.materialChar = original.materialChar;
    }

    @Override
    public double computeFitness() {
        //TODO
        material = new int[problem.getMaterialHeight()][problem.getMaterialLength()];
        nCuts = 0;
        tamMaxPec = 0;

        boolean adicionado = false;
        double tamPec = 0;
        double nCutsPeso = 0;
        double tamMaxPecPeso = 0;

        for (int k = 0; k < genome.length; k++) { //1º percorre genoma, para ver em q parte da matrix encaixa peça

            for (int j = 0; j < problem.getMaterialLength() - 1; j++) { //2º percorre colunas (quero colucar peça o mais a cima e à esquerda possível)
                for (int i = j; i < problem.getMaterialHeight() - 1; i++) { //3º percorre linhas

                    /*se posições forem diferentes houve corte*/
                    if (material[i][j] != material[i][j+1]){ //cortes na horizontal
                        nCuts++;
                    }
                    if (material[i][j] != material[i+1][j]){ //cortes na vertical
                        nCuts++;
                    }

                    Item itemAtual = problem.getItems().get(genome[k]); //onde está o item

                    if (checkValidPlacement(itemAtual, material, i, j)){

                        placement(itemAtual, material); //método aux para add item

                        adicionado = true;

                        tamPec = itemAtual.getColumns();
                        if (tamPec > tamMaxPec){ //se tamanho for maior substituir
                            tamMaxPec = tamPec;
                        }

                        break; //como já add peça à matrix temos de sair dos 'for' que percorrem a matrix
                    }
                }

                if (adicionado){
                    break; //item foi add, já ñ é preciso procurar mais posições. Continuar a percorrer o genoma
                }
            }
        }

        System.out.println("\n");
        System.out.println("nCuts1 " + nCuts);
        System.out.println("tamMaxPec1 " + tamMaxPec);

        //nCuts e tamMaxPec não podem ter o mesmo peso:
        nCutsPeso = nCuts*0.3;
        tamMaxPecPeso = tamMaxPec*0.7;

        fitness = nCutsPeso + tamMaxPecPeso;

        System.out.println("nCuts2 " + nCutsPeso);
        System.out.println("tamMaxPec2 " + tamMaxPecPeso);
        System.out.println("fitness " + fitness);

        return fitness;
    }

    //TODO
    private void placement(Item item, int[][] material) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {

                    material[i][j] = item.getRepresentation(); //Colocar peça!!!

                }
            }
        }
    }

    private boolean checkValidPlacement(Item item, int[][] material, int lineIndex, int columnIndex) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {
                    if ((lineIndex + i) >= material.length
                            || (columnIndex + j) >= material[0].length
                            || material[lineIndex + i][columnIndex + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        //TODO
        sb.append("\n\nNºCortes: " + nCuts);
        sb.append("\n\nTamanhoMaxPeça: " + tamMaxPec);

        sb.append("\n\nFitness: ");
        sb.append(fitness);

        sb.append("\n\nGenoma: " + Arrays.toString(genome));

        sb.append("\n\nMatriz: ");
        for (int i = 0; i < genome.length; i++) {
            materialChar = problem.getItems().get(genome[i]).getRepresentation();
            sb.append(materialChar + "\t");
        }

        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(StockingProblemIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public StockingProblemIndividual clone() {
        return new StockingProblemIndividual(this);

    }
}