package IOModule;

import GameInteractionModule.Rules.*;
import GameInteractionModule.Turn;
import GameStateModule.*;
import ServerModule.Adapter;

import java.security.cert.CertificateEncodingException;
import java.util.*;

import static GameInteractionModule.Rules.BuildRules.isUnnocupied;
import static GameInteractionModule.Rules.Rules.*;
import static GameStateModule.Coordinate.removeDuplicates;

/**
 * Created by johnhenning on 4/4/17.
 */
public class AI implements Player {
    private ArrayList<Coordinate> validTotoroCoordinates;
    private ArrayList<Coordinate> validTigerCoordinates;
    private Random r;
    public BuildMove previousBuildMove;
//    private ArrayList<Coordinate>

    public AI() {
        validTotoroCoordinates = new ArrayList<>();
        validTigerCoordinates = new ArrayList<>();
        r = new Random();
    }
    @Override
    public void completeTurn(Message message, GameState gameState) {
        //Calculate Tile Placement
        ArrayList<Tile> validTiles = calculateValidTilePlacements(message, gameState);
        int tileIndex = r.nextInt(validTiles.size());
        message.tile = validTiles.get(tileIndex);
        Tile tile = message.tile;
        Turn.makeTileMove(tile, gameState);

        BuildMove buildMove = new BuildMove(null, null, null);
        //Calculate Build Move
        if (gameState.getCurrentPlayer().getNumMeeples() > 0) {
            buildMove = calculateBuildMove(tile, gameState);
            message.buildMove = buildMove;
            Turn.makeBuildMove(buildMove, gameState);
        } else {
            message.buildMove = new BuildMove(BuildMoveType.UNABLE_TO_BUILD, null, null);

        }
        previousBuildMove = buildMove;

        //Send updated serverMessage back to server
        sendMessageToServer(message);
    }

