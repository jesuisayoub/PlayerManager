package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class VirtualMenu {

    private final Player player;
    private final OfflinePlayer targetPlayer;
    private Inventory inventory;
    private PlayerObject targetPlayerObject;
    public VirtualMenu(Player player, OfflinePlayer targetPlayer) {
        this.player = player;
        this.targetPlayer = targetPlayer;
        this.targetPlayerObject = new PlayerObject(targetPlayer.getPlayer());
        open();
        PlayerManager.virtualMenu.put(player, this);
    }

    public void open() {
        Inventory inventory = Bukkit.createInventory(null, 9*3, targetPlayer.getName());
        this.inventory = inventory;
        player.openInventory(inventory);
        update();
    }

    public void update() {
        inventory.clear();

        inventory.setItem(0, ButtonManager.add(targetPlayer.getName() + " " + (targetPlayer.isOnline() ? "§aOnline" : "§cOffline"), "§7This menu allows you some interactions with this player", Material.PLAYER_HEAD));
        inventory.setItem(11, ButtonManager.add("Manage Fly", "§7Fly: " + (player.getAllowFlight() ? "§aON" : "§cOFF"), Material.ELYTRA));
        inventory.setItem(12, ButtonManager.add("Manage No fall", "§7Fall: " + (targetPlayerObject.isNofall() ? "§cOFF": "§aON"), Material.LEATHER_BOOTS));
        inventory.setItem(13, ButtonManager.add("Look the Enderchest", "§7Look at the items in the enderchest", Material.ENDER_CHEST));
        inventory.setItem(14, ButtonManager.add("Manage life bar", "§7Increase the life bar to the maximum", Material.DRAGON_BREATH));
        inventory.setItem(15, ButtonManager.add("Manage food bar", "§7Increase the foodbar bar to the maximum", Material.APPLE));
        inventory.setItem(8, ButtonManager.add("Teleport to him/her", "§7Be teleported directly to this player", Material.ENDER_PEARL));
    }

    public void click(InventoryClickEvent event) {
        if (event.getInventory() == inventory) {
            event.setCancelled(true);
        }
        if (!targetPlayer.isOnline()) return;
        switch (event.getSlot()) {
            case 11:
                if (player.getAllowFlight()) {
                    player.setAllowFlight(false);
                } else {
                    player.setAllowFlight(true);
                }
                break;
            case 12:
                if (targetPlayerObject.isNofall()) {
                    targetPlayerObject.setNofall(false);
                } else {
                    targetPlayerObject.setNofall(true);
                }
                break;
            case 13:
                player.openInventory(targetPlayer.getPlayer().getEnderChest());
                break;
            case 14:
                targetPlayer.getPlayer().setHealth(20);
                break;
            case 15:
                targetPlayer.getPlayer().setFoodLevel(20);
                break;
            case 8:
                player.teleport(targetPlayer.getPlayer());
                break;
        }
        update();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Player getPlayer() {
        return player;
    }
}
