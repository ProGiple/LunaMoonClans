package org.comp.progiple.lunamoonclans.Clans.Members;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.Arrays;
import java.util.List;

@Getter
public class Member {
    private final OfflinePlayer player;
    private Role role;
    private String clanId;

    public Member(OfflinePlayer player, Role role, String clanId) {
        this.player = player;
        this.role = role;
        this.join(clanId);
    }

    public Member(String nick, Role role, String clanId) {
        this.player = Bukkit.getOfflinePlayerIfCached(nick);
        this.role = role;
        this.join(clanId);
    }

    public void promote() {
        List<Role> roles = Arrays.asList(Role.values());
        int index = roles.indexOf(this.role);
        if (roles.get(index + 1) != null) {
            this.role = roles.get(index + 1);
        }
    }

    public void demote() {
        List<Role> roles = Arrays.asList(Role.values());
        int index = roles.indexOf(this.role);
        if (roles.get(index - 1) != null) {
            this.role = roles.get(index - 1);
        }
    }

    public void join(String clanId) {
        this.clanId = clanId;
    }

    public void leave() {
        this.clanId = null;
    }
}
