/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 * Interface provádí zaregistrování, smazání a upozornění observeru při zavolání void
 * @author dobm03
 * ZS 2017/2018
 */
public interface Subject {
    
    void registerObserver(Observer observer);
    
    void deleteObserver(Observer observer);
    
    void notifyAllObservers();
}
