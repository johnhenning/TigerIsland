Feature: Leveled Tile Placement
  In order to proceed to the build phase
  the Player should
  Scenario: Place Tile on a Higher Level killing Meeples
    Given there are two or three tiles of the same level
    And the Volcano hex of one tile is adjacent to the other tile(s)
    And there are Meeples on a Terrain hex adjacent to the other tile(s)
    When the Player tries to place a new Tile on top of the other tiles with a valid placement,
    Then The Meeples are killed and the Tile is added to the board

  Scenario: Place Tile on a Higher Level with empty hexes
    Given there are two or three tiles of the same level
    And the Volcano hex of one tile is adjacent to the other tile(s)
    And the tile adjacent hexes are empty
    When the Player tries to place a new Tile on top of the other tiles with a valid placement,
    Then the Tile is added to the board

  Scenario: Place Tile directly above another tile
    Given There is a tile
    When The Player tries to place a new tile directly above the original tile
    Then The new tile is not added to the board
    And The Player is prompted to make a valid tile placement

  Scenario: Place Tile on a Higher Level on different leveled tiles
    Given there are two tiles on different levels
    When the Player tries to place a new Tile on top of the other tiles,
    Then The tile is not added to the board
    And the Player is prompted to make a valid placement

#  Scenario: Place Tile on a Higher Level Without Volcanoes lined up
#    Given there are two or three tiles on the same level
#    And There are three adjacent hexes
#    Wh

  Scenario: Place Tile on a Higher Level on top of a Totoro
    Given there are two or three tiles of the same level
    And the Volcano hex of one tile is adjacent to the other tile
    And there is a Totoro on a Terrain hex adjacent to the other tile
    When the Player tries to place a new Tile on top of the other tiles
    And the The Player made a valid placement,
    Then the Tile is not added to the board
    And the Player is prompted to make a valid placement