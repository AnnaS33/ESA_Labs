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
import java.io.IOException;
import java.util.List;


@WebServlet
@Path("/manager")
public class ManagerController {

    @EJB
    private ManagerDao managerDao;
    @EJB
    private ModelDao modelDao;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Path("/")
    public Response getManager() throws JsonProcessingException {
        List<Manager> manageres = managerDao.getAll();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(manageres))
                .build();
    }

    @GET
    @Path("/{managerId}")
    public Response getManagerById(@PathParam("managerId") String managerId) throws JsonProcessingException {
        Manager manager = managerDao.get(Integer.valueOf(managerId));

        if (manager == null)
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Manager with id %s not found", managerId)).build();

        return Response.status(Response.Status.OK.getStatusCode())
                .entity(objectMapper.writeValueAsString(manager))
                .build();
    }

    @POST
    @Path("/")
    public Response addNewManager(
            @FormParam("fullName") String fullName,
            @FormParam("costOfServices") String costOfServices,
            @FormParam("experience") String experience,
            @FormParam("phone") String phone,
            @FormParam("email") String email
    ) {
        Manager manager = new Manager();

        manager.setFullName(fullName);
        manager.setCostOfServices(Integer.valueOf(costOfServices));
        manager.setExperience(Integer.valueOf(experience));
        manager.setPhone(phone);
        manager.setEmail(email);

        managerDao.save(manager);

        return Response.ok().build();
    }

    @PUT
    @Path("/{managerId}")
    public Response updateManager(
            @PathParam("managerId") String managerId,
            @DefaultValue("") @FormParam("fullName") String fullName,
            @DefaultValue("") @FormParam("costOfServices") String costOfServices,
            @DefaultValue("") @FormParam("experience") String experience,
            @DefaultValue("") @FormParam("phone") String phone,
            @DefaultValue("") @FormParam("email") String email) {
        Manager manager = managerDao.get(Integer.valueOf(managerId));
        if (manager == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Manager with id %s not found", managerId)).build();
        }

        if (!fullName.isEmpty()) {
            manager.setFullName(fullName);
        }
        if (!costOfServices.isEmpty()) {
            manager.setCostOfServices(Integer.valueOf(costOfServices));
        }
        if (!experience.isEmpty()) {
            manager.setExperience(Integer.valueOf(experience));
        }
        if (!phone.isEmpty()) {
            manager.setPhone(phone);
        }
        if (!email.isEmpty()) {
            manager.setEmail(email);
        }

        managerDao.update(manager);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{managerId}")
    public Response deleteManager(@PathParam("managerId") String managerId) {
        Manager manager = managerDao.get(Integer.valueOf(managerId));
        if (manager == null) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode())
                    .entity(String.format("Manager with id %s not found", managerId)).build();
        } else {
            managerDao.delete(manager);
            return Response.ok().build();
        }
    }

}
