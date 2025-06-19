package fr.ayoub.playermanager;

import org.bukkit.entity.Player;

public class PlayerObject {

    private Player player;
    private boolean nofall;
    public PlayerObject(Player player) {
        this.player = player;
        PlayerManager.playerObject.put(player, this);
    }

    public void setNofall(boolean nofall) {
        this.nofall = nofall;
    }

    public boolean isNofall() {
        return nofall;
    }

    public Player getPlayer() {
        return player;
    }
}
