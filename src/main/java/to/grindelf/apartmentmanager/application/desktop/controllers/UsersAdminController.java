package to.grindelf.apartmentmanager.application.desktop.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import to.grindelf.apartmentmanager.auth.UserDaoImpl;
import to.grindelf.apartmentmanager.domain.User;
import to.grindelf.apartmentmanager.domain.UserStatus;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;
import to.grindelf.apartmentmanager.exceptions.UserAlreadyExistsException;

import java.io.IOException;
import java.net.URL;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class UsersAdminController {
    @FXML
    public Text text;
    @FXML
    public Button goBackButton;
    @FXML
    public TableView<User> usersTable;
    @FXML
    public TableColumn<User, String> userName;
    @FXML
    public TableColumn<User, String> password;
    @FXML
    public TableColumn<User, UserStatus> userStatus;
    @FXML
    public Button deleteButton;
    @FXML
    public Button addUserButton;
    @FXML
    public ComboBox<String> statusComboBox;
    @FXML
    public Label nameLabel;
    @FXML
    public Label errorMessage;
    @FXML
    private TextField nameInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Button confirmButton;
    @FXML
    private Button editUserButton;

    private User selectedUserForEdit;

    public void initialize() throws IOException {
        UserDaoImpl userDao = new UserDaoImpl();
        ObservableList<User> users = FXCollections.observableArrayList(userDao.getAll());

        userName.setCellValueFactory(new PropertyValueFactory<>("name"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        userStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        usersTable.setItems(users);

    }

    public void getBackToWhereYouOnceBelong(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(
                ADMIN_VIEW_PATH
        );
        if (fxmlSignUpLocation == null) {
            System.err.println(FXML_NO_FOUND_ERROR_MESSAGE);
            return;
        }
        FXMLLoader loader = new FXMLLoader(fxmlSignUpLocation);

        Parent root = loader.load();
        Stage newStage = new Stage();

        newStage.setTitle(ADMIN_WINDOW_TITLE);
        newStage.setScene(new Scene(root));
        newStage.setWidth(WINDOW_WIDTH);
        newStage.setHeight(WINDOW_HEIGHT);
        newStage.show();
    }

    public void showAddUserForm(ActionEvent actionEvent) {
        nameInput.setText("");
        passwordInput.setText("");
        statusComboBox.setValue(null);

        nameLabel.setVisible(false);
        nameInput.setVisible(true);
        passwordInput.setVisible(true);
        confirmButton.setVisible(true);
        statusComboBox.setVisible(true);
    }


    public void showEditUserForm(ActionEvent actionEvent) {
        selectedUserForEdit = usersTable.getSelectionModel().getSelectedItem();

        if (selectedUserForEdit != null) {
            // Устанавливаем имя в Label (оно не доступно для редактирования)
            nameLabel.setText("Name: " + selectedUserForEdit.getName());

            // Заполняем другие поля для редактирования
            passwordInput.setText(selectedUserForEdit.getPassword());
            statusComboBox.setValue(selectedUserForEdit.getStatus().toString());

            nameLabel.setVisible(true);
            nameInput.setVisible(false);
            passwordInput.setVisible(true);
            confirmButton.setVisible(true);
            statusComboBox.setVisible(true);
        } else {
            errorMessage.setText("No user selected for editing.");
        }
    }

    public void addUser(ActionEvent actionEvent) {
        String name = nameInput.getText();
        String password = passwordInput.getText();
        String statusString = statusComboBox.getValue();

        if (name.isEmpty() || password.isEmpty() || statusString == null) {
            errorMessage.setText("Name, password, and status are required.");
            return;
        }

        // Создаем нового пользователя
        User newUser = new User(name, password, statusString);
        UserDaoImpl userDao = new UserDaoImpl();
        try {
            userDao.save(newUser); // Сохраняем в базе данных
            usersTable.getItems().add(newUser); // Добавляем в таблицу
        } catch (UserAlreadyExistsException e) {
            errorMessage.setText("User already exists: " + name);
        }

        // Скрываем поля после добавления
        hideInputFields();
    }


    public void updateUser(ActionEvent actionEvent) {
        String password = passwordInput.getText();
        String statusString = statusComboBox.getValue();

        if (password.isEmpty() || statusString == null) {
            errorMessage.setText("Password and status are required.");
            return;
        }

        if (selectedUserForEdit != null) {
            // Сохраняем текущее имя пользователя, чтобы оно не менялось
            String name = selectedUserForEdit.getName();

            // Обновляем только пароль и статус
            selectedUserForEdit.setPassword(password);
            selectedUserForEdit.setStatus(UserStatus.valueOf(statusString.toUpperCase()));

            UserDaoImpl userDao = new UserDaoImpl();
            try {
                userDao.update(selectedUserForEdit);
                usersTable.refresh();
            } catch (NoSuchUserException e) {
                errorMessage.setText("User not found: " + name);
            }
        } else {
            errorMessage.setText("No user selected for editing.");
        }

        hideInputFields();
        selectedUserForEdit = null;
    }

    private void hideInputFields() {
        nameInput.setVisible(false);
        passwordInput.setVisible(false);
        confirmButton.setVisible(false);
        statusComboBox.setVisible(false);
    }

    public void confirmAction(ActionEvent actionEvent) {
        if (selectedUserForEdit != null) {
            updateUser(actionEvent);
        } else {
            addUser(actionEvent);
        }
    }

    public void deleteUser(ActionEvent actionEvent) {
        User selectedUser = usersTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            UserDaoImpl userDao = new UserDaoImpl();
            try {
                userDao.delete(selectedUser.getName());
                usersTable.getItems().remove(selectedUser);
            } catch (NoSuchUserException e) {
                errorMessage.setText("User not found: " + selectedUser.getName());
            }
        } else {
            errorMessage.setText("No user selected.");
        }
    }
}
