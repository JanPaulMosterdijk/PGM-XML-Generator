package me.kroest.xmlgenerator.MapObjects;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Team extends MapObject{
    public String name;
    public int maxPlayers;
    public int maxOverfill;
    public Color color;
    public String id;

    public Team(Document doc){
        super(doc);
        //setting defaults to prevent nullpointers
        maxPlayers = 10;
        maxOverfill = 12;
    };

    @Override
    public Element getElement() {
        Element teamElement = doc.createElement("team");
        teamElement.appendChild(doc.createTextNode(name));
        teamElement.setAttribute("id", name);
        // TODO: 28-Apr-19 add proper team color support
        teamElement.setAttribute("color", name);
        teamElement.setAttribute("max", Integer.toString(maxPlayers));
        teamElement.setAttribute("max-overfill", Integer.toString(maxOverfill));
        return teamElement;
    }

    public Element getFilterElement() {
        Element teamFilter = doc.createElement("filter");
        teamFilter.setAttribute("name", name);
        Element teamElement = doc.createElement("team");
        teamElement.appendChild(doc.createTextNode(name));

        teamFilter.appendChild(teamElement);
        return teamFilter;
    }
}
