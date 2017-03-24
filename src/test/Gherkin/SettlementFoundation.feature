Feature: Settlement Foundation
  In order to improve their score
  Players should be able to
  Found a new settlement

  Scenario: Found a Settlement on Unoccupied Level 1 Terrain Hex
    Given There is a tile with an unoccupied Terrain hex
    And the tile is on level 1
    When the Player tries to found a settlement on that hex
    Then the Settlement is founded
    And 1 Meeple is placed on that Hex

  Scenario: Found Settlement on an occupied hex
    Given There is a hex occupied by another game piece
    When the player attempts to found a settlement at that location
    Then the settlment is not founded
    And The Player is prompted to choose a valid location

  Scenario: Found Settlement above level 1
    Given There is an empty hex on a level greater than 1
    When The Player tries to found a settlement on that hex
    Then The Player cannot found the settlement
    And The Player is prompted to choose a valid location

  Scenario: Found Settlement on a Volcano Hex
    Given There is a Volcano Hex
    When the Player tries to found a settlement on the hex
    Then The Player cannot found the settlement on that hex

