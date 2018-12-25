package com.yj.dal.param;

public class ClientDistributionParam {

    private String roleId;

    private String personalId;

    private String[] cids;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String[] getCids() {
        return cids;
    }

    public void setCids(String[] cids) {
        this.cids = cids;
    }
}
