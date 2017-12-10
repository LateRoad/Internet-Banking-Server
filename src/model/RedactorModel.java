package model;

import dao.BankDAO;
import entity.Card;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class RedactorModel {
    private static final RedactorModel instance = new RedactorModel();
    private RedactorModel() {
    }
    public static RedactorModel getInstance() {
        return instance;
    }




    String currentUser;

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
}
