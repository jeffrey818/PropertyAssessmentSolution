package com.PA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ApiPropertyAssessmentDAO implements PropertyAssessmentDAO {

    List<PropertyAssessment> paList;
    String endpoint = "https://data.edmonton.ca/resource/q7d6-ambg.csv";

    public ApiPropertyAssessmentDAO(){
        paList = new ArrayList<>();
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

    public PropertyAssessment getByAccountNumber(int accountNumber) {

        String query = endpoint + "?account_number=" + accountNumber;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                return makePA;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    // to get the list of propertyAssessment by nbhd id
    public List<PropertyAssessment> getByNeighbourhood(String neighbourhood) {
        List<PropertyAssessment> methodList = new ArrayList<>();

        String query = endpoint + "?neighbourhood=" + neighbourhood.toUpperCase();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PropertyAssessment> getByAssessmentClass(String assessmentClass) {
        List<PropertyAssessment> methodList = new ArrayList<>();
        String query = endpoint +
                "?mill_class_1=%27" + assessmentClass + "%27%20OR%20mill_class_2=%27" + assessmentClass +
                "%27%20OR%20mill_class_3=%27" + assessmentClass + "%27";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<PropertyAssessment> getAll() {
        List<PropertyAssessment> methodList = new ArrayList<>();

        String query = endpoint;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();
            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;

    }

    public List<PropertyAssessment> getByAddress(String address) {
        List<PropertyAssessment> methodList = new ArrayList<>();

        address = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String query = endpoint + "?$where=street_name%20like%20" + "%27" + address + "%27";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public List<PropertyAssessment> getMinAbove(int min) throws UnsupportedEncodingException {
        List<PropertyAssessment> methodList = new ArrayList<>();

        String query = endpoint + "?$where=assessed_value";
        String condition = ">" + min;
        condition = URLEncoder.encode(condition, "UTF-8"); // encode it with UTF-8 to safely send the request
        query += condition;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PropertyAssessment> getMaxBelow(int max) throws UnsupportedEncodingException {
        List<PropertyAssessment> methodList = new ArrayList<>();

        String query = endpoint + "?$where=assessed_value";
        String condition = "<" + max;
        condition = URLEncoder.encode(condition, "UTF-8"); // encode it with UTF-8 to safely send the request
        query += condition;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<PropertyAssessment> getBetween(int min, int max) throws UnsupportedEncodingException {
        List<PropertyAssessment> methodList = new ArrayList<>();

        String query = endpoint + "?$where=assessed_value";
        String condition = ">" + min + " AND assessed_value<" + max;
        condition = URLEncoder.encode(condition, "UTF-8"); // encode it with UTF-8 to safely send the request
        query += condition;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BufferedReader br = new BufferedReader(new StringReader(response.body()));
            String readLine = br.readLine(); // read the first line first
            //read through the file line by line
            readLine = br.readLine(); // read the second line
            int count = 0;
            while (readLine != null ) {
                readLine += ", ";
                readLine = readLine.replaceAll("\"","");
                String[] data = readLine.split(",");
                PropertyAssessment makePA = makeNewPA(data[0], data[1], data[2], data[3], data[4],
                        data[5], data[6], data[7], data[8], data[9], data[10], data[11],
                        data[12], data[13], data[14], data[15], data[16], data[17]);
                methodList.add(makePA);
                readLine = br.readLine();

            }
            return methodList;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


}
