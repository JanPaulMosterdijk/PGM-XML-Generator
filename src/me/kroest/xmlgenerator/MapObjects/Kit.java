package me.kroest.xmlgenerator.MapObjects;

import me.kroest.xmlgenerator.Globals;
import net.minecraft.server.v1_13_R2.ItemArmor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Kit extends MapObject {

    public PlayerInventory i;
    public Team team;
    Globals globals;

    public Kit(PlayerInventory playerInventory, Document doc, Globals globals) {
        super(doc);
        this.i = playerInventory;
        this.globals = globals;
    }

    String itemStackEnchantsToString(ItemStack is){
        String attributes = "";
        Object[] keys = is.getEnchantments().keySet().toArray();

        for (int e = 0; e < is.getEnchantments().size(); e++) {
            Enchantment ench = (Enchantment) keys[e];
            attributes += ench.getKey().getKey() + ":" + is.getEnchantments().get(keys[e]) + ";";
        }
        return attributes;
    }

    @Override
    public Element getElement() {
        Element kitElement = doc.createElement("kit");
        kitElement.setAttribute("name", team.name);

        //first add possible armor
        if (i.getHelmet() != null) {
            Element helmetElement = doc.createElement("helmet");
            helmetElement.setAttribute("unbreakable", "true");

            if (i.getHelmet().getItemMeta().hasEnchants()) {
                helmetElement.setAttribute("enchantment", itemStackEnchantsToString(i.getHelmet()));
            }
            if(i.getHelmet().getType() == Material.LEATHER_HELMET) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) i.getHelmet().getItemMeta();
                if ((leatherArmorMeta.getColor() !=  globals.server.getItemFactory().getDefaultLeatherColor())) {
                    helmetElement.setAttribute("color", Integer.toString(leatherArmorMeta.getColor().asRGB()));
                }
            }

            helmetElement.appendChild(doc.createTextNode(i.getHelmet().getType().toString()));
            kitElement.appendChild(helmetElement);
        }

        if (i.getChestplate() != null) {
            Element chestplateElement = doc.createElement("chestplate");
            chestplateElement.setAttribute("unbreakable", "true");

            if (i.getChestplate().getItemMeta().hasEnchants()) {
                chestplateElement.setAttribute("enchantment", itemStackEnchantsToString(i.getChestplate()));
            }
            if(i.getChestplate().getType() == Material.LEATHER_CHESTPLATE) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) i.getChestplate().getItemMeta();
                if ((leatherArmorMeta.getColor() !=  globals.server.getItemFactory().getDefaultLeatherColor())) {
                    chestplateElement.setAttribute("color", Integer.toString(leatherArmorMeta.getColor().asRGB()));
                }
            }

            chestplateElement.appendChild(doc.createTextNode(i.getChestplate().getType().toString()));
            kitElement.appendChild(chestplateElement);
        }

        if (i.getLeggings() != null) {
            Element leggingsElement = doc.createElement("leggings");
            leggingsElement.setAttribute("unbreakable", "true");

            if (i.getLeggings().getItemMeta().hasEnchants()) {
                leggingsElement.setAttribute("enchantment", itemStackEnchantsToString(i.getLeggings()));
            }

            if(i.getLeggings().getType() == Material.LEATHER_LEGGINGS) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) i.getLeggings().getItemMeta();
                if ((leatherArmorMeta.getColor() !=  globals.server.getItemFactory().getDefaultLeatherColor())) {
                    leggingsElement.setAttribute("color", Integer.toString(leatherArmorMeta.getColor().asRGB()));
                }
            }

            leggingsElement.appendChild(doc.createTextNode(i.getLeggings().getType().toString()));
            kitElement.appendChild(leggingsElement);
        }

        if (i.getBoots() != null) {
            Element bootsElement = doc.createElement("boots");
            bootsElement.setAttribute("unbreakable", "true");

            if (i.getBoots().getItemMeta().hasEnchants()) {
                bootsElement.setAttribute("enchantment", itemStackEnchantsToString(i.getBoots()));
            }
            if(i.getBoots().getType() == Material.LEATHER_BOOTS) {
                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) i.getBoots().getItemMeta();
                if ((leatherArmorMeta.getColor() !=  globals.server.getItemFactory().getDefaultLeatherColor())) {
                    bootsElement.setAttribute("color", Integer.toString(leatherArmorMeta.getColor().asRGB()));
                }
            }

            bootsElement.appendChild(doc.createTextNode(i.getBoots().getType().toString()));
            kitElement.appendChild(bootsElement);
        }

        //then add items
        for (int it = 0; it < 27; it++) {
            Element itemElement;
            if(i.getItem(it) != null) {
                if (!(i.getItem(it).getItemMeta() instanceof ItemArmor)) {
                    ItemStack is = i.getItem(it);

                    itemElement = doc.createElement("item");

                    itemElement.setAttribute("slot", Integer.toString(it));
                    itemElement.setAttribute("amount", Integer.toString(is.getAmount()));

                    if (is.getItemMeta().hasEnchants()) {
                        String attributes = "";
                        Object[] keys = is.getEnchantments().keySet().toArray();

                        for (int e = 0; e < is.getEnchantments().size(); e++) {
                            Enchantment ench = (Enchantment) keys[e];
                            attributes += ench.getKey().getKey() + ":" + is.getEnchantments().get(keys[e]) + ";";
                        }
                        itemElement.setAttribute("enchantment", attributes);
                    }

                    if (((Damageable) is.getItemMeta()).hasDamage()) {
                        //item with damage value
                        itemElement.setAttribute("damage", Integer.toString(((Damageable) is.getItemMeta()).getDamage()));
                    }

                    itemElement.appendChild(doc.createTextNode(is.getType().toString()));
                    kitElement.appendChild(itemElement);
                }
            }
        }
        return kitElement;
    }
}
