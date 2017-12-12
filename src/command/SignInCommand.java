package command;

import com.google.gson.Gson;
import dao.BankDAO;
import entity.Card;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class SignInCommand implements ICommand {

    public SignInCommand() {
    }

    @Override
    public String execute(String context) {
        try {
            StringBuilder answer = new StringBuilder("SIGNUP%21");
            String[] data = context.split(" ");
            User newUser = BankDAO.getUserData(data[0], data[1]);
            ArrayList<Card> cards = BankDAO.downloadCards(newUser.getLogin());
            Gson gson = new Gson();
            answer = answer.append(gson.toJson(newUser));
            for (Card card : cards) {
                answer.append("%20").append(gson.toJson(card));
            }
            return answer.toString();
        } catch (SQLException e) {
            return "ERROR";
        }
    }
}
