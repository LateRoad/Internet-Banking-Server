package model;

import dao.BankDAO;
import entity.User;

import java.sql.SQLException;

public class LoginModel {

    BankDAO dao;
    public LoginModel() {
        try {
            dao = new BankDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getUserData(String user, String pass) throws SQLException {
        return dao.getUserData(user, pass);
    }

    public boolean isLogin(String user, String pass) throws SQLException {
        return dao.getUserData(user, pass) != null;
    }
}
