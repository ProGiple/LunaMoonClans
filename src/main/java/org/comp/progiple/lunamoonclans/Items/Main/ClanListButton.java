package org.comp.progiple.lunamoonclans.Items.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.comp.progiple.lunamoonclans.Items.Button;
import org.example.novasparkle.Items.Item;

import java.util.List;

public class ClanListButton extends Item implements Button {
    public ClanListButton(Material material, String displayName, List<String> lore, int amount, byte slot) {
        super(material, displayName, lore, amount, slot);
    }

    @Override
    public void onClick(Player player) {

    }
}
