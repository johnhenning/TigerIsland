package AcceptanceTests;
/**
 * Created by johnhenning on 3/15/17.
 */


import GameStateModule.*;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;


import java.util.ArrayList;

import static junit.framework.TestCase.fail;

public class GridStepDefs {
    Grid gameBoard;
    boolean exceptionThrown = false;
    @Given("^the game just began,$")
    public void the_game_just_began() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        gameBoard = new Grid(200);
        if(!gameBoard.gridEmpty())
            fail("gameboard isn't empty");

    }

    @When("^Player (\\d+) places the first tile,$")
    public void player_places_the_first_tile(int arg1) throws Throwable {
        //TODO: find better way of dealing with coordinates

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        Tile tile = new Tile(coordinates, terrains);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }


    }

    @Then("^the upper Terrain Hex tile becomes the origin of the Tile Grid$")
    public void the_upper_Terrain_Hex_tile_becomes_the_origin_of_the_Tile_Grid() throws Throwable {

        assert !exceptionThrown;
    }

    @Given("^there are tiles placed on the board$")
    public void there_are_tiles_placed_on_the_board() throws Throwable {
        gameBoard = new Grid(200);
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        Tile tile = new Tile(coordinates, terrains);
        try {gameBoard.placeTile(tile);}
        catch (AssertionError e) { exceptionThrown = true; }

    }

    @When("^the player places a tile adjacent to other tiles$")
    public void the_player_places_a_tile_adjacent_to_other_tiles() throws Throwable {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(101,100));
        coordinates.add(new Coordinate(101,99));
        coordinates.add(new Coordinate(102,99));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);

        Tile tile = new Tile(coordinates, terrains);
        try { gameBoard.placeTile(tile); }
        catch (AssertionError e) { exceptionThrown = true; }
    }

    @Then("^The new tile is saved at the coordinates at which it is placed$")
    public void the_new_tile_is_saved_at_the_coordinates_at_which_it_is_placed() throws Throwable {
        assert !exceptionThrown;
    }

    @When("^the player places a tile overlapping other tiles$")
    public void the_player_places_a_tile_overlapping_other_tiles() throws Throwable {
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(100, 100));
        coordinates.add(new Coordinate(100, 99));
        coordinates.add(new Coordinate(101, 99));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.LAKE);
        Tile tile = new Tile(coordinates, terrains);
        //TODO:placed tile needs to have its rules associated with it
        try { gameBoard.placeTile(tile); }
        catch (AssertionError e) { exceptionThrown = true; }
    }

    @Then("^the new tile is not placed$")
    public void the_new_tile_is_not_placed() throws Throwable {

        assert exceptionThrown;
    }

    @Given("^there is a valid location to level a tile$")
    public void there_is_a_valid_location_to_level_a_tile() throws Throwable {
        gameBoard = new Grid(200);

        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,101));
        coordinates.add(new Coordinate(100,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.GRASSLAND);
        terrains.add(TerrainType.ROCKY);

        Tile tile = new Tile(coordinates, terrains);
        try{gameBoard.placeTile(tile);}
        catch (AssertionError e){ exceptionThrown = true; }



        coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(101,100));
        coordinates.add(new Coordinate(101,99));
        coordinates.add(new Coordinate(102,99));

        terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.JUNGLE);
        terrains.add(TerrainType.GRASSLAND);

        tile = new Tile(coordinates, terrains);
        try{ gameBoard.placeTile(tile); }
        catch (AssertionError e){ exceptionThrown = true; }


    }

    @When("^the player levels a tile at certain coordinates$")
    public void the_player_levels_a_tile_at_certain_coordinates() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
        coordinates.add(new Coordinate(100,100));
        coordinates.add(new Coordinate(101,100));
        coordinates.add(new Coordinate(101,101));

        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
        terrains.add(TerrainType.VOLCANO);
        terrains.add(TerrainType.LAKE);
        terrains.add(TerrainType.JUNGLE);

        Tile tile = new Tile(coordinates, terrains);
        try{ gameBoard.levelTile(tile); }
        catch (AssertionError e){ exceptionThrown = true; }

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
        ArrayList<Tile> placedTiles = gameBoard.getPlacedTiles();
        Tile tile = placedTiles.get(2);
        ArrayList<Coordinate> coords = tile.getCoords();
        if(!(coords.get(0).getX() == 100 && coords.get(0).getY() == 100 && coords.get(1).getX()==101 && coords.get(1).getY()==100
                && coords.get(2).getX()==101 && coords.get(2).getY()== 101)){
            fail("Tile not saved at new coordinates");

        }

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
