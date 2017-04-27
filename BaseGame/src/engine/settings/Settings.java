package engine.settings;

import engine.battleShip.BattleShip;

import java.util.List;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public interface Settings {

    String getPlayer1Name();

    String getPlayer2Name();

    void setPlayer1Name(String player1Name);

    void setPlayer2Name(String player2Name);

    int getBoardSize();

    void setBoardSize(int boardSize);

    List<BattleShip> getPlayer1Ships();

    List<BattleShip> getPlayer2Ships();

    int getTotalShipsTilesAmount();

    int getTotalShipsScore();

}
