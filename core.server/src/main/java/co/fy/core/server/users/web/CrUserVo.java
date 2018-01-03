package co.fy.core.server.users.web;

import co.fy.core.server.users.model.Users;

public class CrUserVo {
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    private Users user;
}
