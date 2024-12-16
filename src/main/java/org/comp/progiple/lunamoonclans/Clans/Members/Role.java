package org.comp.progiple.lunamoonclans.Clans.Members;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    MEMBER(0, "members"),
    MODERATOR(1, "moderators"),
    DEPUTY(2, "deputies"),
    LEADER(3, "leader");

    private final int statusWeight;
    private final String string;
}
