/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kimmo T.
 */
public abstract class Exercise extends Subject {
    
    private Player player;
    private int durationInMinutes;
    private String currentExercise;

    public abstract void selectExercise();
    public abstract void beginExercise(Player player);
    public abstract void completeExercise();
    
    public Exercise() {
        this.durationInMinutes = 0;
        this.player = null;
        this.currentExercise = null;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void setDurationInMinutes() {
        if (durationInMinutes == 0) {
            int duration = 0;
            Scanner scan = new Scanner(System.in);
            do {
                try {
                    System.out.print("Set duration between 30 and 90 minutes: ");
                    duration = scan.nextInt();
                    scan.nextLine(); // Consume next line
                    if (duration > 29 && duration < 91) {
                        // Acceptable value
                        this.durationInMinutes = duration;
                        break;
                    }
                } catch (Exception e) {
                    scan.nextLine(); // Consume next line
                }
                System.out.println("Try selecting number between 30 and 90.");
            } while(true);
        }
    }
    
    public void setDurationInMinutes(int duration) {
        // Some sanity check here just as a reminder that it needs to be done.
        if (duration > 0 && duration < 300) {
            this.durationInMinutes = duration;
        }
    }
    
    public int getDurationInMinutes() {
        return durationInMinutes;
    }
    
    public String getCurrentExercise() {
        return currentExercise;
    }
    
    // Intented for the child implementation of abstract selectExercise()
    protected int selectExercise(String[] exercises) {
        int i = 1;
        int selection = 0;
        Scanner scan = new Scanner(System.in);
        for (String it : exercises) {
            System.out.println(i+" - "+it);
            i++;
        }
        do {
            try {
                System.out.print("Select: ");
                selection = scan.nextInt();
                scan.nextLine(); // Consume next line
                if (selection > 0 && selection <= exercises.length) {
                    // Acceptable value, -1 to match it with the table indexes
                    selection -= 1;
                    currentExercise = exercises[selection];
                    return selection;
                }
            } catch (Exception e) {
                scan.nextLine(); // Consume next line
            }
            System.out.println("Try selecting number between 1 and "+
                               exercises.length+".");
        } while(true);
    }
    
    protected void exerciseTimer(Exercise exercise) {
        if (durationInMinutes == 0) {
            System.out.println("Exercise duration is zero, so done already.");
            exercise.completeExercise();
        } else {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    exercise.completeExercise();
                    timer.cancel();
                }
            }, durationInMinutes*100); // lets say 10 min = 1 second
        }
    }
}
