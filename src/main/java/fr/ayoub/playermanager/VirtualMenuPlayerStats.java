package fr.ayoub.playermanager;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class VirtualMenuPlayerStats extends VirtualMenu {

    private int page;
    private OfflinePlayer targetPlayer;

    public VirtualMenuPlayerStats(Player player, OfflinePlayer offlinePlayer) {
        super(player, offlinePlayer.getName(), 4);
        this.targetPlayer = offlinePlayer;
        update();
    }

    public enum PlayerStats {

        WALK_ONE_CM(Statistic.WALK_ONE_CM, "Blocks walked", Material.LEATHER_BOOTS, 11),
        TOTAL_WORLD_TIME(Statistic.TOTAL_WORLD_TIME, "Gametime", Material.CLOCK, 12),
        MOB_KILLS(Statistic.MOB_KILLS, "Mob kills", Material.IRON_SWORD, 13),
        DEATHS(Statistic.DEATHS, "Deaths", Material.WITHER_SKELETON_SKULL, 14),
        FISH_CAUGHT(Statistic.FISH_CAUGHT, "Fish caught", Material.TROPICAL_FISH, 15),
        JUMP(Statistic.JUMP, "Jumps", Material.SLIME_BALL, 20),
        LEAVE_GAME(Statistic.LEAVE_GAME, "Connections", Material.REDSTONE, 21),
        TRADED_WITH_VILLAGER(Statistic.TRADED_WITH_VILLAGER, "Trades with villagers", Material.VILLAGER_SPAWN_EGG, 22),
        CHEST_OPENED(Statistic.CHEST_OPENED, "Chests opened", Material.CHEST, 23),
        BREAK_BLOCK(Statistic.MINE_BLOCK, "Blocks broken", Material.DIAMOND_PICKAXE, 24),
        ;

        public Statistic statistic;
        public String name;
        public Material icon;
        public int slot;

        PlayerStats(Statistic statistic, String name, Material icon, int slot) {
            this.statistic = statistic;
            this.name = name;
            this.icon = icon;
            this.slot = slot;
        }
    }

    @Override
    public void update() {
        inventory.clear();

        inventory.setItem(0, ButtonManager.add(targetPlayer.getName() + " " + (targetPlayer.isOnline() ? "§aOnline" : "§cOffline"), (!player.hasPermission("") && !player.isOp() ? null : "§7Open player manager menu"), Material.PLAYER_HEAD));

        for (PlayerStats playerStats : PlayerStats.values()) {

            String string = "";
            switch (playerStats.statistic) {
                case TOTAL_WORLD_TIME:
                    int gametime = targetPlayer.getStatistic(playerStats.statistic) / 20;
                    int days = (gametime / 86400);
                    int hours = (gametime % 86400) / 3600;
                    int minutes = (gametime % 3600) / 60;
                    string = Manager.setFormat(days) + "d" + Manager.setFormat(hours) + "h" + Manager.setFormat(minutes) + "m";
                    break;
                case MINE_BLOCK:
                    string = Manager.setFormat(breakblock());
                    break;
                case WALK_ONE_CM:
                    string = Manager.setFormat(targetPlayer.getStatistic(Statistic.WALK_ONE_CM)/100);
                    break;
            }
            inventory.setItem(playerStats.slot, ButtonManager.add(playerStats.name, "§7Score: §a" + (
                    playerStats.statistic == Statistic.TOTAL_WORLD_TIME ||
                            playerStats.statistic == Statistic.MINE_BLOCK ||
                            playerStats.statistic == Statistic.WALK_ONE_CM
                            ? string : targetPlayer.getStatistic(playerStats.statistic)), playerStats.icon));
        }
    }

    @Override
    public void click(InventoryClickEvent event) {
        if (event.getInventory() == inventory) {
            event.setCancelled(true);
        }
        if (event.getSlot() == 0) {
            if (!player.hasPermission("playermanager.open") && !player.isOp()) return;
            new VirtualMenuPlayerManager(player, targetPlayer, 3);
        }
    }

    private int breakblock() {
        int nb = 0;
        for (Material material : Material.values()) {
            if (!material.isBlock()) continue;
            nb += player.getStatistic(Statistic.MINE_BLOCK, material);
        }
        return nb;
    }
}
