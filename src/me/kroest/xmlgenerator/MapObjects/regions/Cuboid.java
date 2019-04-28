package me.kroest.xmlgenerator.MapObjects.regions;

import me.kroest.xmlgenerator.models.Vector3;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Cuboid extends Region {
    public Vector3 min;
    public Vector3 max;

    public Cuboid(Vector min, Vector max, Document doc) {
        super(doc);
        this.min = new Vector3(min.getX(), min.getY(), min.getZ());
        this.max = new Vector3(max.getX(), max.getY(), max.getZ());
        fixCoords();
    }

    void fixCoords(){
        //ceiling every axis
        min.x = ceilPositions(min.x, max.x)[0];
        max.x = ceilPositions(min.x, max.x)[1];

        min.y = ceilPositions(min.y, max.y)[0];
        max.y = ceilPositions(min.y, max.y)[1];

        min.z = ceilPositions(min.z, max.z)[0];
        max.z = ceilPositions(min.z, max.z)[1];
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public Element getElement() {
        Element e = doc.createElement("cuboid");

        e.setAttribute("min", this.min.toString());
        e.setAttribute("max", this.max.toString());
        return e;
    }
}
