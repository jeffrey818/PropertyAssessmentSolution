package com.PA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CsvPropertyAssessmentDAO implements PropertyAssessmentDAO {

    List<PropertyAssessment> paList;

    public CsvPropertyAssessmentDAO(){

        paList = new ArrayList<>();
        String fileName = "Property_Assessment_Data_2021.csv";

        // read the csv file
        Path FilePath = Paths.get(fileName); //get the path of the file
        try {
            BufferedReader br = Files.newBufferedReader(FilePath);
            //read the description line first
            String readLine = br.readLine();
            //read through the file line by line
            readLine = br.readLine();
            while (readLine != null) {
                readLine += ", "; // to add one more item to the end of the str, so that array length will be correct
                String[] data = readLine.split(",");
                PropertyAssessment makePropAssess;
                makePropAssess = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                paList.add(makePropAssess);
                //continue loop, read next line
                readLine = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("error, no data file found");
        }
    }

    /** methods ****************************************************************************************************/
    // to make a new propertyAssessment
    public PropertyAssessment makeNewPA(String acctNo, String suite, String houseNum, String stName, String garage,
                                        String nId, String nName, String ward, String value, String lat, String lgt,
                                        String pointLoc,
                                        String assessClass1Percent, String assessClass2Percent, String assessClass3Percent,
                                        String assessClass1, String assessClass2, String assessClass3)
    {
        int x = 0;
        //in case of that some nId does not have any content in the str[], "" cannot be read.
        if (nId.length() > 0) {
            x = Integer.parseInt(nId);
        }
        if (acctNo.length() == 0) acctNo = "0";
        if (suite.length() == 0) suite = "0";
        if (houseNum.length() == 0) houseNum = "0";
        if (stName.length() == 0) stName = " ";
        if (garage.length() == 0) garage = " ";
        if (nName.length() == 0) nName = " ";
        if (ward.length() == 0) ward = " ";
        if (value.length() == 0) value = "0";
        // skip this procedure for location, because every account has a location (only for this document)
        if (assessClass1Percent.length() == 0) assessClass1Percent = "0";
        if (assessClass2Percent.length() == 0) assessClass2Percent = "0";
        if (assessClass3Percent.length() == 0) assessClass3Percent = "0";

        /** if try to add "" to the array, it will cause error, so make every "" to " "*/
        if (assessClass1.length() == 0) assessClass1 = " ";
        if (assessClass2.length() == 0) assessClass2 = " ";
        if (assessClass3.length() == 0) assessClass3 = " ";

        PropertyAssessment propertyAssess = new PropertyAssessment(Integer.parseInt(acctNo),
                new Address(suite, houseNum, stName),
                garage,
                new Neighborhood(x, nName, ward),
                Integer.parseInt(value),
                new Location(Double.parseDouble(lat), Double.parseDouble(lgt), pointLoc),
                Integer.parseInt(assessClass1Percent), Integer.parseInt(assessClass2Percent),
                Integer.parseInt(assessClass3Percent), assessClass1, assessClass2, assessClass3,
                new ClassInfo(assessClass1, assessClass2, assessClass3,Integer.parseInt(assessClass1Percent), Integer.parseInt(assessClass2Percent),Integer.parseInt(assessClass3Percent))
        );
        return propertyAssess;
    }

    public void sortList() {
        Collections.sort(this.paList);
    }

    public PropertyAssessment getProperty(int i){
        return this.paList.get(i);
    }

    public int getSize(){
        return this.paList.size();
    }

    // to get the account by account number
    public PropertyAssessment getByAccountNumber(int accountNumber) {
        this.sortList();

        int i;
        for (i = 0; i < this.getSize(); i++){
            if ((this.getProperty(i).getAcctNo()) == accountNumber) {
                return this.getProperty(i);
            }
        }
        return null;
    }

    // to get the list of propertyAssessment by nbhd id
    public List<PropertyAssessment> getByNeighbourhood(String neighbourhood) {
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {
            if ((this.getProperty(i).getNeighborhood().getName().toUpperCase()).equals(neighbourhood.toUpperCase())) {
                methodList.add(this.getProperty(i));
            }
        }
        return methodList;
    }

    public List<PropertyAssessment> getByAssessmentClass(String assessmentClass) {
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {
            if (
                    (this.getProperty(i).getAssessClass1().toUpperCase()).equals(assessmentClass.toUpperCase()) ||
                    (this.getProperty(i).getAssessClass2().toUpperCase()).equals(assessmentClass.toUpperCase()) ||
                    (this.getProperty(i).getAssessClass3().toUpperCase()).equals(assessmentClass.toUpperCase())
            ) {
                methodList.add(this.getProperty(i));
            }
        }
        return  methodList;
    }

    public List<PropertyAssessment> getAll() {
        this.sortList();
        return this.paList;

    }

    public List<PropertyAssessment> getByAddress(String address) {
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {

            if (
                    (this.getProperty(i).getAddress().getStreetName().toUpperCase()).contains(address.toUpperCase())
            ) {
                methodList.add(this.getProperty(i));
            }

        }
        return methodList;
    }

    public List<PropertyAssessment> getMinAbove(int min){
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {

            if (
                    (this.getProperty(i).getAssessedValue() >= min)
            ) {
                methodList.add(this.getProperty(i));
            }

        }
        return methodList;
    }

    public List<PropertyAssessment> getMaxBelow(int max){
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {

            if (
                    (this.getProperty(i).getAssessedValue() <= max)
            ) {
                methodList.add(this.getProperty(i));
            }

        }
        return methodList;
    }

    public List<PropertyAssessment> getBetween(int min, int max) throws UnsupportedEncodingException {
        this.sortList();
        List<PropertyAssessment> methodList = new ArrayList<>();

        for (int i = 0; i < this.getSize(); i++) {

            if (
                    ( (this.getProperty(i).getAssessedValue() <= max) &&
                            (this.getProperty(i).getAssessedValue() >= min)
                    )
            ) {
                methodList.add(this.getProperty(i));
            }

        }
        return methodList;
    }


}
