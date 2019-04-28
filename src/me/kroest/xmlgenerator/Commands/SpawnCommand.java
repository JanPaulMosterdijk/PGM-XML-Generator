package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import me.kroest.xmlgenerator.MapObjects.Team;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    Globals globals;

    public SpawnCommand(Globals globals){
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length < 1){
                player.sendMessage("please specify a team");
                return false;
            }

            //get last element, must be team name
            Team t = globals.map.getTeamByName(args[0]);
            if(t == null){
                player.sendMessage("Team '" + args[0] + "' not found.");
                return false;
            }
            else {
                boolean succes = globals.map.addSpawn(player.getLocation().toVector(), t);
                if(succes){
                    player.sendMessage("added spawn " + player.getLocation().toVector().toString() + " for team: " + t.name);
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }
}
