package controller;

import dto.Color;
import dto.Country;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PathParam;

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

        return Response.ok(percentage, MediaType.APPLICATION_XML).build();
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

        return Response.ok(percentage, MediaType.APPLICATION_XML).build();
    }
}
