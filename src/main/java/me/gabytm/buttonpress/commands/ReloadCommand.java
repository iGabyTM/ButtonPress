package me.gabytm.buttonpress.commands;

import me.gabytm.buttonpress.ButtonPress;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static me.gabytm.buttonpress.Utils.*;

public class ReloadCommand implements CommandExecutor {

    private ButtonPress plugin;

    public ReloadCommand(ButtonPress plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("buttonpress.reload")) {
            sender.sendMessage(formatText(plugin.getConfig().getString("messages.reload.noPermission")));
        } else {
            try {
                this.plugin.reloadConfig();
                this.plugin.saveConfig();
                sender.sendMessage(formatText("&aThe plugin has been successfully reloaded!"));
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage(formatText("&cAn error occurred while reloading the plugin, check the console for more info."));
            }
        }
        return true;
    }
}
