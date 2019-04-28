package me.kroest.xmlgenerator.MapObjects.regions;

import me.kroest.xmlgenerator.MapObjects.MapObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class Region extends MapObject{
    public Region(Document doc) {
        super(doc);
    }

    public double[] ceilPositions(double p1, double p2){
        if(p1 <= p2){
            p1 = Math.ceil(p1);
            p2 = Math.ceil(p2)+1;
        }

        else {
            p1 = Math.ceil(p1)+1;
            p2 = Math.ceil(p2);
        }
        return new double[]{p1,p2};
    }

    @Override
    public abstract Element getElement();
}
