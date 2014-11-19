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
public class Relaxation extends Exercise {

    @Override
    public void selectExercise() {
        setDurationInMinutes(30);
    }

    @Override
    public void beginExercise(Player player) {
        super.setPlayer(player);
        System.out.println(player.getName()+"#"+player.getNumber()+
                           " is relaxing "+
                           " for "+getDurationInMinutes()+" minutes.");
        exerciseTimer(this);
    }

    @Override
    public void completeExercise() {
        Player player = getPlayer();
        if (player != null) {
            player.adjustAccuracy(1);
            player.adjustAgility(1);
            player.adjustEndurance(5);
            player.adjustStrength(1);
            notifyObserver();
        } else {
            System.out.println("This exercise was not even started.");
        }
    }
    
}
