package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if (!player.hasPermission("playermanager.open") && !player.isOp()) {
            player.sendMessage("§cYou don't have permission.");
            return false;
        }

        if (strings.length != 1) {
            VirtualMenu virtualMenu = new VirtualMenu(player, player);
            return false;
        }

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!targetPlayer.hasPlayedBefore()) {
            player.sendMessage("Not online.");
            return false;
        }

        VirtualMenu virtualMenu = new VirtualMenu(player, targetPlayer);
        return false;
    }
}
