import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class UpthrustGame {
    String[][] matrizDeJogo;
    ArrayList<String[][]> possibilidades;

   private Boolean isPLayer1;


    public UpthrustGame() {
        this.matrizDeJogo = new String[][]{{"0", "0", "0", "0"},
                                                                {"0", "0", "0", "0",},
                                                                {"0", "0", "0", "0"},
                                                                {"0", "0", "0", "0"},
                                                                {"0", "0", "0", "0"},
                                                                {"0", "0", "0", "0"},
                                                                {"0", "0", "0", "0"},
                                                                {"1", "2", "3", "4"},
                                                                {"4", "1", "2","3"},
                                                                {"3", "4", "1", "2"},
                                                                {"2","3" ,"4", "1" }};
        this.isPLayer1 = true;
        this.possibilidades = new ArrayList<>();
    }

    public String[][] getMatrizDeJogo() {
        return matrizDeJogo;
    }

    public void setMatrizDeJogo(String[][] matrizDeJogo) {
        this.matrizDeJogo = matrizDeJogo;
    }


    /**
     * player  1  : 2 and 3
     * player 2 : 1 and 4
     *
     */
    public void suc(){
        for (int i=0;i< matrizDeJogo.length;i++){
            int n = numberOfJumps(matrizDeJogo[i]);
            for (int k= 0;k<matrizDeJogo[i].length;k++){
               if((isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("2")) || (isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("3"))){
                   if(matrizDeJogo[i-n][k].equalsIgnoreCase("0")&& maiorPosicao(i,matrizDeJogo[i][k])){

                       String[][] tempArray = copy(matrizDeJogo);;
                       String temp = tempArray[i-n][k];
                       tempArray[i-n][k] = tempArray[i][k];
                       tempArray[i][k] = temp;
                       possibilidades.add(tempArray);
                   }else if((!isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("1") )||( !isPLayer1 && matrizDeJogo[i][k].equalsIgnoreCase("4"))){
                       if(matrizDeJogo[1-n][k].equalsIgnoreCase("0")) {
                       String[][] tempArray = copy(matrizDeJogo);;
                       String temp = tempArray[i-n][k];
                       tempArray[i-n][k] = tempArray[i][k];
                       tempArray[i][k] = temp;
                       possibilidades.add(tempArray);
                       }
                   }
               }
            }

        }
    }
/*public boolean verificaCenas(String cor){
        for(int i =0;i<matrizDeJogo.length;i++) {
            for (int k = 0; k < matrizDeJogo[i].length; k++) {
                if (matrizDeJogo[i][k].equalsIgnoreCase(cor)){

                }
            }
        }
}*/
    public int numberOfJumps(String[] p){
        int n = 0;
        for (int i =0;i<p.length;i++){
            if(!p[i].equalsIgnoreCase("0"))
            n++;
        }
        return n;
    }
    public String[][] copy(String[][] p){
        String[][] newArr = new String[p.length][p[0].length];
        for (int i = 0; i < newArr.length; i++) {
            for(int k =0;k< newArr[i].length;k++)
            {
                newArr[i] [k] = p[i][k];
            }
        }
        return newArr;
    }
    public boolean maiorPosicao(int m, String cor){
        for(int i = m-1;i> 0 ;i--){
            for(int k=0;k<4;k++){

                if(matrizDeJogo[i][k].equalsIgnoreCase(cor)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean color(int m,String cor){
        for(int i =0;i<4;i++){
            if(matrizDeJogo[m][i].equalsIgnoreCase(cor)){
                return true;
            }
        }
        return false;
    }
    @Override
    public String toString() {

        for (int i =0;i<possibilidades.size();i++){
            System.out.println("Possiblilidade " + (i+1) + " :");
            for(int k =0;k<possibilidades.get(i).length;k++){
                System.out.println(""+Arrays.toString(possibilidades.get(i)[k]));
            }
            System.out.println("\n");
        }
        return "\n";
    }
}
