package controller;

import entity.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.RedactorModel;
import switcher.SceneSwitcher;

import java.net.URL;
import java.util.ResourceBundle;

public class CardRedactorController implements Initializable{
    @FXML public TableView<Card> tableViewCard;
    @FXML public TableColumn<Card, String> idColumn;
    @FXML public TableColumn<Card, String> numberColumn;
    @FXML public TableColumn<Card, String> passwordColumn;
    @FXML public TableColumn<Card, String> secretNumberColumn;
    @FXML public TableColumn<Card, String> endDateColumn;
    @FXML public TableColumn<Card, String> moneyColumn;

    @FXML public AnchorPane rootPane;
    @FXML public TextField cardNumber;
    @FXML public TextField secretNumber;
    @FXML public TextField endDate;
    @FXML public Button buttonAdd;

    @FXML public Button buttonToUsers;

    private RedactorModel model = RedactorModel.getInstance();


    public void showUserTable(ActionEvent actionEvent) {
        SceneSwitcher.toScene(rootPane, "../view/userRedactor.fxml");
    }

    public void addCard(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        secretNumberColumn.setCellValueFactory(new PropertyValueFactory<>("secretNumber"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        moneyColumn.setCellValueFactory(new PropertyValueFactory<>("money"));
        for (int i = 0; i < model.getCards().size(); ++i) {
            tableViewCard.getItems().add(model.getCards().get(i));
        }
    }
}
