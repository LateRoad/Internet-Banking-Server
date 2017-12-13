package controller;

import dao.BankDAO;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.RedactorModel;
import switcher.SceneSwitcher;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserRedactorController implements Initializable {
    @FXML public TableView<User> tableViewUser;
    @FXML public TableColumn<User, String> loginColumn;
    @FXML public TableColumn<User, String> passwordColumn;
    @FXML public TableColumn<User, String> nameColumn;
    @FXML public TableColumn<User, String> surnameColumn;
    @FXML public TableColumn<User, String> lastnameColumn;

    @FXML public AnchorPane rootPane;
    @FXML public TextField login;
    @FXML public TextField password;
    @FXML public TextField name;
    @FXML public TextField surname;
    @FXML public TextField lastname;

    @FXML public Button buttonAddUser;

    private RedactorModel model = RedactorModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        for (int i = model.getUsers().size() - 1; i >= 0; --i) {
            tableViewUser.getItems().add(model.getUsers().get(i));
        }

        final ContextMenu cm = new ContextMenu();
        MenuItem mi1 = new MenuItem("Карты клиента");
        mi1.setOnAction(event -> {
            model.setCurrentUser(tableViewUser.getSelectionModel().getSelectedItem().getLogin());
            SceneSwitcher.toScene(rootPane, "../view/cardRedactor.fxml");
        });
        cm.getItems().add(mi1);

        tableViewUser.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getButton() == MouseButton.SECONDARY) {
                cm.show(tableViewUser, event.getScreenX(), event.getScreenY());
            }
        });
    }

    public void addUser(ActionEvent actionEvent) {
        User newUser = new User(
                login.getText(),
                password.getText(),
                "user",
                name.getText(),
                surname.getText(),
                lastname.getText()
                );
        try {
            BankDAO.insertUser(newUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showMain(ActionEvent actionEvent) {
        SceneSwitcher.toScene(rootPane, "../view/main.fxml");
    }
}
