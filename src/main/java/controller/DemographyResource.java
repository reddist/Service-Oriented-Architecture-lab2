package controller;

import dto.Color;
import dto.Country;
import dto.ErrorMessage;
import dto.Percentage;
import service.DemographyService;
import service.NoPersonsException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/demography")
public class DemographyResource {
    @Inject
    DemographyService demographyService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getHelloCity() {
        return Response.ok().entity(new ErrorMessage("Hello jax-rs!")).build();
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
            return Response.serverError().entity(new ErrorMessage("Invalid Eye color")).build();
        }

        try {
            Integer percentage = demographyService.calculateEyeColorPercentage(eyeColor);
            return Response.ok(new Percentage(percentage)).build();
        } catch (NoPersonsException e) {
            return Response.serverError().entity(new ErrorMessage("No persons in store")).build();
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
            return Response.serverError().entity(new ErrorMessage("Invalid Nationality")).build();
        }

        Color eyeColor;
        try {
            eyeColor = Color.valueOf(eyeColorString.toUpperCase());
        } catch (IllegalArgumentException error) {
            return Response.serverError().entity(new ErrorMessage("Invalid Eye color")).build();
        }

        try {
            Integer percentage = demographyService.calculateEyeColorAndNationalityPercentage(nationality, eyeColor);
            return Response.ok(new Percentage(percentage)).build();
        } catch (NoPersonsException e) {
            return Response.serverError().entity(new ErrorMessage("No persons in store")).build();
        }
    }
}
