package buscaminas;

public class Analisis {
    final private int MIN_TALLA=8;
    final private int MAX_TALLA =72;
    final private int INCREMENTO =8;
    
    public void CasoPeor(){
        for (int i= MIN_TALLA;i<=MAX_TALLA;i+=INCREMENTO){
          //int i= 72;
          Tablero tablero = new Tablero (i);
          System.out.print("Talla: "+i+" || Numero de llamadas recursivas: ");
          long ti = System.nanoTime(); 
          int numRep=tablero.peorBuscarMinas(i/2,i/2,i);
          long tf = System.nanoTime();
          long tt=tf-ti;
          System.out.println(numRep + "|| Tiempo de ejecucion: "+tt);
        }
        
       
    
    }
    
}
