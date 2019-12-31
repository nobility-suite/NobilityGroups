package net.civex4.group;

import net.civex4.group.privilege.Privilege;
import net.civex4.group.privilege.PrivilegeList;

import java.util.ArrayList;
import java.util.List;

public class Guild {

    private class GuildPrivilegeList extends PrivilegeList {
        private PrivilegeList old;

        @Override
        public void addPrivilege(Privilege privilege) {
            old = copy();
            super.addPrivilege(privilege);
            updatePrivileges();
        }

        @Override
        public void removePrivilege(Privilege privilege) {
            old = copy();
            super.removePrivilege(privilege);
            updatePrivileges();
        }

        @Override
        public void addPrivilegeList(PrivilegeList privilegeList) {
            old = copy();
            super.addPrivilegeList(privilegeList);
            updatePrivileges();
        }

        @Override
        public void removePrivilegeList(PrivilegeList privilegeList) {
            old = copy();
            super.removePrivilegeList(privilegeList);
            updatePrivileges();
        }

        private void updatePrivileges() {
            for(Member member: members) {
                member.privileges().removePrivilegeList(old);
                member.privileges().addPrivilegeList(this);
            }
        }
    }

    private GuildPrivilegeList privileges;
    private List<Member> members;

    public Guild(Member member) {
        members = new ArrayList<>();
        privileges = new GuildPrivilegeList();
        members.add(member);
    }

    public PrivilegeList privileges() {
        return privileges;
    }

    public void addMember(Member member) {
        if(members.contains(member)) throw new IllegalArgumentException("Member cannot be added: Member already " +
                "part of this guild");

        member.privileges().addPrivilegeList(privileges);
        members.add(member);
    }

    public void removeMember(Member member) {
        if(!members.contains(member)) throw new IllegalArgumentException("Member cannot be removed: Not a part of " +
                "this guild");

        member.privileges().removePrivilegeList(privileges);
        members.remove(member);
    }

}
