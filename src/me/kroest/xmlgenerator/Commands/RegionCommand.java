package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import me.kroest.xmlgenerator.MapObjects.Team;
import me.kroest.xmlgenerator.models.FilterType;
import me.kroest.xmlgenerator.MapObjects.regions.Cuboid;
import me.kroest.xmlgenerator.MapObjects.regions.Cylinder;
import me.kroest.xmlgenerator.MapObjects.regions.Rectangle;
import me.kroest.xmlgenerator.MapObjects.regions.Region;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RegionCommand implements CommandExecutor {

    Globals globals;

    public RegionCommand(Globals globals) {
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                //load all defined regions

                return true;
            }

            else if(args.length == 1){
                player.sendMessage("please specify a region shape and filter(s), /region <shape> void/block/access");
            }

            else {
                String regionShape = args[0];
                Region r = null;
                int filterIt = 1;
                switch (regionShape) {
                    case "cuboid":
                        r = new Cuboid(globals.pos1, globals.pos2, globals.doc);
                        break;
                    case "rectangle":
                        r = new Rectangle(globals.pos1, globals.pos2, globals.doc);
                        break;
                    case "cylinder":
                        //args needs to be at least 4, possibly checking if fields are correct
                        if (args.length >= 4) {
                            r = new Cylinder(globals.pos1, Integer.valueOf(args[2]), Integer.valueOf(args[3]), globals.doc);
                            filterIt = 4;
                        } else {
                            player.sendMessage("Usage: /region cylinder <name> radius height.");
                            return false;
                        }
                        break;
                    default:
                        player.sendMessage("unknown region type");
                        return false;
                }
                if (r != null) {
                    for (int i = filterIt; i < args.length-1; i++){
                        ArrayList<FilterType> filters = new ArrayList<>();
                        String filtersAsString = "";
                        for (FilterType f: FilterType.values()) {
                            if (args[i].equalsIgnoreCase(f.name())){
                                filters.add(f);
                                filtersAsString += f.name();
                            }
                        }

                        //get last element, must be team name
                        Team t = globals.map.getTeamByName(args[args.length-1]);
                        if(t == null){
                            player.sendMessage("Team '" + args[args.length-1] + "' not found.");
                            return false;
                        }

                        globals.map.addRegion(r, filters.toArray(new FilterType[0]), t);
                        player.sendMessage("Added region with filters: '" + filtersAsString + "', for team: '" + t.name + "'.");
                        return true;
                    }
                }
                return true;
            }
        }
        return true;
    }
}
