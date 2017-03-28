Feature: Settlement Merging
  At the end of a players turn
  All settlements that are adjacent
  Combine into one settlement

  Scenario: Two Adjacent Settlements merge at the end of a turn
    Given A player has founded a settlement on the previous turn
    When the player founds a settlement adjacent to that settlement
    Then the settlements merge at the end of the turn

  Scenario: Two non-adjacent Settlements do not merge at the end of a turn
    Given A player has founded a settlement on the previous turn
    When the player founds a settlment on a hex not adjacent to his other settlment
    Then the settlements do not merge at the end of the turn