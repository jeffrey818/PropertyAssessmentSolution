package com.PA;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** this java program is for me to check if the dao is working or not*/

public class demo {
    public static void main(String[] args) {
//        PropertyAssessmentDAO dao = new CsvPropertyAssessmentDAO();
//
//        PropertyAssessment pa = dao.getByAccountNumber(1103530);
//        List<PropertyAssessment> paByNeighbourhood = new ArrayList<>();
//        paByNeighbourhood = dao.getByNeighbourhood("Granville");
//        System.out.println(paByNeighbourhood);

//        List<PropertyAssessment> paByClass = new ArrayList<>();
//        paByClass = dao.getByAssessmentClass("residential");
//        System.out.println(paByClass);

//        List<PropertyAssessment> allPA = new ArrayList<>();
//        allPA = dao.getAll();
//        System.out.println(allPA);
        System.out.print("Enter account number");
        Scanner scanner = new Scanner(System.in);
        int accountNumber = scanner.nextInt();

        String endpoint = "https://data.edmonton.ca/resource/q7d6-ambg.csv";
        String query = endpoint + "?account_number=" + accountNumber;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }
}
