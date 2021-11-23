package com.epam.jwd.onlinetraining.dao.model;

public class Entity<T> {
    protected T id;

    public Entity() {
    }
    public Entity(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
