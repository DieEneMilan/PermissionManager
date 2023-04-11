package com.foxdev.permissions;

import com.foxdev.permissions.Listener.PermissionGuiClickListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Permissions extends JavaPlugin {
    private PermissionManager permissionManager;

    @Override
    public void onEnable() {
        permissionManager = new PermissionManager();
        getCommand("permission").setExecutor(new Command(this));
        getServer().getPluginManager().registerEvents(new PermissionGuiClickListener(this), this);

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public PermissionManager getPermissionManager(){
        return permissionManager;
    }
}
