package me.kroest.xmlgenerator;

import me.kroest.xmlgenerator.Commands.*;
import me.kroest.xmlgenerator.listeners.BlockBreakListener;
import me.kroest.xmlgenerator.listeners.RightClickListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;

public class Main extends JavaPlugin {
    public Globals globals;

    @Override
    public void onEnable() {
        File f = new File(getDataFolder() + "/");
        if(!f.exists()) {
            f.mkdir();
        }
        Document doc = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }

        globals = new Globals(doc, getDataFolder().toString());

        this.getCommand("map").setExecutor(new MapCommand(globals));
        this.getCommand("region").setExecutor(new RegionCommand(globals));
        this.getCommand("objective").setExecutor(new ObjectiveCommand(globals));
        this.getCommand("team").setExecutor(new TeamCommand(globals));
        this.getCommand("spawn").setExecutor(new SpawnCommand(globals));
        this.getCommand("kit").setExecutor(new KitCommand(globals));

        getServer().getPluginManager().registerEvents(new RightClickListener(globals), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable(){

    }
}
