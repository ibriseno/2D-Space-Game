# csc413-SecondGame

## Student Name : Ivan Briseno

Second Game Documentation:
--------------------------------------------------------------
This is a single player shooter game. The purpose of this game is to deliver mail to the space station that are
scattered around the screen. Once the ship has landed on the station, the ship will attach itself to it and stay there.
The ship takes no damage when docked at the station as well as receives points after leaving the station. When the player 
leaves the station the station is removed from the map (not replaced). The asteroids on the game are randomly drawn into 
the game. The asteroid deal damage to the ship but not the stations, and as previously mentioned the ship takes no damage
when docked in the station. The asteroid are destructable this means that the ship can shoot at them and destroy them.
The health of the ship consists of a single life bar that depletes to zero when hit three times by the asteroids. The 
game is over if the ship is hit three times.

Game Description and KeyControl
--------------------------------------------------------------
Controls:
Right Arrow Key - Move right
Left Arrow Key  - Move Left
Down Arrow Key  - Slow Down
Up Arrow Key    - Move Forward
Enter Key       - Shoot
SpaceBar        - Launch From Station

This game contains no power ups of any kind. The score can be seen on the left hand side of the screen. The score depletes
while a player is docked at a station, leaving the station stops the depletion.
The only way to exit the game is to close it down manually by clicking the top exit buttom on the window. 
The only way to start a new game so far is to relaunch the game, restart has not been implemented.

Compiler Used
---------------------------------------------------------------
IntelliJ IDEA 2018.2.2 (Ultimate Edition)
Build #IU-182.4129.33, built on August 21, 2018
Licensed to Ivan Briseno
Subscription is active until September 4, 2019
JRE: 1.8.0_152-release-1248-b8 amd64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Windows 10 10.0
Java SDK Version 10.0.2

JAR File
----------------------------------------------------------------
JAR File is created by does not display anything. The IDE plays the game with no issues but the jar file appears
to only be blank screen. This is the same issue that I encountered with the previous game where the Jar file
had the same behavior as this one. 
