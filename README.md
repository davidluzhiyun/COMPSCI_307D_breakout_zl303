# breakout
## NAME


DO NO FORK THIS REPOSITORY, clone it directly to your computer.


This project implements the game of Breakout.

### Timeline

Start Date: 9/3/2022 (14:34)

Finish Date: 9/4/2022 (19:49)

Hours Spent: 25

### Resources Used

El Capitan spinning beachball GIF by madebyjw.com
https://gfycat.com/rapidathleticilladopsis

Red Brick Wall (8-bit) by Trarian on deviantart.com
https://www.deviantart.com/trarian/art/Red-Brick-Wall-8-bit-905507140

Platform Main by 9664c4 on pixelartmaker.com
http://pixelartmaker.com/art/72bd303189fe5db

### Running the Program

Main class:
Main.java

Data files needed: 

Key/Mouse inputs:
* LEFT to move platform left
* RIGHT to move platform RIGHT

Cheat keys:


### Notes/Assumptions

Assumptions or Simplifications:
1. Sees the ball as a single point.
   1. Which implies we are ignoring the case where the ball hits the corner
   of a brick.
   2. Ignore the case where multiple bricks are hit at the same time
2. Frame rate is high enough so that the ball doesn't go much into or through bricks
between frames


Known Bugs:
1. If the ball hit close to the corner of bricks at a high enough speed, the 
collision might not be correctly processed.
2. Due to 1, the ball sometimes just cut through 2 brick walls without changing
course


Extra features or interesting things we should not miss:


### Impressions
The collision detection method requires improvement. The structure for the code
needs cleaning. (First Draft,David Lu)

