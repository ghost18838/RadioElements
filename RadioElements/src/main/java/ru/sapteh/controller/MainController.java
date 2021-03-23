package ru.sapteh.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.RadioElement;
import ru.sapteh.service.RadioElementService;

import java.io.IOException;


public class MainController {
    @FXML
    public Button buttonDelete;
    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final ObservableList<RadioElement> radioElements = FXCollections.observableArrayList();


    @FXML
    private TableView<RadioElement> tableView;

    @FXML
    private TableColumn<RadioElement, Integer> idColumn;

    @FXML
    private TableColumn<RadioElement, String> nameColumn;

    @FXML
    private TableColumn<RadioElement, String> manufacturerColumn;

    @FXML
    private TableColumn<RadioElement, String> descriptionColumn;

    @FXML
    private TableColumn<RadioElement, Integer> quantityColumn;

    @FXML
    private TableColumn<RadioElement, Integer> costColumn;


    @FXML
    void buttonCreate(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/view/create.fxml"));
        stage.setTitle("Create table");
        stage.setScene(new Scene(root));
        stage.show();
        buttonDelete.getScene().getWindow().hide();


    }

    @FXML
    public void initialize(){
        initData();

        tableView.setItems(radioElements);
        idColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getId()));
        nameColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getName()));
        manufacturerColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getManufacturer()));
        descriptionColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getDescription()));
        quantityColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getQuantity()));
        costColumn.setCellValueFactory(c ->
                new SimpleObjectProperty<>(c.getValue().getCost()));


    }

    private void initData(){
        DAO<RadioElement,Integer> radioElementIntegerDAO = new RadioElementService(factory);
        radioElements.addAll(radioElementIntegerDAO.findByAll());
    }

}
