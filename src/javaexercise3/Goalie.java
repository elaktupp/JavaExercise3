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
public class Goalie extends Player {

    private final String role = "Goalie";
    
    @Override
    public String getRole() {
        return role;
    }
    
    @Override
    public void printStatistics() {
        System.out.println("#"+getNumber()+" "+getName()+"'s statistics: "+
                           getAccuracy()+"/"+getAgility()+"/"+
                           getEndurance()+"/"+getStrength());
        System.out.println("Saves: "+getStatGoalsSaved()+", Lets: "+getStatGoalsLet());
        System.out.println("Made:  "+getStatGoalsMade()+", Own: "+getStatGoalsOwn());
    }

    /**
     * 
     * @return 0 = no goal, +1 = home team goal, -1 = visitor team goal
     */
    @Override
    public int doAction() {
        int r = Player.rand.nextInt(100);
        if (r < 30) {
            return doShot();
        } else if (r >= 30 && r < 70 ) {
            return doSecondary();
        } else {
            return doPrimary();
        }
    }

    private int doPrimary() {
        int chance = ((getAgility() + getEndurance()) / 2);
        if (Player.rand.nextInt(100) < chance) {
            System.out.println("#"+getNumber()+" "+getName()+" SAVES!");
            incrementStatGoalsSaved();
            adjustEndurance(-5);
            adjustAgility(+1);
            return 0;
        } else {
            System.out.println("#"+getNumber()+" "+getName()+" FAILS to save!");
            incrementStatGoalsLet();
            adjustEndurance(-10);
            adjustAgility(-2);
            return -1;
        }
    }
    
    private int doShot() {
        if (Player.rand.nextInt(100) == 0) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " shoots and SCORES!!!");
            incrementStatGoalsMade();
            adjustEndurance(+5);
            adjustAgility(+1);
            return 1;
        } else {
            System.out.println("#"+getNumber()+" "+getName()+
                               " stops the puck and passes it on.");
            return 0;
        }
    }
    
    private int doSecondary() {
        int r = Player.rand.nextInt(100);
        int chance = ((getStrength() + getEndurance()) / 2);
        if (r == 0) {
            System.out.println("#"+getNumber()+" "+getName()+
                               " bounces the puck to OWN GOAL!");
            incrementStatDefenceSuccess();
            adjustEndurance(-5);
            return -1;
        } else if (r < chance) {
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
        
        return 0;
    }
    
}
