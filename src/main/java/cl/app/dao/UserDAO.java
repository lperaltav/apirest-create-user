package cl.app.dao;

import cl.app.model.User;

public interface UserDAO {

	User add(User user);

	boolean search(String correo);

}
