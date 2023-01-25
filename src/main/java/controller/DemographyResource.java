package controller;

import model.Color;
import model.Country;
import model.response.ErrorMessage;
import model.response.Percentage;
import service.DemographyService;
import service.NoPersonsException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/demography")
public class DemographyResource {
    @Inject
    DemographyService demographyService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getHello() {
        return enableCORS(Response.ok())
            .entity(new ErrorMessage("Hello jax-rs!")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorPercentage(@PathParam("eye-color") String eyeColorString) {
        System.out.println("calculateEyeColorPercentage method in controller");

        Color eyeColor;
        try {
            eyeColor = Color.valueOf(eyeColorString.toUpperCase());
        } catch (IllegalArgumentException error) {
            return enableCORS(Response.serverError())
                .entity(new ErrorMessage("Invalid Eye color")).build();
        }

        try {
            Integer percentage = demographyService.calculateEyeColorPercentage(eyeColor);
            return enableCORS(Response.ok())
                .entity(new Percentage(percentage)).build();
        } catch (NoPersonsException e) {
            return enableCORS(Response.serverError())
                .entity(new ErrorMessage("No persons in store")).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/nationality/{nationality}/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorAndNationalityPercentage(
            @PathParam("nationality") String nationalityString,
            @PathParam("eye-color") String eyeColorString
    ) {
        System.out.println("calculateEyeColorAndNationalityPercentage method in controller");

        Country nationality;
        try {
            nationality = Country.valueOf(nationalityString.toUpperCase());
        } catch (IllegalArgumentException error) {
            return enableCORS(Response.serverError())
                .entity(new ErrorMessage("Invalid Nationality")).build();
        }

        Color eyeColor;
        try {
            eyeColor = Color.valueOf(eyeColorString.toUpperCase());
        } catch (IllegalArgumentException error) {
            return enableCORS(Response.serverError())
                .entity(new ErrorMessage("Invalid Eye color")).build();
        }

        try {
            Integer percentage = demographyService.calculateEyeColorAndNationalityPercentage(nationality, eyeColor);
            return enableCORS(Response.ok())
                .entity(new Percentage(percentage)).build();
        } catch (NoPersonsException e) {
            return enableCORS(Response.serverError())
                .entity(new ErrorMessage("No persons in store")).build();
        }
    }

    @OPTIONS
    @Path("/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorPercentageOptions () {
        return enableCORS(Response.ok()).build();
    }

    @OPTIONS
    @Path("/nationality/{nationality}/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorAndNationalityPercentageOptions () {
        return enableCORS(Response.ok()).build();
    }


    private Response.ResponseBuilder enableCORS (Response.ResponseBuilder response) {
        return response
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600");
    }
}
