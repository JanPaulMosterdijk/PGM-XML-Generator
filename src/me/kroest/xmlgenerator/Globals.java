package me.kroest.xmlgenerator;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;

public class Globals {
    public Vector pos1;
    public Vector pos2;
    public Map map;
    public Document doc;
    public RootXML rootXML;
    public String rootFolder;
    public Inventory selectedInv;
    public Server server;

    public Globals(Document doc, String rootFolder, Server server){
        this.doc = doc;
        this.server = server;
        this.rootFolder = rootFolder;
        map = new Map("", null, doc, this);
        rootXML = new RootXML(doc, this);
    }

    public void resetMap(Player player){
        map = new Map("", player, doc, this);
    }
}
