package com.epam.jwd.onlinetraining.service.validator;

public interface Validator<T> {
    void validate(T dto);
}
