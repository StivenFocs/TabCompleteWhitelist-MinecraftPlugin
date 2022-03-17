package cloud.stivenfocs.TabCompleteWhitelist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Vars {

    public static Loader plugin;
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    static Vars vars;
    public static Vars getVars() {
        if (vars == null) {
            vars = new Vars();
        }

        return vars;
    }

    //////////////////////////

    public static Boolean blacklist = false;
    public static List<String> commandsList = new ArrayList<>();

    public static String configuration_reloaded = "";
    public static String an_error_occurred = "";
    public static String unknown_subcommand = "";
    public static String no_permissions = "";
    public static String shutdown = "";
    public static List<String> help_admin = new ArrayList<>();
    public static List<String> help_user = new ArrayList<>();

    //////////////////////////

    public boolean reloadVars() {
        try {
            plugin.reloadConfig();

            getConfig().options().copyDefaults(true);
            getConfig().options().header("Developed with LOV by StivenFocs");

            getConfig().addDefault("options.blacklist", false);
            List<String> new_commands_list = new ArrayList<>();
            new_commands_list.add("spawn");
            new_commands_list.add("stuck");
            new_commands_list.add("tpa");
            new_commands_list.add("tpahere");
            new_commands_list.add("tpaccept");
            new_commands_list.add("tpdeny");
            new_commands_list.add("message");
            new_commands_list.add("msg");
            new_commands_list.add("reply");
            new_commands_list.add("r");
            getConfig().addDefault("options.commands_list", new_commands_list);

            getConfig().addDefault("messages.configuration_reloaded", "&aConfiguration successfully reloaded");
            getConfig().addDefault("messages.an_error_occurred", "&cAn error occurred while doing this task..");
            getConfig().addDefault("messages.unknown_subcommand", "&cUnknown subcommand, use &4/tabcompletewhitelist &cto see a commands list");
            getConfig().addDefault("messages.no_permissions", "&cYou you're not permitted to do this.");
            getConfig().addDefault("messages.shutdown", "&cPlugin shutdown..");
            List<String> new_help_admin = new ArrayList<>();
            new_help_admin.add("&8&m=======================");
            new_help_admin.add("&7* &6TabComplete&eWhitelist &8%version%");
            new_help_admin.add("");
            new_help_admin.add("&7* /tabcompletewhitelist reload &8&m| &7Reload the configuration");
            new_help_admin.add("&7* /tabcompletewhitelist shutdown &8&m| &7Shutdown the plugin &mUNTIL RESTART");
            new_help_admin.add("");
            new_help_admin.add("&8&m=======================");
            getConfig().addDefault("messages.help_admin", new_help_admin);
            List<String> new_help_user = new ArrayList<>();
            new_help_user.add("&8&m=======================");
            new_help_user.add("");
            new_help_user.add("&7* This server runs &6TabComplete&eWhitelist");
            new_help_user.add("&7* (they can edit this message to may \"hide it\")");
            new_help_user.add("");
            new_help_user.add("&8&m=======================");
            getConfig().addDefault("messages.help_user", new_help_user);

            plugin.saveConfig();
            plugin.reloadConfig();

            blacklist = getConfig().getBoolean("options.blacklist", false);
            commandsList = getConfig().getStringList("options.commands_list");

            configuration_reloaded = getConfig().getString("messages.configuration_reloaded", "&aConfiguration successfully reloaded");
            an_error_occurred = getConfig().getString("messages.an_error_occurred", "&cAn error occurred while doing this task..");
            unknown_subcommand = getConfig().getString("messages.unknown_subcommand", "&cUnknown subcommand, use &4/tabcompletewhitelist &cto see a commands list");
            no_permissions = getConfig().getString("messages.no_permissions", "&cYou you're not permitted to do this.");
            shutdown = getConfig().getString("messages.shutdown", "&cPlugin shutdown..");
            help_admin = getConfig().getStringList("messages.help_admin");
            help_user = getConfig().getStringList("messages.help_user");

            plugin.getLogger().info("Configuration reloaded");
            return true;
        } catch (Exception ex) {
            plugin.getLogger().severe("An exception occurred while trying to reload the whole configuration");
            ex.printStackTrace();
            Bukkit.getPluginManager().disablePlugin(plugin);
            return false;
        }
    }

    //////////////////////////

    public static boolean hasExceptPermissions(CommandSender sender) {
        return sender.hasPermission("tabcompletewhitelist.except");
    }

    public static boolean hasAdminPermissions(CommandSender sender) {
        return sender.hasPermission("tabcompletewhitelist.admin");
    }

    //////////////////////////

    public static void sendString(String text, CommandSender sender) {
        if (plugin.getConfig().getString(text) != null) {
            text = plugin.getConfig().getString(text);
        }

        if (text.length() > 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', text.replaceAll("%version%", plugin.getDescription().getVersion()).replaceAll("%name%", sender.getName())));
        }
    }

    public static List<String> coloredList(List<String> uncolored_list) {
        List<String> colored_list = new ArrayList<>();
        for(String line : uncolored_list) {
            colored_list.add(ChatColor.translateAlternateColorCodes('&', line.replaceAll("%version%", plugin.getDescription().getVersion())));
        }
        return colored_list;
    }
}
