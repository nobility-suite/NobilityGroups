package net.civex4.group.privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles a list of privileges. Has special functions allowing to join and delete many privileges at once (useful for
 * someone leaving a guild for example).
 */
public class PrivilegeList {

    private List<Privilege> privileges;

    public PrivilegeList() {
        privileges = new ArrayList<>();
    }

    protected PrivilegeList copy() {
        PrivilegeList copy = new PrivilegeList();
        copy.privileges = new ArrayList<>(this.privileges);
        return copy;
    }

    public boolean hasPrivilege(Privilege privilege) {
        for(Privilege privilegeToCheck: privileges) {
            if(privilegeToCheck.equals(privilege)) return true;

            List<Privilege> predecessors = privilegeToCheck.getPredecessors();
            if(predecessors != null && predecessors.contains(privilege)) return true;
        }

        return false;
    }

    public List<Privilege> getPrivileges() {
        return new ArrayList<>(privileges);
    }

    public void addPrivilege(Privilege privilege) {
        privileges.add(privilege);
    }

    public void removePrivilege(Privilege privilege) {
        privileges.remove(privilege);
    }

    public void addPrivilegeList(PrivilegeList privilegeList) {
        List<Privilege> list = privilegeList.privileges;
        for(Privilege privilege: list) addPrivilege(privilege);
    }

    public void removePrivilegeList(PrivilegeList privilegeList) {
        List<Privilege> list = privilegeList.privileges;
        for(Privilege privilege: list) removePrivilege(privilege);
    }

}
