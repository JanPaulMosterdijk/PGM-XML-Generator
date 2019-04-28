package me.kroest.xmlgenerator;

import me.kroest.xmlgenerator.MapObjects.Kit;
import me.kroest.xmlgenerator.MapObjects.Spawn;
import me.kroest.xmlgenerator.MapObjects.Team;
import me.kroest.xmlgenerator.models.FilterType;
import me.kroest.xmlgenerator.MapObjects.regions.Region;
import net.minecraft.server.v1_13_R2.ItemArmor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Set;

public class RootXML {
    Document doc;
    Globals globals;

    public Element mapElement;
    public Element nameElement;
    public Element objectiveElement;
    public Element authorsElement;
    public Element kitsElement;
    public Element toolrepairElement;
    public Element itemremoveElement;
    public Element spawnsElement;
    public Element filtersElement;
    public Element regionsElement;
    public Element woolsElement;
    public Element maxbuildheightElement;
    public Element killrewardElement;
    public Element denyAllElement;
    public Element negativeElement;
    public Element enterAllElement;

    public RootXML(Document doc, Globals globals){
        this.doc = doc;
        this.globals = globals;
    }

    public static void removeChilds(Node... nodes) {
        for (Node node: nodes) {
            if(node != null) {
                while (node.hasChildNodes())
                    node.removeChild(node.getFirstChild());
            }
        }
    }

    public boolean generateXML(){
        Map map = globals.map;
        removeChilds(doc);

        mapElement = doc.createElement("map");
        doc.appendChild(mapElement);

        nameElement = doc.createElement("name");
        nameElement.appendChild(doc.createTextNode(map.name));
        mapElement.appendChild(nameElement);

        objectiveElement = doc.createElement("objective");
        objectiveElement.appendChild(doc.createTextNode(map.objective));
        mapElement.appendChild(objectiveElement);

        authorsElement = doc.createElement("authors");
        authorsElement.setAttribute("uuid", map.author.getUniqueId().toString());
        mapElement.appendChild(authorsElement);

        filtersElement = doc.createElement("filters");
        mapElement.appendChild(filtersElement);

        kitsElement = doc.createElement("kits");
        for (Team t: map.kits.keySet()) {
            Kit k = map.kits.get(t);

            if (k != null) {
                k.team = t;
                kitsElement.appendChild(k.getElement());
            }
        }
        mapElement.appendChild(kitsElement);


        toolrepairElement = doc.createElement("toolrepair");
        mapElement.appendChild(toolrepairElement);

        itemremoveElement = doc.createElement("itemremove");
        mapElement.appendChild(itemremoveElement);

        spawnsElement = doc.createElement("spawns");
        for (Team t: map.spawns.keySet()) {
            for (Spawn s : map.spawns.get(t)) {
                s.team = t;
                spawnsElement.appendChild(s.getElement());
            }
        }
        mapElement.appendChild(spawnsElement);


        regionsElement = doc.createElement("regions");
        mapElement.appendChild(regionsElement);

        // TODO: 24-Apr-19 check if wools got defined
        //if(map.)
        woolsElement = doc.createElement("wools");
        mapElement.appendChild(woolsElement);

        // TODO: 24-Apr-19 add killreward support
        killrewardElement = doc.createElement("killreward");
        mapElement.appendChild(killrewardElement);


        //void filter
        Element voidFilter = doc.createElement("filter");
        voidFilter.setAttribute("name", "void");
        Element deny = doc.createElement("deny");
        Element voidE = doc.createElement("void");

        deny.appendChild(voidE);
        voidFilter.appendChild(deny);
        filtersElement.appendChild(voidFilter);

        if(map.maxBuildHeight > 0) {
            maxbuildheightElement = doc.createElement("maxbuildheight");
            maxbuildheightElement.appendChild(doc.createTextNode(Integer.toString(map.maxBuildHeight)));
            mapElement.appendChild(maxbuildheightElement);
        }

        //teams filter
        for (Team t: map.teams) {
            if(!t.name.equalsIgnoreCase("all")) {
                filtersElement.appendChild(t.getElement());
            }
        }

        //iterate all regions
        for (Team t: map.teams) {
            Set<FilterType> keys = map.regions.get(t).keySet();
            for (FilterType f: keys) {
                if(f == FilterType.BLOCK && map.regions.get(t).get(f).size() > 0) {
                    denyAllElement = doc.createElement("apply");
                    denyAllElement.setAttribute("block", t.name);
                    regionsElement.appendChild(denyAllElement);

                    for (Region r : map.regions.get(t).get(f)) {
                        denyAllElement.appendChild(r.getElement());
                    }
                }

                if(f == FilterType.ENTER && map.regions.get(t).get(f).size() > 0){
                    //enter filter
                    enterAllElement = doc.createElement("apply");
                    enterAllElement.setAttribute("enter", t.name);
                    regionsElement.appendChild(enterAllElement);

                    for (Region r : map.regions.get(t).get(f)) {
                        enterAllElement.appendChild(r.getElement());
                    }
                }

                if(f == FilterType.VOID && map.regions.get(t).get(f).size() > 0){
                    Element applyVoidElement = doc.createElement("apply");
                    applyVoidElement.setAttribute("block", "void");

                    regionsElement.appendChild(applyVoidElement);
                    negativeElement = doc.createElement("negative");
                    applyVoidElement.appendChild(negativeElement);

                    for (Region r : map.regions.get(t).get(f)) {
                        negativeElement.appendChild(r.getElement());
                    }
                }
            }
        }

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            new File(globals.rootFolder + "\\" + map.author.getUniqueId().toString()).mkdirs();
            new File(globals.rootFolder + "\\" + map.author.getUniqueId().toString() + "\\" + map.name).mkdirs();

            StreamResult result = new StreamResult(new File(globals.rootFolder + "\\" + map.author.getUniqueId().toString() + "\\" + map.name + "\\map.xml"));
            transformer.transform(source, result);

            return true;
        }
        catch (TransformerException tfe) {
            tfe.printStackTrace();
            return false;
        }
    }
}
