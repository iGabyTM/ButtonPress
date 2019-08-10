package me.gabytm.buttonpress;

import me.gabytm.buttonpress.commands.PressCommand;
import me.gabytm.buttonpress.events.PlayerInteract;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class ButtonPress extends JavaPlugin {

    private Map<UUID, Location> lastPressedButton;

    @Override
    public void onEnable() {
        this.getCommand("press").setExecutor(new PressCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);

        lastPressedButton = new HashMap<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Map<UUID, Location> getLastPressedButton() {
        return lastPressedButton;
    }
}
