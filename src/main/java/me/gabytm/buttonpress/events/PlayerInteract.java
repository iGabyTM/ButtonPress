package me.gabytm.buttonpress.events;

import me.gabytm.buttonpress.ButtonPress;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    private ButtonPress plugin;

    public PlayerInteract(ButtonPress plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && (event.getClickedBlock().getType().equals(Material.WOOD_BUTTON) || event.getClickedBlock().getType().equals(Material.STONE_BUTTON))) {
            plugin.getLastPressedButton().put(player.getUniqueId(), event.getClickedBlock().getLocation());
        }
    }
}
