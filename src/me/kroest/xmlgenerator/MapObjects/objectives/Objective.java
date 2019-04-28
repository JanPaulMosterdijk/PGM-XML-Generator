package me.kroest.xmlgenerator.MapObjects.objectives;

import me.kroest.xmlgenerator.MapObjects.Team;
import me.kroest.xmlgenerator.models.ObjectiveType;
import me.kroest.xmlgenerator.MapObjects.regions.Region;
import org.w3c.dom.Element;

public abstract class Objective {
    public Team team;
    public Region region;
    public ObjectiveType type;

    public abstract Element getElement();
}
