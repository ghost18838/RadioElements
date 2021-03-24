package ru.sapteh.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.sapteh.dao.DAO;
import ru.sapteh.model.RadioElement;
import ru.sapteh.service.RadioElementService;

public class UpdateMainController {

    @FXML
    private TextField nameTextFiled;

    @FXML
    private TextField quantityTextField;

    @FXML
    private TextField descriptionTextField;


    public Label idLbl;


    public Button buttonUpdate;

    public void setData(RadioElement radioElement){
        if (radioElement != null){
            idLbl.setText(String.valueOf(radioElement.getId()));
            nameTextFiled.setText(radioElement.getName());
            quantityTextField.setText(String.valueOf(radioElement.getQuantity()));
            descriptionTextField.setText(radioElement.getDescription());
        }else {
            idLbl.setText("");
            nameTextFiled.setText("");
            quantityTextField.setText("");
            descriptionTextField.setText("");
        }
    }

    public RadioElement getData(){
        RadioElement radioElement = new RadioElement();
        radioElement.setName(nameTextFiled.getText());
        radioElement.setQuantity(Integer.parseInt(quantityTextField.getText()));
        radioElement.setDescription(descriptionTextField.getText());
        return radioElement;
    }

    @FXML
    void onActionUpdate(ActionEvent event) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        DAO<RadioElement, Integer> radioElementDaoImpl = new RadioElementService(factory);
        radioElementDaoImpl.update(getData());
        factory.close();

    }

}
