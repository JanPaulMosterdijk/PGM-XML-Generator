package me.kroest.xmlgenerator.listeners;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler
    public void onFlag(BlockBreakEvent event) {
        Player player = event.getPlayer();

        if (player.getItemInHand().getType().equals(Material.STICK)) {
            event.setCancelled(true);
            return;
        }
    }

}
