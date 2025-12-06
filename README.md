# Stud Poker Game – Java Texas Hold'em Simulator

A console-based Texas Hold'em–style poker game written in Java for CSC-120 at Union College.

The program models a deck of cards, 2-card "stud" hands, and a shared community card set. On
each round, the game deals two hands, shows the community cards, and asks the user to guess
which hand is stronger (or if they are equal).

## Features

- Object-oriented design with separate classes for `Card`, `Deck`, `PokerHand`,
  `CommunityCardSet`, `StudPokerHand`, and `Client`.
- Logic to generate all possible 5-card `PokerHand` combinations from 7 cards and select
  the best hand using a custom `compareTo` method.
- Interactive console loop that deals cards, displays community cards, prompts the user for
  a guess (`a`, `b`, or tie), tracks score, and ends when the guess is wrong or the deck
  runs out.
- Simple tester classes to verify deck operations, community card behavior, and
  hand comparison.

## How to Run

```bash
javac proj4/*.java
java proj4.Client
