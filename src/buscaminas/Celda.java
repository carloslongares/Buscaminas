
package buscaminas;

import javax.swing.JButton;
import javax.swing.*;
/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marc Diago
 */
public class Celda
{
    private boolean mina;
    private boolean marca;
    private boolean vacia;
    private JButton boton;
    private int numMinasAd;
    private boolean minaContada;
    public Celda(JButton boton)
    {
        this.boton=boton;
        mina=false;
        marca=false;
        vacia = false;
        numMinasAd=0;
        minaContada=false;
        /*cont++;*/
    }
   
    public JButton getBoton(){
        return boton;
    }
    
    public boolean getMarca(){
        return marca;
    }
    
    public boolean getMina(){
        return mina;
    }
    
    public boolean getVacia(){
        return vacia;
    }
    
    public int getNumMinasAd(){
        return numMinasAd;
    }
    
    public boolean getMinaContada(){
        return minaContada;
    }
    public void setMarca(boolean marca){
        this.marca=marca;
    }
   
    public void setMina(boolean mina){
        this.mina=mina;
    }
    public void setVacia(boolean vacia){
        this.vacia=vacia;
    }
    
    public void setNumMinasAd(int numMinasAd){
        this.numMinasAd=numMinasAd;
    }
    
    public void setMinaContada(boolean minaContada){
        this.minaContada= minaContada;
    }
}