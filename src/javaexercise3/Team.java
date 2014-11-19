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
public class Team {
    
    private Player[] team = new Player[6];
    
    private final String[] positions = {
        "goalkeeper",
        "center",
        "left winger",
        "right winger",
        "left defence",
        "right defence"
    };
    
    public final static int GOALIE = 0;
    public final static int CENTER = 1;
    public final static int LEFTWING = 2;
    public final static int RIGHTWING = 3;
    public final static int LEFTDEFENCE = 4;
    public final static int RIGHTDEFENCE = 5;
    
    
    public Team() {
        for (int i=0; i<team.length; i++) {
            team[i] = null;
        }
    }
    
    public void addPlayerToPosition(Player player, int position) {
        if (position >= GOALIE && position <= RIGHTDEFENCE) {
            System.out.println("#"+player.getNumber()+" "+player.getName()+
                               " plays as the "+positions[position]);
            team[position] = player;
        } else {
            System.out.println("Incorrect position value!");
            System.exit(1);
        }
    }
    
    public Player getPlayerInPosition(int position) {
        return team[position];
    }
    
    public boolean isTeamFull() {
        for (Player it : team) {
            if (it == null) {
                return false;
            }
        }
        return true;
    }
}
