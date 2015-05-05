package buscaminas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marc Diago
 */
public class Tablero
{
    public Celda[][] celdas = new Celda[8][8]; 
    public boolean primerTurno = true;
    private int numMinMarca=10;
    
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
            celdas[a][b].getBoton().setIcon(new ImageIcon("Imagenes/CELDA.jpg"));
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
                    ponMinas(col, row);
                    contarTablero();
                    buscarMinas(col,row);
                }else{
                    if(celda.getMina()){
                        perderJuego();
                    }else
                     buscarMinas(col,row);
                }
	    }	    
            //si el boton derecho es pulsado
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
                
               if(!celda.getVacia() && !primerTurno)
                    if(celda.getMarca()){
                        celda.setMarca(false);
                        celda.getBoton().setIcon(new ImageIcon("Imagenes/CELDA.jpg"));
                        numMinMarca++;
                        Interfaz.numMinasLab.setText("Minas restantes: "+numMinMarca);
                    }else if(numMinMarca!=0){
                        celda.setMarca(true);
                        celda.getBoton().setIcon(new ImageIcon("Imagenes/MARCA.jpg"));
                        numMinMarca--;
                        Interfaz.numMinasLab.setText("Minas restantes: "+numMinMarca);
                    }
               
               if(compruebaVictoria())
                {
                    JOptionPane.showMessageDialog(Interfaz.panelMatriz,"Has Ganado!!","=)",JOptionPane.PLAIN_MESSAGE);
                    reiniciar();
                }
                else{
                }
            }
    }
    
   
    public void ponMinas(int col,int row){
        int cont=0;
        while(cont<10){
            int a=(int)(Math.random()*7);
            int b=(int)(Math.random()*7);
            if(!celdas[a][b].getMina()&& (col!=b || row!=a)){
                celdas[a][b].setMina(true);
                cont++;
            }
        }
    }
    
    public void perderJuego(){
        for (int i=0; i<8;i++)
            for (int j=0; j<8;j++)
                if(celdas[i][j].getMina())
                     celdas[i][j].getBoton().setIcon(new ImageIcon("Imagenes/MINA.jpg"));
        JOptionPane.showMessageDialog(Interfaz.panelMatriz,"Has perdido","=(",JOptionPane.PLAIN_MESSAGE);
        reiniciar();
    }
    
    public boolean compruebaVictoria(){
        int numMinasMarc=0;
        for (int i=0; i<8;i++)
            for (int j=0; j<8;j++)
                if(celdas[i][j].getMina()&&celdas[i][j].getMarca())
                    numMinasMarc++;
        if(numMinasMarc==10){
            return true;
        }else
            return false;
        
    }
    
    public void buscarMinas(int col,int row){
            //obtengo la posicion de la primera adyacente
            int firstCol=getCol(col-1);
            int firstRow= getRow(row-1);
            //obtengo la posicion de la ultima adyacente
            int lastCol=getCol(col+1);
            int lastRow=getRow(row+1);

            if(celdas[row][col].getNumMinasAd()!=0&&!celdas[row][col].getMina())
               setNumero(celdas[row][col]);
            else{
                System.out.print(row+" "+col);
                celdas[row][col].getBoton().setIcon(new ImageIcon("Imagenes/VACIO.jpg"));
                celdas[row][col].setVacia(true);
                for(int i=firstRow; i<=lastRow;i++)
                    for(int j =firstCol; j<=lastCol;j++)
                        if(!celdas[i][j].getMina()&&celdas[i][j].getNumMinasAd()==0&&(col!=j || row!=i)){
                            celdas[i][j].getBoton().setIcon(new ImageIcon("Imagenes/VACIO.jpg"));
                            if(celdas[i][j].getVacia()==false){
                                buscarMinas(j,i);
                            }
                        }else if(!celdas[i][j].getVacia())
                            setNumero(celdas[i][j]);
            }     
   }
    
    
    public void setNumero(Celda celda){
        int numAd=celda.getNumMinasAd();
        switch (numAd) {
            case 1:   celda.getBoton().setIcon(new ImageIcon("Imagenes/1.jpg"));
                     break;
            case 2:  celda.getBoton().setIcon(new ImageIcon("Imagenes/2.jpg"));
                     break;
            case 3:  celda.getBoton().setIcon(new ImageIcon("Imagenes/3.jpg"));
                     break;
            case 4:  celda.getBoton().setIcon(new ImageIcon("Imagenes/4.jpg"));
                     break;
            case 5:  celda.getBoton().setIcon(new ImageIcon("Imagenes/5.jpg"));
                     break;
            case 6:  celda.getBoton().setIcon(new ImageIcon("Imagenes/6.jpg"));
                     break;
            case 7:  celda.getBoton().setIcon(new ImageIcon("Imagenes/7.jpg"));
                     break;
            case 8:  celda.getBoton().setIcon(new ImageIcon("Imagenes/8.jpg"));
                     break;
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
        
        if(primerTurno==false){
        limpiarBotones(0,0);
        limpiarCeldas();
        primerTurno=true;
        numMinMarca=10;
        Interfaz.numMinasLab.setText("Minas restantes: "+numMinMarca);
        }
   }

    public void limpiarCeldas(){
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++){  
                    celdas[i][j].setMina(false);
                    celdas[i][j].setNumMinasAd(0);
                    celdas[i][j].setVacia(false);
                    celdas[i][j].setMarca(false);
            }
     }
    
   public void limpiarBotones(int i, int j){ 
        if(j==celdas.length-1){
            if (i==celdas.length-1){
                celdas[i][j].getBoton().setIcon(new ImageIcon("Imagenes/CELDA.jpg"));
            }
            else{
                celdas[i][j].getBoton().setIcon(new ImageIcon("Imagenes/CELDA.jpg"));
                limpiarBotones(i+1,0);
            }
            
        }
        else{
            celdas[i][j].getBoton().setIcon(new ImageIcon("Imagenes/CELDA.jpg"));
            limpiarBotones(i,j+1);
        }
    }
    
}
