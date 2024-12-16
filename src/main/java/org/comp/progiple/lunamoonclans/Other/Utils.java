package org.comp.progiple.lunamoonclans.Other;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.comp.progiple.lunamoonclans.Clans.Clan;
import org.comp.progiple.lunamoonclans.Menu.Menu;
import org.comp.progiple.lunamoonclans.Menu.MenuId;
import org.example.novasparkle.Menus.MenuManager;

import java.util.HashSet;
import java.util.Set;

public class Utils {
    public static String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static Clan getClan(String nick) {
        for (Clan clan : Clan.getClanMap().values()) {
            Set<String> memberList = new HashSet<>();
            clan.getMembers().forEach(member -> memberList.add(member.getPlayer().getName()));

            if (memberList.contains(nick)) return clan;
        }
        return null;
    }

    public static void openMenu(Player player, MenuId menuId) {
        MenuConfig menuConfig = new MenuConfig(menuId);
        MenuManager.openInventory(player, new Menu(player, Utils.color(menuConfig.getStr("menu.title")),
                (byte) (menuConfig.getInt("menu.rows") * 9), ));
    }
}
