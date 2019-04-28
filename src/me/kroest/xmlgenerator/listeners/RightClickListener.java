package me.kroest.xmlgenerator.listeners;

import me.kroest.xmlgenerator.Globals;
import me.kroest.xmlgenerator.models.Pos;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class RightClickListener implements Listener {

    Globals globals;

    public RightClickListener(Globals globals){
        this.globals = globals;
    };

    @EventHandler
    public void PlayerClickBlock(PlayerInteractEvent event){
        Player p = event.getPlayer();
        EquipmentSlot e = event.getHand();
        if(e.equals(EquipmentSlot.HAND)){
            if(p.getItemInHand().getType() == Material.STICK) {
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK ) {
                    globals.pos2 = event.getClickedBlock().getLocation().toVector();
                    p.sendMessage("pos2 = " + globals.pos2);

                }
                else if(event.getAction() == Action.LEFT_CLICK_BLOCK){
                    globals.pos1 = event.getClickedBlock().getLocation().toVector();
                    p.sendMessage("pos1 = " + globals.pos1);
                }
            }
            else{
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                    Block b = event.getClickedBlock();
                    if(b.getType() == Material.CHEST){
                        Inventory blockInv = ((InventoryHolder) b.getState()).getInventory();
                        globals.selectedInv = blockInv;
                        p.sendMessage("Copied inventory.");
                    }
                }
            }
        }
    }
}
