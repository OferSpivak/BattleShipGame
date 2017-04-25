package dal;

import dal.autoGenerated.BattleShipGame;
import engine.enums.Direction;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OFer.Spivak on 25/04/2017.
 */
public class DalImpl {
    private BattleShipGame battleShipGame = null;
    private List<ShipType> shipTypeList = null;

    public DalImpl(String path) {
        try {
            File file = new File(path);
            JAXBContext jaxbContext = JAXBContext.newInstance(BattleShipGame.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            battleShipGame = (BattleShipGame) unmarshaller.unmarshal(file);
        } catch (Exception e) {
            System.out.println("Failed"); // todo: add initialization exceptions!
            System.out.println(e.getMessage());
        }
    }

    private ShipType getShipTypeFromList(String shipTypeName){
        if (shipTypeList == null) {
            shipTypeList = getShipTypeList();
        }
        for (ShipType item: shipTypeList){
            if (item.getShipTypeName().equals(shipTypeName)){
                return item;
            }
        }
        return null;
    }

    public List<ShipType> getShipTypeList() {
        List<ShipType> shipTypeList = new ArrayList<>();
        for (BattleShipGame.ShipTypes.ShipType xmlShipType : battleShipGame.getShipTypes().getShipType()) {
            ShipType shipType = new ShipType() {
                @Override
                public int getShipSize() {
                    return xmlShipType.getLength();
                }

                @Override
                public int getShipAmountOnBoard() {
                    return xmlShipType.getAmount();
                }

                @Override
                public shipTypeStyle getType() {
                    return shipTypeStyle.valueOf(xmlShipType.getType());
                }

                @Override
                public String getShipTypeName() {
                    return xmlShipType.getName();
                }
            };
            shipTypeList.add(shipType);
        }
        return shipTypeList;
    }

    public List<Ship> getBoard(int boardIndex) {
        List<Ship> board = new ArrayList<>();
        BattleShipGame.Boards.Board xmlBoard = battleShipGame.getBoards().getBoard().get(boardIndex);
        for (BattleShipGame.Boards.Board.Ship xmlShip : xmlBoard.getShip()) {
            Ship ship = new Ship() {
                @Override
                public Direction getDirection() {
                    return Direction.valueOf(xmlShip.getDirection());
                }

                @Override
                public int getPositionX() {
                    return xmlShip.getPosition().getX();
                }

                @Override
                public int getPositionY() {
                    return xmlShip.getPosition().getY();
                }

                @Override
                public ShipType getShipType() {
                    return getShipTypeFromList(xmlShip.getType());
                }

                @Override
                public String toString() {
                    return "Position: " + getPositionX() + ", " + getPositionY() + ". Direction: " + getDirection() + ". Size: " + getShipType().getShipSize();
                }
            };
            board.add(ship);
        }
        return board;
    }


}
