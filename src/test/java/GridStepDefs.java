
/**
 * Created by johnhenning on 3/15/17.
 */

import GameInteraction.Rules;
import GameState.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


import java.util.ArrayList;

import static junit.framework.TestCase.fail;
public class GridStepDefs {

    Grid gameBoard = new Grid(200); //this is a hack
    //rules needs to be initialized unless we make methods statics


    @Given("^the game just began,$")
    public void the_game_just_began() throws Throwable {
//        if (gameBoard.TurnNumber() != 0)
//            fail("gameboard isn't empty");
        if(Rules.GameStarted(gameBoard))
            fail("game has already started");
    }



    @When("^Player (\\d+) places the first tile,$")
    public void player_places_the_first_tile(int arg1) throws Throwable {
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

        Tile tile = new Tile(coord, terrains);
        gameBoard.PlaceTile(tile);

        // Write code here that turns the phrase above into concrete actions

    }

    @Then("^the upper Terrain Hex tile becomes the origin of the Tile Grid$")
    public void the_upper_Terrain_Hex_tile_becomes_the_origin_of_the_Tile_Grid() throws Throwable {

        //we are defining the origin of the tile grid to be the center of the array [max_size/2][max_size/2]
        if(gameBoard.HexEmpty(100,100) || gameBoard.HexEmpty(101,101)
                || gameBoard.HexEmpty(100,101))
            fail("hexes are empty");


        // Write code here that turns the phrase above into concrete actions

    }

    @Given("^there are tiles placed on the board$")
    public void there_are_tiles_placed_on_the_board() throws Throwable {
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
        //TODO:placed tile needs to have its rules associated with it
        gameBoard.PlaceTile(tile);



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
        //TODO:placed tile needs to have its rules associated with it
        gameBoard.PlaceTile(tile);



    }

    @Then("^The new tile is saved at the coordinates at which it is placed$")
    public void the_new_tile_is_saved_at_the_coordinates_at_which_it_is_placed() throws Throwable {

        //TODO: write check tile function
        if(gameBoard.HexEmpty(101,100) || gameBoard.HexEmpty(101,99) || gameBoard.HexEmpty(102,99))
            fail("hexes weren't saved");
    }

    @When("^the player places a tile overlapping other tiles$")
    public void the_player_places_a_tile_overlapping_other_tiles() throws Throwable {
        // how do I make it so I want an assert failure to pass this test?
        throw new PendingException();
    }

    @Then("^the new tile is not placed$")
    public void the_new_tile_is_not_placed() throws Throwable {

        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there is a valid location to level a tile$")
    public void there_is_a_valid_location_to_level_a_tile() throws Throwable {
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

        coord[0][0] = 100+1;
        coord[0][1] = 100;
        coord[1][0] = 100 + 2;
        coord[1][1] = 100 + 1;
        coord[2][0] = 100+2;
        coord[2][1] = 100;

        terrains[0] = TerrainType.VOLCANO;
        terrains[1] = TerrainType.JUNGLE;
        terrains[2] = TerrainType.GRASSLAND;

        Tile tile2 = new Tile(coord, terrains);
        //TODO:placed tile needs to have its rules associated with it
        gameBoard.PlaceTile(tile2);

    }

    @When("^the player levels a tile at certain coordinates$")
    public void the_player_levels_a_tile_at_certain_coordinates() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        int[][] coordLevel = new int[3][2];
        coordLevel[0][0] = 100;
        coordLevel[0][1] = 100;
        coordLevel[1][0] = 100 +1;
        coordLevel[1][1] = 100;
        coordLevel[2][0] = 100+1;
        coordLevel[2][1] = 100+1;

        TerrainType[] terrainsLevel = new TerrainType[3];
        terrainsLevel[0] = TerrainType.VOLCANO;
        terrainsLevel[1] = TerrainType.LAKE;
        terrainsLevel[2] = TerrainType.JUNGLE;

        Tile tile3 = new Tile(coordLevel, terrainsLevel);
        gameBoard.LevelTile(tile3);
    }

    @Then("^the original hexes at the coordinates are removed$")
    public void the_original_hexes_at_the_coordinates_are_removed() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //check lvl >1
        Hex hex = gameBoard.getHexFromCoordinate(new Coordinate(100,100));
        int level = hex.getLevel();
        if(level == 1){
            fail("Level not incremented");
        }

    }

    @Then("^the new tile is saved at those coordinates$")
    public void the_new_tile_is_saved_at_those_coordinates() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ArrayList<Tile> leveledTile = gameBoard.getListOfTiles();
       // leveledTile.get(leveledTile.size()-1).getHexes();


    }


    @Given("^there are two or three tiles of the same level$")
    public void there_are_two_or_three_tiles_of_the_same_level() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        throw new PendingException();
    }

    @Given("^the Volcano hex of one tile is adjacent to the other tile\\(s\\)$")
    public void the_Volcano_hex_of_one_tile_is_adjacent_to_the_other_tile_s() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there are Meeples on a Terrain hex adjacent to the other tile\\(s\\)$")
    public void there_are_Meeples_on_a_Terrain_hex_adjacent_to_the_other_tile_s() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the Player tries to place a new Tile on top of the other tiles with a valid placement,$")
    public void the_Player_tries_to_place_a_new_Tile_on_top_of_the_other_tiles_with_a_valid_placement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Meeples are killed and the Tile is added to the board$")
    public void the_Meeples_are_killed_and_the_Tile_is_added_to_the_board() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the tile adjacent hexes are empty$")
    public void the_tile_adjacent_hexes_are_empty() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the Tile is added to the board$")
    public void the_Tile_is_added_to_the_board() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^There is a tile$")
    public void there_is_a_tile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^The Player tries to place a new tile directly above the original tile$")
    public void the_Player_tries_to_place_a_new_tile_directly_above_the_original_tile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The new tile is not added to the board$")
    public void the_new_tile_is_not_added_to_the_board() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The Player is prompted to make a valid tile placement$")
    public void the_Player_is_prompted_to_make_a_valid_tile_placement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there are two tiles on different levels$")
    public void there_are_two_tiles_on_different_levels() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^the Player tries to place a new Tile on top of the other tiles,$")
    public void the_Player_tries_to_place_a_new_Tile_on_top_of_the_other_tiles() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^The tile is not added to the board$")
    public void the_tile_is_not_added_to_the_board() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the Player is prompted to make a valid placement$")
    public void the_Player_is_prompted_to_make_a_valid_placement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^the Volcano hex of one tile is adjacent to the other tile$")
    public void the_Volcano_hex_of_one_tile_is_adjacent_to_the_other_tile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^there is a Totoro on a Terrain hex adjacent to the other tile$")
    public void there_is_a_Totoro_on_a_Terrain_hex_adjacent_to_the_other_tile() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }


    @When("^the The Player made a valid placement,$")
    public void the_The_Player_made_a_valid_placement() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^the Tile is not added to the board$")
    public void the_Tile_is_not_added_to_the_board() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

}
