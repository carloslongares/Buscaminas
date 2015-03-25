package buscaminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marca Diago
 */
public class Tablero
{
    public Celda[][] celdas = new Celda[8][8]; 
    public boolean primerTurno = true;
    
    public Tablero()
    {
        rellenaMatriz(0);
        //Boton de reinicio se le ha puesto un mouse listener para capturar el evento
        Interfaz.botonRei.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                reiniciar();
            }
         });
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
            //A todos los botones de la matriz se les ha puesto un mouse listener para capturar los clicks o eventos
            celdas[a][b].getBoton().addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                      juego(e,celdas[a][b],b,a);
                }
             });
            rellenaMatriz2(a,b+1);
        }
    }
    
    public void juego(MouseEvent e,Celda celda,int col,int row){
            //si el boton izquierdo es pulsado
            if(e.getButton() == MouseEvent.BUTTON1)
	    {
                if(primerTurno){
                    primerTurno= false;
                    celda.getBoton().setText("V");
                    celda.setVacia(true);
                    ponMinas(col, row);
                    System.out.println(col+","+row);
                    buscarMinas(col,row);
                }else{
                     buscarMinas(col,row);
                    System.out.println(col+","+row);
                }
	    }	    
            //si el boton derecho es pulsado
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
               if(!celda.getVacia() && !primerTurno)
                    if(celda.getMarca()){
                        celda.setMarca(false);
                        celda.getBoton().setText("");    
                    }else{
                        celda.setMarca(true);
                        celda.getBoton().setText("Ma");
                    }
            }
    }
    
    //Pasar este metodo a recursivo
    public void ponMinas(int col,int row){
        int cont=0;
        while(cont<10){
            int a=(int)(Math.random()*7);
            int b=(int)(Math.random()*7);
            if(!celdas[a][b].getMina()&&
                    ( (col!=b || row!=a)&& (col-1!=b || row-1!=a)&& (col!=b || row-1!=a)&& (col+1!=b || row-1!=a)
                       &&(row!=a|| col-1!=b)&&(row!=a|| col+1!=b)
                       &&(row+1!=a || col-1!=b)&&(row+1!=a || col!=b)&&(row+1!=a || col+1!=b))){
                celdas[a][b].setMina(true);
                celdas[a][b].getBoton().setText("Mi");
                cont++;
            }
        }
    }
    
    public void buscarMinas(int col,int row){
        //Intentar pensarlo en grupo
        //obtengo la posicion de la primera adyacente
        int firstCol=col-1;
        int firstRow= row-1;
        //obtengo la posicion de la ultima adyacente
        int lastCol=col+1;
        int lastRow=row+1;
        
        for(int i=firstRow; i<=lastRow;i++)
            for(int j =firstCol; j<=lastCol;j++){
                if(!celdas[i][j].getMina()){
                    celdas[i][j].getBoton().setText("V");
                    celdas[i][j].setVacia(true);
                }
            }
        
    }
    
    public void reiniciar(){
        
        limpiarBotones();
        Interfaz.panelMatriz.removeAll();
        rellenaMatriz(0);
        Interfaz.rellenaPanelMatriz();
        primerTurno=true;
    }

    public void limpiarBotones(){
    //Pasar este metodo a recursivo
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                celdas[i][j].getBoton().setText("");    
    }
    
    
}
