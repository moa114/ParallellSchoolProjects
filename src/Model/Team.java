package Model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    String teamName;
    List<String> names;
    final int limit;

    public Team(String teamName, List<String> names, int limit) {
        this.teamName = teamName;
        this.names = names;
        this.limit = limit;
    }
    public Team(String teamName, int limit) {
        this.teamName = teamName;
        this.names = new ArrayList<>();
        this.limit = limit;
    }

    public List<String> getNames() {
        return names;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getLimit() {
        return limit;
    }
}
