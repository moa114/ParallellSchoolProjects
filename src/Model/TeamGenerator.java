package Model;

import java.util.List;

public interface TeamGenerator {
    List<Team> generateTeams(String[] names);
    void setNTeams(int i);
}
