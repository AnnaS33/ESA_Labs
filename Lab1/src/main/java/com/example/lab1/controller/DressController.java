package com.example.lab1.controller;

import com.example.lab1.DAO.DressDao;
import com.example.lab1.DAO.ModelDao;
import com.example.lab1.models.Dress;
import com.example.lab1.models.Model;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

@WebServlet
@Path("/dress")
public class DressController {

    @EJB
    private DressDao dressDao;

    @EJB
    private ModelDao modelDao;


    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/")
    public Response getDress() throws JsonProcessingException {
        List<Dress> dresses = dressDao.getAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(dresses))
                .build();
    }

    @GET
    @Path("/{dressId}")
    public Response getDressById(@PathParam("dressId") String dressId) throws JsonProcessingException {
        Dress dress = dressDao.get(Integer.valueOf(dressId));

        if (dress == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Dress with id %s not found", dressId)).build();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(dress))
                .build();
    }

    @POST
    @Path("/")
    public Response addNewDress(
            @FormParam("modelId") String model_id,
            @FormParam("cost") String cost,
            @FormParam("numberD") String numberD,
            @FormParam("color") String color) {

        Dress dress = new Dress();
        dress.setCost(Integer.parseInt(cost));
        dress.setColor(color);
        dress.setNumberD(Integer.parseInt(numberD));

        Model model = modelDao.get(Integer.valueOf(model_id));
        if (model == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Dress with id %s not found", model_id)).build();
        dress.setModel(model);

        dressDao.save(dress);
        return Response.ok().build();
    }

    @PUT
    @Path("/{dressId}")
    public Response updateDress(
            @PathParam("dressId") String dressId,
            @DefaultValue("") @FormParam("cost") String cost,
            @DefaultValue("") @FormParam("numberD") String numberD,
            @DefaultValue("") @FormParam("color") String color) {

        Dress dress = dressDao.get(Integer.valueOf(dressId));
        if (dress == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Dress with id %s not found", dressId)).build();
        }


        if (!cost.isEmpty()) {
            dress.setCost(Integer.parseInt(cost));
        }
        if (!numberD.isEmpty()) {
            dress.setNumberD(Integer.parseInt(numberD));
        }
        if (!color.isEmpty()) {
            dress.setColor(color);
        }
        dressDao.update(dress);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{dressId}")
    public Response deleteDress(@PathParam("dressId") String dressId) {
        Dress dress = dressDao.get(Integer.valueOf(dressId));
        if (dress == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Dress with id %s not found", dressId)).build();
        }

        dressDao.delete(dress);
        return Response.ok().build();
    }

}
