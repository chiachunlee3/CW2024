# **Table Contents**
1. [GitHub Repository](#1-github-repository)
2. [Compilation Instructions](#2-compilation-instructions)
3. [Implemented Working Features](#3-implemented-working-features-completed)
4. [Implemented but not Working Features](#4-implemented-but-not-working-features)
5. [Features Not Implemented](#5-features-not-implemented)
6. [Additional Java Class](#6-additional-java-class)
7. [Modified Java Class](#7-modified-java-class)
8. [Unexpected Problem](#8-unexpected-problem)

---
<p>&nbsp;</p>

# **1. Github repository:**
[CW2024 Repository](https://github.com/chiachunlee3/CW2024)

https://github.com/chiachunlee3/CW2024

# **2. Compilation Instructions**
Follow these steps to set up, compile, and run the application in Eclipse:

## Requirements
Ensure you have the following installed on your system:

1. **Java Development Kit (JDK)**: Version 11 or later.
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.
2. **JavaFX SDK**: Required for JavaFX applications.
   - Download from [JavaFX Downloads](https://gluonhq.com/products/javafx/).

## Steps to Set Up in Eclipse

1. **Install Eclipse IDE**:
   - Download and install [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/).
   - Ensure you have a version compatible with your JDK.

2. **Set Up the JavaFX SDK in Eclipse**:
   - Download and extract the JavaFX SDK to a directory on your system.
   - Open Eclipse and go to `Window > Preferences`.
   - Navigate to `Java > Build Path > User Libraries` and click `New`.
   - Name the library (e.g., `JavaFX`) and click `OK`.
   - Click `Add External JARs`, navigate to the `lib` folder of your JavaFX SDK, and add all `.jar` files.
   - Click `OK` to save the library.

3. **Import the Project**:
   - Open Eclipse and create a new JavaFX project:
     - Go to `File > New > Project > JavaFX > JavaFX Project`.
     - If the option for JavaFX Project is not available, use a regular `Java Project` and add JavaFX support manually.
   - Place your `Main.java` and other files in the appropriate `src` folder, ensuring the package structure (`com.example.demo.controller`) is maintained.

4. **Configure the Module Path**:
   - Right-click the project in the `Package Explorer` and select `Build Path > Configure Build Path`.
   - Go to the `Libraries` tab, click `Modulepath`, and click `Add Library`.
   - Select `User Library`, choose your JavaFX library, and click `Finish`.

5. **Set VM Arguments**:
   - Right-click the project and select `Run As > Run Configurations`.
   - Select your application, go to the `Arguments` tab, and under `VM Arguments`, add:

     ```plaintext
     --module-path <path-to-javafx-lib> --add-modules javafx.controls,javafx.fxml
     ```

     Replace `<path-to-javafx-lib>` with the actual path to the `lib` folder of your JavaFX SDK.

6. **Run the Application**:
   - Right-click the `Main` class in the `Package Explorer` and select `Run As > Java Application`.
   - Your application should now launch.

## Notes
- Ensure all required files (e.g., `Controller` class) are in the appropriate package structure and compiled successfully.
- If you encounter issues, verify the JavaFX library configuration and VM arguments.

<p>&nbsp;</p>

# **3. Implemented Working Features**
## 3.1 Main Menu
Added a main menu for when the game start it won't straight away start level 1. Player can choose to play or exit the game. The title of the will be on screen too.

## 3.2 Pause Function
When press the button 'p' in the game screen, the game will paused, press the button 'p' again the game will resume.

## 3.3 Text to display how many enemy reamining and the boss health
It will display the number of enemy needs to be kill before advancing to level 2 on the top right of the screen, it will also display the boss health as text in level 2 and level 3.

## 3.4 Back to Main Menu button
A button which will show up when player loses the game or win the game. It will also show up when player paused the game. By press that button it will bring player back to the main menu.

## 3.5 Transition Screen
Transition screen between level and when the start button is clicked in the main menu the game slowly transition into level 1 instead of jumping straight into level 1. When level is clear it will slowly transition into the next level rather than just switch scene to the next level. It offers a smoother experience.

## 3.6 Boss Health Bar
Health bar on the top right of the screen below the boss health text to better indicate the current health of the boss. It visible in level 2 and level 3 when the boss is present.

## 3.7 Visual Effect when player takes damange
A red filter will appear on screen when player take damage either from the enemy bullet, colliding with enemy plane or when enemy plane penerated through the left side of the screen. 

## 3.8 Level Cleared indicator
When a level is clear a red color text will appear in the middle of the screen that say 'Level Cleared' and the game will pauses for a while before transition to the next level.

## 3.9 Restart Game 
Player can restart the game by press the button 'R', the game will restart back from level 1. When the button 'R' is press it will bring player back to level 1 and the game restarts.

## 3.10 Instruction Text
Added a text to indicate how to pause the game and how to restart the game on the bottom left of the screen in each level.

## 3.11 Addition Level 3
Added a harder level after level 2 where the boss and enemy plane will spawn, player have to destroy the boss to win the game. The enemy plane will keep spawning until the boss is defeated and player wil the game.

## 3.12 Health projectile
Health projectile for the boss where instead of shooting fireball the boss will sometimes shoot a heart shape projectile, when player touches the heart shape projectile it will increase their current health bt one, the maximum health for a player is 5. When a a player has 5 current health and they touches the health projectile nothing will happen.

<p>&nbsp;</p>

# **4. Implemented but not Working Features**
## 4.1 Pause when transitioning
When the press button is press during the game transitioning to the next level, the game would bug. The solution to fix this problem is to add error handling to the pause function so that when the pause button is trigger during transitioning nothing happens.

## 4.2 Hitbox not truly accurate
Tried to reduce the hit box by creating boundaries around the image of the entity but after trying to reduce the boundaries by 80%, the hitbox is still not really accurate, to fix this problem I should remove the backgroudn of the entity so that the hitbox can be 100% accurate.

<p>&nbsp;</p>

# **5. Features Not Implemented**
## 5.1 Ultimate move for the player
When player gets enough energy they can activate an ultimate move where it increases the projectile firing speed, therefore easier to clear harder level. The reason why it wasn't implemented is because of time constraint 

## 5.2 Timer for level 3
Player has to clear level 3 before the timer is up and they lose the game. The reason why this is not implemented is because of timeconstraint too.

<p>&nbsp;</p>

# **6. Additional Java Class**
## 6.1 Main Menu Class
Added a new class for main menu, where the game doesn't start straight away on level one it start on the main menu, player can choose to play the game or exit it.

## 6.2 Transition Screen Class
Added this new class to handle all the logic for transition screen between level. 

## 6.3 Red Screen Effect Class
To handle the red screen effect that appears up on screen when player take damage.

## 6.4 Level Three Class
To handle the logic of level three where the boss and enemy plane will spawn.

## 6.5 Level Three Level View Class
Responsible for setting up the scene of level Three.

## 6.6 Health Projectile Class
Handle the health projectile images and speed.

## 6.7 Bounds Handler Class
To get a precious bound of the image of all the plane and projectiles. By reducing the bound, it increases the accuracy of the hitbox of the enemy and player.

## 6.8 Destruction State Class
This class serve as a state tracker which indicates whether an entity is destroyed or not.

## 6.9 Image Handler Class
This class is to simplify the process of configuring an ImageView by handling the loading of images from a predefined location and applying specific properties such as height and aspect ratio preservation.

## 6.10 Movement Manager Class
Manage movement patterns for object, generating randomized vertical movement sequences and applyign horizontal or vertical translation to the image.

## 6.11 Projectile Manager Class
Handles the firing of projectiles with randomized types and determines their initial positions and firing conditions based on probabilities.

## 6.12 Shield Manager Class
Manages the activation, positioning, and deactivation of a shield in a game level, based on a probability and a frame duration limit.

## 6.13 Actor Manager Class
Handles updating, removing, and managing interactions such as collisions between game actors.

## 6.14 Background Manager Class
Manages the game background, sets up its display properties, and handles user input for controlling the player character and triggering game actions.

## 6.15 Collision Manager Class
Processes collisions between various game entities, including friendly units, enemy units, and projectiles, while triggering effects and handling special cases like health projectiles.

## 6.16 Enemy Manager Class
Handles spawning, tracking, and removing enemy units in the game, managing their presence in the scene and detecting if they breach defensive boundaries.

## 6.17 Game Loop Manager Class
Controls the main game loop using a JavaFX Timeline, allowing periodic updates to the game scene and providing methods to start, stop, pause, and resume the loop.

## 6.18 Level Projectile Manager Class
Manages the addition, updating, and removal of user and enemy projectiles in the game, ensuring they are properly tracked and displayed in the scene.

## 6.19 Level Transition Manager Class
Handles level transitions by displaying messages like "Level Cleared," pausing the game loop, and triggering callbacks to transition to the next level.

## 6.20 Pause Manager Class
Controls the game's pause functionality by toggling the game loop state, displaying a pause overlay with UI elements, and applying visual effects like blurring to the game scene.

## 6.21 Scene Manager Class
Organizes and manages the main game scene, including separate layers for the root, overlays, and pause elements, providing access to these groups for structured scene management.

## 6.22 Health Manager Class
Manages the player's health, providing methods to decrement, check if health is zero, and get or set the current health value.

## 6.23 Game Over Handler Class
Manages end-of-game scenarios, handling game-over conditions or transitioning to the next level based on the player's status and game progress.

## 6.24 Main Menu Button Manager Class
Manages the creation, styling, and functionality of Main Menu button allowing it to be shown, hidden, and interacted with to navigate back to the main menu.

## 6.25 Pause Text Manager Class
Manages the display, styling, and positioning of Game Paused! text providing methods to show, hide, and update its appearance.

## 6.26 Instruction Text Manager Class
Manages the display and styling of instruction text overlay on the bottom left corner of the screen which provide a guide on how to pause and restart the game.

## 6.27 Kills Remaining Text Manager Class
Manages the display, positioning, and updating of a text element showing the remaining kills required in the game, providing methods to update or hide the text.

## 6.28 Boss Health Manager Class
Manages the display and updates of a boss's health bar and health text in the game, providing a visual representation of the boss's remaining health.

<p>&nbsp;</p>

# **7. Modified Java Class**
## 7.1 Controller 
- Removed Observer interface and replaced update logic with streamlined transition methods.
- Added MainMenu and TransitionScreen for enhanced game flow and visual transitions.
- Intorduced pause and restart functionality with methods togglePause and restart Level.
- Enhanced error handling with showError for userr-friendly alerts.
- Updated level constructor to include Controller reference, simplyfing scene management.
- Applied fade-in effects to smoother level transitions and added default level handling.

## 7.2 Active Actor 
- Delegated image handling to ImageHandler for modularity, removing IMAGE_LOCATION.
- Encapsulated movement logic in MovementManager for cleaner code.
- Added getPreciseBounds() using BoundsHandler for accurate collision detection.

## 7.3 Active Actor Destructible 
- Replaced isDestroyed boolean with DestructionState for enhanced state management.
- Added modularized DestructionState logic for tracking destruction staus.

## 7.4 Boss
- Replaced internal movement, shield and projectile logic with dedicated managers.
- Removed hardcoded shield logic.
- Enhanced health management by introducing currentHealth and accessors.

## 7.5 Enemy Plane
- Encapsulated movement logic in MovementManager for cleaner code.
- Delegated projectile creation to ProjectileManager for modular projectile handling.

## 7.6 Fighter Plane
- Introduced HealthManager for modular health tracking and management
- Delegated projectile position calculations to helper methods for reusability.
- Enhanced abstraction with getMaxHealth method for flexible subclass health definitions.

## 7.7 User Plane
- Added a LevelView reference for UI updates and introduced health-increase logic with UI integration.
- Encapsulated movement logic with boundary checks and a velocity multipluer for flexible direction control.
- Tracked user performance via numberOfKills with methods to retrieve and increment the count.

## 7.8 Level One
- Integrated GameOverHandler for modular game-over logic.
- Enhanced enemy spawning with dynamic spawn probability and maximum enemy count.
- Added modular checks for advancing to the next level or losing the game.

## 7.9 Level Parent
- Replaced direct logic with modular managers for improved maintainability.
- Enhanced pause functionality with PauseManager and added level transitions via LevelTransitionManager.
- Improved projecitle and enemy handling using LevelProjectileManager and EnemyManager.

## 7.10 Level Two
- Introduced Boss enemy as the primary challenge with health dynamically updated in LevelViewLevelTwo.
- Managed game-over logic and level transition using GameOverHandler.

# 7.11 Level View
- Added managers for instruciton text, kills remaining, pause text and main menu button for modular handling.
- Enhanced GameOverImage and WinImage display with dynamic positioning based on window size.
- Included methods to dynamically update heart display, kills remaining and pause state.

## 7.12 Level View Level Two
- Added ShieldImage and BossHealhManager for dynamic shield and boss health management.
- Included methods to show, hide and update the shield position and boss health.

## 7.13 Boss Projectile
- Encapsulated movement logic with updatePosition to handle horizontal motion.

## 7.14 Enemy Projectile
- Encapsulated movement logic in updatedPosition to handle horizontal motion.

## 7.15 Projectile
- Defined as an abstract class for destructible projectiles with common behaviour for taking damage and position updates.
- Mandates implementation of updatePosition in subclasses for specific movement logic.

## 7.16 User Projectile
- Implemented movement logic in updatePosition to handle rightward horizontal motion.

## 7.17 Game Over Image
- Enhanced constructor to support setting custom width and height for flexible positioning and scaling. 
- Display "Game Over" image using ImageView and initializes it with provided dimensions and coordinates.

## 7.18 Win Image
- Displays the "You Win" image using ImageView with predefined deminsions and postioning.
- Includes a method to make the image visible when showWinImage is trigger, initally set to invisible.

## 7.19 Heart Display
- Displays player health using heart icons within an HBox container.
- Support dynamic updates with methods to add or remove hearts.
- Provid real-time tracking of the displayed heart count via getCurrentHeartCount.

## 7.20 Shield Image
- Provides methods to show, hide and reposition the shield dynamically.

<p>&nbsp;</p>

# **8. Unexpected Problem** 
## 8.1 Game crashes randomly
Encounter “class java.lang.reflect.invocationtargetexception" error when playing the game, the game freezes and crash. To fix this issue, I have changed the wrong image type that is in ShieldImage class, from jpg to it's original file type png. This fixed the game crashing issue.

## 8.2 When switching to level 2 the game freeze and crash
When I beat level 1, the game freeze when switching to level 2, sometimes the game crashes completely and show this error “Exception: java.lang.OutOfMemoryError thrown from the UncaughtExceptionHandler in thread "InvokeLaterDispatcher”. I added a command in levelparent class to make sure to stop the current timeline so that it can continue to the next level. This fix the issue where the game freeze and crash when switching to level 2.

## 8.3 Missing requires transitive
A warning in both controller file and main file which are related to the type Stage from the module javafx.graphics which indicated it cannot be access by the client due to missing requires transitive. Added a line in module-info.java file to fix this warning.

## 8.4 Observable type is deprecated
The type Observable is deprecated since version 9, removed the observable pattern completely from levelparent and controller class. Uses constructor and added a setonlevelchange method to help with the callback. Created a notifyLevelChange method to trigger level change. This fixed the warning.

## 8.5 String created but not being used in ShieldImage class
Warning fixes in ShieldImage class where the string IMAGE_NAME is created but not being used at all, I fix the path that the string IMAGE_NAME points to and the string are being called in the following function.