/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

//import logika.IHra;

/**
 * Aktualiace observeru pomocí inteface
 * zavolá update() při každé změně
 * @author dobm03
 */
public interface Observer {
    
    void update();
    
   // void novaHra(IHra hra);
    
}
