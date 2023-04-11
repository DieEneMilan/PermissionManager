package com.foxdev.permissions.Gui;

import com.foxdev.permissions.PermissionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class PermissionGUI {


    public static Inventory createPermissionGUI(String playerName, PermissionManager permissionManager) {
        Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.DARK_GREEN + "Permissies van " + playerName);

// Voorbeeld: Lijst met permissies
        String[] examplePermissions = {"example.permission", "example.anotherpermission"};

        for (int i = 0; i < examplePermissions.length; i++) {
            boolean hasPermission = permissionManager.hasPermission(Bukkit.getPlayer(playerName), examplePermissions[i]);

            ItemStack permissionItem = createPermissionItem(examplePermissions[i], hasPermission);
            inventory.setItem(i + 9, permissionItem);
        }

        return inventory;
    }

    private static ItemStack createPermissionItem(String permission, boolean hasPermission) {
        Material material = hasPermission ? Material.GREEN_WOOL : Material.RED_WOOL;
        ItemStack permissionItem = new ItemStack(material);
        ItemMeta meta = permissionItem.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.WHITE + permission);
            meta.setLore(Arrays.asList(
                    ChatColor.GRAY + "Klik om de permissie",
                    hasPermission ? ChatColor.RED + "te verwijderen" : ChatColor.GREEN + "toe te voegen"
            ));
            permissionItem.setItemMeta(meta);
        }

        return permissionItem;
    }
}