package me.kroest.xmlgenerator.MapObjects.regions;

import me.kroest.xmlgenerator.models.Vector2;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Rectangle extends Region{
    public Vector2 min;
    public Vector2 max;

    public Rectangle(Vector min, Vector max, Document doc) {
        super(doc);
        this.min = new Vector2(min.getX(), min.getZ());
        this.max = new Vector2(max.getX(), max.getZ());
        fixCoords();
    }

    void fixCoords(){
        //ceiling every axis
        ceilPositions(min.x, max.x);
        ceilPositions(min.z, max.z);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }

    @Override
    public Element getElement() {
        Element e = doc.createElement("rectangle");

        e.setAttribute("min", this.min.toString());
        e.setAttribute("max", this.max.toString());
        return  e;
    }
}
