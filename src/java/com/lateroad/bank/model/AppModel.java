package com.lateroad.bank.model;

import com.lateroad.bank.logic.dao.BankDAO;
import com.lateroad.bank.logic.entity.Card;
import com.lateroad.bank.logic.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class AppModel {
    private static final AppModel instance = new AppModel();

    private AppModel() {
    }

    public static AppModel getInstance() {
        return instance;
    }

    private String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public ArrayList<User> getUsers() {
        try {
            return BankDAO.downloadUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Card> getCards() {
        try {
            return BankDAO.downloadCards(currentUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserData(String user, String pass) throws SQLException {
        return BankDAO.getUserData(user, pass);
    }
}
