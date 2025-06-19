package fr.ayoub.playermanager;

import org.bukkit.entity.Player;

import java.util.Comparator;

public class Manager {

    public static PlayerObject getPlayer(Player player) {
        for (PlayerObject playerObject : PlayerManager.playerObject.values()) {
            if (playerObject.getPlayer() == player) {
                return playerObject;
            }
        }
        return null;
    }

}
