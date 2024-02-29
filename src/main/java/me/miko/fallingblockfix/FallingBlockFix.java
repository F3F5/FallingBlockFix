package me.miko.fallingblockfix;

import org.bukkit.plugin.java.JavaPlugin;

public final class FallingBlockFix extends JavaPlugin {

    public void onEnable() {
        this.saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new FallingListener(this), this);
    }
}