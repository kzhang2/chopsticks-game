# Chopsticks (Game) exploration
Has [GraphStream](http://graphstream-project.org/) as a dependency.  
This application calculates the graph representing the connections between the possible states of the game of Chopsticks.
Each state is connected to others through several possible moves, which are  
* Taking the number of fingers on one hand of the player whose turn it is and adding it to one hand of the other player.
* Recombining the total number of fingers on both hands arbitrarily.  
 
Also, each state has a number (hashcode) associated with it which is the number of fingers on each hand of each player
and whose turn it is concatenated together. For example, the initial state of the game is 1110.  
Sample image (for the original game which is modulo 5):
![Graph of game states](/src/rpsgame/images/graph.png)
