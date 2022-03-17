package cloud.stivenfocs.TabCompleteWhitelist.Commands;

import cloud.stivenfocs.TabCompleteWhitelist.Loader;
import cloud.stivenfocs.TabCompleteWhitelist.Vars;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class tabcompletewhitelist implements CommandExecutor {

    Loader plugin;
    public tabcompletewhitelist(Loader plugin) {
        this.plugin = plugin;
    }

    //////////////////////////

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (args.length <= 0) {
            if (Vars.hasAdminPermissions(sender)) {
                sender.sendMessage(Vars.coloredList(Vars.help_admin).toArray(new String[0]));
            } else {
                sender.sendMessage(Vars.coloredList(Vars.help_user).toArray(new String[0]));
            }
        } else {
            if (args[0].equalsIgnoreCase("reload")) {
                if (Vars.hasAdminPermissions(sender)) {
                    if (Vars.getVars().reloadVars()) {
                        Vars.sendString(Vars.configuration_reloaded, sender);
                    } else {
                        Vars.sendString(Vars.an_error_occurred, sender);
                    }
                } else {
                    Vars.sendString(Vars.no_permissions, sender);
                }
                return true;
            }
            if (args[0].equalsIgnoreCase("shutdown")) {
                if (Vars.hasAdminPermissions(sender)) {
                    Vars.sendString(Vars.shutdown, sender);
                    Bukkit.getPluginManager().disablePlugin(plugin);
                } else {
                    Vars.sendString(Vars.no_permissions, sender);
                }
                return true;
            }

            Vars.sendString(Vars.unknown_subcommand, sender);
        }
        return false;
    }
}
