package service;

import model.Color;
import model.Country;
import model.Person;
import model.response.GetPersonsResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

// https: 26506 - Spring service
// https: 26505 - JAX-RS service

@ApplicationScoped
public class DemographyService {
    Client client;
    KeyStore keyStore;
    String api = "https://localhost:26506/lab2springmvc/persons?count=10000";

    public DemographyService() throws CertificateException, IOException, NoSuchAlgorithmException, KeyStoreException {
        this.keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        this.keyStore.load(
                new FileInputStream(System.getProperty("ssl_cert")),
                System.getProperty("ssl_pass").toCharArray()
        );
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        this.client = ClientBuilder
                .newBuilder()
                .trustStore(this.keyStore)
                .hostnameVerifier(hostnameVerifier)
                .build();
    }


    public List<Person> getPersonsFromMainService() throws NoPersonsException {
        GetPersonsResponse getPersonsResponse = client
                .target(api)
                .request(MediaType.APPLICATION_XML)
                .get(GetPersonsResponse.class);
        List<Person> persons = getPersonsResponse.getPersons();
        if (persons.size() == 0) {
            throw new NoPersonsException();
        }
        return persons;
    }

    public Integer calculateEyeColorPercentage(Color color) throws NoPersonsException {
        List<Person> persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + persons.toString());

        long count = persons
                .stream()
                .filter((person) -> color.equals(person.getEyeColor()))
                .count();

        return (int) Math.floor(count * 100.00 / persons.size());
    }

    public Integer calculateEyeColorAndNationalityPercentage(Country nationality, Color color) throws NoPersonsException {
        List<Person> persons = getPersonsFromMainService();
        System.out.println("Received from main service: " + persons.toString());

        long nationality_count = persons
            .stream()
            .filter((person) -> nationality.equals(person.getNationality()))
            .count();

        long count = persons
                .stream()
                .filter((person) -> nationality.equals(person.getNationality()))
                .filter((person) -> color.equals(person.getEyeColor()))
                .count();

        return (int) Math.floor(count * 100.00 / nationality_count);
    }
}