package me.kroest.xmlgenerator.Commands;

import me.kroest.xmlgenerator.Globals;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ObjectiveCommand implements CommandExecutor {
    Globals globals;

    public ObjectiveCommand(Globals globals){
        this.globals = globals;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;



        }
        return false;
    }
}
