import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

/**
 * Created by kyle on 3/17/17.
 */

public class GridStepDefs {

    static Grid gameBoard = new Grid(200); //this is a hack

    @Given("^the game just began,$")
    public void the_game_just_began() throws Throwable {
        if(!gameBoard.gridEmpty())
            throw new PendingException();

    }

    @When("^Player (\\d+) places the first tile,$")
    public void player_places_the_first_tile(int arg1) throws Throwable {
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

        Tile tile = new Tile(coord, terrains);
        gameBoard.PlaceTile(tile);

    }

    @Then("^the upper Terrain Hex tile becomes the origin of the Tile Grid$")
    public void the_upper_Terrain_Hex_tile_becomes_the_origin_of_the_Tile_Grid() throws Throwable {
        //we are defining the origin of the tile grid to be the center of the array [max_size/2][max_size/2]
        if(gameBoard.gridEmpty())
            throw new PendingException();


    }

    @Given("^there are tiles placed on the board$")
    public void there_are_tiles_placed_on_the_board() throws Throwable {

        if(gameBoard.gridEmpty())
            throw new PendingException();

//            throw new PendingException();


    }

    @When("^the player places a tile adjacent to other tiles$")
    public void the_player_places_a_tile_adjacent_to_other_tiles() throws Throwable {
        int[][] coord = new int[3][2];

        coord[0][0] = 100 + 1;
        coord[0][1] = 100;
        coord[1][0] = 100 + 1;
        coord[1][1] = 100 - 1;
        coord[2][0] = 100 + 2 ;
        coord[2][1] = 100 - 1;
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;

        Tile tile = new Tile(coord, terrains);
        gameBoard.PlaceTile(tile);


    }

    @Then("^The new tile is saved at the coordinates at which it is placed$")
    public void the_new_tile_is_saved_at_the_coordinates_at_which_it_is_placed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there is a valid location to level a tile$")
    public void there_is_a_valid_location_to_level_a_tile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the player levels a tile at certain coordinates$")
    public void the_player_levels_a_tile_at_certain_coordinates() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the original hexes at the coordinates are removed$")
    public void the_original_hexes_at_the_coordinates_are_removed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the new tile is saved at those coordinates$")
    public void the_new_tile_is_saved_at_those_coordinates() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
