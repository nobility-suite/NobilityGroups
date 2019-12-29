package net.civex4.group.privilege;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the static list of Privileges (Nobility Permissions). Allows other parts of Nobility to dynamically add
 * permissions as needed by simply creating one.
 */
public class Privilege {

    private static List<Privilege> privileges;

    public static List<Privilege> getPrivileges() {
        return new ArrayList<Privilege>(privileges);
    }

    public static Privilege getPrivilege(String name) {
        for(Privilege privilege: privileges) if(privilege.getName().equals(name)) return privilege;
        throw new IllegalArgumentException("There is no privilege by name '" + name + "'");
    }

    private String name;
    private List<Privilege> predecessors = null;

    public Privilege(String name) {
        this.name = name;

        try {
            Privilege privilege = getPrivilege(name);
            this.predecessors = privilege.predecessors;
        } catch (IllegalArgumentException e) {
            privileges.add(this);
        }
    }

    public Privilege(String name, List<Privilege> predecessors) {
        this.name = name;
        this.predecessors = predecessors;

        try {
            Privilege privilege = getPrivilege(name);
            this.predecessors = privilege.predecessors;
        } catch (IllegalArgumentException e) {
            privileges.add(this);
        }
    }

    public String getName() {
        return name;
    }

    public List<Privilege> getPredecessors() {
        if(predecessors == null) return null;
        else return new ArrayList<Privilege>(predecessors);
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Privilege && ((Privilege)o).getName().equals(name)) return true;
        return false;
    }

}