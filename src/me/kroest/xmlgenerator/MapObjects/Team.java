package me.kroest.xmlgenerator.MapObjects;

import org.bukkit.ChatColor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Team extends MapObject{
    public String name;
    public int maxPlayers;
    public int maxOverfill;
    public ChatColor color;
    public String id;

    public Team(Document doc){
        super(doc);
    };

    @Override
    public Element getElement() {
        Element teamFilter = doc.createElement("filter");
        teamFilter.setAttribute("name", name);
        Element teamElement = doc.createElement("team");
        teamElement.appendChild(doc.createTextNode(name));

        teamFilter.appendChild(teamElement);
        return teamFilter;
    }
}
