package fr.ayoub.playermanager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PmCMD implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return false;

        Player player = (Player) commandSender;

        if (!player.hasPermission("playermanager.open") && !player.isOp()) {
            player.sendMessage("§cYou don't have permission.");
            return false;
        }

        if (strings.length != 1) {
            new VirtualMenuPlayerManager(player, player, 3);
            return false;
        }

        OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
        if (!targetPlayer.hasPlayedBefore()) {
            player.sendMessage("Not online.");
            return false;
        }

        new VirtualMenuPlayerManager(player, targetPlayer, 3);
        return false;
    }
}
