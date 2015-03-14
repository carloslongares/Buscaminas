package buscaminas;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marca Diago
 */
public class Interfaz {
    public static void main(String[] args){
        Tablero tablero = new Tablero();
        
        //PARTE GRAFICA
        JFrame frame = new JFrame("Buscaminas");
        frame.setSize(500, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        Container pane = frame.getContentPane();
        pane.setLayout(null);
        
        JPanel panelMatriz= new JPanel();
        GridLayout grid = new GridLayout(8, 8);
        panelMatriz.setLayout(grid);
        panelMatriz.setBounds(30, 140, 420, 390);
  
        JPanel panelBoton= new JPanel();
        panelBoton.setBounds(30, 30, 420, 90);
        panelBoton.setLayout(null);
        JButton boton = new JButton("Reinciar");
        JLabel numMinasLab = new JLabel("Minas restantes:"); 
        boton.setBounds(175, 0, 70, 50);
        numMinasLab.setBounds(280, 0, 95, 35);
        panelBoton.add(boton);
        panelBoton.add(numMinasLab);
        //PARTE GRAFICA
     
        
        //FALTA POR HACER ESTOS FOR EN FORMA RECURSIVA
        for(int i=0; i<tablero.celdas.length;i++)
            for (int j=0;j<tablero.celdas.length;j++)
            panelMatriz.add(tablero.celdas[i][j].getBoton());
        
        //NO TOCAR ESTO SIEMPRE AL FINAL
        pane.add(panelBoton);
        pane.add(panelMatriz);
    
    }
}
