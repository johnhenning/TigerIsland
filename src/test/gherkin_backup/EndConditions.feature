Feature: End Conditions
  In order to complete the game
  The Players are able to
  Meet conditions to try to win the game

  Scenario: Win The Game:
    Given the player has placed all of his or her Totoros
    When the player has placed all of his or her Meeples
    And the player has more points than his or her opponent
    Then The player wins

  Scenario: Lose by Running out of Meeples:
    Given the player has not placed all of his or her Totoros
    When the player has placed all of his or her Meeples
    Then the player loses

  Scenario: Lose by no build move
    Given the player has not placed all of his or her Meeples
    When it is the player's build phase
    And the player is unable to make a legal move
    Then the player loses