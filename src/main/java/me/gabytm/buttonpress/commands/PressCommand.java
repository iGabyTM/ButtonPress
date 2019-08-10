package me.gabytm.buttonpress.commands;

import me.gabytm.buttonpress.ButtonPress;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Button;

import static me.gabytm.buttonpress.Utils.*;

public class PressCommand implements CommandExecutor {

    private ButtonPress plugin;

    public PressCommand(ButtonPress plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage(formatText(plugin.getConfig().getString("messages.press.consoleUsage")));
            return;
        }
        
        Player player = ((Player) sender);

        if (!player.hasPermission("buttonpress.press")) {
            player.sendMessage(formatText(plugin.getConfig().getString("messages.press.noPermission")));
        } else if (!plugin.getLastPressedButton().containsKey(player.getUniqueId())) {
            player.sendMessage(formatText(plugin.getConfig().getString("messages.press.noButtonPressed")));
        } else {
            World world = player.getWorld();

            if (world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType().equals(Material.STONE_BUTTON) || world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType().equals(Material.WOOD_BUTTON)) {
                BlockState buttonState = world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getState();
                Button button = (Button) buttonState.getData();

                button.setPowered(true);
                buttonState.setData(button);
                buttonState.update();

                plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                    button.setPowered(false);
                    buttonState.setData(button);
                    buttonState.update();
                }, delay(world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType()));

                player.sendMessage(formatText(plugin.getConfig().getString("messages.press.buttonPressed")));
            } else {
                player.sendMessage(formatText(plugin.getConfig().getString("messages.press.buttonNotFound")));
            }
        }
        return true;
    }
}
