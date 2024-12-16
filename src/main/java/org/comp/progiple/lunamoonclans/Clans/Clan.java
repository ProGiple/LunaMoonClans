package org.comp.progiple.lunamoonclans.Clans;

import lombok.Getter;
import lombok.SneakyThrows;
import org.bukkit.OfflinePlayer;
import org.comp.progiple.lunamoonclans.Clans.Members.Member;
import org.comp.progiple.lunamoonclans.Clans.Members.Role;
import org.comp.progiple.lunamoonclans.LunaMoonClans;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

@Getter
public class Clan {
    @Getter private static Map<String, Clan> clanMap = new HashMap<>();
    private static final JSONParser parser = new JSONParser();

    private String prefix;
    private final String id;
    private byte level = 1;
    private int balance = 0;
    private Member leader;
    private final List<Member> members = new ArrayList<>();
    private final File configFile;

    @SneakyThrows
    public Clan(String id) {
        this.id = id;
        this.configFile = new File(LunaMoonClans.getPlugin().getDataFolder(), String.format("clans/%s.json", id));

        JSONObject container = (JSONObject) parser.parse(new FileReader(this.configFile));
        JSONObject playerStructure = (JSONObject) container.get("members");

        this.leader = new Member((String) playerStructure.get("leader"), Role.LEADER, this.id);
        Arrays.asList(Role.values()).forEach(role -> {
            if (!role.getString().equalsIgnoreCase("leader")) {
                JSONArray array = (JSONArray) playerStructure.get(role.getString());
                for (Object object : array) {
                    Member member = new Member((String) object, role, this.id);
                    this.members.add(member);
                }
            }
        });

        this.level = (byte) container.get("level");
        this.prefix = (String) container.get("prefix");
        this.balance = (int) container.get("balance");
        clanMap.put(this.id, this);
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    public Clan(String id, String prefix, OfflinePlayer leader) {
        this.prefix = prefix;
        this.id = id;
        this.leader = new Member(leader, Role.LEADER, this.id);

        this.configFile = new File(LunaMoonClans.getPlugin().getDataFolder(), String.format("clans/%s.json", id));
        if (!this.configFile.createNewFile()) {
            throw new RuntimeException("Не удалось создать файл клана!");
        }
        FileWriter writer = new FileWriter(this.configFile);

        JSONObject container = new JSONObject();
        JSONObject playerStructure = new JSONObject();

        playerStructure.put("leader", this.leader);
        Arrays.asList(Role.values()).forEach(role -> {
            if (!role.getString().equalsIgnoreCase("leader")) {
                JSONArray array = new JSONArray();
                playerStructure.put(role.getString(), array);
            }
        });

        container.put("prefix", this.prefix);
        container.put("level", this.level);
        container.put("balance", this.balance);
        container.put("members", playerStructure);

        writer.write(container.toJSONString());
        writer.flush();
        writer.close();
        clanMap.put(this.id, this);
    }

    public void levelUp() {
        this.level = (byte) (this.level + 1);
        this.writeToJSON("level", this.level);
    }

    public void setLevel(byte newLevel) {
        this.level = newLevel;
        this.writeToJSON("level", this.level);
    }

    public void setPrefix(String newPrefix) {
        this.prefix = newPrefix;
        this.writeToJSON("prefix", this.prefix);
    }

    public void addBalance(int adding) {
        this.balance = this.balance + adding;
        this.writeToJSON("balance", this.balance);
    }

    public void setBalance(int count) {
        this.balance = count;
        this.writeToJSON("balance", this.balance);
    }

    public void changeLeader(Member newLeader) {
        this.leader.demote();
        this.members.add(this.leader);
        this.writeToJSON(Role.DEPUTY.getString(), this.leader.getPlayer().getName(), false);

        this.leader = newLeader;
        this.members.remove(this.leader);
        this.writeToJSON(Role.LEADER.getString(), newLeader.getPlayer().getName(), false);
    }

    public boolean promoteMember(String nick) {
        Member member = this.searchMember(nick);
        if (member == null) return false;

        if (member.getRole() != Role.DEPUTY && member.getRole() != Role.LEADER) {
            this.writeToJSON(member.getRole().getString(), nick, true);
            member.promote();
            this.writeToJSON(member.getRole().getString(), nick, false);
            return true;
        }
        return false;
    }

    public boolean demoteMember(String nick) {
        Member member = this.searchMember(nick);
        if (member == null) return false;

        if (member.getRole() != Role.LEADER && member.getRole() != Role.MEMBER) {
            this.writeToJSON(member.getRole().getString(), nick, true);
            member.demote();
            this.writeToJSON(member.getRole().getString(), nick, false);
            return true;
        }
        return false;
    }

    public boolean kickMember(String nick) {
        Member member = this.searchMember(nick);
        if (member == null) return false;

        if (member.getRole() != Role.LEADER) {
            this.members.remove(member);
            member.leave();
            this.writeToJSON(member.getRole().getString(), nick, true);
            return true;
        }
        return false;
    }

    public void inviteMember(String nick) {
        this.members.add(new Member(nick, Role.MEMBER, this.id));
        this.writeToJSON("members", nick, false);
    }

    private Member searchMember(String nick) {
        for (Member member : this.members) {
            if (Objects.requireNonNull(member.getPlayer().getName()).equalsIgnoreCase(nick)) {
                return member;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private void writeToJSON(String key, Object object) {
        JSONObject container = (JSONObject) parser.parse(new FileReader(this.configFile));
        FileWriter writer = new FileWriter(this.configFile);
        container.put(key, object);

        writer.write(container.toJSONString());
        writer.flush();
        writer.close();
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    private void writeToJSON(String key, Object object, boolean isRemoved) {
        JSONObject defaultContainer = (JSONObject) parser.parse(new FileReader(this.configFile));
        JSONObject container = (JSONObject) defaultContainer.get("members");
        FileWriter writer = new FileWriter(this.configFile);

        if (!key.equalsIgnoreCase("leader")) {
            JSONArray array = (JSONArray) container.get(key);
            if (isRemoved) array.remove(object);
            else array.add(object);
            object = array;
        }

        container.put(key, object);
        writer.write(container.toJSONString());
        writer.flush();
        writer.close();
    }
}
