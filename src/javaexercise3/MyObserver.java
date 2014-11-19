/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

/**
 *
 * @author Kimmo
 */
public abstract class MyObserver {
    
    private Subject subject;

    public abstract void update(Subject subject);
    
}
