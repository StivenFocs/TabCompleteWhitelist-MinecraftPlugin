package cloud.stivenfocs.TabCompleteWhitelist;

import cloud.stivenfocs.TabCompleteWhitelist.Commands.tabcompletewhitelist;
import cloud.stivenfocs.TabCompleteWhitelist.Events.TabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Loader extends JavaPlugin {

    public void onEnable() {
        Vars.plugin = this;
        Vars vars = Vars.getVars();
        vars.reloadVars();

        getCommand("tabcompletewhitelist").setExecutor(new tabcompletewhitelist(this));
        Bukkit.getPluginManager().registerEvents(new TabCompletion(this), this);
    }

}
