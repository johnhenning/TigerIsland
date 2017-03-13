Feature: Scoring
  In order to decide a winner
  The player is able to
  score points by completing certain objectives

  Scenario: Settlement Expanded
    Given there is an existing settlement
    When the Player makes a valid expansion
    Then the Player's score is increased by the sum of all the added Meeple's value
    And  the Meeple's value is equivalent to the level they are placed on

  Scenario: Settlement Founded
    Given there is an empty Terrain Hex on level 1
    When the Player founds a settlement
    Then the Player's score is increased by 1 point

  Scenario: Totoro Placed:
    Given there is a settlement of size 5 or greater
    And the settlement does not have a Totoro
    And the there is an empty Terrain hex next to it