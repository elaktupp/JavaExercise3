/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Kimmo
 */
public abstract class Player implements Serializable {

    public static Random rand = new Random();
    
    private String name;
    private int number;
    
    private int statGoalsMade;
    private int statGoalsSaved;
    private int statGoalsLet;
    private int statGoalsOwn;
    private int statShotsHit;
    private int statShotsMissed;
    private int statOffenceSuccess;
    private int statOffenceFailure;
    private int statDefenceSuccess;
    private int statDefenceFailure;

    private int[] agility;   // 0 - 100
    private int[] accuracy;  // 0 - 100
    private int[] endurance; // 0 - 100
    private int[] strength;  // 0 - 100
    
    public abstract void printStatistics();
    public abstract int doAction();
    public abstract String getRole();
    
    public Player() {
        // [0] is current value, [1] is default value
        this.agility = new int[2];
        this.agility[0] = 50 + rand.nextInt(50);
        this.agility[1] = this.agility[0];
        this.accuracy = new int[2];
        this.accuracy[0] =  50 + rand.nextInt(50);
        this.accuracy[1] = this.accuracy[0];
        this.endurance = new int[2];
        this.endurance[0] = 50 + rand.nextInt(50);
        this.endurance[1] = this.endurance[0];
        this.strength = new int[2];
        this.strength[0] = 50 + rand.nextInt(50);
        this.strength[1] = this.strength[0];
    }
    
    public void rest() {
        if (this.accuracy[0] < this.accuracy[1]) {
            this.accuracy[0] = this.accuracy[1];
        }
        if (this.agility[0] < this.agility[1]) {
            this.agility[0] = this.agility[1];
        }
        if (this.endurance[0] < this.endurance[1]) {
            this.endurance[0] = this.endurance[1];
        }
        if (this.strength[0] < this.strength[1]) {
            this.strength[0] = this.strength[1];
        }
    }
    
    public void setExercise(Exercise exercise) {
        exercise.beginExercise(this);
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setNumber(int number) {
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }

    public int getStatGoalsMade() {
        return statGoalsMade;
    }

    public void setStatGoalsMade(int statGoalsMade) {
        this.statGoalsMade = statGoalsMade;
    }

    public void incrementStatGoalsMade() {
        this.statGoalsMade++;
    }
    
    public int getStatGoalsSaved() {
        return statGoalsSaved;
    }

    public void setStatGoalsSaved(int statGoalsSaved) {
        this.statGoalsSaved = statGoalsSaved;
    }

    public void incrementStatGoalsSaved() {
        this.statGoalsSaved++;
    }
    
    public int getStatGoalsLet() {
        return statGoalsLet;
    }

    public void setStatGoalsLet(int statGoalsLet) {
        this.statGoalsLet = statGoalsLet;
    }

    public void incrementStatGoalsLet() {
        this.statGoalsLet++;
    }
    
    public int getStatGoalsOwn() {
        return statGoalsOwn;
    }
    
    public void setStatGoalsOwn(int statGoalsOwn) {
        this.statGoalsOwn = statGoalsOwn;
    }

    public void incrementStatGoalsOwn() {
        this.statGoalsOwn++;
    }
    
    public int getStatShotsHit() {
        return statShotsHit;
    }

    public void setStatShotsHit(int statShotsHit) {
        this.statShotsHit = statShotsHit;
    }

    public void incrementStatShotsHit() {
        this.statShotsHit++;
    }
    
    public int getStatShotsMissed() {
        return statShotsMissed;
    }

    public void setStatShotsMissed(int statShotsMissed) {
        this.statShotsMissed = statShotsMissed;
    }

    public void incrementStatShotsMissed() {
        this.statShotsMissed++;
    }
    
    public int getStatOffenceSuccess() {
        return statOffenceSuccess;
    }

    public void setStatOffenceSuccess(int statOffenceSuccess) {
        this.statOffenceSuccess = statOffenceSuccess;
    }

    public void incrementStatOffenceSuccess() {
        this.statOffenceSuccess++;
    }
    
    public int getStatOffenceFailure() {
        return statOffenceFailure;
    }

    public void setStatOffenceFailure(int statOffenceFailure) {
        this.statOffenceFailure = statOffenceFailure;
    }

    public void incrementStatOffenceFailure() {
        this.statOffenceFailure++;
    }
    
    public int getStatDefenceSuccess() {
        return statDefenceSuccess;
    }

    public void setStatDefenceSuccess(int statDefenceSuccess) {
        this.statDefenceSuccess = statDefenceSuccess;
    }

    public void incrementStatDefenceSuccess() {
        this.statDefenceSuccess++;
    }
    
    public int getStatDefenceFailure() {
        return statDefenceFailure;
    }

    public void setStatDefenceFailure(int statDefenceFailure) {
        this.statDefenceFailure = statDefenceFailure;
    }
    
    public void incrementStatDefenceFailure() {
        this.statDefenceFailure++;
    }
    
    public void adjustAccuracy(int value) {
        this.accuracy[0] += value;
        if (this.accuracy[0] > 100) {
            this.accuracy[0] = 100;
        } else if (this.accuracy[0] < 0) {
            this.accuracy[0] = 0;
        }
    }

    public void adjustAgility(int value) {
        this.agility[0] += value;
        if (this.agility[0] > 100) {
            this.agility[0] = 100;
        } else if (this.agility[0] < 0) {
            this.agility[0] = 0;
        }
    }
    
    public void adjustEndurance(int value) {
        this.endurance[0] += value;
        if (this.endurance[0] > 100) {
            this.endurance[0] = 100;
        } else if (this.endurance[0] < 0) {
            this.endurance[0] = 0;
        }
    }
    
    public void adjustStrength(int value) {
        this.strength[0] += value;
        if (this.strength[0] > 100) {
            this.strength[0] = 100;
        } else if (this.strength[0] < 0) {
            this.strength[0] = 0;
        }
    }
    
    public int getAccuracy() {
        return this.accuracy[0];
    }
    
    public int getAgility() {
        return this.agility[0];
    }
    
    public int getEndurance() {
        return this.endurance[0];
    }
    
    public int getStrength() {
        return this.strength[0];
    }
}
