package dao;

import entity.Card;
import entity.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankDAO {
    private static BankDAOConnection databaseConnection;

    private static final String DOWNLOAD_USERS = "SELECT * FROM `bank_mysql`.`user` " +
            "STRAIGHT_JOIN `bank_mysql`.`user_info` ON `user`.`login` = `user_info`.`login`";
    private static final String DOWNLOAD_CARDS_BY_LOGIN = "SELECT * FROM `bank_mysql`.`card` " +
            "WHERE `card`.`login` = ?;";
    private static final String SELECT_CARD = "SELECT * FROM `bank_mysql`.`card` " +
            "WHERE `card`.`number` = ?;";
    private static final String INSERT_USER = "INSERT INTO `bank_mysql`.`user` values ('?', '?', '?');";
    private static final String INSERT_USER_INFO = "INSERT INTO `bank_mysql`.`user_info` values ('?', '?', '?', '?');";

    private static final String SELECT_USER_DATA_BY_LOGIN_AND_PASSWORD = "SELECT * FROM `bank_mysql`.`user` " +
            "STRAIGHT_JOIN `bank_mysql`.`user_info` ON `user`.`login` = `user_info`.`login` " +
            "WHERE `user`.`login` = ? AND `user`.`password` = ?;";

    private static final String UPDATE_USER = "UPDATE `bank_mysql`.`user` AS u " +
            "SET u.`login` = '?', u.`password` = '?', u.`permission` = '?' " +
            "WHERE u.`login` = '?' AND u.`password` = '?'";
    private static final String UPDATE_USER_INFO = "UPDATE `bank_mysql`.`user` AS u " +
            "SET u.`login` = '?', u.`password` = '?', u.`permission` = '?' " +
            "WHERE u.`login` = '?' AND u.`password` = '?'";
    private static final String UPDATE_CARD = "UPDATE `bank_mysql`.`card` AS c " +
            "SET c.`money` = ? " +
            "WHERE c.`number` = ?;";

    public  BankDAO() throws SQLException {
        if (databaseConnection == null) {
            databaseConnection = BankDAOConnection.getInstance();
        }
    }

    public static Card selectCard(String number) throws SQLException{
        ArrayList<Card> cards = new ArrayList<>();
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(SELECT_CARD);
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
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(DOWNLOAD_USERS);
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
        PreparedStatement downloadUserQuery = BankDAOConnection.getConnection().prepareStatement(DOWNLOAD_CARDS_BY_LOGIN);
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

    public static void insertUserIntoDatabase(User newUser) throws SQLException {
        PreparedStatement insertUserQuery = BankDAOConnection.getConnection().prepareStatement(INSERT_USER);
        insertUserQuery.setString(1, newUser.getLogin());
        insertUserQuery.setString(2, newUser.getPassword());
        insertUserQuery.setString(3, newUser.getPermissionName());
        insertUserQuery.executeUpdate();

        PreparedStatement insertUserInfoQuery = BankDAOConnection.getConnection().prepareStatement(INSERT_USER_INFO);
        insertUserInfoQuery.setString(1, newUser.getLogin());
        insertUserInfoQuery.setString(2, newUser.getName());
        insertUserInfoQuery.setString(3, newUser.getSurname());
        insertUserInfoQuery.setString(4, newUser.getLastname());
        insertUserInfoQuery.executeUpdate();
    }


    public static User getUserData(String login, String password) throws SQLException {
        PreparedStatement selectUser = BankDAOConnection.getConnection().prepareStatement(SELECT_USER_DATA_BY_LOGIN_AND_PASSWORD);
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

    public void updateUserData(User userForUpdate, String login, String password) throws SQLException {
        PreparedStatement updateClient = BankDAOConnection.getConnection().prepareStatement(UPDATE_USER);
        updateClient.setString(1, userForUpdate.getLogin());
        updateClient.setString(2, userForUpdate.getPassword());
        updateClient.setString(3, userForUpdate.getPermissionName());
        updateClient.setString(4, login);
        updateClient.setString(5, password);
        updateClient.executeUpdate();
    }

    public static void updateCardData(String cardForUpdate, String money) throws SQLException {
        PreparedStatement updateClient = BankDAOConnection.getConnection().prepareStatement(UPDATE_CARD);
        updateClient.setString(1, money);
        updateClient.setString(2, cardForUpdate);
        updateClient.executeUpdate();
    }
}
