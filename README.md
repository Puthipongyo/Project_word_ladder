# Word Ladder Puzzle Solver

## Project Overview
The **Word Ladder Puzzle Solver** is designed to utilize **graph theory** and **Dijkstra's shortest path algorithm** to find the most efficient transformation between two words. The puzzle involves transforming one word into another by changing one letter at a time, ensuring that each intermediate word is also a valid entry.

![image](https://github.com/user-attachments/assets/3e89dbc5-23a6-4bff-b282-312085ff9867)
![image](https://github.com/user-attachments/assets/6308e116-87fb-4825-b448-fbda7cbf1caa)


## Transformation Rules
1. **Ladder Step**: Only one character can be changed at a time. The transformation cost is calculated by the difference between the old and new characters based on their positions in the alphabet.  
   **Example**: hears → heirs (cost = 8, since 'a' and 'i' differ by 8 characters).

2. **Elevator Step**: This step can be taken if two words are permutations (anagrams) of each other. The transformation cost is zero.  
   **Example**: these → sheet (cost = 0).

## Programming Requirements
- The program is written in Java and processes word files provided for testing and grading:
  - **Small file**: `words_250.txt` for functionality testing.
  - **Large file**: `words_5757.txt` for grading.
- The program gracefully handles missing files by prompting the user to input a new filename.

## Features
1. **Search Function**: Allows users to search for words starting with a specific string.
2. **Word Ladder Solver**: Users can input two words, and the program will generate the shortest transformation path between them using ladder and elevator steps.
3. **Neighbor Search**: Users can view all neighboring words (words one step away) for a specific word.

## Algorithm & Research
- This project heavily emphasizes **graphs** and employs **Dijkstra's algorithm** to efficiently find the shortest transformation path. Each word can be represented as a node in a graph, with edges connecting nodes that can be transformed into each other based on the defined rules.
- External algorithms or code snippets can be utilized, but must be properly cited from textbooks, research papers, or online resources.
- Referencing classmates is not allowed.
