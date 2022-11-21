package service;

import dto.*;

import javax.enterprise.context.ApplicationScoped;
//import javax.ws.rs.client.Client;
//import javax.ws.rs.client.ClientBuilder;
//import javax.ws.rs.core.MediaType;

import java.time.ZonedDateTime;
import java.util.Arrays;

// http: 35867 - service1
// https: 35868 - service1
// management-http: 35998 - serivce1
// management-https: 35999 - service1

@ApplicationScoped
public class DemographyService {
//    Client client = ClientBuilder.newClient();
//    String api = "http://localhost:8080/persons";

    public Person[] getPersonsFromMainService() throws NoPersonsException {
//        throw new NoPersonsException();
        return new Person[]{
                new Person(1, "Alex", new Coordinates(1L, 2L), ZonedDateTime.now(), 180.0, "4021 856033", Color.BLUE, Country.VATICAN, new Location(1L, 2, 3)),
                new Person(4, "Alex", new Coordinates(1L, 2L), ZonedDateTime.now(), 180.0, "4021 856033", Color.BLUE, Country.VATICAN, new Location(1L, 2, 3)),
                new Person(2, "Fara", new Coordinates(2L, 3L), ZonedDateTime.now(), 177.0, "1122 293746", Color.BROWN, Country.JAPAN, new Location(10L, 105, 6)),
                new Person(3, "Darina", new Coordinates(4L, 5L), ZonedDateTime.now(), 173.0, "2734 297477", Color.BLUE, Country.JAPAN, new Location(1L, 2, 2)),
        };

//        Person[] persons = client
//                .target(api)
//                .request(MediaType.APPLICATION_XML)
//                .get(Person[].class);
//        if (persons.length == 0) {
//            throw new NoPersonsException();
//        }
//        return persons;
    }

    public Integer calculateEyeColorPercentage(Color color) throws NoPersonsException {
        Person[] persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + Arrays.toString(persons));

        long count = Arrays
                .stream(persons)
                .filter((person) -> person.getEyeColor().equals(color))
                .count();

        return (int) Math.floor(count * 100.00 / persons.length);
    }

    public Integer calculateEyeColorAndNationalityPercentage(Country nationality, Color color) throws NoPersonsException {
        Person[] persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + Arrays.toString(persons));

        long count = Arrays
                .stream(persons)
                .filter((person) -> person.getNationality().equals(nationality))
                .filter((person) -> person.getEyeColor().equals(color))
                .count();

        return (int) Math.floor(count * 100.00 / persons.length);
    }
}