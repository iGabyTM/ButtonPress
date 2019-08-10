package me.gabytm.buttonpress.commands;

import me.gabytm.buttonpress.ButtonPress;
import org.bukkit.Material;
import org.bukkit.World;
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
        Player player = ((Player) sender);

        if (!(sender instanceof Player)) {
            sender.sendMessage(formatText("&cThis command can only be used by a player!"));
        } else if (!player.hasPermission("buttonpress.press")) {
            player.sendMessage(formatText("&cSorry but you can't use this command!"));
        } else if (!plugin.getLastPressedButton().containsKey(player.getUniqueId())) {
            player.sendMessage(formatText("&cCouldn't find a button pressed by you!"));
        } else {
            World world = player.getWorld();

            if (world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType().equals(Material.STONE_BUTTON) || world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType().equals(Material.WOOD_BUTTON)) {
                Button button = new Button(world.getBlockAt(plugin.getLastPressedButton().get(player.getUniqueId())).getType());
                button.setPowered(true);

                player.sendMessage(formatText("&aThe button has been powered!"));
            } else {
                player.sendMessage(formatText("&cCouldn't find a button att that location."));
            }
        }
        return true;
    }
}
