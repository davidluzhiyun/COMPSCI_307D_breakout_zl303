# breakout
## NAME
David Lu

DO NOT FORK THIS REPOSITORY, clone it directly to your computer.


This project implements the game of Breakout.

### Timeline

Start Date: 9/3/2022 (14:34)

Finish Date: 9/5/2022 (1:55)

Hours Spent: 35.5



Start Date: 9/10/2022 (12:00)

Finish Date: 9/16/2022 (1:30)

Hours Spent: N/A (Long pauses due to COVID-19)

### Resources Used

El Capitan spinning beachball GIF by madebyjw.com
https://gfycat.com/rapidathleticilladopsis

Red Brick Wall (8-bit) by Trarian on deviantart.com
https://www.deviantart.com/trarian/art/Red-Brick-Wall-8-bit-905507140

Platform Main by 9664c4 on pixelartmaker.com
http://pixelartmaker.com/art/72bd303189fe5db

Mystery Box by Squid Face on Amino
https://aminoapps.com/c/nintendo-switch-1/page/blog/whats-inside-the-mystery-box-5/j0lD_EPouKu3amXbjpkQMebm3EmLag0eV6W

Nuke by 4891af on pixelartmaker.com
http://pixelartmaker.com/art/580ebbf477b2740

### Running the Program

Main class:
Game.java

Data files needed: 

Key/Mouse inputs:
* LEFT to move platform left
* RIGHT to move platform right
* SPACE to restart game after death

Cheat keys:
* L for more lives
* R to restart
* Digit Keys for level warping

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
3. Mysterious speeding after death and restart and level switching


Extra features or interesting things we should not miss:

1. Platform goes to the other side of the screen when travels too far 
2. Power-Up that make the ball invincible before the next miss

### Impressions
The collision detection method requires improvement. The structure for the code
needs cleaning. (First Draft,David Lu)
Still not fun enough for a game (Second Draft, David Lu)

