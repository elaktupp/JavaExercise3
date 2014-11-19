/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexcercise3;

import java.util.Scanner;

/**
 *
 * @author Kimmo
 */
public class Strength extends Exercise {
    
    private final String[] drills = {
        "lower body strength",
        "upper body strength",
    };
    
    private int drillNumber;
    
    // Sam Playerson #88 begins exercise
    @Override
    public void beginExercise(Player player) {
        super.setPlayer(player);
        System.out.println(player.getName()+"#"+player.getNumber()+
                           " is at Gym exercising "+drills[drillNumber]+
                           " for "+getDurationInMinutes()+" minutes.");
        exerciseTimer(this);
    }

    // Sam Playerson #88 completes exercise
    @Override
    public void completeExercise() {
        Player player = getPlayer();
        if (player != null) {
//            System.out.println("Player "+player.getNumber()+
//                               " has finished "+drills[drillNumber]+
//                               " of "+getDurationInMinutes()+" minutes.");
            player.adjustStrength(Player.rand.nextInt(getDurationInMinutes()/10));
            notifyObserver();
        } else {
            System.out.println("This exercise was not even started.");
        }
    }
    
    // A trick to get the Strength specific exercises and not have
    // same selection query in each child. 
    @Override
    public void selectExercise() {
        drillNumber = super.selectExercise(drills); // using super is not must
    }
    
}
