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
    
    public void addPlayer(Player player) {
        switch (player.getRole()) {
            case "Goalie":
                team[Team.GOALIE] = player;
                break;
            case "Center":
                team[Team.CENTER] = player;
                break;
            case "Leftwing":
                team[Team.LEFTWING] = player;
                break;
            case "Rightwing":
                team[Team.RIGHTWING] = player;
                break;
            case "Defence":
                if (team[Team.LEFTDEFENCE] == null) {
                    team[Team.LEFTDEFENCE] = player;
                } else {
                    team[Team.RIGHTDEFENCE] = player;
                }
                break;
            default:
                System.out.println("ERROR: Bad role");
                System.exit(1);
        }
    }
    
    public Player getPlayerInPosition(int position) {
        return team[position];
    }
    
    public String openPositions() {
        String open = "Needs ";
        for (int i = 0; i < team.length; i++) {
            if (team[i] == null) {
                open += (positions[i]+" ");
            }
        }
        return open;
    }
    
    public boolean isTeamFull() {
        for (Player it : team) {
            if (it == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isTeamReady() {
        for (Player it : team) {
            if (it.isExercising()) {
                return false;
            }
        }
        return true;
    }
}
