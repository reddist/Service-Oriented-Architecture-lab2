package controller;

import dto.Color;
import dto.Country;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import javax.enterprise.context.ApplicationScoped;

import dto.Percentage;
import service.DemographyService;

@ApplicationScoped
@Path("/demography")
public class DemographyResource {
    @Inject
    DemographyService demographyService;

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Response getHelloCity() {
        return Response.ok().entity("Hello jax-rs!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorPercentage(@PathParam("eye-color") String eyeColorString) {
        System.out.println("calculateEyeColorPercentage method in controller");

        // TODO add catching IllegalArgumentException
        Color eyeColor = Color.valueOf(eyeColorString.toUpperCase());
        Integer percentage = demographyService.calculateEyeColorPercentage(eyeColor);

        return Response.ok(new Percentage(percentage)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/nationality/{nationality}/eye-color/{eye-color}/percentage")
    public Response calculateEyeColorAndNationalityPercentage(
            @PathParam("nationality") String nationalityString,
            @PathParam("eye-color") String eyeColorString
    ) {
        System.out.println("calculateEyeColorAndNationalityPercentage method in controller");

        // TODO add catching IllegalArgumentException
        Country nationality = Country.valueOf(nationalityString.toUpperCase());
        // TODO add catching IllegalArgumentException
        Color eyeColor = Color.valueOf(eyeColorString.toUpperCase());

        Integer percentage = demographyService.calculateEyeColorAndNationalityPercentage(nationality, eyeColor);

        return Response.ok(new Percentage(percentage)).build();
    }
}
