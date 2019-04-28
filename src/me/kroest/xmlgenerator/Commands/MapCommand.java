package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import me.kroest.xmlgenerator.Map;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MapCommand implements CommandExecutor{

    Globals globals;

    public MapCommand(Globals globals){
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(globals.map.author == null){
                globals.map.author = player;
            }

            if(args.length > 0) {
                if(args[0].equals("reset")){
                    globals.resetMap(player);
                    player.sendMessage("Resetted map succesfully.");
                    return true;
                }

                if (args[0].equals("generate")) {
                    if(globals.map.name.length() < 1){
                        player.sendMessage("Please specify a map name.");
                        return false;
                    }

                    if (globals.rootXML.generateXML()) {
                        player.sendMessage("successfully generated xml of map: " + globals.map.name + ".");
                        return true;
                    } else {
                        player.sendMessage("Error generating xml of map: " + globals.map.name + ".");
                        return true;
                    }
                }
                else if (args[0].equals("objective")) {
                    String objectiveText = "";

                    for(int i = 1; i < args.length; i++){
                        objectiveText += args[i];
                    }
                    globals.map.objective = objectiveText;
                    return true;

                }
                else {
                    String mapText = "";

                    for(int i = 0; i < args.length; i++){
                        mapText += args[i];
                        if(i < args.length-1){mapText += " ";}
                    }

                    if (globals.map == null) {
                        globals.map = new Map(mapText, player, globals.doc, globals);
                    }

                    globals.map.name = mapText;
                    player.sendMessage("map: '" + globals.map.name + "'");
                    return true;
                }
            }
            else {
                player.sendMessage("map: " + globals.map);
                return true;
            }
        }
        return false;
    }
}
