# Bug Busters (Software Architectures Assignment)

## Specification
A local primary school wishes to use computer applications to help teach very young children to learn. You are required to create a single interactive game that teaches primary school children some aspect of science in a very colourful and fun way.

## My Project
A tower defence game where the user plays as the body's immune system and helps defend against a
series of pathogens. Different towers are effective against differnt pathogens, ie) The anit-biotic
tower will be effective against Bacteria, but will be useless against a Virus. This will teach the 
player that certain medicines only work against certain pathogens.

## Design Patterns
### Design Patterns Implemented

 - Factory: Used to create Towers for the player to place on the map, and all the pathogens that 
 are spawned in a wave.
 - MVC: Used to handle events that cross multiple UI Components
 - Singleton: Used in various places throughout the program to ensure only one object of a certain type is created
 - Command Pattern: Used in the implementation of the pathogen's path finding