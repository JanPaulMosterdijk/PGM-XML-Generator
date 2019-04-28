package me.kroest.xmlgenerator.MapObjects;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public abstract class MapObject {

    protected Document doc;

    public MapObject(Document doc){
        this.doc = doc;
    }

    public abstract Element getElement();
}
