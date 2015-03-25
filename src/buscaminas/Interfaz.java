package buscaminas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marca Diago
 */
public class Interfaz {
    public static JButton botonRei = new JButton("Re");
    public static    Tablero tablero = new Tablero();
    public static JPanel panelMatriz= new JPanel();
            
    public static void rellenaPanelMatriz(){
        //Pasar este metodo a recursivo
        for(int i=0; i<tablero.celdas.length;i++)
            for (int j=0;j<tablero.celdas.length;j++)
                panelMatriz.add(tablero.celdas[i][j].getBoton());
        
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
        JLabel numMinasLab = new JLabel("Minas restantes:"); 
        botonRei.setBounds(175, 0, 70, 50);
        numMinasLab.setBounds(280, 0, 95, 35);
        panelTop.add(botonRei);
        panelTop.add(numMinasLab);
        //PARTE GRAFICA
     
        
        rellenaPanelMatriz();
        //NO TOCAR ESTO SIEMPRE AL FINAL
        pane.add(panelTop);
        pane.add(panelMatriz);
    
    }
}
