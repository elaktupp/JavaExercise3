/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

import java.util.Scanner;

/**
 *
 * @author Kimmo
 */
public class Game {
    
    private Team team = null;
    
    private int home = 0;
    private int visitors = 0;
    
    public void setTeam(Team team) {
        this.team = team;
    }
    
    public void playTheGame() {
        
        Scanner scan = new Scanner(System.in);
        Player player;
        int result = 0;
        System.out.println("#############################################");
        System.out.println("###   T H E   G A M E   I S   O N ! ! !   ###");
        System.out.println("#############################################");
        System.out.println("### HOME "+home+" : "+visitors+" VISITORS ###");
        System.out.println("#############################################");
        
        // Play few rounds
        for (int rounds = 0; rounds < 10; rounds++) {
            // Go through each player in the team and doAction()
            for (int pos = Team.GOALIE; pos <= Team.RIGHTDEFENCE; pos++) {
                player = team.getPlayerInPosition(pos);
                result = player.doAction();
                if (result > 0) {
                    home++;
                } else if (result < 0) {
                    visitors++;
                }
            }
            System.out.println("#############################################");
            System.out.println("### HOME "+home+" : "+visitors+" VISITORS ###");
            System.out.println("#############################################");
            System.out.print("<Press ENTER to continue>");
            scan.nextLine();
        }
    }
}
