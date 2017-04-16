package engine.settings;

import engine.battleShip.BattleShip;
import engine.battleShip.BattleShipImpl;
import engine.enums.Direction;

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


    //todo ship types, boards size etc... should come from the DAL
    // constructor should get list of shipTypes, list of player1 ships, player2 ships
    public SettingsImpl() throws Exception {
        // adding ship types
        ShipType shipTypeA = new ShipType() {
            @Override
            public int getShipSize() {
                return 4;
            }

            @Override
            public int getShipAmountOnBoard() {
                return 1;
            }

            @Override
            public String getType() {
                return "A";
            }
        };
        ShipType shipTypeB = new ShipType() {
            @Override
            public int getShipSize() {
                return 3;
            }

            @Override
            public int getShipAmountOnBoard() {
                return 2;
            }

            @Override
            public String getType() {
                return "B";
            }
        };
        List<ShipType> shipTypeList = Arrays.asList(shipTypeA, shipTypeB);

        stringShipTypeMap = new HashMap<>();
        setShipTypes(shipTypeList);

        // adding player1 ships
        Ship ship1 = new Ship() {
            @Override
            public Direction getDirection() {
                return Direction.ROW;
            }

            @Override
            public int getPoisitionX() {
                return 1;
            }

            @Override
            public int getPositionY() {
                return 1;
            }

            @Override
            public ShipType getShipType() {
                return shipTypeA;
            }
        };
        Ship ship2 = new Ship() {
            @Override
            public Direction getDirection() {
                return Direction.COLUMN;
            }

            @Override
            public int getPoisitionX() {
                return 3;
            }

            @Override
            public int getPositionY() {
                return 1;
            }

            @Override
            public ShipType getShipType() {
                return shipTypeB;
            }
        };
        List<Ship> player1ShipList = Arrays.asList(ship1, ship2);

        Ship ship3 = new Ship() {
            @Override
            public Direction getDirection() {
                return Direction.COLUMN;
            }

            @Override
            public int getPoisitionX() {
                return 1;
            }

            @Override
            public int getPositionY() {
                return 1;
            }

            @Override
            public ShipType getShipType() {
                return shipTypeA;
            }
        };
        Ship ship4 = new Ship() {
            @Override
            public Direction getDirection() {
                return Direction.ROW;
            }

            @Override
            public int getPoisitionX() {
                return 1;
            }

            @Override
            public int getPositionY() {
                return 3;
            }

            @Override
            public ShipType getShipType() {
                return shipTypeB;
            }
        };
        List<Ship> player2ShipList = Arrays.asList(ship3, ship4);

        player1Ships = addPlayerShips(player1ShipList);
        player2Ships = addPlayerShips(player2ShipList);
    }

    private void setShipTypes(List<ShipType> shipTypeList) throws Exception {
        for (ShipType shipType : shipTypeList) {
            if (stringShipTypeMap.get(shipType.getType()) != null) {
                throw new Exception("Type already declared");
            }
            stringShipTypeMap.put(shipType.getType(), shipType);
        }
    }

    private List<BattleShip> addPlayerShips(List<Ship> playerShipList) {
        List<BattleShip> playerBattleShips = new ArrayList<>();
        // initializing the shipTypeCount for Player
        Map<String, Integer> shipTypeCount = new HashMap<>();
        for (String key : stringShipTypeMap.keySet()) {
            shipTypeCount.put(key, stringShipTypeMap.get(key).getShipAmountOnBoard());
        }

        for (Ship ship : playerShipList) {
            ShipType shipType = ship.getShipType();
            int currentCount = shipTypeCount.get(shipType.getType());
            if (currentCount == 0) {
                // throw exception initialization failed due to trying to add ship above the allowed amount.
                return null;
            }
            shipTypeCount.remove(shipType.getType());
            shipTypeCount.put(shipType.getType(), currentCount - 1);
            BattleShip battleShip = new BattleShipImpl(
                    shipType.getShipSize(),
                    ship.getDirection(),
                    convertPositionToArrayIndex(ship.getPoisitionX()),
                    convertPositionToArrayIndex(ship.getPositionY())
            );
            playerBattleShips.add(battleShip);
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

    private int convertPositionToArrayIndex(int position) {
        return position - 1;
    }
}
