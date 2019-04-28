package me.kroest.xmlgenerator.MapObjects.regions;

import me.kroest.xmlgenerator.models.Vector3;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Cuboid extends Region {
    public Vector3 min;
    public Vector3 max;
    Document doc;

    public Cuboid(Vector min, Vector max, Document doc) {
        super(doc);
        this.min = new Vector3(min.getX(), min.getY(), min.getZ());
        this.max = new Vector3(max.getX(), max.getY(), max.getZ());
        fixCoords();
    }

    void fixCoords(){
        //ceiling every axis
        ceilPositions(min.x, max.x);
        ceilPositions(min.y, max.y);
        ceilPositions(min.z, max.z);
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
