import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;


/**
 * Created by kyle on 3/16/17.
 */

public class TilePlacement {

    Grid gameBoard = new Grid(200); //this is a hack

    @Given("^there are no tiles on the grid$")
    public void there_are_no_tiles_on_the_grid() throws Throwable {
        //TODO: make function that checks if all of grid is NULL

        if(gameBoard == null) { //needs to go
            throw new PendingException();
        }
    }

    @When("^the Player tries to place a tile,$")
    public void the_Player_tries_to_place_a_tile() throws Throwable {
        //TODO: this should be the first file
        int[][] coord = new int[3][2];
        for(int i = 0; i < 3; i++){
            coord[i][0] = 0;
            coord[i][1] = 0;
        }
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;

        Tile tile = new Tile(coord, terrains);
        //place tile into game board
        if(!gameBoard.PlaceTile(tile)) //placeTile fails -- bad code
            throw new PendingException();

    }

    @Then("^the tile is added to the board.$")
    public void the_tile_is_added_to_the_board() throws Throwable {

        //TODO: can just see if we can place piece exactly like above, if we can't it was added?
        int[][] coord = new int[3][2];
        for(int i = 0; i < 3; i++){
            coord[i][0] = 0;
            coord[i][1] = 0;
        }
        TerrainType[] terrains = new TerrainType[3];
        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.GRASSLAND;
        terrains[2] = TerrainType.LAKE;
        Tile tile = new Tile(coord, terrains);



        if(gameBoard.CheckForUnoccupiedHexes(tile)) //could use better naming or seperate function
            throw new PendingException();

    }

    @When("^the Player tries to place the tile not adjacent to the existing tiles$")
    public void the_Player_tries_to_place_the_tile_not_adjacent_to_the_existing_tiles() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^the tile is not added to the board$")
    public void the_tile_is_not_added_to_the_board() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^the Player is prompted to pick a valid location$")
    public void the_Player_is_prompted_to_pick_a_valid_location() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }
    public void setCoordiantes(int[][] coordiantes) {
        return;
    }
}
