Feature: Totoro Placement

  Scenario: Add Totoro to Settlement with Size < 5
    Given: There is a settlement with size less than 5
    And there is an unoccupied Terrain Hex adjacent to the settlement
    When The Player tries to add a Totoro to that hex
    Then the Totoro is not added to the board
    And the player is prompted to make a valid placement

  Scenario: Add Totoro to Settlement that already contains a Totoro
    Given there is a settlement that already has a Totoro
    When the Player tries to add a Totoro to that settlement
    Then the Totoro is not added to the Settlement
    And the Player is prompted to make a valid move

  Scenario: Add Totoro to Settlement with Size >= 5
    Given there is a settlement with size greater than or equal to 5
    And it does not already contain a Totoro
    And there is an unoccupied Terrain Hex adjacent to the settlement
    When the Player tries to add a Totoro at the hex
    Then the Totoro is added to the board

