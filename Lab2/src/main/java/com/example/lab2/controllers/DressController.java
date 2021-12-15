package com.example.lab2.controllers;


import com.example.lab2.models.Dress;
import com.example.lab2.models.Manager;
import com.example.lab2.models.Model;
import com.example.lab2.services.DressService;
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
@RequestMapping(value = "/dress")
public class DressController {

    @Autowired
    private DressService dressService;
    @Autowired
    private ModelService modelService;


    @RequestMapping(value = "/", method = RequestMethod.GET,headers = "accept=application/json")
    public ResponseEntity getDress() throws JsonProcessingException{
        List<Dress> dresses = dressService.findAll();

        return ResponseEntity.ok().body(dresses);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET,headers = "accept=application/xml")
    public ModelAndView getDressXSLT() throws JsonProcessingException {
        List<Dress> dresses = dressService.findAll();
        ModelAndView modelAndView=new ModelAndView("dress");
        Source source = new StreamSource(new ByteArrayInputStream(new XmlMapper().writeValueAsBytes(dresses)));
        modelAndView.addObject(source);
        return modelAndView;
    }


    @RequestMapping(value = "/{dressId}", method = RequestMethod.GET)
    public ResponseEntity getDressById(@PathVariable("dressId") Integer dressId) {

        Optional<Dress> dress = dressService.findById(dressId);
        if (!dress.isPresent()) {
            return new ResponseEntity<Object>(String.format("Dress with id %s not found", dressId), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(dress.get());
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity addNewDress(@RequestBody Dress dress, @RequestParam Integer modelId) {

        Optional<Model> model = modelService.findById(modelId);
        if (!model.isPresent()) {
            return new ResponseEntity<Object>(String.format("Model with id %s not found", modelId), HttpStatus.NOT_FOUND);
        }
        dress.setModel(model.get());

        dressService.save(dress);
        return ResponseEntity.ok().build();
    }


    //Не работает, а нет) Уже работает
    @RequestMapping(value = "/{dressId}", method = RequestMethod.PUT)
    public ResponseEntity updateDress(
            @PathVariable("dressId") Integer dressId,
            @RequestParam(defaultValue = "") Integer modelId,
            @RequestBody Dress dress) {
        Optional<Dress> exdress = dressService.findById(dressId);
        if (!exdress.isPresent() && modelId>0) {
            return new ResponseEntity<Object>(String.format("Model with id %s not found", dressId), HttpStatus.NOT_FOUND);
        }
        Dress targetDress = exdress.get();

        if (!dress.getColor().isEmpty()) {
            targetDress.setColor(dress.getColor());
        }
        if (!(dress.getCost() == null)) {
            targetDress.setCost(dress.getCost());
        }
        if (!(dress.getNumberD() == null)) {
            targetDress.setNumberD(dress.getNumberD());
        }

        Optional<Model> modell = modelService.findById(modelId);
        if (!modell.isPresent()) {
            return new ResponseEntity<Object>(String.format("Model with id %s not found", modelId), HttpStatus.NOT_FOUND);
        }
        targetDress.setModel(modell.get());

        dressService.save(targetDress);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/{dressId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteDress(@PathVariable("dressId") Integer dressId) {
        Optional<Dress> exdress = dressService.findById(dressId);
        if (!exdress.isPresent()) {
            return new ResponseEntity<Object>(String.format("Model with id %s not found", dressId), HttpStatus.NOT_FOUND);
        }
        dressService.delete(exdress.get());
        return ResponseEntity.ok().build();
    }
}


