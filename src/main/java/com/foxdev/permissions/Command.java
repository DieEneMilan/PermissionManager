package com.foxdev.permissions;

import com.foxdev.permissions.Gui.PermissionGUI;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {
    private Permissions plugin;

    public Command(Permissions plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Dit commando kan alleen door een speler worden uitgevoerd!");
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(ChatColor.RED + "Incorrect gebruik! /permissions player>");
            return true;
        }

        Player target = plugin.getServer().getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage(ChatColor.RED + "Speler niet gevonden!");
            return true;
        }

        Player player = (Player) sender;
        player.openInventory(PermissionGUI.createPermissionGUI(target.getName(), plugin.getPermissionManager()));
        return true;
    }
}
