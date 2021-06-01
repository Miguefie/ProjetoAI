package stockingproblem;

import algorithms.IntVectorIndividual;
import ga.GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes

    private int[][] material; // Fenotipo

    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);

        //TODO
        material = new int[problem.getMaterialLength()][problem.getMaterialHeight()];

        List<Integer> itemList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            itemList.add(i);
        }

        Random rn = GeneticAlgorithm.random;
        Integer itemAleatorio = itemList.get(rn.nextInt(itemList.size())); // Random (1...N)
        this.genome[0] = itemAleatorio.intValue(); 
        itemList.remove(itemAleatorio);

        for (int i = 1; i < this.genome.length; i++) {
            itemAleatorio = itemList.get(rn.nextInt(itemList.size()));
            this.genome[i] = itemAleatorio.intValue(); // Adiciona o Item aleatorio ao Genotipo
            itemList.remove(itemAleatorio);
        }

    }

    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);
        this.material = original.material;
        //this.nCuts = original.nCuts;
        //this.tamMaxPec = original.tamMaxPec;

        //this.pecasChar = original.pecasChar;
        //this.matrixArray = original.matrixArray;
    }

    @Override
    public double computeFitness() {
        //TODO
        double cuts = 0;
        int[] columnIndex = new int[problem.getMaterialLength()];
        boolean flag=false;
        for (int k = 0; k < genome.length; k++) {
            Item itemAtual = problem.getItems().get(k);
            for (int i = 0; i < problem.getMaterialLength(); i++) { // Linhas Do Material
                if(material[i][0] == 0) {
                    columnIndex[i] = 0;
                }
                for (int j = columnIndex[i]; j < itemAtual.getColumns() + columnIndex[i]; j++) { // Colunas do Material (Vai ser o Tamanho das Pecas)
                    if (checkValidPlacement(itemAtual, material, i, j)) {
                        cuts++;
                        material[i][j] = itemAtual.getRepresentation(); // Adiciona ao Fenotipo
                        flag=false;
                    }
                    else
                    {
                        flag=true;
                        break;
                    }

                }

                columnIndex[i] += itemAtual.getColumns();

                if(itemAtual.getLines()<=i+1 && !flag) // se o Nr de linhas do Fenotipo for maior que o Nr de linhas do Item (+1 porque nao existe linhas "0")
                {
                    break;
                }
            }
        }
        return cuts;
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
        sb.append("fitness: ");
        sb.append(fitness);
        //TODO
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