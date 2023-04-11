package com.foxdev.permissions.Listener;

import com.foxdev.permissions.Gui.PermissionGUI;
import com.foxdev.permissions.PermissionManager;
import com.foxdev.permissions.Permissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class PermissionGuiClickListener implements Listener {
    private Permissions plugin;

    public PermissionGuiClickListener(Permissions plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getClickedInventory();
        if (inventory == null || !event.getView().getTitle().startsWith(ChatColor.DARK_GREEN + "Permissies van ")) {

            return;
        }

        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        PermissionManager permissionManager = plugin.getPermissionManager();

        String targetName = ChatColor.stripColor(event.getView().getTitle()).substring("Permissies van ".length());
        Player target = plugin.getServer().getPlayer(targetName);

        if (target == null) {
            player.sendMessage(ChatColor.RED + "Speler niet gevonden!");
            player.closeInventory();
            return;
        }

        String permission = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());

        if (permissionManager.hasPermission(target, permission)) {
            permissionManager.removePermission(target, permission);
            player.sendMessage(ChatColor.RED + "Permissie '" + permission + "' verwijderd van speler " + target.getName());
        } else {
            permissionManager.addPermission(target, permission);
            player.sendMessage(ChatColor.GREEN + "Permissie '" + permission + "' toegevoegd aan speler " + target.getName());
        }

// Update de GUI
        player.openInventory(PermissionGUI.createPermissionGUI(targetName, permissionManager));
    }
}