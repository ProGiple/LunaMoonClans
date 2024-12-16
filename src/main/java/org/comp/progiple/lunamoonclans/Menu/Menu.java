package org.comp.progiple.lunamoonclans.Menu;

import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.comp.progiple.lunamoonclans.Items.Button;
import org.example.novasparkle.Menus.AMenu;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Menu extends AMenu {
    @Getter private static Map<Player, AMenu> lastOpenedMenu = new HashMap<>();

    private final Set<Button> clickableItems = new HashSet<>();
    public Menu(Player player, String title, byte size, ConfigurationSection decorationSection, ConfigurationSection actionSection) {
        super(player, title, size, decorationSection);
    }

    @Override
    public void onOpen(InventoryOpenEvent e) {
        this.insertAllItems();
        this.clickableItems.forEach(item -> this.getInventory().setItem(item.getSlot(), item.getItemStack()));
    }

    @Override
    public void onClick(InventoryClickEvent e) {
        ItemStack itemStack = e.getCurrentItem();
        this.clickableItems.forEach(button -> {
            if (button.getItemStack().equals(itemStack)) {
                button.onClick((Player) e.getWhoClicked());
            }
        });
    }

    @Override
    public void onClose(InventoryCloseEvent e) {
        Menu.getLastOpenedMenu().put((Player) e.getPlayer(), this);
    }
}
