# Swingy42

A text-based RPG game that is playable in both console and with a gui environment. Maven + Java project.

to build and run:

./build.sh

./run.sh

change argument in run.sh to run in either gui or console mode, or switch in-game when prompted with the option.

## Features

- Load an existing avatar if you have created one before
- Create a new avatar (which will then be saved to load in a new game later)
- when creating an avatar, you can enter a Name and then choose the class-type from a given list 
- when the game begins, you will be prompted to choose a direction to move in (to move - WASD)
- a 5x5 board with randomly generated Villains *(red - [-.-])* and your avatar Hero *(green - [0.0])* will appear
- if you move to a cell occupied by a Villain, you have the choice to either *Run* or *Fight*.
- if *Run* was chosen, you have a 50% chance to return to your previous cell, or stay and fight.
- if *Fight* was chosen,you will engage in battle and will either Win and level up, or Lose and fail the mission.
- Level-up formula - level x 1000 + (level - 1)^2 x 450
- Player stats are affected by level and artifacts
- board will increase in size when a player levels up

