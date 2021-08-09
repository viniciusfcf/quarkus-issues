package com.github.viniciusfcf;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    GreetingService service;
    
    @GET
    @Path("add-and-list-transactional")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<CustomUser> addAndListTransactional() {
        try {
            service.doSomething();
        }catch(Exception e) {
            e.printStackTrace();
        }
       return entityManager.createQuery("select u from CustomUser u").getResultList();
    }

    @GET
    @Path("add-and-list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomUser> addAndList() {
        try {
            service.doSomething();
        }catch(Exception e) {
            e.printStackTrace();
        }
       return entityManager.createQuery("select u from CustomUser u").getResultList();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomUser> list() {
       return entityManager.createQuery("select u from CustomUser u").getResultList();
    }
}