package org.comp.progiple.lunamoonclans.Items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Button {
    byte getSlot();
    ItemStack getItemStack();
    void onClick(Player player);
}
