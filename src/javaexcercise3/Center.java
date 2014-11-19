/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexcercise3;

/**
 *
 * @author Kimmo
 */
public class Center extends Player {

    private final String role = "Center";
    
    @Override
    public String getRole() {
        return role;
    }
    
    @Override
    public void printStatistics() {
        System.out.println("#"+getNumber()+" "+getName()+"'s statistics: "+
                           getAccuracy()+"/"+getAgility()+"/"+
                           getEndurance()+"/"+getStrength());
        System.out.println("Hits: "+getStatShotsHit()+", Misses: "+getStatShotsMissed());
        System.out.println("Made:  "+getStatGoalsMade()+", Own: "+getStatGoalsOwn());
        int good = getStatOffenceSuccess();
        int bad = getStatOffenceFailure();
        System.out.println("Offence (good - bad): "+good+
                           " - "+bad+" = "+(good-bad));
    }

    // #88 Sam Playerson does action
    @Override
    public int doAction() {
        int r = Player.rand.nextInt(100);
        if (r == 0) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " what the... OWN GOAL!!!");
            incrementStatGoalsMade();
            adjustEndurance(-20);
            adjustAccuracy(-2);
            return -1;
        } else if (r > 0 && r < 20) {
            // Usual action
            return doShot();
        } else if (r >= 20 && r < 40) {
            // Secondary role
            doSecondary();
        } else {
            // Primary role
            doPrimary();
        }
        
        return 0;
    }
    
    private void doPrimary() {
        int chance = ((getAgility() + getStrength() + getEndurance()) / 3);
        if (Player.rand.nextInt(100) < chance) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " gets the puck and attacks!");
            incrementStatOffenceSuccess();
            adjustEndurance(-5);
        } else {
            System.out.println("#"+getNumber()+" "+getName()+
                               " gets the puck and loses it!");
            incrementStatOffenceFailure();
            adjustEndurance(-5);
        }
    }
    
    private int doShot() {
        int chance = ((getAccuracy() + getEndurance()) / 2);
        if (Player.rand.nextInt(100) < chance) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " SHOOTS and SCORES!");
            incrementStatShotsHit();
            incrementStatGoalsMade();
            adjustEndurance(-10);
            adjustAccuracy(+1);
            return 1;
        } else {
            System.out.println("#"+getNumber()+" "+getName()+
                               " SHOOTS and misses!");
            incrementStatShotsMissed();
            adjustEndurance(-15);
            adjustAccuracy(-1);
            return 0;
        }
    }
    
    private void doSecondary() {
        int chance = ((getStrength() + getEndurance()) / 2);
        if (Player.rand.nextInt(100) < chance) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " snatches the puck!");
            incrementStatDefenceSuccess();
            adjustEndurance(-5);
        } else {
            System.out.println("#"+getNumber()+" "+getName()+
                               " can not get the puck!");
            incrementStatDefenceFailure();
            adjustEndurance(-5);
        }
    }
}
