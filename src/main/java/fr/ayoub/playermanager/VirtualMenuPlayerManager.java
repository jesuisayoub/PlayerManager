package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class VirtualMenuPlayerManager extends VirtualMenu {

    private final OfflinePlayer targetPlayer;
    private PlayerObject targetPlayerObject;

    public VirtualMenuPlayerManager(Player player, OfflinePlayer targetPlayer, int size) {
        super(player, targetPlayer.getName(), 3);
        this.targetPlayer = targetPlayer;
        PlayerObject playerObject = Manager.getPlayer(targetPlayer.getPlayer());
        if (playerObject == null) {
            this.targetPlayerObject = new PlayerObject(targetPlayer.getPlayer());
        } else {
            this.targetPlayerObject = playerObject;
        }
        update();
    }

    public void update() {
        inventory.clear();

        inventory.setItem(0, ButtonManager.add(targetPlayer.getName() + " " + (targetPlayer.isOnline() ? "§aOnline" : "§cOffline"), "§7This menu allows you some interactions with this player", Material.PLAYER_HEAD));
        inventory.setItem(11, ButtonManager.add("Manage Fly", !targetPlayer.isOnline() ? "§cPlayer is offline" :  "§7Fly: " + (player.getAllowFlight() ? "§aON" : "§cOFF"), Material.ELYTRA));
        inventory.setItem(12, ButtonManager.add("Manage No fall", !targetPlayer.isOnline() ? "§cPlayer is offline" :  "§7Fall: " + (targetPlayerObject.isNofall() ? "§cOFF": "§aON"), Material.LEATHER_BOOTS));
        inventory.setItem(13, ButtonManager.add("Look the Enderchest", !targetPlayer.isOnline() ? "§cPlayer is offline" :  "§7Look at the items in the enderchest", Material.ENDER_CHEST));
        inventory.setItem(14, ButtonManager.add("Manage life bar", !targetPlayer.isOnline() ? "§cPlayer is offline" : "§7Increase the life bar to the maximum", Material.DRAGON_BREATH));
        inventory.setItem(15, ButtonManager.add("Manage foodbar", !targetPlayer.isOnline() ? "§cPlayer is offline" :  "§7Increase the foodbar bar to the maximum", Material.APPLE));
        inventory.setItem(18, ButtonManager.add("Open stats", "§7Get somes statistics of player " + targetPlayer.getName(), Material.BOOKSHELF));
        inventory.setItem(8, ButtonManager.add("Teleport to him/her", !targetPlayer.isOnline() ? "§cPlayer is offline" : "§7Be teleported directly to this player", Material.ENDER_PEARL));
    }

    public void click(InventoryClickEvent event) {
        if (event.getInventory() == inventory) {
            event.setCancelled(true);
        }
        switch (event.getSlot()) {
            case 11:
                if (!targetPlayer.isOnline()) return;
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                } else {
                    player.setAllowFlight(true);
                }
                break;
            case 12:
                if (!targetPlayer.isOnline()) return;
                if (targetPlayerObject.isNofall()) {
                    targetPlayerObject.setNofall(false);
                } else {
                    targetPlayerObject.setNofall(true);
                }
                break;
            case 13:
                if (!targetPlayer.isOnline()) return;
                player.openInventory(targetPlayer.getPlayer().getEnderChest());
                break;
            case 14:
                if (!targetPlayer.isOnline()) return;
                targetPlayer.getPlayer().setHealth(20);
                break;
            case 15:
                if (!targetPlayer.isOnline()) return;
                targetPlayer.getPlayer().setFoodLevel(20);
                break;
            case 18:
                new VirtualMenuPlayerStats(player, targetPlayer);
                break;
            case 8:
                if (!targetPlayer.isOnline()) return;
                player.teleport(targetPlayer.getPlayer());
                break;
        }
        update();
    }

}
