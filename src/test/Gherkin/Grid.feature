Feature: Tile Grid
  In order to keep track of the tile positions,
  Players should be able to
  Place a Tile in a proper location

  Scenario: Place First Tile
    Given the game just began,
    When Player 1 places the first tile,
    Then the upper Terrain Hex tile becomes the origin of the Tile Grid

  Scenario: Place a Tile
    Given there are tiles placed on the board
    When the player places a tile adjacent to other tiles
    Then The new tile is saved at the coordinates at which it is placed

  Scenario:  Placing a Tile
    Given there are tiles placed on the board
    When the player places a tile overlapping other tiles
    Then the new tile is not placed

  Scenario: Level a Tile
    Given there is a valid location to level a tile
    When the player levels a tile at certain coordinates
    Then the original hexes at the coordinates are removed
    And the new tile is saved at those coordinates
