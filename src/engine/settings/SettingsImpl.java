package engine.settings;

/**
 * Created by Ofer.Spivak on 12/04/2017.
 */
public class SettingsImpl implements Settings{
    private String player1Name, player2Name;
    private int boardSize;

    public SettingsImpl(String player1Name, String player2Name, int boardSize) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.boardSize = boardSize;
    }

    @Override
    public String getPlayer1Name() {
        return player1Name;
    }

    @Override
    public String getPlayer2Name() {
        return player2Name;
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }
}
