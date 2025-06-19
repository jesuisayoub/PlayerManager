package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class PlayerManager extends JavaPlugin {

    public static HashMap<Player, VirtualMenu> virtualMenu = new HashMap<>();
    public static HashMap<Player, PlayerObject> playerObject = new HashMap<>();

    @Override
    public void onEnable() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryClick(), this);
        pluginManager.registerEvents(new Nofall(), this);
        getCommand("playermanager").setExecutor(new Command());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
