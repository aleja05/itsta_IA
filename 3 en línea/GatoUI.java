/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 *
 * @author Alejandra
 */
public class GatoUI extends JFrame {

    Font f;
    JPanel contenedorBotones;
    boolean turno;
    JButton[][] botones;
    Gato juego;
    JLabel jugador;
    int k;
    int l;
    int [] mov;
    Board b;

    public static void main(String[] args) {
        GatoUI game = new GatoUI();
        game.setVisible(true);
    }

    public GatoUI() {
        juego = new Gato();
        turno = true;
        setTitle("Gato");
        b = new Board();
        f = new Font("Century Gothic", Font.PLAIN, 20);
        setSize(400, 400);
        preparaPantalla();
        prepareAccionesInicio();
        setResizable(false);
    }

    private void preparaPantalla() {
        jugador = new JLabel();
        jugador.setText("Turno: " + ((turno) ? "Jugador." : "Máquina."));
        setLayout(new BorderLayout());
        add(jugador, BorderLayout.PAGE_END);
        Dimension tam = this.getContentPane().getSize();
        contenedorBotones = new JPanel();
        contenedorBotones.setLayout(new GridLayout(3, 3));
        crearBotones();
        add(contenedorBotones);
    }

    private void crearBotones() {
        botones = new JButton[3][3];
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones.length; j++) {
                botones[i][j] = new JButton(((juego.getPosition(i, j)) != null) ? juego.getPosition(i, j) : "");
                contenedorBotones.add(botones[i][j]);
            }
        }
        prepareAccionesBotones();
    }

    private void prepareAccionesInicio() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                salga();
            }
        });
    }

    private void salga() {
        Object[] opciones = {"Aceptar", "Cancelar"};
        int eleccion = JOptionPane.showOptionDialog(this, "En realidad desea cerrar la aplicacion", "Mensaje de Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
        if (eleccion == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void prepareAccionesBotones() {
        for (k = 0; k < botones.length; k++) {
            for (l = 0; l < botones.length; l++) {
                botones[k][l].addActionListener(new ActionListener() {
                    final int i = k;
                    final int j = l;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        juega(i, j);
                    }
                });
            }
        }
    }

    private void juega(int i, int j) {
    	
    	
    	
    	
    	
    	
        boolean win = false;
        if (!b.isGameOver()) {
            //if (turno) {
                if (b.board[i][j] == 0) {
                	Point userMove = new Point(i, j);
                	b.placeAMove(userMove, 2);
                    //juego.changePosition(i, j, "O");
                    turno = !turno;
                    jugador.setText("Turno: " + ((turno) ? "Jugador." : "Máquina."));
                    win = b.isGameOver();
                } else {
                    JOptionPane.showMessageDialog(this, (Object) "No se puede jugar en esa casilla", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            //} else{
            	b.minimax(0, 1);
            	b.placeAMove(b.computersMove, 1);
                turno = !turno;
                jugador.setText("Turno: " + ((turno) ? "Jugador." : "Máquina."));
                win = b.isGameOver();
            //} 
            refresca();
        }
        if(b.isGameOver()){
        	
        	
        	String winner;
        	
            JOptionPane.showMessageDialog(this, "Juego Terminado, ganador:" + b.winner());
            b = new Board();
            turno = true;
            jugador.setText("Turno: " + ((turno) ? "Jugador." : "Máquina."));
            refresca();
        }

    }

    
    public int score(String[][] board){
    	String winner = juego.winner();
		if( winner.equals("X"))
			return 1;
		else if (winner.equals("O"))
			return -1;
		else 
			return 0;
	}
   /* public int minimax(Gato juego){
		if(gameIsOver()){
			return score(juego.tablero);
		}else{
			ArrayList<int[]> scores = new ArrayList<int[]>();
		
			
			ArrayList<int[]> possible_moves = juego.movimentosPosibles();
			for(int i = 0 ; i < possible_moves.size() ; i++){
				int[] m = possible_moves.get(i);
				juego.changePosition(m[0], m[1],  (turno)?"0":"X");
				scores.add(new int[]{minimax(juego), m[0], m[1]} );
				
			}
			int res[];
			if(turno){
				res = getMax(scores);
				mov = new int[]{res[1] , res[2]};
				return res[0];
			}else{
				res = getMin(scores);
				mov = new int[]{res[1] , res[2]};
				return res[0];
			}
			
		}
		
	}
    */
    
    public int[] getMax(ArrayList<int[]> scores){
    	int index = 0;
    	int max = -1;
    	
    	for(int i = 0; i < scores.size(); i++){
    		int score[] = scores.get(i);
    		if(max < score[0]){
    			max = score[0];
    			index = i;
    		}
    	}
    	return scores.get(index);
    }
    
    public int[] getMin(ArrayList<int[]> scores){
    	int index = 0;
    	int min = Integer.MAX_VALUE;
    	for(int i = 0; i < scores.size(); i++){
    		int score[] = scores.get(i);
    		if(min > score[0]){
    			min = score[0];
    			index = i;
    		}
    	}
    	return scores.get(index);
    }
   /* private boolean gameIsOver() {
    	String winner = juego.winner();
    	return (winner.equals("X") || winner.equals("O") ||  juego.empate());
		
	}*/
    
    private void juegaMaquina() {
    	
    }

    private void refresca() {
        contenedorBotones.removeAll();
        contenedorBotones.updateUI();
        botones = new JButton[3][3];
        for (int i = 0; i < botones.length; i++) {
            for (int j = 0; j < botones.length; j++) {
                botones[i][j] = new JButton((b.board[i][j] != 0)?((b.board[i][j] == 1)?"X" : "O") :"");
                contenedorBotones.add(botones[i][j]);
            }
        }
        prepareAccionesBotones();
    }
}
