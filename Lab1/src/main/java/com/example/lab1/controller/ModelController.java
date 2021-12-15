package com.example.lab1.controller;

import com.example.lab1.DAO.ManagerDao;
import com.example.lab1.DAO.ModelDao;
import com.example.lab1.models.Manager;
import com.example.lab1.models.Model;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;


@WebServlet
@Path("/model")
public class ModelController {

    @EJB
    private ModelDao modelDao;

    @EJB
    private ManagerDao managerDao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/")
    public Response getModel() throws JsonProcessingException {
        List<Model> models = modelDao.getAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(models))
                .build();
    }

    @GET
    @Path("/{modelId}")// localhost:8080/model/
    public Response getModelById(@PathParam("modelId") String modelId) throws JsonProcessingException {
        Model model = modelDao.get(Integer.valueOf(modelId));

        if (model == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Model with id %s not found", modelId)).build();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(model))
                .build();
    }

    @POST
    @Path("/")
    public Response addNewModel(
            @FormParam("fullName") String fullName,
            @FormParam("age") String age,
            @FormParam("height") String height,
            @FormParam("weight") String weight,
            @FormParam("contractNumber") String contractNumber,
            @FormParam("phone") String phone,
            @FormParam("managerId") String managerId
    ) {
        Model model = new Model();

        model.setFullName(fullName);
        model.setAge(Integer.parseInt(age));
        model.setHeight(Integer.parseInt(height));
        model.setWeight(Integer.parseInt(weight));
        model.setContractNumber(Integer.parseInt(contractNumber));
        model.setPhone(phone);

        Manager manager = managerDao.get(Integer.valueOf(managerId));
        if (manager == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Manager with id %s not found", managerId)).build();
        model.setManager(manager);

        modelDao.save(model);
        return Response.ok().build();
    }

    @PUT
    @Path("/{modelId}")
    public Response updateModel(
            @PathParam("modelId") String modelId,
            @DefaultValue("") @FormParam("fullName") String fullName,
            @DefaultValue("") @FormParam("age") String age,
            @DefaultValue("") @FormParam("height") String height,
            @DefaultValue("") @FormParam("weight") String weight,
            @DefaultValue("") @FormParam("contractNumber") String contractNumber,
            @DefaultValue("") @FormParam("phone") String phone) {
        Model model = modelDao.get(Integer.valueOf(modelId));
        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Model with id %s not found", modelId)).build();
        }


        if (!fullName.isEmpty()) {
            model.setFullName(fullName);
        }
        if (!age.isEmpty()) {
            model.setAge(Integer.parseInt(age));
        }
        if (!height.isEmpty()) {
            model.setHeight(Integer.parseInt(height));
        }
        if (!weight.isEmpty()) {
            model.setWeight(Integer.parseInt(weight));
        }
        if (!contractNumber.isEmpty()) {
            model.setContractNumber(Integer.parseInt(contractNumber));
        }
        if (!phone.isEmpty()) {
            model.setPhone(phone);
        }
        modelDao.update(model);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{modelId}")
    public Response deleteModel(@PathParam("modelId") String modelId) {
        Model model = modelDao.get(Integer.valueOf(modelId));
        if (model == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Model with id %s not found", modelId)).build();
        }
        modelDao.delete(model);
        return Response.ok().build();

    }

}
