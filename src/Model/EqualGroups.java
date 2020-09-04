package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EqualGroups implements TeamGenerator{
    int nTeams;
    public EqualGroups(int nTeams){
        this.nTeams = nTeams;
    }

    public void setNTeams(int nTeams) {
        this.nTeams = nTeams;
    }

    @Override
    public List<Team> generateTeams(String[] names) {
        List<Team> teams = new ArrayList<>();
        if (names.length%nTeams == 0)
            for (int i = 0; i<nTeams; i++){
                teams.add(new Team("team" + Engine.nTeams++,names.length/nTeams));
            }
        else{
            for (int i = 0; i<nTeams; i++){
                teams.add(new Team("team" + Engine.nTeams++,names.length/nTeams+1));
            }
        }
        scrambleNames(names);
        for(int i=0; i<names.length; i++){
            teams.get(i%nTeams).names.add(names[i]);
        }
        return teams;
    }

    private void scrambleNames(String[] names){
        Random rn = new Random();
        String tmp;
        for (int i = names.length-1; i>=0; i--){
            int randomI = rn.nextInt(i);
            tmp = names[i];
            names[i] = names[randomI];
            names[randomI] = tmp;
        }
    }
}
