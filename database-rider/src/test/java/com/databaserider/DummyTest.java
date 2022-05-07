package com.databaserider;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@DBRider
@DBUnit(schema = "public", caseSensitiveTableNames = true)
public class DummyTest {

    @Inject
    EntityManager em;

    @Test
    @DataSet(value = "entities.yml", cleanBefore = true)
    public void testDummy() {
        assertNotNull(Dummy.findById(1L));
    }

}