    public BuildMove calculateBuildMove(Tile tile, GameState gameState) {

        ArrayList<Hex> totoroHex = new ArrayList<>();
        Hex settlementHex = null;
        totoroHex = canPlaceTotoro(gameState);
        settlementHex = getBestHexForFoundation(gameState);
        BuildMove expansionMove = null;
        expansionMove = calculateExpansion(gameState);
        if(gameState.getCurrentPlayer().getNumMeeples() < 5){
            expansionMove = null;
        }
        if(previousBuildMove != null && expansionMove != null && previousBuildMove.equals(expansionMove)){
            expansionMove = null;
        }
        if (totoroHex.size() != 0) {
            return new BuildMove(BuildMoveType.PLACETOTORO, totoroHex.get(0).getCoordinate(), null);
        } else if(expansionMove != null){

            return expansionMove;
        } else if (settlementHex != null) {
            return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, settlementHex.getCoordinate(), null);
        } else {
            Coordinate coordinate = new Coordinate(-1, -1);
            for (Hex h : tile.getHexes()) {
                if (h.getTerrain() != TerrainType.VOLCANO) {
                    coordinate = h.getCoordinate();
                }
            }
            return new BuildMove(BuildMoveType.FOUNDSETTLEMENT, coordinate, null);
        }

    }

    public ArrayList<Coordinate> calculateTilePlacement(GameState gameState) {
        ArrayList<Tile> placedTiles = gameState.getGameboard().getPlacedTiles();
        Hex h = calculateBottomRightMostHex(placedTiles);
        Coordinate c = Adapter.downRight(h.getCoordinate());
        ArrayList<Coordinate> coords = new ArrayList<>();
        coords.add(Adapter.downLeft(c));
        coords.add(c);
        coords.add(Adapter.downRight(c));

        return coords;
    }

    public ArrayList<Tile> calculateValidTilePlacements(Message message, GameState gameState) {
        ArrayList<Coordinate> nullCoordinates = getAdjacentNullCoordinates(gameState);
        ArrayList<TerrainType> terrains = new ArrayList<>();
        for (Hex hex : message.tile.getHexes()) {
            terrains.add(hex.getTerrain());
        }
        ArrayList<Tile> validTiles = new ArrayList<>();
        for (Coordinate coordinate : nullCoordinates) {
            for (int i = 1; i <= 6; i++) {
                Coordinate [] coordinates  = Adapter.getCoordinatesOfOpponentsTile(coordinate, i);
                boolean coordinatesAreNull = gameState.getHex(coordinates[0]) == null
                        && gameState.getHex(coordinates[1]) == null;
                if (coordinatesAreNull) {
                    ArrayList<Coordinate> validCoordinates = new ArrayList<>();
                    validCoordinates.add(coordinate);
                    validCoordinates.add(coordinates[0]);
                    validCoordinates.add(coordinates[1]);
                    Tile tile = new Tile(validCoordinates, terrains);
                    validTiles.add(tile);
                }
            }
        }
        return validTiles;
    }

    public ArrayList<Coordinate> getAdjacentNullCoordinates(GameState gameState) {
        ArrayList<Coordinate> nullCoordinates = new ArrayList<>();

        for (Tile tile : gameState.getGameboard().getPlacedTiles()) {
            for (Hex hex : tile.getHexes()) {
                ArrayList<Coordinate> coordinates = gameState.getGameboard().getNeighborHexes(hex);
                for (Coordinate coordinate : coordinates) {
                    if (gameState.getHex(coordinate) == null) {
                        nullCoordinates.add(coordinate);
                    }
                }
            }
        }
        nullCoordinates = Coordinate.removeDuplicates(nullCoordinates);
        return nullCoordinates;
    }

    public Hex calculateBottomRightMostHex(ArrayList<Tile> placedTiles) {
        int max = 0;
        Tile bottomRightTile;
        Hex bottomRightHex = new Hex(null, null);
        for (Tile t : placedTiles) {
            for (Hex h : t.getHexes()) {
                int coord_val = h.getCoordinate().getX() + h.getCoordinate().getY();
                if (coord_val > max) {
                    max = coord_val;
                    bottomRightHex = h;
                }
            }
        }
        return bottomRightHex;
    }

    private void sendMessageToServer(Message message) {

    }

    public ArrayList<Settlement> getPlayerSettlementsLessThanFive(GameState gameState) {

        ArrayList<Settlement> settlementList = gameState.getSettlementList();
        ArrayList<Settlement> playerSettlements = new ArrayList<>();
        ArrayList<Settlement> playerSettlementsLessThanFive = new ArrayList<>();
        for (Settlement s : settlementList) {
            if (s.getOwner().equals(gameState.getCurrentPlayer())) {
                playerSettlements.add(s);
            }
        }

        for (Settlement s : playerSettlements) {
            if (s.getSize() <= 4) {
                playerSettlementsLessThanFive.add(s);
            }
        }
        return playerSettlementsLessThanFive;
    }

    public boolean isNewSettlementLarger(Settlement previous, Settlement current) {
        if (previous == null) {
            return true;
        }

        return previous.getSettlementCoordinates().size() < current.getSettlementCoordinates().size();

    }

    public Hex getBestHexForFoundation(GameState gameState) {
        ArrayList<Settlement> lessThanSizeFivePlayerSettlements = getPlayerSettlementsLessThanFive(gameState);
        Settlement bestSettlment = null;
        Hex bestHex = null;
        for (Settlement s : lessThanSizeFivePlayerSettlements) {
            ArrayList<Hex> adjacentHexes = getHexesAdjacentToSettlementLessThanFive(s, gameState);
            if ((adjacentHexes != null) && isNewSettlementLarger(bestSettlment, s) && (adjacentHexes.size() >0)) {
                bestSettlment = s;
                bestHex = adjacentHexes.get(0);
            }
        }
        if(SettlementFoundationRules.isValidFoundation(bestHex, gameState.getCurrentPlayer())) {
            return bestHex;
        }else{
            return  null;
        }

    }

    public Settlement getLargestSettlement(GameState gameState){
        ArrayList<Settlement> lessThanSizeFivePlayerSettlements = getPlayerSettlementsLessThanFive(gameState);
        Settlement bestSettlment = null;

        for (Settlement s : lessThanSizeFivePlayerSettlements) {

            if (isNewSettlementLarger(bestSettlment, s)) {
                bestSettlment = s;
            }
        }
        return bestSettlment;
    }

    public ArrayList<Hex> getHexesAdjacentToSettlements(ArrayList<Settlement> playerSettlements, GameState gameState) {

        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();

        for (Settlement s : playerSettlements) {
            for (Coordinate c : s.getSettlementCoordinates()) {
                Hex hex = gameboard.getHexFromCoordinate(c);
                for (Hex h : getAdjacentHexes(hex, gameboard)) {
                    if (!containsHex(adjacentHexes, h)) {
                        adjacentHexes.add(h);
                    }
                }
            }
        }


        Iterator<Hex> hexIterator = adjacentHexes.iterator();
        while(hexIterator.hasNext()) {
            Hex hex = hexIterator.next();
            if(!SettlementFoundationRules.isValidFoundation(hex, gameState.getCurrentPlayer()))
            {
                hexIterator.remove();
            }
        }
        return adjacentHexes;
    }

    public ArrayList<Hex> getHexesAdjacentToSettlementLessThanFive(Settlement playerSettlement, GameState gameState) {
        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();


        for (Coordinate c : playerSettlement.getSettlementCoordinates()) {
            Hex hex = gameboard.getHexFromCoordinate(c);
            for (Hex h : getAdjacentHexes(hex, gameboard)) {
                if (!containsHex(adjacentHexes, h)) {
                    adjacentHexes.add(h);
                }
            }
        }

        Iterator<Hex> hexIterator = adjacentHexes.iterator();
        while(hexIterator.hasNext()){
            Hex hex2 = hexIterator.next();
            if(!SettlementFoundationRules.isValidFoundation(hex2, gameState.getCurrentPlayer())){
                hexIterator.remove();
            }
        }

        return adjacentHexes;
    }
    public ArrayList<Hex> getAdjacentHexes(Hex hex, Grid gameboard){
        ArrayList<Hex> adjacentHexes = new ArrayList<>();
        if(downRight(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(downRight(gameboard, hex.getCoordinate()));
        if(downLeft(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(downLeft(gameboard, hex.getCoordinate()));
        if(topRight(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(topRight(gameboard, hex.getCoordinate()));
        if(topLeft(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(topLeft(gameboard, hex.getCoordinate()));
        if(rightOfHex(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(rightOfHex(gameboard, hex.getCoordinate()));
        if(leftOfHex(gameboard, hex.getCoordinate()) != null)
            adjacentHexes.add(leftOfHex(gameboard, hex.getCoordinate()));
        return adjacentHexes;
    }
    public ArrayList<Hex> getHexesAdjacentToSettlementLessThanFiveTotoro(Settlement playerSettlement, GameState gameState) {
        Grid gameboard = gameState.getGameboard();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();


        for (Coordinate c : playerSettlement.getSettlementCoordinates()) {
            Hex hex = gameboard.getHexFromCoordinate(c);
            for (Hex h : getAdjacentHexes(hex, gameboard)) {
                if (!containsHex(adjacentHexes, h)) {
                    adjacentHexes.add(h);
                }
            }
        }

        Iterator<Hex> hexIterator = adjacentHexes.iterator();
        while(hexIterator.hasNext()){
            Hex hex2 = hexIterator.next();
            if(!TotoroBuildRules.isValidTotoroLocation(hex2,gameState.getCurrentPlayer(),gameState)){
                hexIterator.remove();
            }
        }

        return adjacentHexes;
    }
    public boolean containsHex(ArrayList<Hex> hexes, Hex hex) {
        for (Hex h : hexes) {
            if (h.equals(hex)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Hex> canPlaceTotoro(GameState gameState) {
        ArrayList<Settlement> playerSettlement = new ArrayList<>();
        ArrayList<Hex> adjacentHexes = new ArrayList<>();
        playerSettlement = TotoroBuildRules.playerHasSizeFiveSettlement(gameState);
        if (playerSettlement.size() != 0) {
            for (Settlement s : playerSettlement) {
                if (TotoroBuildRules.settlementNotContainTotoros(s, gameState)) {
                    adjacentHexes = getHexesAdjacentToSettlementLessThanFiveTotoro(s, gameState);
                    return adjacentHexes;
                }
            }
        }
//        Iterator<Hex> hexIterator = adjacentHexes.iterator();
//        while(hexIterator.hasNext()){
//            Hex hex2 = hexIterator.next();
//            if(!TotoroBuildRules.isValidTotoroLocation(hex2, gameState.getCurrentPlayer(), gameState)){
//                hexIterator.remove();
//            }
//        }


        return adjacentHexes;
    }

    public ArrayList<Coordinate> expandHex(GameState gameState, Settlement settlement, TerrainType terrain){
        ArrayList<Coordinate> settlementCoords = gameState.getCoordinatesofSettlement(settlement);
        Settlement copyOfSettlement = new Settlement(settlementCoords, gameState.getCurrentPlayer(), settlement.getSettlementID());
        ArrayList<Coordinate> hexesEncountered = copyOfSettlement.getSettlementCoordinates();
        ArrayList<Coordinate> newHexesAdded = new ArrayList<>();
        Stack<Coordinate> coords = new Stack();
        coords.addAll(hexesEncountered);

        while(!coords.empty()){
            Coordinate currentAdjacentCoordinate = coords.pop();
            ArrayList<Coordinate> neighboringCoordinates = findAdjacentCoords(gameState.getGameboard(), terrain, currentAdjacentCoordinate);
            for(int i=0; i<neighboringCoordinates.size(); i++){
                if(contains(hexesEncountered, neighboringCoordinates.get(i))== false){
                    newHexesAdded.add(neighboringCoordinates.get(i));
                    hexesEncountered.add(neighboringCoordinates.get(i));
                    coords.push(neighboringCoordinates.get(i));
                }
            }
        }
        return newHexesAdded;
    }

    public BuildMove calculateExpansion(GameState gameState) {
        Hex expansionHex = null;
        Settlement largestSettlement = getLargestSettlement(gameState);
        ArrayList<Coordinate> coordsToExpandJungle = new ArrayList<>();
        ArrayList<Coordinate> coordsToExpandLake = new ArrayList<>();
        ArrayList<Coordinate> coordsToExpandRock = new ArrayList<>();
        ArrayList<Coordinate> coordsToExpandGrass = new ArrayList<>();
        int largestExpansion = 0;

        if(largestSettlement != null) {
            coordsToExpandJungle = expandHex(gameState, largestSettlement, TerrainType.JUNGLE);
        }
        else
        {
            return null;
        }
        int settlementSizeJungle = largestSettlement.getSettlementCoordinates().size();
        settlementSizeJungle = settlementSizeJungle + coordsToExpandJungle.size();
        if (settlementSizeJungle > 6) {
            return null;
        } else if ((settlementSizeJungle == 5) || (settlementSizeJungle == 6)) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandJungle))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.JUNGLE);
            }
        } else {
            largestExpansion = settlementSizeJungle;
        }

        int settlementSizeLake = largestSettlement.getSettlementCoordinates().size();
        coordsToExpandLake = expandHex(gameState, largestSettlement, TerrainType.LAKE);
        settlementSizeLake = settlementSizeLake + coordsToExpandLake.size();
        if (settlementSizeLake > 6) {
            return null;
        } else if ((settlementSizeLake == 5) || (settlementSizeLake == 6)) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandLake))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.LAKE);
            }

        } else {
            if (settlementSizeLake > largestExpansion) {
                largestExpansion = settlementSizeLake;
            }
        }

        int settlementSizeRock = largestSettlement.getSettlementCoordinates().size();
        coordsToExpandRock = expandHex(gameState, largestSettlement, TerrainType.ROCK);
        settlementSizeRock = settlementSizeRock + coordsToExpandRock.size();
        if (settlementSizeRock > 6) {
            return null;
        } else if ((settlementSizeRock == 5) || (settlementSizeRock == 6)) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandRock))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.ROCK);
            }

        } else {
            if (settlementSizeRock > largestExpansion) {
                largestExpansion = settlementSizeRock;
            }
        }

        int settlementSizeGrass = largestSettlement.getSettlementCoordinates().size();
        coordsToExpandGrass = expandHex(gameState, largestSettlement, TerrainType.GRASS);
        settlementSizeGrass = settlementSizeGrass + coordsToExpandGrass.size();
        if (settlementSizeGrass > 6) {
            return null;
        } else if ((settlementSizeGrass == 5) || (settlementSizeGrass == 6)) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandGrass))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.GRASS);
            }

        } else {
            if (settlementSizeGrass > largestExpansion) {
                largestExpansion = settlementSizeGrass;
            }
        }
        if(largestExpansion == largestSettlement.getSettlementCoordinates().size()){
            return null;
        }
        else if (largestExpansion == settlementSizeJungle) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandJungle))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.JUNGLE);
            }
        } else if (largestExpansion == settlementSizeLake) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandLake))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.LAKE);
            }
        } else if (largestExpansion == settlementSizeRock) {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandRock))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.ROCK);
            }
        } else {
            if (checkPlayerHasEnoughMeeples(gameState, getMeeplesRequiredExpansion(gameState, coordsToExpandGrass))) {
                return new BuildMove(BuildMoveType.EXPANDSETTLEMENT, largestSettlement.getSettlementCoordinates().get(0), TerrainType.GRASS);
            }
        }
        return null;
    }
    public boolean checkPlayerHasEnoughMeeples(GameState gameState, int numMeeples){
        return gameState.getCurrentPlayer().getNumMeeples() >= numMeeples;
    }
    public int getMeeplesRequiredExpansion(GameState gameState, ArrayList<Coordinate> coordinates){
        int meeplesRequired = 0;
        for(Coordinate c: coordinates){
            Hex h = gameState.getHex(c);
            meeplesRequired += h.getLevel();
        }
        return meeplesRequired;
    }

    public boolean contains(ArrayList<Coordinate> hexEncountered, Coordinate currentCoord){
        for(Coordinate c : hexEncountered){
            if(c.getX() == currentCoord.getX() && c.getY() == currentCoord.getY()){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Coordinate> findAdjacentCoords(Grid gameboard, TerrainType terrain, Coordinate coordinate){
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        //we only want to add the coordinates, if they have the same terrain type, and they are unoccupied
        //if the match occurs we need to add the coordinates of that match to the array list
        Hex hex;
        hex = downLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = downRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topRight(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = topLeft(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = leftOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());
        hex = rightOfHex(gameboard, coordinate);
        if(hex != null && (hex.getTerrain() == terrain) && isUnnocupied(hex))
            adjacentCoordinates.add(hex.getCoordinate());

        return adjacentCoordinates;
    }
    public Hex downRight(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y += 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            x += 1;
            y += 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex downLeft(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y += 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            y += 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex topRight(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            x += 1;
            y -= 1;
            return  gameboard.getGameboard()[x][y];
        }
    }
    public Hex topLeft(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        if((y % 2) == 0) {  //even
            x -= 1;
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
        else {  //odd
            y -= 1;
            return gameboard.getGameboard()[x][y];
        }
    }

    public Hex leftOfHex(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x -= 1;

        return gameboard.getGameboard()[x][y];
    }

    public Hex rightOfHex(Grid gameboard, Coordinate coordinate){
        int x, y;
        x = coordinate.getX();
        y = coordinate.getY();
        x += 1;
        return gameboard.getGameboard()[x][y];
    }

    private boolean canPlaceNearSettlement(GameState gameState) {
        return true;
    }
}

