package cl.app.service;


import cl.app.exception.ConflictException;
import cl.app.model.User;

public interface RegisterService {

    User createUser(User user) throws ConflictException;
}
