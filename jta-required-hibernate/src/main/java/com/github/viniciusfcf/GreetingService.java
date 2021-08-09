package com.github.viniciusfcf;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
public class GreetingService {

    @PersistenceContext
    EntityManager entityManager;
    
    @Transactional(value = TxType.REQUIRED)
    public void doSomething() {
        CustomUser u = new CustomUser();
        u.nome = "Vinicius";
        entityManager.persist(u);
        // force rollback!!
        throw new IllegalArgumentException();
    }
}
