# Word Ladder Puzzle Solver

## Project Overview
The **Word Ladder Puzzle Solver** is designed to utilize **graph theory** and **Dijkstra's shortest path algorithm** to find the most efficient transformation between two words. The puzzle involves transforming one word into another by changing one letter at a time, ensuring that each intermediate word is also a valid entry.

<img width="398" alt="image" src="https://github.com/user-attachments/assets/cb0c606f-c351-469c-a5f9-b2279bf25b11">

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
- This project uses **graphs** and **Dijkstra's algorithm** to find the shortest transformation path.
- External algorithms or code snippets can be utilized, but must be properly cited from textbooks, research papers, or online resources.
- Referencing classmates is not allowed.
