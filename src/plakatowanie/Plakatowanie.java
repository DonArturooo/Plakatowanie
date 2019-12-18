package plakatowanie;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Artur Flisek
 */
public class Plakatowanie {
    
    int liczbaBudynkow, najmniejszyBudynek = Integer.MAX_VALUE, najwiekszyBudynek = Integer.MIN_VALUE;
    int[] wysokoscBudynków, szerokoscBudynkow; 
    
    public static void main(String[] args){
        
        Plakatowanie plakat = new Plakatowanie();
        try {
            
            Scanner scanujDane = new Scanner(new File("pla.in"));
            
            plakat.liczbaBudynkow = scanujDane.nextInt();   //odczytujemy zgodnie z dokumentacją ile jest budynków
            
            if(plakat.liczbaBudynkow >= 1 && plakat.liczbaBudynkow <= 250000){
                
                plakat.wysokoscBudynków = new int[plakat.liczbaBudynkow];
                plakat.szerokoscBudynkow = new int[plakat.liczbaBudynkow];
                

                for (int i = 0; i < plakat.liczbaBudynkow; i++){ 
                    // dodajemy do tablic dane budynków
                    scanujDane.nextLine();
                    int szrokoscBudynku = scanujDane.nextInt();
                    int wysokoscBudynku = scanujDane.nextInt();
                    
                    
                        plakat.szerokoscBudynkow[i] = szrokoscBudynku;
                        plakat.wysokoscBudynków[i] = wysokoscBudynku;
                        //odrazu przy dodawaniu wysokości sprawdzamy jaki jest największy i najmniejszy budynek
                        if(wysokoscBudynku > plakat.najwiekszyBudynek){
                            plakat.najwiekszyBudynek = wysokoscBudynku;
                        }
                        if(wysokoscBudynku < plakat.najmniejszyBudynek){
                            plakat.najmniejszyBudynek = wysokoscBudynku;
                        }
                    
                }
            }
            else{
                throw new Exception("Liczba budynków jest niezgodna z dokumentacją");
            }
            
            scanujDane.close();
        }
        catch (FileNotFoundException ex) {
            ex.getMessage();
            System.err.println(ex);
        }
        catch(Exception ex){
            ex.getMessage();
            System.err.println(ex);
            System.exit(0);
        }
        
        plakat.drukujWynik();
    }


    int ilePlakatow(){
        int plakaty = 0;

        for (int i = najmniejszyBudynek; i <= najwiekszyBudynek; i++) {

            int n = 0;
            boolean czyJest = false; //z każdym piętrem sprawdzamy czy jest jakiś budynek o takiej wysokości
            while(n < liczbaBudynkow){

                if(wysokoscBudynków[n] == i && czyJest == false){ //jeżeli jest to budynek o takiej wysokości, a poprzednie nie były równy sprawdzanemu piętru
                    czyJest = true;
                    if(n == liczbaBudynkow - 1){ //jeżeli jest to ostatni budynek to dodaj plakat
                        plakaty++;
                    }
                }
                else if(wysokoscBudynków[n] >= i && czyJest == true){// jeżeli budynek jest większy lub taki sam, a porzedni był równy sprawdzanemu piętru
                    if(n == liczbaBudynkow - 1){ //jeżeli jest to ostatni budynek to dodaj plakat
                        plakaty++;
                    }
                }
                else if(wysokoscBudynków[n] < i && czyJest == true){//jeżeli budynek jest mniejszy niż sprawdzane piętro
                    czyJest = false;
                    plakaty++;
                }
                else{
                    czyJest = false;
                }
                n++;
            }
        }
        
        return plakaty;
    }

    void drukujWynik(){
        try {
            PrintWriter zapisz = new PrintWriter(new FileWriter("pla.out", false));

            zapisz.print(ilePlakatow());
            zapisz.close();
        } catch (IOException ex) {
            ex.getMessage();
        }
    }   
}
