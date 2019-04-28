package me.kroest.xmlgenerator.MapObjects;

import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Spawn extends MapObject{
    public Vector position;
    public Team team;

    public Spawn(Vector position, Document doc){
        super(doc);
        this.position = position;
    }

    @Override
    public Element getElement() {
        Element spawnElement = doc.createElement("spawn");
        spawnElement.setAttribute("kit", team.name);
        spawnElement.setAttribute("team", team.name);
        // TODO: 24-Apr-19 get yaw from player
        spawnElement.setAttribute("yaw", "0");

        // TODO: 24-Apr-19 support different region types
        Element spawnRegion = doc.createElement("cylinder");
        spawnRegion.setAttribute("base", position.getX() + ", " + position.getY() + ", " + position.getZ());
        spawnRegion.setAttribute("radius", "2");
        spawnRegion.setAttribute("height", "0");

        spawnElement.appendChild(spawnRegion);
        return spawnElement;
    }
}
