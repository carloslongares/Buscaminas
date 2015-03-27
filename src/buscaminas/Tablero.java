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
                    //celda.getBoton().setText("V");
                    //celda.setVacia(true);
                    ponMinas(col, row);
                    //System.out.println(col+","+row);
                    contarTablero();
                    buscarMinas(col,row);
                }else{
                     buscarMinas(col,row);
                    //System.out.println(col+","+row);
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
            if(!celdas[a][b].getMina()&& (col!=b || row!=a)){
                celdas[a][b].setMina(true);
                celdas[a][b].getBoton().setText("Mi");
                cont++;
            }
        }
    }
    
    public void buscarMinas(int col,int row){
        //Intentar pensarlo en grupo
        //obtengo la posicion de la primera adyacente
        int firstCol=getCol(col-1);
        int firstRow= getRow(row-1);
        //obtengo la posicion de la ultima adyacente
        int lastCol=getCol(col+1);
        int lastRow=getRow(row+1);
    
        if(celdas[row][col].getNumMinasAd()!=0&&!celdas[row][col].getMina())
           celdas[row][col].getBoton().setText(Integer.toString(celdas[row][col].getNumMinasAd()));
        else{
            celdas[row][col].getBoton().setText("V");
            celdas[row][col].setVacia(true);
            for(int i=firstRow; i<=lastRow;i++)
                for(int j =firstCol; j<=lastCol;j++)
                    if(!celdas[i][j].getMina()&&celdas[i][j].getNumMinasAd()==0&&(col!=j || row!=i)){
                        celdas[i][j].getBoton().setText("V");
                        if(celdas[i][j].getVacia()==false){
                            buscarMinas(j,i);
                        }
                    }else if(!celdas[i][j].getVacia())
                        celdas[i][j].getBoton().setText(Integer.toString(celdas[i][j].getNumMinasAd()));
          
        }
    }
    
    public void contarTablero(){
        for (int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                if(!celdas[i][j].getMina())
                    contarAdyacentes(j, i);
    
    }
    
    public void contarAdyacentes(int col, int row){
        int firstCol=getCol(col-1);
        int firstRow= getRow(row-1);
        //obtengo la posicion de la ultima adyacente
        int lastCol=getCol(col+1);
        int lastRow=getRow(row+1);
        
        for(int i=firstRow; i<=lastRow;i++)
            for(int j =firstCol; j<=lastCol;j++)
                if(celdas[i][j].getMina())
                    celdas[row][col].setNumMinasAd(celdas[row][col].getNumMinasAd()+1);
                    

           
       // if(celdas[row][col].getNumMinasAd()!=0)
         //   celdas[row][col].getBoton().setText(Integer.toString(celdas[row][col].getNumMinasAd()));
        
       }
    
    public int getRow(int row){
        if(row<0){
         return 0;
        }else if(row>7)
            return 7;
        else
            return row;
    }
    
    public int getCol(int col){
        if(col<0){
         return 0;
        }else if(col>7)
            return 7;
        else
            return col;
    }
    
    public void reiniciar(){
        
        limpiarBotones(0,0);
        Interfaz.panelMatriz.removeAll();
        rellenaMatriz(0);
        Interfaz.rellenaPanelMatriz(0,0);
        primerTurno=true;
    }

   /* public void limpiarBotones(){
    //Pasar este metodo a recursivo
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
                celdas[i][j].getBoton().setText("");    
    }*/
    public void limpiarBotones(int i, int j){
        if(j==celdas.length-1){
            if (i==celdas.length-1){
                celdas[i][j].getBoton().setText("");
            }
            else{
                celdas[i][j].getBoton().setText("");
                limpiarBotones(i+1,0);
            }
            
        }
        else{
            celdas[i][j].getBoton().setText("");
            limpiarBotones(i,j+1);
        }
    }
    
}
