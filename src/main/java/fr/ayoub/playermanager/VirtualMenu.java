package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public abstract class VirtualMenu {

    Inventory inventory;
    Player player;

    public VirtualMenu(Player player, String name, int size) {
        Inventory inventory = Bukkit.createInventory(null, 9*size, name);
        this.inventory = inventory;
        this.player = player;
        player.openInventory(inventory);
        PlayerManager.virtualMenu.put(player, this);
    }

    public abstract void update();

    public abstract void click(InventoryClickEvent event);

}
