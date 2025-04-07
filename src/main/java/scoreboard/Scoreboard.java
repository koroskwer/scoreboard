package scoreboard;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Scoreboard {

    private final MatchService matchService;

    Scoreboard(MatchService matchService){
        this.matchService = matchService;
    };

    public void startMatch(String firstTeamName, String secondTeamName){

    };

    public void finishMatch(String firstTeamName, String secondTeamName){

    };

    public void updateMatch(String firstTeamName, String secondTeamName, int firstTeamScore, int secondTeamScore){

    }

    public Set<Match> getMatchesSummary(){
      return new TreeSet();
    };
}
