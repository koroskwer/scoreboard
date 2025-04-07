# Scoreboard

### Assumptions and architectural ideas

* Given a pair of teams can play a single match at once.
* Matches are identified both by an unique identifier and a pair of teams who play the match, but as requirements
  specified, when updating and removing the match only the names of the teams are provided.
* Most of the classes are made Package Private on purpose, to encapsulate the Scoreboard package.
* Unit tests were written in spirit of TDD, and the Scoreboard package is the entire Unit, not each class.
* There are some TODOs left, in case of growth of the project

### How to use the scoreboard

1. **Initialize the Scoreboard**
   ```java
   Scoreboard scoreboard = ScoreboardConfiguration.createProdConfiguration();

2. **Start matches**
    ```java
    scoreboard.startMatch("Poland", "Netherlands");
    scoreboard.startMatch("Finland", "Sweden");
    scoreboard.startMatch("Portugal", "Argentina");

3. **Update scores**
    ```java
    scoreboard.updateMatch("Poland", "Netherlands", 0, 1);
    scoreboard.updateMatch("Portugal", "Argentina", 1, 0);
    scoreboard.updateMatch("Poland", "Netherlands", 0, 2);
    scoreboard.updateMatch("Poland", "Netherlands", 0, 3);
    scoreboard.updateMatch("Portugal", "Argentina", 1, 1);

4. **Get summary**
    ```java
    scoreboard.getMatchesSummary();
    // [Poland 0 - 3 Netherlands, Portugal 1 - 1 Argentina, Finland 0 - 0 Sweden]

5. **Finish matches**
    ```java
    scoreboard.finishMatch("Poland", "Netherlands");
    scoreboard.finishMatch("Finland", "Sweden");
    scoreboard.finishMatch("Portugal", "Argentina");
