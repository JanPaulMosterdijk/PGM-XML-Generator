package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

public class KitCommand implements CommandExecutor {
    Globals globals;

    public KitCommand(Globals globals){
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length < 1){
                player.sendMessage("specify a team");
                return false;
            }
            else {
                PlayerInventory i = player.getInventory();
                boolean succes =  globals.map.setKit(i, globals.map.getTeamByName(args[0]));
                if(succes){
                    player.sendMessage("Succesfully set kit of team: " + args[0] + " to your inventory.");
                    return true;
                }
                else {
                    player.sendMessage("Team with name: " + args[0] + " not found.");
                    return false;
                }
            }
        }
        return false;
    }
}
