/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alejandra
 */
public class Gato {
    public String[][] tablero;
    
    public Gato(){
        tablero = new String[3][3];
        for (int i = 0; i < tablero.length; i++) {
            Arrays.fill(tablero[i], "");
        }
        
    }
    
    public String getPosition(int i, int j){
        return tablero[i][j];
    }
    
    public void changePosition(int x, int y, String player){
        tablero[x][y]=player;
    }
    
    public boolean finish(){
        return false;
    }
    
    
    
    
    public ArrayList<int[]> movimentosPosibles(){
    	ArrayList<int[]> moves = new ArrayList<int[]>();
    	for(int i = 0 ; i <  tablero.length ; i++){
    		for(int j = 0 ; j <  tablero.length ; j++){
    			if(tablero[i][j].isEmpty())
    				moves.add(new int[]{i,j});
    		}
    	}
    	return moves;
    	
    }
    
    
    
    

	/**
     * 
     * @return X si gana la maquina o 0 si gana el jugador 
     */
    public String winner(){
        String win= "";
        if(tablero[0][0]==tablero[0][1]&&tablero[0][1]==tablero[0][2]){
            win=tablero[0][0];
        }else{
            if(tablero[1][0]==tablero[1][1]&&tablero[1][1]==tablero[1][2]){
                win=tablero[1][0];
            }else{
                if(tablero[2][0]==tablero[2][1]&&tablero[2][1]==tablero[2][2]){
                    win=tablero[2][0];
                }else{
                    if(tablero[0][0]==tablero[1][0]&&tablero[1][0]==tablero[2][0]){
                        win=tablero[0][0];
                    }else{
                        if(tablero[0][1]==tablero[1][1]&&tablero[1][1]==tablero[2][1]){
                            win=tablero[0][1];
                        }else{
                            if(tablero[2][0]==tablero[2][1]&&tablero[2][1]==tablero[2][2]){
                                win=tablero[2][0];
                            }else{
                                if(tablero[0][0]==tablero[1][1]&&tablero[1][1]==tablero[2][2]){
                                    win=tablero[0][0];
                                }else{
                                    if(tablero[0][2]==tablero[1][1]&&tablero[1][1]==tablero[2][0]) win=tablero[0][2];
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return win;
    }
    
    
}
