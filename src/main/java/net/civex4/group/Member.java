package net.civex4.group;

import net.civex4.group.privilege.PrivilegeList;

import java.util.UUID;

/**
 * A member of a Group -- has a PrivilegeList, UUID, and primary Guild (null if there is none)
 */
public class Member {

    private PrivilegeList privileges;
    private UUID uuid;
    private Guild primaryGuild;

    public Member(UUID uuid) {
        this.uuid = uuid;
        privileges = new PrivilegeList();
    }

    public PrivilegeList privileges() {
        return privileges;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Guild getPrimaryGuild() {
        return primaryGuild;
    }

    public void setPrimaryGuild(Guild guild) {
        primaryGuild = guild;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Member && ((Member)o).uuid.equals(uuid)) return true;
        else return false;
    }

}
