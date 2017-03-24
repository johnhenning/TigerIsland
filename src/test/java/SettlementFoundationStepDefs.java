import GameInteraction.*;
import GameState.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
/**
 * Created by jslocke on 3/23/17.
 */

/* TODO: Why can't we use the same language in test?
 * Is this duplication of tests? How do we avoid it
 */
public class SettlementFoundationStepDefs {
    GameState game = new GameState();
    Tile tile;
    boolean settlementFounded = false;
    @Given("^There is a tile with an unoccupied Terrain hex$")
    public void there_is_a_tile_with_an_unoccupied_Terrain_hex() throws Throwable {
        //TODO: find better way of dealing with coordiantes
        int[][] coord = new int[3][2];

        coord[0][0] = 100;
        coord[0][1] = 100;
        coord[1][0] = 100 + 1;
        coord[1][1] = 100 + 1;
        coord[2][0] = 100;
        coord[2][1] = 100 + 1;
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;

        tile = new Tile(coord,terrains);

        game.placeTile(tile);

    }

    @Given("^the tile is on level (\\d+)$")
    public void the_tile_is_on_level(int arg1) throws Throwable {
        Hex hex = game.getHex(new Coordinate(100,100));
        assert 1 == hex.getLevel();
    }

    @When("^the Player tries to found a settlement on that hex$")
    public void the_Player_tries_to_found_a_settlement_on_that_hex() throws Throwable {
        settlementFounded = game.foundSettlement(new Coordinate(101,101),new Player());
    }

    @Then("^the Settlement is founded$")
    public void the_Settlement_is_founded() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assert game.getSettlementList().size()>0;
    }

    @Then("^(\\d+) Meeple is placed on that Hex$")
    public void meeple_is_placed_on_that_Hex(int arg1) throws Throwable {
        Coordinate c = new Coordinate(101,101);
        Hex h = game.getHex(c);
        assert h.getMeepleCount()>0;
    }

    @Given("^There is a hex occupied by another game piece$")
    public void there_is_a_hex_occupied_by_another_game_piece() throws Throwable {
        int[][] coord = new int[3][2];

        coord[0][0] = 100;
        coord[0][1] = 100;
        coord[1][0] = 100 + 1;
        coord[1][1] = 100 + 1;
        coord[2][0] = 100;
        coord[2][1] = 100 + 1;
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;

        tile = new Tile(coord,terrains);

        game.placeTile(tile);
        Coordinate c = new Coordinate(101,101);
        Hex h = game.getHex(c);
        game.foundSettlement(new Coordinate(101,101),new Player());
        assert !SettlementFoundationRules.isUnnocupied(h);
    }
    @When("^the player attempts to found a settlement at that location$")
    public void the_player_attempts_to_found_a_settlement_at_that_location() throws Throwable {
        settlementFounded = game.foundSettlement(new Coordinate(101,101),new Player());
    }

    @Then("^the settlment is not founded$")
    public void the_settlment_is_not_founded() throws Throwable {
        assert !settlementFounded;
    }


    @Then("^The Player cannot found the settlement$")
    public void the_Player_cannot_found_the_settlement() throws Throwable {
        assert !settlementFounded;
    }

    @Then("^The Player is prompted to choose a valid location$")
    public void the_Player_is_prompted_to_choose_a_valid_location() throws Throwable {
        System.out.println("Please choose a valid location");
    }

    @Given("^There is an empty hex on a level greater than (\\d+)$")
    public void there_is_an_empty_hex_on_a_level_greater_than(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There is a Volcano Hex$")
    public void there_is_a_Volcano_Hex() throws Throwable {
        int[][] coord = new int[3][2];

        coord[0][0] = 100;
        coord[0][1] = 100;
        coord[1][0] = 100 + 1;
        coord[1][1] = 100 + 1;
        coord[2][0] = 100;
        coord[2][1] = 100 + 1;
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;

        tile = new Tile(coord,terrains);

        game.placeTile(tile);
    }

    @When("^the Player tries to found a settlement on the hex$")
    public void the_Player_tries_to_found_a_settlement_on_the_hex() throws Throwable {
        settlementFounded = game.foundSettlement(new Coordinate(100,100),new Player());
    }

    @Then("^The Player cannot found the settlement on that hex$")
    public void the_Player_cannot_found_the_settlement_on_that_hex() throws Throwable {
        assert !settlementFounded;
    }

}
