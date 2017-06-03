package engine.settings;

import dal.DalImpl;
import dal.Ship;
import dal.ShipType;
import engine.battleShip.BattleShip;
import engine.battleShip.BattleShipImpl;
import engine.enums.Direction;
import exceptions.AddingShipsAboveAllowedAmountException;
import exceptions.InitializationFailException;
import exceptions.NotAllShipTypeAddedToBoardException;
import exceptions.ShipTypeAlreadyDeclaredException;

import java.util.*;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public class SettingsImpl implements Settings {
    private String player1Name = "player 1",
            player2Name = "player 2";
    private int boardSize = 10;
    private Map<String, ShipType> stringShipTypeMap;
    private List<BattleShip> player1Ships;
    private List<BattleShip> player2Ships;
    private int totalShipsTilesAmount = 0;
    private int totalShipsScore = 0;

    public SettingsImpl(DalImpl dal) throws InitializationFailException {
        this.boardSize = dal.getBoardSize();
        stringShipTypeMap = new HashMap<>();
        setShipTypes(dal.getShipTypeList());
        player1Ships = addPlayerShips(dal.getBoard(0));
        player2Ships = addPlayerShips(dal.getBoard(1));
    }

    private void setShipTypes(List<ShipType> shipTypeList) throws ShipTypeAlreadyDeclaredException {
        for (ShipType shipType : shipTypeList) {
            if (stringShipTypeMap.containsKey(shipType.getShipTypeName())) {
                throw new ShipTypeAlreadyDeclaredException(shipType);
            }
            stringShipTypeMap.put(shipType.getShipTypeName(), shipType);
            totalShipsTilesAmount += shipType.getShipSize() * shipType.getShipAmountOnBoard();
            totalShipsScore += shipType.getScore();
        }
    }

    private List<BattleShip> addPlayerShips(List<Ship> playerShipList) throws AddingShipsAboveAllowedAmountException, NotAllShipTypeAddedToBoardException {
        List<BattleShip> playerBattleShips = new ArrayList<>();
        // initializing the shipTypeCount for Player
        Map<String, Integer> shipTypeCount = new HashMap<>();
        for (String key : stringShipTypeMap.keySet()) {
            shipTypeCount.put(key, stringShipTypeMap.get(key).getShipAmountOnBoard());
        }

        for (Ship ship : playerShipList) {
            ShipType shipType = ship.getShipType();
            int currentCount = shipTypeCount.get(shipType.getShipTypeName());
            if (currentCount == 0) {
               throw new AddingShipsAboveAllowedAmountException(ship);
            }
            shipTypeCount.replace(shipType.getShipTypeName(), currentCount - 1);
            BattleShip battleShip = new BattleShipImpl(
                    shipType.getShipSize(),
                    ship.getDirection(),
                    convertPositionToArrayIndex(ship.getPositionX()),
                    convertPositionToArrayIndex(ship.getPositionY()),
                    shipType.getScore()
            );
            playerBattleShips.add(battleShip);
        }
        //validating All required ships had been added
        for (String key : stringShipTypeMap.keySet()) {
            int count = shipTypeCount.get(key);
            if (count != 0) {
                throw new NotAllShipTypeAddedToBoardException(key);
            }
        }

        return playerBattleShips;
    }

    public String getPlayer1Name() {
        return player1Name;
    }

    public String getPlayer2Name() {
        return player2Name;
    }

    public void setPlayer1Name(String player1Name) {
        this.player1Name = player1Name;
    }

    public void setPlayer2Name(String player2Name) {
        this.player2Name = player2Name;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<BattleShip> getPlayer1Ships() {
        return player1Ships;
    }

    public List<BattleShip> getPlayer2Ships() {
        return player2Ships;
    }

    public int getTotalShipsTilesAmount() {
        return totalShipsTilesAmount;
    }

    public int getTotalShipsScore() {
        return totalShipsScore;
    }

    private int convertPositionToArrayIndex(int position) {
        return position - 1;
    }
}
