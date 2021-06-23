# Monte Carlo Tree Search

Implementation of an AI agent for Tic Tac Toe and other games.  

## Description

This project implements the Monte Carlo Tree Search (MCTS) algorithm that is widely used in the field of Artificial Intelligence (AI). MCTS is a search algorithm that is based on probability and combines two AI concepts: search tree and principles of reinforcement learning (during the backpropagation step). 

Many of the predecessors to MCTS, such as Best First Search, explored the entire game tree, or a large portion thereof, e.g. with alpha-beta pruning. This approach is suitable for games with a small branching factor, such as 8-puzzle and Blocks World, but is not tenable for more complex games like chess and go. This led to the use of game tree search algorithms that only selectively grow a game tree. 

In order to aoivd convergence to a local optimum, an “exploration-exploitation” trade off is introduced to MCTS in the form of the Upper Confidence bounds for Trees (UCT) algorithm.

The MCTS algorithm is carried out in 4 main steps and these 4 steps are repeated until a given resource is depleted. The resources, in the case of this project, is time in milliseconds but can easily be adapted. The 4 steps are outlined below:

  1. Selection: A leaf node is selected using the UCT algorithm, which can be read about [here](http://www.cs.cornell.edu/courses/cs6700/2016sp/lectures/CS6700-UCT.pdf).
  2. Expansion: Once the leaf node is selected, it is expanded by creating its children.
  3. Simulation: A random rollout/playout takes place either for all children expanded in step 2 or only for one random child.
  4. Backpropagation: The result of the simulation is shared with all nodes on the path from the leaf to the root.

![Image showing steps of the Monte Carlo Tree Search algorithm](https://miro.medium.com/max/2982/1*Ntm0xHhJ5jOgsL9AdB2kNw.jpeg)

The MCTS portion of the project consists of 3 classes: MCTS, State and UCT, where the State class is nested in the MCTS class. This projevct follows the Strategy Design Pattern and therefore the MCTS class can be applied to any game that implements that methods defined by the Layout interface. In the case of this project, the Tic Tac Toe game has been developed.

## Getting Started

### Dependencies

* Java 8 is the only requirement. 

### Installing and using

* Simply clone the code from this repository. If using Eclipe, IntelliJ or another IDE, main.java can be run. If running on terminal, src code needs to be compiled before running main.java.
