/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

import java.util.ArrayList;

/**
 *
 * @author Kimmo
 */
public abstract class Subject {
    
    private ArrayList<MyObserver> observers;
    
    public Subject() {
        this.observers = new ArrayList();
    }
    
    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObserver() {
        for (MyObserver it : observers) {
            it.update(this);
        }
    }
}
