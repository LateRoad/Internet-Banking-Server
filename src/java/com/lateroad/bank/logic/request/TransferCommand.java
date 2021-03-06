package com.lateroad.bank.logic.request;

import com.google.gson.Gson;
import com.lateroad.bank.logic.dao.BankDAO;
import com.lateroad.bank.logic.entity.Card;
import com.lateroad.bank.model.AppModel;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransferCommand implements ICommand {

    @Override
    public String execute(String context) {
        try {
            StringBuilder answer = new StringBuilder("UPDATE%21");
            ArrayList<Card> cards = AppModel.getInstance().getCards();
            String receiver = context.split("%20")[0];
            BigDecimal money = new BigDecimal(context.split("%20")[1]);
            String adresser = context.split("%20")[2];
            Card receiverOld = BankDAO.selectCard(receiver);
            Card adresserOld = BankDAO.selectCard(adresser);

            BankDAO.updateCardData(receiver, new BigDecimal(receiverOld.getMoney()).add(money).toString());
            BankDAO.updateCardData(adresser, new BigDecimal(adresserOld.getMoney()).subtract(money).toString());

            ArrayList<Card> cardsToUpdate = BankDAO.downloadCards(adresserOld.getOwner());
            Gson gson = new Gson();
            for (Card card : cardsToUpdate) {
                answer.append(gson.toJson(card)).append("%20");
            }
            return answer.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
