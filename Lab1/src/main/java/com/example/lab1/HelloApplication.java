package com.example.lab1;

import com.example.lab1.controller.DressController;
import com.example.lab1.controller.ManagerController;
import com.example.lab1.controller.ModelController;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {

    @Override
    public Set<Class<?>> getClasses(){
        final Set<Class<?>> classes=new HashSet<>();
        classes.add(DressController.class);
        classes.add(ModelController.class);
        classes.add(ManagerController.class);
        return classes;

    }
}