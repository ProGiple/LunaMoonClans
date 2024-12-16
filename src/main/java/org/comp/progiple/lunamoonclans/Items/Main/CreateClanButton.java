package org.comp.progiple.lunamoonclans.Items.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.comp.progiple.lunamoonclans.Clans.Clan;
import org.comp.progiple.lunamoonclans.Items.Button;
import org.comp.progiple.lunamoonclans.Other.Config;
import org.comp.progiple.lunamoonclans.Other.Utils;
import org.example.novasparkle.Items.Item;

import java.util.List;

public class CreateClanButton extends Item implements Button {
    public CreateClanButton(Material material, String displayName, List<String> lore, int amount, byte slot) {
        super(material, displayName, lore, amount, slot);
    }

    @Override
    public void onClick(Player player) {
        if (Utils.getClan(player.getName()) == null) {
            if (Config.getInt("settings.createCost") > 10000) {
                new Clan("id", "&cprefix", player);
            }
            else player.sendMessage(Utils.color(""));
        }
        else player.sendMessage(Utils.color(""));
    }
}
