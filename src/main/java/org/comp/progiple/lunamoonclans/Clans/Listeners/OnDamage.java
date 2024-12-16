package org.comp.progiple.lunamoonclans.Clans.Listeners;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.comp.progiple.lunamoonclans.Clans.Clan;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OnDamage implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity entity = event.getEntity();
        Entity damager = event.getDamager();
        if (damager.getType().equals(EntityType.PLAYER) && entity.getType().equals(EntityType.PLAYER)) {
            OfflinePlayer damagerPlayer = (OfflinePlayer) damager;
            OfflinePlayer player = (OfflinePlayer) entity;

            for (Map.Entry<String, Clan> entry : Clan.getClanMap().entrySet()) {
                Clan clan = entry.getValue();
                Set<OfflinePlayer> memberList = new HashSet<>();
                clan.getMembers().forEach(member -> memberList.add(member.getPlayer()));

                if (memberList.contains(damagerPlayer) && memberList.contains(player)) {
                    event.setCancelled(true);
                    break;
                }
            }
        }
    }
}
