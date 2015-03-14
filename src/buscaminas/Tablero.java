package buscaminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marca Diago
 */
public class Tablero
{
    public Celda[][] celdas = new Celda[8][8]; 
    public Tablero()
    {
        /*for(int i=0; i<8;i++)
            for(int j=0;j<8;j++)
                tablero[i][j]=new Celda(new JButton());*/
                rellenaMatriz(0);
    }
    
    
    
    public void rellenaMatriz(int a){
        if(a<8){    
            rellenaMatriz2(a,0);
            rellenaMatriz(a+1);
        }
    } 

    public void rellenaMatriz2(final int a,final int b){
        if(b<8){
            celdas[a][b]=new Celda(new JButton());
            celdas[a][b].getBoton().addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //AQUI VA EL CODIGO QUE SE EJECUTARA CADA VEZ QUE SE CLIKEE EN UNA CASILLA DE LA MATRIZ
                    System.out.println(a+","+b);
                }
            });
            rellenaMatriz2(a,b+1);
        }
    } 
    
}
