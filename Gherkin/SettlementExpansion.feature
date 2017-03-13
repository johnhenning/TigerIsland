Feature: Settlement Expansion
  In order to increase their score
  The player is able to
  expand their settlement on a specific terrain type

  Scenario Template: Expand Settlement
    Given there is a settlement with adjacent <type> Terrain Hex(es)
    When the Player expands the settlement onto <type> terrains
    Then expand to all <type> terrain hexes that are continually adjacent
    And place 1 Meeple on each <type> Hex for each Level of the Hex
    But do not expand past other settlements or other Terrain Types
    Examples:
      |   type    |
      |  Beach    |
      |  Jungle   |
      |  Pasture  |
      |  Mountain |
