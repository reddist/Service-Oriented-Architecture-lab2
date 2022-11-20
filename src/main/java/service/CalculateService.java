package service;

import dto.Color;
import dto.Country;
import dto.Person;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;

// http: 35867 - service1
// https: 35868 - service1
// management-http: 35998 - serivce1
// management-https: 35999 - service1

@ApplicationScoped
public class CalculateService {
    Client client = ClientBuilder.newClient();
    String api = "http://localhost:8080/persons";

    public Person[] getPersonsFromMainService() {
        return client
                .target(api)
                .request(MediaType.APPLICATION_XML)
                .get(Person[].class);
    }

    public Integer calculateEyeColorPercentage (Color color) {
        Person[] persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + Arrays.toString(persons));

        long count = Arrays
                .stream(persons)
                .filter((person) -> person.getEyeColor().equals(color))
                .count();

        return (int)Math.floor(count * 100.00 / persons.length);
    }

    public Integer calculateEyeColorAndNationalityPercentage(Color color, Country nationality) {
        Person[] persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + Arrays.toString(persons));

        long count = Arrays
                .stream(persons)
                .filter((person) -> person.getNationality().equals(nationality))
                .filter((person) -> person.getEyeColor().equals(color))
                .count();

        return (int)Math.floor(count * 100.00 / persons.length);
    }
}