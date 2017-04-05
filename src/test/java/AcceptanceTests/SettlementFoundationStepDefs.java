//package AcceptanceTests;
//
//import GameInteractionModule.Rules.SettlementFoundationRules;
//import GameStateModule.*;
//import cucumber.api.PendingException;
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.When;
//import cucumber.api.java.en.Then;
//
//import java.util.ArrayList;
//
///**
// * Created by jslocke on 3/23/17.
// */
//
///* TODO: Why can't we use the same language in test?
// * Is this duplication of tests? How do we avoid it
// */
//public class SettlementFoundationStepDefs {
//    GameState game = new GameState();
//    Tile tile;
//    boolean exceptionThrown = false;
//    @Given("^There is a tile with an unoccupied Terrain hex$")
//    public void there_is_a_tile_with_an_unoccupied_Terrain_hex() throws Throwable {
//        //TODO: find better way of dealing with coordinates
//
//
//        /*ArrayList<Coordinate> coordinates = new ArrayList<>();
//        coordinates.add(new Coordinate(100,100));
//        coordinates.add(new Coordinate(101,101));
//        coordinates.add(new Coordinate(100,101));
//
//        ArrayList<TerrainType> terrains = new ArrayList<>();
//        terrains.add(TerrainType.VOLCANO);
//        terrains.add(TerrainType.GRASSLAND);
//        terrains.add(TerrainType.LAKE);*/
//
//        Hex[] hexes = new Hex[3];
//        hexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
//        hexes[1] = new Hex(new Coordinate(101,101), TerrainType.GRASSLAND);
//        hexes[2] = new Hex(new Coordinate(100,101), TerrainType.LAKE);
//
//        Tile tile = new Tile(hexes);
//
//
//
//        try{ game.placeTile(tile); }
//        catch (AssertionError e){ exceptionThrown = true; }
//
//    }
//
//    @Given("^the tile is on level (\\d+)$")
//    public void the_tile_is_on_level(int arg1) throws Throwable {
//        Hex hex = game.getHex(new Coordinate(100,100));
//        assert 1 == hex.getLevel();
//    }
//
//    @When("^the Player tries to found a settlement on that hex$")
//    public void the_Player_tries_to_found_a_settlement_on_that_hex() throws Throwable {
//        try{game.foundSettlement(new Coordinate(101,101),new Player());}
//        catch (AssertionError e){ exceptionThrown = true; }
//    }
//
//    @Then("^the Settlement is founded$")
//    public void the_Settlement_is_founded() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        assert  !exceptionThrown;
//    }
//
//    @Then("^(\\d+) Meeple is placed on that Hex$")
//    public void meeple_is_placed_on_that_Hex(int arg1) throws Throwable {
//        Coordinate c = new Coordinate(101,101);
//        Hex h = game.getHex(c);
//        assert h.getMeepleCount()>0;
//    }
//
//    @Given("^There is a hex occupied by another game piece$")
//    public void there_is_a_hex_occupied_by_another_game_piece() throws Throwable {
//
//        /*ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
//        coordinates.add(new Coordinate(100,100));
//        coordinates.add(new Coordinate(101,101));
//        coordinates.add(new Coordinate(100,101));
//        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
//        terrains.add(TerrainType.VOLCANO);
//        terrains.add(TerrainType.GRASSLAND);
//        terrains.add(TerrainType.LAKE);*/
//
//        Hex[] hexes = new Hex[3];
//        hexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
//        hexes[1] = new Hex(new Coordinate(101,101), TerrainType.GRASSLAND);
//        hexes[2] = new Hex(new Coordinate(100,101), TerrainType.LAKE);
//
//        tile = new Tile(hexes);
//
//        game.placeTile(tile);
//        Coordinate c = new Coordinate(101,101);
//        Hex h = game.getHex(c);
//        try{game.foundSettlement(new Coordinate(101,101),new Player());}
//        catch (AssertionError e){}
//        assert !SettlementFoundationRules.isUnnocupied(h);
//    }
//    @When("^the player attempts to found a settlement at that location$")
//    public void the_player_attempts_to_found_a_settlement_at_that_location() throws Throwable {
//        try{ game.foundSettlement(new Coordinate(101,101),new Player());}
//        catch (AssertionError e){ exceptionThrown = true; }
//    }
//
//    @Then("^the settlment is not founded$")
//    public void the_settlment_is_not_founded() throws Throwable {
//        assert  exceptionThrown;
//    }
//
//
//    @Then("^The Player cannot found the settlement$")
//    public void the_Player_cannot_found_the_settlement() throws Throwable {
//        assert  exceptionThrown;
//    }
//
//    @Then("^The Player is prompted to choose a valid location$")
//    public void the_Player_is_prompted_to_choose_a_valid_location() throws Throwable {
//        System.out.println("Please choose a valid location");
//    }
//
//    @Given("^There is an empty hex on a level greater than (\\d+)$")
//    public void there_is_an_empty_hex_on_a_level_greater_than(int arg1) throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        throw new PendingException();
//    }
//
//    @Given("^There is a Volcano Hex$")
//    public void there_is_a_Volcano_Hex() throws Throwable {
//        ArrayList<Coordinate> coordinates = new ArrayList<Coordinate>();
//        coordinates.add(new Coordinate(100,100));
//        coordinates.add(new Coordinate(101,101));
//        coordinates.add(new Coordinate(100,101));
//        ArrayList<TerrainType> terrains = new ArrayList<TerrainType>();
//        terrains.add(TerrainType.VOLCANO);
//        terrains.add(TerrainType.GRASSLAND);
//        terrains.add(TerrainType.LAKE);
//
//        Hex[] hexes = new Hex[3];
//        hexes[0] = new Hex(new Coordinate(100,100), TerrainType.VOLCANO);
//        hexes[1] = new Hex(new Coordinate(101,101), TerrainType.GRASSLAND);
//        hexes[2] = new Hex(new Coordinate(100,101), TerrainType.LAKE);
//
//        tile = new Tile(hexes);
//
//
//        game.placeTile(tile);
//    }
//
//    @When("^the Player tries to found a settlement on the hex$")
//    public void the_Player_tries_to_found_a_settlement_on_the_hex() throws Throwable {
//        try{ game.foundSettlement(new Coordinate(100,100),new Player());}
//        catch (AssertionError e){ exceptionThrown = true; }
//    }
//
//    @Then("^The Player cannot found the settlement on that hex$")
//    public void the_Player_cannot_found_the_settlement_on_that_hex() throws Throwable {
//        //TODO: might need to look at e?
//        assert  exceptionThrown;
//    }
//
//}