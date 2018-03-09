package com.lateroad.bank.logic.dao;

import com.lateroad.bank.logic.entity.Card;
import com.lateroad.bank.logic.entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankDAO {
    private static BankDAOConnection databaseConnection;
    private static SqlMap sqlMap;

    static {
        try {
            databaseConnection = BankDAOConnection.getInstance();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sqlMap = new SqlMap();
            sqlMap.loadXML("resources/prop.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Card selectCard(String number) throws SQLException {
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("SELECT_CARD"));
        downloadUserQuery.setString(1, number);
        ResultSet card = downloadUserQuery.executeQuery();
        card.next();
        return new Card(
                card.getString("id"),
                card.getString("login"),
                card.getString("number"),
                card.getString("password"),
                card.getString("secret_number"),
                card.getString("end_date"),
                card.getString("money"));
    }

    public static ArrayList<User> downloadUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("DOWNLOAD_USERS"));
        ResultSet user = downloadUserQuery.executeQuery();
        while (user.next()) {
            users.add(new User(
                    user.getString("login"),
                    user.getString("password"),
                    user.getString("role"),
                    user.getString("name"),
                    user.getString("surname"),
                    user.getString("lastname")));
        }
        return users;
    }

    public static ArrayList<Card> downloadCards(String login) throws SQLException {
        ArrayList<Card> cards = new ArrayList<>();
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("DOWNLOAD_CARDS_BY_LOGIN"));
        downloadUserQuery.setString(1, login);
        ResultSet card = downloadUserQuery.executeQuery();
        while (card.next()) {
            cards.add(new Card(
                    card.getString("id"),
                    card.getString("login"),
                    card.getString("number"),
                    card.getString("password"),
                    card.getString("secret_number"),
                    card.getString("end_date"),
                    card.getString("money")));
        }
        return cards;
    }

    public static void insertUser(User newUser) throws SQLException {
        PreparedStatement insertUserQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("INSERT_USER"));
        insertUserQuery.setString(1, newUser.getLogin());
        insertUserQuery.setString(2, newUser.getPassword());
        insertUserQuery.setString(3, newUser.getPermissionName());
        insertUserQuery.executeUpdate();

        PreparedStatement insertUserInfoQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("INSERT_USER_INFO"));
        insertUserInfoQuery.setString(1, newUser.getLogin());
        insertUserInfoQuery.setString(2, newUser.getName());
        insertUserInfoQuery.setString(3, newUser.getSurname());
        insertUserInfoQuery.setString(4, newUser.getLastname());
        insertUserInfoQuery.executeUpdate();
    }

    public static void insertCard(Card newCard) throws SQLException {
        PreparedStatement insertCardQuery = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("INSERT_CARD"));
        insertCardQuery.setString(1, newCard.getOwner());
        insertCardQuery.setString(2, newCard.getNumber());
        insertCardQuery.setString(3, newCard.getPassword());
        insertCardQuery.setString(4, newCard.getSecretNumber());
        insertCardQuery.setString(5, newCard.getEndDate());
        insertCardQuery.setString(6, newCard.getMoney());
        insertCardQuery.executeUpdate();
    }


    public static User getUserData(String login, String password) throws SQLException {
        PreparedStatement selectUser = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("SELECT_USER_DATA_BY_LOGIN_AND_PASSWORD"));
        selectUser.setString(1, login);
        selectUser.setString(2, password);
        ResultSet user = selectUser.executeQuery();
        user.next();
        return new User(
                user.getString("login"),
                user.getString("password"),
                user.getString("role"),
                user.getString("name"),
                user.getString("surname"),
                user.getString("lastname"));
    }

    public static void updateUserData(User userForUpdate, String login, String password) throws SQLException {
        PreparedStatement updateClient = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("UPDATE_USER"));
        updateClient.setString(1, userForUpdate.getLogin());
        updateClient.setString(2, userForUpdate.getPassword());
        updateClient.setString(3, userForUpdate.getPermissionName());
        updateClient.setString(4, login);
        updateClient.setString(5, password);
        updateClient.executeUpdate();
    }

    public static void updateCardData(String cardForUpdate, String money) throws SQLException {
        PreparedStatement updateClient = BankDAOConnection.getConnection().prepareStatement(sqlMap.getSql("UPDATE_CARD"));
        updateClient.setString(1, money);
        updateClient.setString(2, cardForUpdate);
        updateClient.executeUpdate();
    }
}
