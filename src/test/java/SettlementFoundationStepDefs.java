import GameInteraction.*;
import GameState.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
/**
 * Created by jslocke on 3/23/17.
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
        assert 1 == tile.getLevel();
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





    @Then("^The Player cannot found the settlement$")
    public void the_Player_cannot_found_the_settlement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^The Player is prompted to choose a valid location$")
    public void the_Player_is_prompted_to_choose_a_valid_location() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There is an empty hex on a level greater than (\\d+)$")
    public void there_is_an_empty_hex_on_a_level_greater_than(int arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There is a Volcano Hex$")
    public void there_is_a_Volcano_Hex() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
