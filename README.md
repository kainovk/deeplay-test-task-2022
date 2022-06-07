# Test task for an internship at [Deeplay](https://internship.deeplay.io/)

## Task description
### Given:
- a 16-character string describing the 4x4 playing field
- and a string containing the creature's race

### Goal
Write a method _getResult()_ that **returns the minimum cost** 
for a creature to move **from the top left to the bottom right** cell

### Example
Race and terrain compatibility table:

|         | S | W | T | P |
|:-------:|:---:|:---:|:---:|:---:|
| HUMAN   | 5 | 2 | 3 | 1 |
| SWAMPER | 5 | 2 | 3 | 1 |
| WOODMAN | 5 | 2 | 3 | 1 |

If terrain string is "STWSWTPPTPTTPWPP", method should return
- 10 for Human
- 15 for Swamper
- 12 for Woodman

### Implementation
1. Compile a **cost matrix** for a specific creature
2. From the cost matrix, create the **adjacency matrix**
3. Using a **Dijkstra's algorithm** for finding the shortest path
