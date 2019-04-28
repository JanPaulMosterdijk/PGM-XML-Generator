package me.kroest.xmlgenerator.MapObjects.regions;

import me.kroest.xmlgenerator.MapObjects.MapObject;
import me.kroest.xmlgenerator.models.Vector3;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Cylinder extends Region {
    public Vector3 base;
    public int radius;
    public int height;

    public Cylinder(Vector base, int radius, int height, Document doc) {
        super(doc);
        this.base = new Vector3(base.getX(), base.getY(), base.getZ());
        this.radius = radius;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "base=" + base +
                ", radius=" + radius +
                ", height=" + height +
                '}';
    }

    @Override
    public Element getElement() {
        Element e = doc.createElement("cylinder");

        e.setAttribute("base", this.base.toString());
        e.setAttribute("radius", Double.toString(this.radius));
        e.setAttribute("height", Double.toString(this.height));
        return e;
    }
}
