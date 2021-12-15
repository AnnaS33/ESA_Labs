package com.example.lab2.controllers;


import com.example.lab2.models.Dress;
import com.example.lab2.models.Manager;
import com.example.lab2.models.Model;
import com.example.lab2.services.ManagerService;
import com.example.lab2.services.ModelService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private ModelService modelService;


    @RequestMapping(value = "/", method = RequestMethod.GET,headers = "accept=application/json")
    public ResponseEntity<Object> getManager()throws JsonProcessingException {
        List<Manager> manageres = managerService.findAll();

        return ResponseEntity.ok().body(manageres);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,headers = "accept=application/xml")
    public ModelAndView getManagerXSLT() throws JsonProcessingException {
        List<Manager> manageres = managerService.findAll();
        ModelAndView modelAndView=new ModelAndView("manager");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(manageres)));
        modelAndView.addObject(source);
        return modelAndView;
    }


    @RequestMapping(value = "/{managerId}", method = RequestMethod.GET)
    public ResponseEntity getManagerById(@PathVariable("managerId") Integer managerId) {

        Optional<Manager> manager = managerService.findById(managerId);
        if (!manager.isPresent()) {
            return new ResponseEntity<Object>(String.format("Manager with id %s not found", managerId), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(manager.get());
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity addNewManager(@RequestBody Manager manager) {

        managerService.save(manager);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{managerId}", method = RequestMethod.PUT)
    public ResponseEntity updateManager(
            @PathVariable("managerId") Integer managerId,
            @RequestBody Manager manager) {

        Optional<Manager> exmanager = managerService.findById(managerId);
        if (!exmanager.isPresent()) {
            return new ResponseEntity<Object>(String.format("Manager with id %s not found", managerId), HttpStatus.NOT_FOUND);
        }
        Manager targetManager = exmanager.get();

        if (!manager.getEmail().isEmpty()) {
            targetManager.setEmail(manager.getEmail());
        }
        if (!manager.getFullName().isEmpty()) {
            targetManager.setFullName(manager.getFullName());
        }
        if (!manager.getPhone().isEmpty()) {
            targetManager.setPhone(manager.getPhone());
        }
        if (!(manager.getExperience() == null)) {
            targetManager.setExperience(manager.getExperience());
        }
        if (!(manager.getCostOfServices() == null)) {
            targetManager.setCostOfServices(manager.getCostOfServices());
        }

        managerService.save(targetManager);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{managerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteManager(@PathVariable("managerId") Integer managerId) {
        Optional<Manager> exmanager = managerService.findById(managerId);

        if (!exmanager.isPresent()) {
            return new ResponseEntity<Object>(String.format("Manager with id %s not found", managerId), HttpStatus.NOT_FOUND);
        }
        managerService.delete(exmanager.get());
        return ResponseEntity.ok().build();
    }

}
