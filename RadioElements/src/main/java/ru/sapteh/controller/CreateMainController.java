package ru.sapteh.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.RadioElement;
import ru.sapteh.service.RadioElementService;

import java.io.IOException;

public class CreateMainController {

    ObservableList<RadioElement> radioElements = FXCollections.observableArrayList();


    @FXML
    private TextField filedId;

    @FXML
    private TextField fieldName;

    @FXML
    private TextField fieldManufacturer;

    @FXML
    private TextField fieldDescription;

    @FXML
    private TextField fieldQuantity;

    @FXML
    private TextField fieldCost;

    @FXML
    private Label label;

    @FXML
    private Button buttonOk;

    @FXML
    public Button buttonEx;

    SessionFactory factory = new Configuration().configure().buildSessionFactory();

    @FXML
    void buttonOk(ActionEvent event) {
        RadioElement radioElement = new RadioElement(Integer.parseInt(filedId.getText()),
                fieldName.getText(),
                fieldManufacturer.getText(),
                fieldDescription.getText(),
                Integer.parseInt(fieldQuantity.getText()),
                Integer.parseInt(fieldCost.getText()));

        if (radioElement != null) {
            label.setText("Данные успешно добавлены");
        }else {
            label.setText("Данные не добавлены");
        }
        DAO<RadioElement,Integer> radioElementIntegerDAO = new RadioElementService(factory);
        radioElementIntegerDAO.create(radioElement);


    }
    @FXML
    void buttonExit(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        stage.setTitle("Radioelements");
        stage.setScene(new Scene(root));
        stage.show();

        buttonEx.getScene().getWindow().hide();
    }



}
