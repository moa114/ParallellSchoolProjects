package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Engine {
    List<Team> teams;
    List<String> names = new ArrayList<>();
    public static int nTeams;
    TeamGenerator tg;
    Scanner s = new Scanner(System.in);

    public void createTeams(){
        teams.addAll(tg.generateTeams(names.toArray(new String[0])));
        names.clear();
    }
    
}
