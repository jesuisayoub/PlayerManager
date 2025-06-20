package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if (strings.length != 1) {
            new VirtualMenuPlayerStats(player, player);
            return false;
        }

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!targetPlayer.hasPlayedBefore()) {
            player.sendMessage("Not online.");
            return false;
        }
        new VirtualMenuPlayerStats(player, player);
        return false;
    }
}
