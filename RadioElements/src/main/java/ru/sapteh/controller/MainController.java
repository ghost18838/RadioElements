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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.RadioElement;
import ru.sapteh.service.RadioElementService;

import java.io.IOException;


public class MainController {

    SessionFactory factory = new Configuration().configure().buildSessionFactory();
    private final ObservableList<RadioElement> radioElements = FXCollections.observableArrayList();
    private AnchorPane anchorPane;
    private RadioElement selectedItem;

    @FXML
    public Button buttonCr;

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
        buttonCr.getScene().getWindow().hide();


    }

    @FXML
    void buttonDelete(ActionEvent event) {
        RadioElement selectedItem = tableView.getSelectionModel().getSelectedItem();
        tableView.getItems().remove(selectedItem);
        DAO<RadioElement, Integer> radioElementIntegerDAO = new RadioElementService(factory);
        radioElementIntegerDAO.delete(selectedItem);
    }

    @FXML
    void buttonUpdate(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/update.fxml"));
        anchorPane = loader.load();
        UpdateMainController updateMainController = loader.getController();
        stage.setTitle("Update table");
        stage.setScene(new Scene(anchorPane));
        stage.show();
        updateMainController.setData(selectedItem);

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

        tableView.getSelectionModel().selectedItemProperty().addListener((obj,oldValue,newValue) -> {
            selectedItem = newValue;
        });

    }

    private void initData(){
        DAO<RadioElement,Integer> radioElementIntegerDAO = new RadioElementService(factory);
        radioElements.addAll(radioElementIntegerDAO.findByAll());
    }

}
