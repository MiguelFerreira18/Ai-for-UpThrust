import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UpthrustGame {
    String[][] matrizDeJogo;


    private Boolean isPLayer1;

    public UpthrustGame(String[][] matrizDeJogo) {
        this.matrizDeJogo = matrizDeJogo;
        isPLayer1 = true;

    }


    /*
     * this.matrizDeJogo = new String[][]
     * { { "0", "0", "0", "0" },
     * { "0", "0", "0", "0",},
     * { "0", "0", "0", "0" },
     * { "0", "0", "0", "0" },
     * { "0", "0", "0", "0" },
     * { "0", "0", "0", "0" },
     * { "0", "0", "0", "0" },
     * { "1", "2", "3", "4" },
     * { "2", "3", "4", "1" },
     * { "3", "4", "1", "2" },
     * { "4", "1", "2", "1" } };
     */
    public String[][] getMatrizDeJogo() {
        return matrizDeJogo;
    }

    public void setMatrizDeJogo(String[][] matrizDeJogo) {
        this.matrizDeJogo = matrizDeJogo;
    }

    /**
     * player 1 : 2 and 3
     * player 2 : 1 and 4
     */

    public ArrayList<UpthrustGame> suc() {
        ArrayList<UpthrustGame> possibilidades = new ArrayList<>();
        for (int i = 0; i < matrizDeJogo.length; i++) {
            int n = numberOfJumps(matrizDeJogo[i]);
            for (int k = 0; k < matrizDeJogo[i].length; k++) {
                if ((isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("2"))
                        || (isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("3"))) {
                    if (i - n >= 0 && matrizDeJogo[i - n][k].equalsIgnoreCase("0")
                            && maiorPosicao(i, k, matrizDeJogo[i][k])) {// esqueci me de contar quantos estavam na
                        // linha: Funciona

                        String[][] tempArray = copy(matrizDeJogo);
                        String temp = tempArray[i - n][k];
                        tempArray[i - n][k] = tempArray[i][k];
                        tempArray[i][k] = temp;
                        //printMatrizInv(tempArray);
                        if (color(i - n, k, tempArray[i - n][k])) {// simples erro de logica: corrigido
                            printMatrizInv(tempArray);
                            possibilidades.add(new UpthrustGame(tempArray));
                        }
                        //para fazer todos verificar antes se está ou não ativo;
                    }
                }if ((isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("1"))
                        || (isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("4"))) {
                    if (i - n >= 0 && matrizDeJogo[i - n][k].equalsIgnoreCase("0")
                            && maiorPosicao(i, k, matrizDeJogo[i][k])) {

                        String[][] tempArray = copy(matrizDeJogo);

                        String temp = tempArray[i - n][k];
                        tempArray[i - n][k] = tempArray[i][k];
                        tempArray[i][k] = temp;

                        if (color(i - n, k, tempArray[i - n][k])) {
                            possibilidades.add(new UpthrustGame(tempArray));
                            printMatrizInv(tempArray);
                        }
                    }
                }
            }
        }
        return possibilidades;
    }
    public void adicionaNovoEstado(UpthrustGame newEstado){
        printMatrizInv(newEstado.getMatrizDeJogo());

    }
    /*
     * Regras de jogo a fazer:
     * --Checar se é a unica cor igual na mesma linha
     * --Checar se é a maior posição de uma determinada cor
     */

    /**
     * calcula o numero de saltos que uma peça pode dar
     *
     * @param p Linha da matrizDeJogo
     * @return retorna o numero de peças que existem nessa linha
     */
    public int numberOfJumps(String[] p) {
        int n = 0;
        for (int i = 0; i < p.length; i++) {
            if (!p[i].equalsIgnoreCase("0"))
                n++;
        }
        return n;
    }

    /**
     * retorna uma matriz identica à que é enviada(evita que seja usada a mesma
     * referencia de forma a evitar erros)
     *
     * @param p
     * @return
     */
    public String[][] copy(String[][] p) {
        String[][] newArr = new String[p.length][p[0].length];
        for (int i = 0; i < newArr.length; i++) {
            for (int k = 0; k < newArr[i].length; k++) {
                newArr[i][k] = p[i][k];
            }
        }
        return newArr;
    }

    /**
     * Este metodo retorna falso se a cor que é encontrada está
     * na mesma linha, coluna e tem 0 elementos na mesma linha.
     * Retorna verdadeiro se a cor não estiver na
     * mesma linha,coluna e tem de ter mais do que um elemento na mesma linha
     *
     * @param linhaCorPos  linha em que se encontra a cor que queremos ver se é a
     *                     maior
     * @param colunaCorPos coluna em que se encontra a cor que queremos ver se é a
     *                     maior
     * @param cor          cor que queremos ver se é a maior
     * @return retorna verdadeiro ou falso
     */
    public boolean maiorPosicao(int linhaCorPos, int colunaCorPos, String cor) {

        for (int i = 0; i < matrizDeJogo.length; i++) {
            for (int k = 0; k < 4; k++) {

                if (matrizDeJogo[i][k].equals(cor) && i == linhaCorPos && colunaCorPos == k
                        && numberOfJumps(matrizDeJogo[i]) == 1) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Este metodo retorna falso se existir dois elementos com a mesma cor nessa
     * mesma linha e retorna verdaderio se apenas existir um elemento com essa cor
     * nessa linha
     *
     * @param linhaCorPos linha que queremos percorrer para ver se existe ou não
     *                    mais do que uma cor igual
     * @param corPos      coluna onde se encontra o elemento que sabemos que existe,
     *                    para que seja ignorado
     * @param cor         cor que queremos ver se existe ou não
     * @return
     */
    public boolean color(int linhaCorPos, int corPos, String cor) {
        if (linhaCorPos >= 5) {
            for (int i = 0; i < matrizDeJogo[linhaCorPos].length; i++) {
                if (i == corPos) {
                    continue;
                } else if (matrizDeJogo[linhaCorPos][i].equalsIgnoreCase(cor)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * metodo simples para imprimir uma matriz
     *
     * @param matriz matriz que queremos imprimir
     */
    public void printMatrizInv(String[][] matriz) {
        for (int i = 0; i < matrizDeJogo.length; i++) {
            System.out.println(Arrays.toString(matriz[i]) + " linha   " + i);
        }
        System.out.println("\n");
    }

    @Override
    public String toString() {

        for (int i = 0; i < this.matrizDeJogo.length; i++) {
            System.out.println(Arrays.toString(matrizDeJogo[i]));
        }
        return "---------------------------------\n";
    }
}
