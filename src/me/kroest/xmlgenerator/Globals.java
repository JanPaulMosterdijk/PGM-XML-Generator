package me.kroest.xmlgenerator;

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

    public Globals(Document doc, String rootFolder){
        this.doc = doc;
        this.rootFolder = rootFolder;
        map = new Map("", null, doc);
        rootXML = new RootXML(doc, this);
    }
}
