package buscaminas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marc Diago
 */
public class Interfaz {
    public static JButton botonRei = new JButton();
    public static    Tablero tablero = new Tablero();
    public static JPanel panelMatriz= new JPanel();
    public static JLabel numMinasLab = new JLabel("Minas restantes: 10"); 

    public static void rellenaPanelMatriz(int i, int j){
        if(j==tablero.celdas.length-1){
            if (i==tablero.celdas.length-1){
                panelMatriz.add(tablero.celdas[i][j].getBoton());
            }
            else{
                panelMatriz.add(tablero.celdas[i][j].getBoton());
                rellenaPanelMatriz(i+1,0);
            }
            
        }
        else{
            panelMatriz.add(tablero.celdas[i][j].getBoton());
            rellenaPanelMatriz(i,j+1);
        }
    }
    
    public static void main(String[] args){
     
        //PARTE GRAFICA
        JFrame frame = new JFrame("Buscaminas");
        frame.setSize(500, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        Container pane = frame.getContentPane();
        pane.setLayout(null);
        
        GridLayout grid = new GridLayout(8, 8);
        panelMatriz.setLayout(grid);
        panelMatriz.setBounds(30, 140, 420, 390);
  
        JPanel panelTop= new JPanel();
        panelTop.setBounds(30, 30, 420, 90);
        panelTop.setLayout(null);
        
        botonRei.setIcon(new ImageIcon("Imagenes/REINICIO.jpg"));
        botonRei.setBounds(0, 0, 53, 50);
        numMinasLab.setBounds(200,0,120, 35);
        panelTop.add(botonRei);
        panelTop.add(numMinasLab);
        //PARTE GRAFICA
     
        
        rellenaPanelMatriz(0,0);
        //NO TOCAR ESTO SIEMPRE AL FINAL
        pane.add(panelTop);
        pane.add(panelMatriz);
    
    }
}
