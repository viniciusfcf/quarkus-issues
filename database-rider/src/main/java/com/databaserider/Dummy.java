
package com.databaserider;

import javax.persistence.Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;


@Entity
public class Dummy extends PanacheEntity {

    public String name;
}
