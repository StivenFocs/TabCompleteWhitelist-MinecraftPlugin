package cloud.stivenfocs.TabCompleteWhitelist.Events;

import cloud.stivenfocs.TabCompleteWhitelist.Loader;
import cloud.stivenfocs.TabCompleteWhitelist.Vars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TabCompletion implements Listener {

    Loader plugin;
    public TabCompletion(Loader plugin) {
        this.plugin = plugin;
    }

    //////////////////////////

    @EventHandler
    public void onTabComplete(PlayerCommandSendEvent event) {
        Player p = (Player) event.getPlayer();

        if (!Vars.hasExceptPermissions(p)) {
            if (Vars.blacklist) {
                List<String> commands = new ArrayList<>(event.getCommands());
                for(String command : commands) {
                    if (Vars.commandsList.contains(command.toLowerCase())) {
                        event.getCommands().remove(command);
                    }
                }
                //event.getCommands().removeAll(Vars.commandsList);
            } else {
                event.getCommands().clear();
                for(String command : Vars.commandsList) {
                    event.getCommands().add(command);
                }
            }
        }
    }

}
