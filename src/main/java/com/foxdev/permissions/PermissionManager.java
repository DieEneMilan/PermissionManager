package com.foxdev.permissions;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermissionManager {
    private final Map<String, Set<String>> playerPermissions;

    public PermissionManager() {
        this.playerPermissions = new HashMap<>();
    }

    public void addPermission(Player player, String permission) {
        String uuid = player.getUniqueId().toString();
        playerPermissions.putIfAbsent(uuid, new HashSet<>());
        playerPermissions.get(uuid).add(permission.toLowerCase());
    }

    public void removePermission(Player player, String permission) {
        String uuid = player.getUniqueId().toString();
        Set<String> permissions = playerPermissions.get(uuid);
        if (permissions != null) {
            permissions.remove(permission.toLowerCase());
        }
    }

    public boolean hasPermission(Player player, String permission) {
        Set<String> permissions = playerPermissions.get(player.getUniqueId().toString());
        return permissions != null && permissions.contains(permission.toLowerCase());
    }
}
