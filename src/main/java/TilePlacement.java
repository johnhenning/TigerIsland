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

        if(!gameBoard.gridEmpty())
            throw new PendingException();

    }

    @When("^the Player tries to place a tile,$")
    public void the_Player_tries_to_place_a_tile() throws Throwable {

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
        if(!gameBoard.PlaceTile(tile)) //placeTile fails -- bad code
            throw new PendingException();

    }

    @Then("^the tile is added to the board.$")
    public void the_tile_is_added_to_the_board() throws Throwable {

        //TODO: can just see if we can place piece exactly like above, if we can't it was added?
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



    public void setCoordiantes(int[][] coordiantes) {
        return;
    }

//    public boolean checkGameBoardEmpty(Grid grid){
//        return grid.placedTile.isEmpty();
//    }
}
