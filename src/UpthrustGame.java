import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UpthrustGame {
    String[][] matrizDeJogo;
    ArrayList<String[][]> possibilidades;

    private Boolean isPLayer1;

    public UpthrustGame() {
        this.matrizDeJogo = new String[][] { { "0", "0", "0", "0" },
                { "0", "0", "0", "0", },
                { "0", "0", "0", "0" },
                { "0", "0", "0", "0" },
                { "0", "0", "4", "0" },
                { "3", "0", "0", "0" },
                { "0", "0", "0", "0" },
                { "1", "2", "3", "4" },
                { "2", "3", "0", "1" },
                { "0", "4", "1", "2" },
                { "4", "1", "2", "3" } };
        this.isPLayer1 = true;
        this.possibilidades = new ArrayList<>();
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
     *
     */
    public void suc() {
        for (int i = 0; i < matrizDeJogo.length; i++) {
            int n = numberOfJumps(matrizDeJogo[i]);
            for (int k = 0; k < matrizDeJogo[i].length; k++) {
                if ((isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("2"))
                        || (isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("3"))) {
                    if (i - n >= 0 && matrizDeJogo[i - n][k].equalsIgnoreCase("0") ) {/*&& maiorPosicao(matrizDeJogo[i][k])*/
                        String[][] tempArray = copy(matrizDeJogo);
                        String temp = tempArray[i - n][k];
                        tempArray[i - n][k] = tempArray[i][k];
                        tempArray[i][k] = temp;
                        if (!color(i, tempArray[i][k])) {//Este metodo não funciona verificar mais tarde ou com o professor
                            possibilidades.add(tempArray);
                        }
                    } else if ((!isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("1"))
                            || (!isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("4"))) {
                        if (matrizDeJogo[1 - n][k].equalsIgnoreCase("0")) {
                            String[][] tempArray = copy(matrizDeJogo);

                            String temp = tempArray[i - n][k];
                            tempArray[i - n][k] = tempArray[i][k];
                            tempArray[i][k] = temp;
                            possibilidades.add(tempArray);
                        }
                    }
                }
            }

        }
    }
/*
* Possiblilidade Não contada:
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 0, 0]
[0, 0, 4, 0]
[3, 0, 0, 0]
[0, 0, 0, 2]
[1, 2, 3, 4]
[2, 3, 0, 1]
[0, 4, 1, 0]
[4, 1, 2, 3]
*
* */
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
     * retorna verdadeiro se o elemento é o maior da sua posição
     *
     *
     * @param cor cor que queremos checar se é ou não a maior posição
     * @return retorna verdadeiro se for a unica retorna false se não for a unica
     */
    public boolean maiorPosicao( String cor) {
        for (int i = matrizDeJogo.length-1; i > 0; i--) {
            for (int k = 0; k < 4; k++) {
                if (matrizDeJogo[i][k].equalsIgnoreCase(cor)) {
                    System.out.println("é o maior da posição desse numero " + matrizDeJogo[i][k] + "  " + cor);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * retorna verdadeiro se for a unica da sua cor na linha da matrizDeJogo
     * ##############Não funciona direito##################
     * 
     * @param m   linha(array) da matrizDeJogo
     * @param cor cor que queremos checar se existe outra igual na mesma linha
     * @return
     */
    public boolean color(int m, String cor) {

        for (int i = 0; i < 4; i++) {
            System.out.println(matrizDeJogo[m][i]);
            if(matrizDeJogo[m][i].equalsIgnoreCase("0"))
            {
                continue;
            } else if (matrizDeJogo[m][i].equalsIgnoreCase(cor)) {
                System.out.println("tem cor no mesmo sitio");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {

        for (int i = 0; i < possibilidades.size(); i++) {
            System.out.println("Possiblilidade " + (i + 1) + " :");
            for (int k = 0; k < possibilidades.get(i).length; k++) {
                System.out.println("" + Arrays.toString(possibilidades.get(i)[k]));
            }
            System.out.println("\n");
        }
        return "\n";
    }
}
