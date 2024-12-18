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
import to.grindelf.apartmentmanager.bookings.BookingsDaoImpl;
import to.grindelf.apartmentmanager.domain.Booking;
import to.grindelf.apartmentmanager.exceptions.NoSuchUserException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import static to.grindelf.apartmentmanager.utils.ConstantValues.*;

public class BookingsAdminController {
    @FXML
    public Text text;
    @FXML
    public Button goBackButton;
    @FXML
    public TableView<Booking> bookingsTable;
    @FXML
    public TableColumn<Booking, Integer> bookingId;
    @FXML
    public TableColumn<Booking, Integer> apartmentId;
    @FXML
    public TableColumn<Booking, String> bookerName;
    @FXML
    public TableColumn<Booking, String> dateFrom;
    @FXML
    public TableColumn<Booking, String> dateTo;
    @FXML
    public Button addBookingButton;
    @FXML
    public Button deleteBookingButton;
    @FXML
    public Button editBookingButton;
    @FXML
    public Label errorMessage;
    @FXML
    public Label idLabel;
    @FXML
    public TextField idInput;
    @FXML
    public TextField apartmentIdInput;
    @FXML
    public TextField bookerNameInput;
    @FXML
    public DatePicker bookingDateFromPicker;
    @FXML
    public DatePicker bookingDateToPicker;
    @FXML
    public Button confirmButton;
    public Label bookingFromLabel;
    public Label bookingToLabel;

    private Booking selectedBookingForEdit;

    public void initialize() throws SQLException {
        BookingsDaoImpl bookingDao = new BookingsDaoImpl();
        ObservableList<Booking> bookings = FXCollections.observableArrayList(bookingDao.getAll());

        bookingId.setCellValueFactory(new PropertyValueFactory<>("id"));
        apartmentId.setCellValueFactory(new PropertyValueFactory<>("apartmentId"));
        bookerName.setCellValueFactory(new PropertyValueFactory<>("bookerName"));
        dateFrom.setCellValueFactory(new PropertyValueFactory<>("dateFrom"));
        dateTo.setCellValueFactory(new PropertyValueFactory<>("dateTo"));

        bookingsTable.setItems(bookings);
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();

        URL fxmlSignUpLocation = getClass().getResource(ADMIN_VIEW_PATH);
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

    public void showAddBookingForm(ActionEvent actionEvent) {
        // Очистить поля для добавления нового бронирования
        idInput.clear();
        apartmentIdInput.clear();
        bookerNameInput.clear();
        bookingDateFromPicker.setValue(null);
        bookingDateToPicker.setValue(null);

        // Показать поля для ввода
        idLabel.setVisible(false);
        idInput.setVisible(true);
        confirmButton.setVisible(true);
        apartmentIdInput.setVisible(true);
        bookerNameInput.setVisible(true);
        bookingFromLabel.setVisible(true);
        bookingDateFromPicker.setVisible(true);
        bookingToLabel.setVisible(true);
        bookingDateToPicker.setVisible(true);
    }

    public void showEditBookingForm(ActionEvent actionEvent) {
        selectedBookingForEdit = bookingsTable.getSelectionModel().getSelectedItem();

        if (selectedBookingForEdit != null) {
            // Заполнение данных для редактирования
            idLabel.setText("ID: " + selectedBookingForEdit.getId());
            apartmentIdInput.setText(String.valueOf(selectedBookingForEdit.getApartmentId()));
            bookerNameInput.setText(selectedBookingForEdit.getBookerName());
            bookingDateFromPicker.setValue(java.time.LocalDate.parse(selectedBookingForEdit.getDateFrom()));
            bookingDateToPicker.setValue(java.time.LocalDate.parse(selectedBookingForEdit.getDateTo()));

            idLabel.setVisible(true);
            idInput.setVisible(false);
            confirmButton.setVisible(true);
            apartmentIdInput.setVisible(true);
            bookerNameInput.setVisible(true);
            bookingFromLabel.setVisible(true);
            bookingDateFromPicker.setVisible(true);
            bookingToLabel.setVisible(true);
            bookingDateToPicker.setVisible(true);
        } else {
            errorMessage.setText("No booking selected for editing.");
        }
    }

    public void addBooking(ActionEvent actionEvent) {
        String bookerName = bookerNameInput.getText();
        String apartmentId = apartmentIdInput.getText();
        String dateFrom = bookingDateFromPicker.getValue() != null ? bookingDateFromPicker.getValue().toString() : null;
        String dateTo = bookingDateToPicker.getValue() != null ? bookingDateToPicker.getValue().toString() : null;

        if (bookerName.isEmpty() || apartmentId.isEmpty() || dateFrom == null || dateTo == null) {
            errorMessage.setText("All fields are required.");
            return;
        }

        Booking newBooking = new Booking(
                Integer.parseInt(idInput.getText()),
                Integer.parseInt(apartmentId),
                bookerName,
                dateFrom,
                dateTo
        );

        BookingsDaoImpl bookingDao = new BookingsDaoImpl();
        try {
            bookingDao.save(newBooking);
            bookingsTable.getItems().add(newBooking);
        } catch (Exception e) {
            errorMessage.setText("Failed to save booking.");
        }

        hideInputFields();
    }

    public void updateBooking(ActionEvent actionEvent) {
        if (selectedBookingForEdit != null) {
            String apartmentId = apartmentIdInput.getText();
            String bookerName = bookerNameInput.getText();
            String dateFrom = bookingDateFromPicker.getValue() != null ? bookingDateFromPicker.getValue().toString() : null;
            String dateTo = bookingDateToPicker.getValue() != null ? bookingDateToPicker.getValue().toString() : null;

            if (apartmentId.isEmpty() || bookerName.isEmpty() || dateFrom == null || dateTo == null) {
                errorMessage.setText("All fields are required.");
                return;
            }

            selectedBookingForEdit = new Booking(selectedBookingForEdit.getId(),
                    Integer.parseInt(apartmentId),
                    bookerName,
                    dateFrom,
                    dateTo);

            BookingsDaoImpl bookingDao = new BookingsDaoImpl();
            try {
                bookingDao.update(selectedBookingForEdit);
                bookingsTable.refresh();
            } catch (NoSuchUserException e) {
                errorMessage.setText("Booking not found: " + selectedBookingForEdit.getId());
            }

            hideInputFields();
            selectedBookingForEdit = null;
        }
    }

    public void deleteBooking(ActionEvent actionEvent) {
        Booking selectedBooking = bookingsTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            BookingsDaoImpl bookingDao = new BookingsDaoImpl();
            try {
                bookingDao.delete(selectedBooking.getId());
                bookingsTable.getItems().remove(selectedBooking);
            } catch (NoSuchUserException e) {
                errorMessage.setText("Booking not found: " + selectedBooking.getId());
            }
        } else {
            errorMessage.setText("No booking selected.");
        }
    }

    private void hideInputFields() {
        idLabel.setVisible(false);
        idInput.setVisible(false);
        confirmButton.setVisible(false);
        apartmentIdInput.setVisible(false);
        bookerNameInput.setVisible(false);
        bookingFromLabel.setVisible(false);
        bookingDateFromPicker.setVisible(false);
        bookingToLabel.setVisible(false);
        bookingDateToPicker.setVisible(false);
    }

    public void confirmAction(ActionEvent actionEvent) {
        if (selectedBookingForEdit != null) {
            updateBooking(actionEvent);
        } else {
            addBooking(actionEvent);
        }
    }
}
