package me.gabytm.buttonpress;

import me.gabytm.buttonpress.commands.PressCommand;
import me.gabytm.buttonpress.commands.ReloadCommand;
import me.gabytm.buttonpress.events.PlayerInteract;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ButtonPress extends JavaPlugin {

    private Map<UUID, Location> lastPressedButton;
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        lastPressedButton = new HashMap<>();

        config.options().copyDefaults(true);
        saveConfig();

        this.getCommand("buttonpress").setExecutor(new ReloadCommand(this));
        this.getCommand("press").setExecutor(new PressCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<UUID, Location> getLastPressedButton() {
        return lastPressedButton;
    }
}
