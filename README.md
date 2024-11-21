# Github repository link:
https://github.com/chiachunlee3/CW2024

# Compilation Instructions

# Implemented Working Features

# Implemented but not Working Features
## String created but not being used in ShieldImage class
Warning fixes in ShieldImage class where the string IMAGE_NAME is created but not being used at all, I fix the path that the string IMAGE_NAME points to and the string are being called in the following function.


# Additional Java Class
## Main Menu class
Added a new class for main menu, where the game doesn't start straight away on level one it start on the main menu, player can choose to play the game or exit it.


# Modified Java Class
## Observable type is deprecated
The type Observable is deprecated since version 9, removed the observable pattern completely from levelparent and controller class. Uses constructor and added a setonlevelchange method to help with the callback. Created a notifyLevelChange method to trigger level change. This fixed the warning.


# Unexpected Problem
## Game crashes randomly
Encounter “class java.lang.reflect.invocationtargetexception" error when playing the game, the game freezes and crash. To fix this issue, I have changed the wrong image type that is on ShieldImage class line 15, from jpg to it's original file type png. This fixed the game crashing issue.

## When switching to level 2 the game freeze and crash
When I beat level 1, the game freeze when switching to level 2, sometimes the game crashes completely and show this error “Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "InvokeLaterDispatcher”. I added a command in levelparent class to make sure to stop the current timeline so that it can continue to the next level. This fix the issue where the game freeze and crash when switching to level2

## Missing requires transitive
A warning in both controller file and main file which are related to the type Stage from the module javafx.graphics which indicated it cannot be access by the client due to missing requires transitive. Added a line in module-info.java file to fix this warning.

# Refactoring
## Reduced the hitbox of enemy
Make the hitbox of enemy smaller so that the projectile need to actually hit the enemy plane to count as a hit. In ActiveActor class, made a function to get the precise bound of the image of the enemy plane.

## Rearrange the Game Over Screen layout
Make the game over layout image fit into the screen, by adjusting the image width and height, moved the game over image so that it's centered on the screen. This helps to make the game over screen more visible and readable.

## Removed Irrelevant command
Removed a line of command in ActiveActor class where the command I removed look like a commented out version that tries to load the image directly from a path string, where in the next line of code included the proper way to load resources. Removed a line of command in GameOverImage class where the command I removed is redundant and not being utilized in the code at all. 

# Addition
## Added pause function
When in the game screen, player can pause the game by pressing the button 'p', the game will pause and the background would be blurred and the text "Game Paused" will appear on screen. 

## Display kill needed to advance/win 
Added a text that display the current kill needed to advance/win the game, it will update everytime an enemy was hit and it will display the amount of enemy needed to be killed before advancing to the next level.
