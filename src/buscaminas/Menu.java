/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package buscaminas;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author longares
 */


public class Menu {
    
    public static void muestraMenu(){
        System.out.println("Elija una opcion entre el 0-2");
        System.out.println("    1 - Jugar Buscaminas");
        System.out.println("    2 - Analisis de coste del Buscaminas");
        System.out.println("    0 - Salir");
    }
    
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
        boolean menu= true;
        int opcion=0;
        
            while(menu){

                muestraMenu();  
                opcion = scanner.nextInt();
                if(opcion >0 && opcion <3)
                    menu=false;
                else{
                    System.out.println("El numero debe estar entre el 0 y el 2");
                }
             
            }
      
        
        switch(opcion){
            case 0: System.exit(0);
            break;
            case 1: Interfaz a = new Interfaz();
            break;
            case 2:Analisis analisis = new Analisis();
                   analisis.CasoPeor();
        }
    }    
}
