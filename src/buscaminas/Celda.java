
package buscaminas;

import javax.swing.JButton;
import javax.swing.*;
/**
 *
 * @author Carlos Longares , Daniel Fenollar , Marca Diago
 */
public class Celda
{
    /*private static int cont=0; Lo hemos usado para comprobar que al crear no se crearán de mas*/
    private boolean mina;
    private boolean marca;
    private boolean vacia;
    private JButton boton;
    
    public Celda(JButton boton)
    {
        this.boton=boton;
        mina=false;
        marca=false;
        vacia = false;
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
    
    public void setMarca(boolean marca){
        this.marca=marca;
    }
   
    public void setMina(boolean mina){
        this.mina=mina;
    }
    public void setVacia(boolean vacia){
        this.vacia=vacia;
    }
}