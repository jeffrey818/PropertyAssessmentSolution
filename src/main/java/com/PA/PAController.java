package com.PA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class PAController implements Initializable {
    @FXML
    private TableView myTable;

    @FXML
    private TableColumn account;

    @FXML
    private TableColumn address;

    @FXML
    private TableColumn value;

    @FXML
    private TableColumn assessClass;

    @FXML
    private TableColumn nbhd;

    @FXML
    private TableColumn location;

    @FXML
    private ComboBox dataSrc;

    @FXML
    private TextField accountText;

    @FXML
    private TextField addressText;

    @FXML
    private TextField nbhdText;

    @FXML
    private TextField minValue;

    @FXML
    private TextField maxValue;

    @FXML
    private ComboBox selectAssessClass;

    @FXML
    private Button readDataButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button resetButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        dataSrc.getItems().addAll("CSV File", "Edmonton's Open Data Portal");

        /**
         Format column type using currency type*/
        value.setCellFactory(column -> new TableCell<PropertyAssessment, Integer>() {
                    @Override
                    protected void updateItem(Integer value, boolean empty) {
                        super.updateItem(value, empty);
                        currencyFormat.setMaximumFractionDigits(0);
                        setText(empty ? "" : currencyFormat.format(value));
                    }
                });

        final ObservableList<PropertyAssessment> propertyList = FXCollections.observableArrayList();

        selectAssessClass.getItems().addAll("RESIDENTIAL", "COMMERCIAL", "OTHER RESIDENTIAL", "FARMLAND",
                "NONRES MUNICIPAL/RES EDUCATION");

        readDataButton.setOnAction(actionEvent -> {

            if (dataSrc.getValue() == "CSV File") {
                PropertyAssessmentDAO dao = new CsvPropertyAssessmentDAO();

                for (PropertyAssessment pa : dao.getAll() ) propertyList.add(pa);

                account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                address.setCellValueFactory((new PropertyValueFactory<>("address")));
                value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                location.setCellValueFactory((new PropertyValueFactory<>("location")));

                /** search button function*/
                searchButton.setOnAction((ActionEvent actionEvent1) -> {

                    myTable.getItems().clear(); // clear all existing items in the table

                    // get the usr input
                    String accountNum = accountText.getText();
                    String addr = addressText.getText();
                    String nb = nbhdText.getText();
                    String min = minValue.getText();
                    String max = maxValue.getText();

                    if (accountNum != "") {

                        PropertyAssessment pa = dao.getByAccountNumber(Integer.parseInt(accountNum));
                        propertyList.add(pa);

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                    if (nb != "") {

                        for (PropertyAssessment pa : dao.getByNeighbourhood(nb)) {
                            propertyList.add(pa);
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                    if (addr != "") {
                        for (PropertyAssessment pa : dao.getByAddress(addr)) {
                            propertyList.add(pa);
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);

                    }

                    if (selectAssessClass.getValue() != null) {

                        if (selectAssessClass.getValue() == "RESIDENTIAL") {
                            for (PropertyAssessment pa : dao.getByAssessmentClass("RESIDENTIAL")) {
                                propertyList.add(pa);
                            }
                        }

                        if (selectAssessClass.getValue() == "COMMERCIAL") {
                            for (PropertyAssessment pa : dao.getByAssessmentClass("COMMERCIAL")) {
                                propertyList.add(pa);
                            }
                        }

                        if (selectAssessClass.getValue() == "OTHER RESIDENTIAL") {
                            for (PropertyAssessment pa : dao.getByAssessmentClass("OTHER RESIDENTIAL")) {
                                propertyList.add(pa);
                            }
                        }

                        if (selectAssessClass.getValue() == "FARMLAND") {
                            for (PropertyAssessment pa : dao.getByAssessmentClass("FARMLAND")) {
                                propertyList.add(pa);
                            }
                        }

                        if (selectAssessClass.getValue() == "NONRES MUNICIPAL/RES EDUCATION") {
                            for (PropertyAssessment pa : dao.getByAssessmentClass("NONRES MUNICIPAL/RES EDUCATION")) {
                                propertyList.add(pa);
                            }
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                    if (min != "" && max == "") {
                        try {
                            for (PropertyAssessment pa : dao.getMinAbove(Integer.parseInt(min))) {
                                propertyList.add(pa);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                    if (max != "" && min == "") {
                        try {
                            for (PropertyAssessment pa : dao.getMaxBelow(Integer.parseInt(max))) {
                                propertyList.add(pa);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                    if (max != "" && min != "") {
                        try {
                            for (PropertyAssessment pa : dao.getBetween(Integer.parseInt(min), Integer.parseInt(max))) {
                                propertyList.add(pa);
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                        address.setCellValueFactory((new PropertyValueFactory<>("address")));
                        value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                        assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                        nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                        location.setCellValueFactory((new PropertyValueFactory<>("location")));

                        myTable.setItems(propertyList);
                    }

                });

                /** reset button function*/
                resetButton.setOnAction((ActionEvent actionEvent2) -> {
                    myTable.getItems().clear();
                    accountText.clear();
                    addressText.clear();
                    nbhdText.clear();
                    minValue.clear();
                    maxValue.clear();
                    selectAssessClass.setValue(null);
                });

                myTable.setItems(propertyList);

            } else {
                if (dataSrc.getValue() == "Edmonton's Open Data Portal") {
                    PropertyAssessmentDAO dao = new ApiPropertyAssessmentDAO();

                    for (PropertyAssessment pa : dao.getAll() ) propertyList.add(pa);

                    account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                    address.setCellValueFactory((new PropertyValueFactory<>("address")));
                    value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                    assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                    nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                    location.setCellValueFactory((new PropertyValueFactory<>("location")));

                    /** search button function*/
                    searchButton.setOnAction((ActionEvent actionEvent1) -> {

                        myTable.getItems().clear(); // clear all existing items in the table

                        // get the usr input
                        String accountNum = accountText.getText();
                        String addr = addressText.getText();
                        String nb = nbhdText.getText();
                        String min = minValue.getText();
                        String max = maxValue.getText();

                        if (accountNum != "") {

                            PropertyAssessment pa = dao.getByAccountNumber(Integer.parseInt(accountNum));
                            propertyList.add(pa);

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                        if (nb != "") {

                            for (PropertyAssessment pa : dao.getByNeighbourhood(nb)) {
                                propertyList.add(pa);
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                        if (addr != "") {
                            for (PropertyAssessment pa : dao.getByAddress(addr)) {
                                propertyList.add(pa);
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);

                        }

                        if (selectAssessClass.getValue() != null) {

                            if (selectAssessClass.getValue() == "RESIDENTIAL") {
                                for (PropertyAssessment pa : dao.getByAssessmentClass("RESIDENTIAL")) {
                                    propertyList.add(pa);
                                }
                            }

                            if (selectAssessClass.getValue() == "COMMERCIAL") {
                                for (PropertyAssessment pa : dao.getByAssessmentClass("COMMERCIAL")) {
                                    propertyList.add(pa);
                                }
                            }

                            if (selectAssessClass.getValue() == "OTHER RESIDENTIAL") {
                                for (PropertyAssessment pa : dao.getByAssessmentClass("OTHER RESIDENTIAL")) {
                                    propertyList.add(pa);
                                }
                            }

                            if (selectAssessClass.getValue() == "FARMLAND") {
                                for (PropertyAssessment pa : dao.getByAssessmentClass("FARMLAND")) {
                                    propertyList.add(pa);
                                }
                            }

                            if (selectAssessClass.getValue() == "NONRES MUNICIPAL/RES EDUCATION") {
                                for (PropertyAssessment pa : dao.getByAssessmentClass("NONRES MUNICIPAL/RES EDUCATION")) {
                                    propertyList.add(pa);
                                }
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                        if (min != "" && max == "") {
                            try {
                                for (PropertyAssessment pa : dao.getMinAbove(Integer.parseInt(min))) {
                                    propertyList.add(pa);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                        if (max != "" && min == "") {
                            try {
                                for (PropertyAssessment pa : dao.getMaxBelow(Integer.parseInt(max))) {
                                    propertyList.add(pa);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                        if (max != "" && min != "") {
                            try {
                                for (PropertyAssessment pa : dao.getBetween(Integer.parseInt(min), Integer.parseInt(max))) {
                                    propertyList.add(pa);
                                }
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            }

                            account.setCellValueFactory((new PropertyValueFactory<>("acctNo")));
                            address.setCellValueFactory((new PropertyValueFactory<>("address")));
                            value.setCellValueFactory((new PropertyValueFactory<>("assessedValue")));
                            assessClass.setCellValueFactory((new PropertyValueFactory<>("classInfo")));
                            nbhd.setCellValueFactory((new PropertyValueFactory<>("neighborhood")));
                            location.setCellValueFactory((new PropertyValueFactory<>("location")));

                            myTable.setItems(propertyList);
                        }

                    });

                    /** reset button function*/
                    resetButton.setOnAction((ActionEvent actionEvent2) -> {
                        myTable.getItems().clear();
                        accountText.clear();
                        addressText.clear();
                        nbhdText.clear();
                        minValue.clear();
                        maxValue.clear();
                        selectAssessClass.setValue(null);
                    });

                    myTable.setItems(propertyList);
                }


            }








        });


    }
}