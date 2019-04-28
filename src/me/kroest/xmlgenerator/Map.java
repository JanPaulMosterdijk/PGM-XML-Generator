package me.kroest.xmlgenerator;

import me.kroest.xmlgenerator.MapObjects.Kit;
import me.kroest.xmlgenerator.MapObjects.Team;
import me.kroest.xmlgenerator.models.FilterType;
import me.kroest.xmlgenerator.models.ObjectiveType;
import me.kroest.xmlgenerator.MapObjects.Spawn;
import me.kroest.xmlgenerator.MapObjects.objectives.Objective;
import me.kroest.xmlgenerator.MapObjects.regions.Region;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    public String name;
    public Player author;
    public HashMap<Team, HashMap<FilterType, ArrayList<Region>>> regions;
    public ArrayList<Team> teams;
    public String objective;
    public Team global;
    public HashMap<Team, Kit> kits;
    public HashMap<Team, ArrayList<Spawn>> spawns;
    public Document doc;
    public int maxBuildHeight;
    public HashMap<ObjectiveType, ArrayList<Objective>> objectives;

    public Map(String name, Player player, Document doc){
        this.doc = doc;
        objective = "";
        this.name = name;
        this.author = player;
        this.maxBuildHeight = 0;

        teams = new ArrayList<>();
        regions = new HashMap<>();
        kits = new HashMap<>();
        spawns = new HashMap<>();
        objectives = new HashMap<>();

        global = new Team(doc);

        global.name = "all";
        addTeam(global);

        for (ObjectiveType objectiveType: ObjectiveType.values()) {
            objectives.put(objectiveType, new ArrayList<>());
        }
    }

    public boolean addSpawn(Vector v, Team t){
        if(teams.contains(t)){
            spawns.get(t).add(new Spawn(v, doc));
            return true;
        }
        else{
            return false;
        }
    }

    public boolean setKit(PlayerInventory i, Team t){
        if(kits.containsKey(t)){
            kits.put(t, new Kit(i, doc));
            return true;
        }
        else {
            return false;
        }
    }

    public void addTeam(Team team){
        if(!teams.contains(team)){
            teams.add(team);
        }

        if(!regions.containsKey(team)) {
            regions.put(team, new HashMap<>());
        }

        if(!spawns.containsKey(team)) {
            spawns.put(team, new ArrayList<>());
        }

        if(!kits.containsKey(team)) {
            kits.put(team, null);
        }

        for (FilterType f: FilterType.values()) {
            regions.get(team).put(f, new ArrayList<>());
        }
    }

    public Team getTeamByName(String teamName){
        Team team = null;
        for (Team t: teams) {
            if (t.name.equals(teamName)){
                team = t;
            }
        }
        return team;
    }

    public boolean removeTeam(String teamName){
        Team team = getTeamByName(teamName);
        if(team == null){
            return false;
        }
        else {
            if (regions.containsKey(team)) {
                regions.remove(team);
                return true;
            } else {
                return false;
            }
        }
    }

    public void addObjective(Objective o){
        objectives.get(o.type).add(o);
    }

    public boolean addRegion(Region region, FilterType filters[], Team team){
        if(regions.keySet().contains(team)) {
            for (FilterType filter : filters) {
                regions.get(team).get(filter).add(region);
            }
            return true;
        }
        else {
            return false;
        }
    }
}
