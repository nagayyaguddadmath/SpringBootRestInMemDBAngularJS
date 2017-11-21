package com.uxpsystems.assignment.model;

public enum UserStatus {

	Activated ("true"),
	Deactivated("false"),
	Default("");
	
	String status;

    private UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static UserStatus getStatus(String status) {
        if (status == null)
            return null;
        for (UserStatus g : values()) {
            if (g.getStatus().equals(status))
                return g;
        }
        return null;
    }
}
