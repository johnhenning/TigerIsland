Feature: Tile Placement
  In order to proceed to the build phase,
  Players should be able to
  Place Tiles in only valid locations

  Scenario: Place Tile on Empty Grid
    Given there are no tiles on the grid
    When the Player tries to place a tile,
    Then the tile is added to the board.


#  Scenario: Place Tile adjacent to another tile
#    Given there are tiles on the grid
#    When the Player tries to place the tile adjacent to another tile in a valid location
#    Then the tile is added to the board
#
#
#  Scenario: Place Tile not adjacent to existing tiles
#    Given there are tiles on the grid
#    When the Player tries to place the tile not adjacent to the existing tiles
#    Then the tile is not added to the board
#    And the Player is prompted to pick a valid location
