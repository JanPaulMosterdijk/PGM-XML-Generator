package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import me.kroest.xmlgenerator.MapObjects.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeamCommand implements CommandExecutor{
    Globals globals;

    public TeamCommand(Globals globals){
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length < 2){
                player.sendMessage("Usage: /team add/remove <name>");
                return false;
            }

            else {
                if(args[0].equals("add")){
                    Team t = new Team(globals.doc);
                    t.name = args[1];
                    globals.map.addTeam(t);
                    player.sendMessage("Added team : '" + t.name + "'");
                    return true;
                }
                else if(args[0].equals("remove")){
                    boolean success = globals.map.removeTeam(args[1]);
                    if(success){
                        player.sendMessage("Removed team '" + args[1] + "'");
                        return true;
                    }
                    else {
                        player.sendMessage("Team '" + args[1] + "' does not exist");
                        return true;
                    }
                }
                else{
                    player.sendMessage("Usage: /team add/remove <name>");
                    return false;
                }
            }
        }
        return false;
    }
}
