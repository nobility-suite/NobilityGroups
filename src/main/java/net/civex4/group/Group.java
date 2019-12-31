package net.civex4.group;

import net.civex4.group.privilege.PrivilegeList;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A group of players with different Privileges used to handle permissions across all Nobility-related things and to
 * hook into other plugins which have permission systems (e.g. NameLayer).
 */
public class Group {

    private static List<Group> groups = new ArrayList<>(); // static list of all groups

    public static List<Group> getGroups() {
        return new ArrayList<>(groups);
    }

    public static Group getGroup(String name) {
        for(Group group: groups) if(group.name.equals(name)) return group;
        return null;
    }

    private PrivilegeList privilegeMasterList;  // all the permissions that are relevant for this group
    private PrivilegeList genericPrivileges; // privileges given when joining the group
    private List<Guild> guilds;
    private List<Member> members;
    private String name;

    public Group(String name, UUID creatorUUID, PrivilegeList master, PrivilegeList generic) {
        this.name = name;
        if (groups.contains(this)) throw new IllegalArgumentException("Group '" + name + "' already exists");

        privilegeMasterList = master;
        genericPrivileges = generic;
        guilds = new ArrayList<>();
        members = new ArrayList<>();
        Member creator = new Member(creatorUUID);
        addMember(creator);
        creator.privileges().addPrivilegeList(privilegeMasterList);

        groups.add(this);
    }

    public Member getMember(UUID uuid) {
        for(Member member: members) if(member.getUUID().equals(uuid)) return member;
        return null;
    }

    public void addMember(Member member) {
        if(members.contains(member)) throw new IllegalArgumentException("Member cannot be added: Member already " +
                "part of this group");

        member.privileges().addPrivilegeList(genericPrivileges);
        members.add(member);
    }

    public void removeMember(Member member) {
        if(!members.contains(member)) throw new IllegalArgumentException("Member cannot be removed: Not a part of " +
                "this group");

        members.remove(member);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Group && ((Group)o).name.equals(name)) return true;
        return false;
    }
}
