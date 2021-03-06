/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexercise3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Kimmo
 */
public class Coach extends MyObserver {

    private final File file = new File("roster.txt");
    private ArrayList<Player> players;
    private Team team;
    private Game game;
    
    public Coach() {
        this.players = new ArrayList();
        this.team = new Team();
        this.game = new Game();

        Player aPlayer;
        
        if (file.exists()) {
            // We have existing Player roster, use it!
            readRoster(file);
            
        } else {
            // No existing Players, create players!
            aPlayer = new Goalie();
            aPlayer.setName("Erich Gamma");
            aPlayer.setNumber(19);
            players.add(aPlayer);

            aPlayer = new Goalie();
            aPlayer.setName("Allan Vermeulen");
            aPlayer.setNumber(29);
            players.add(aPlayer);
            
            aPlayer = new Center();
            aPlayer.setName("Richard Helm");
            aPlayer.setNumber(71);
            players.add(aPlayer);

            aPlayer = new Center();
            aPlayer.setName("Scott Ambler");
            aPlayer.setNumber(81);
            players.add(aPlayer);
            
            aPlayer = new Leftwing();
            aPlayer.setName("Ralph Johnson");
            aPlayer.setNumber(8);
            players.add(aPlayer);

            aPlayer = new Leftwing();
            aPlayer.setName("Greg Bumgardner");
            aPlayer.setNumber(18);
            players.add(aPlayer);
            
            aPlayer = new Rightwing();
            aPlayer.setName("John Vlissides");
            aPlayer.setNumber(26);
            players.add(aPlayer);

            aPlayer = new Rightwing();
            aPlayer.setName("Eldon Metz");
            aPlayer.setNumber(36);
            players.add(aPlayer);
            
            aPlayer = new Defence();
            aPlayer.setName("Grady Booch");
            aPlayer.setNumber(20);
            players.add(aPlayer);

            aPlayer = new Defence();
            aPlayer.setName("Andrew Tanenbaum");
            aPlayer.setNumber(14);
            players.add(aPlayer);
            
            aPlayer = new Defence();
            aPlayer.setName("Trevor Misfeldt");
            aPlayer.setNumber(30);
            players.add(aPlayer);

            aPlayer = new Defence();
            aPlayer.setName("Jim Shur");
            aPlayer.setNumber(24);
            players.add(aPlayer);
            
            writeRoster(file);
        }
    }
    
    public void startCoaching() {

        setupPreGameExercises(); 
        // --> once exercises are completed setupTheGame() gets called

    }
    
    @Override
    public void update(Subject subject) {
        // Exercise update
        if (subject instanceof Exercise) {
            Exercise exercise = (Exercise)subject;
            Player player = exercise.getPlayer();
            System.out.println("Hey coach, "+player.getName()+
                               " #"+player.getNumber()+
                               " completed "+exercise.getDurationInMinutes()+
                               " minutes "+exercise.getCurrentExercise()+
                               " exercise.");
            player.setIsExercising(false);
            if (team.isTeamReady()) {
                // Exercising is completed
                setupTheGame();
            }
        } else {
            System.out.println("ERROR: Unexpected class");
            System.exit(1);
        }
    }
    
    private void setupPreGameExercises() {
        // Set exercise for the players to be added to the team:
        // 1 goalie, 1 center, 1 leftwing, 1 rightwing and 2 defecnce
        // anything more will cause trouble...
        int index = 0;
        int selected;
        Exercise[] drills = new Exercise[players.size()];
        Scanner scan = new Scanner(System.in);
        
        for (Player player : players) {
            System.out.println("TEAM: "+team.openPositions());
            System.out.println("PLAYER: "+player.getName()+
                               " #"+player.getNumber()+" "+player.getRole());
            System.out.println("0 - Not playing");
            System.out.println("1 - Accuracy  ("+player.getAccuracy()+")");
            System.out.println("2 - Agility   ("+player.getAgility()+")");
            System.out.println("3 - Endurance ("+player.getEndurance()+")");
            System.out.println("4 - Strength  ("+player.getStrength()+")");
            System.out.println("5 - Relaxation");
            System.out.print("Select type of exercise: ");

            selected = scan.nextInt();
            scan.nextLine(); // Consume next line

            switch (selected) {
                case 0:
                    drills[index] = null;
                    break;
                case 1:
                    drills[index] = new Accuracy();
                    break;
                case 2:
                    drills[index] = new Agility();
                    break;
                case 3:
                    drills[index] = new Endurance();
                    break;
                case 4:
                    drills[index] = new Strength();
                    break;
                case 5:
                    drills[index] = new Relaxation();
                    break;
                default:
                    System.out.println("ERROR: Unexpected selection!");
                    System.exit(1);
            }
            // If not resting then initialise exercise
            if (drills[index] != null) {
                drills[index].addObserver(this);
                drills[index].selectExercise();
                drills[index].setDurationInMinutes();
                index++;
                team.addPlayer(player);
            }
        }
        
        index = 0;
        
        // Set exercise starts the exercise (timer) immediately
        for (Player player : players) {
            if (drills[index] != null) {
                player.setExercise(drills[index++]);
            }
        }
        
    }
    
    private void setupTheGame() {
        
        System.out.println("PLAY BALL... er... PUCK!");
        game.setTeam(team);
        game.playTheGame();
        
        team.getPlayerInPosition(Team.GOALIE).printStatistics();
        team.getPlayerInPosition(Team.CENTER).printStatistics();
        team.getPlayerInPosition(Team.LEFTWING).printStatistics();
        team.getPlayerInPosition(Team.RIGHTWING).printStatistics();
        team.getPlayerInPosition(Team.LEFTDEFENCE).printStatistics();
        team.getPlayerInPosition(Team.RIGHTDEFENCE).printStatistics();
        
        // Rest all players before updating the roster
        for (Player it : players) {
            it.rest();
        }
        writeRoster(file);
    }
    
    private void readRoster(File file) {
        FileInputStream fs = null;
        ObjectInputStream os = null;
        try {
            fs = new FileInputStream(file);
            os = new ObjectInputStream(fs);
            players = (ArrayList) os.readObject();
            os.close();
        } catch (Exception e) {
            System.out.println("ERROR: File/ObjectInputStream operation fails. "+e);
            System.exit(1);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("ERROR: ObjectInputStream closing fails. "+e);
                    System.exit(1);
                }
            }
        }
    }
    
    private void writeRoster(File file) {
    FileOutputStream fs = null;
    ObjectOutputStream os = null;
    try {
        fs = new FileOutputStream(file);
        os = new ObjectOutputStream(fs);
        os.writeObject(players);
        os.close();
    } catch (Exception e) {
        System.out.println("ERROR: File/ObjectOutputStream operation fails. "+e);
        System.exit(1);
    } finally {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                System.out.println("ERROR: ObjectOutputStream closing fails. "+e);
                System.exit(1);
            }
        }
    }
}
}
