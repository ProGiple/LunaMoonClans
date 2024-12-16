package org.comp.progiple.lunamoonclans.Items.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.comp.progiple.lunamoonclans.Items.Button;
import org.comp.progiple.lunamoonclans.Menu.Menu;
import org.example.novasparkle.Items.Item;
import org.example.novasparkle.Menus.AMenu;
import org.example.novasparkle.Menus.MenuManager;

import java.util.List;

public class PreviewPage extends Item implements Button {
    public PreviewPage(Material material, String displayName, List<String> lore, int amount, byte slot) {
        super(material, displayName, lore, amount, slot);
    }

    @Override
    public void onClick(Player player) {
        AMenu aMenu = Menu.getLastOpenedMenu().get(player);
        MenuManager.openInventory(player, aMenu);
    }
}
