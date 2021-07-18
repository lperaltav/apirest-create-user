package cl.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.app.dao.UserDAO;
import cl.app.exception.ConflictException;
import cl.app.model.User;
import cl.app.service.RegisterService;
import cl.app.util.JWTUtil;
import cl.app.util.RegexValidate;
import lombok.extern.slf4j.Slf4j;
import cl.app.enums.RegexType;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

	private final UserDAO userDAO;

	@Autowired
	public RegisterServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	@Transactional
	public User createUser(User user) throws ConflictException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		UUID uuid = UUID.randomUUID();
		validateInput(user);
		existEmail(user.getEmail());
		user.setId(uuid.toString());
		user.setCreated(timeStamp);
		user.setLastLogin(timeStamp);
		user.setModified(timeStamp);
		user.setIsactive("Y");
		user.setToken(JWTUtil.sign(user.getId(), user.getPassword()));
		this.userDAO.add(user);
		return user;
	}

	private void existEmail(String correo) throws ConflictException {
		if (this.userDAO.search(correo)) {
			String msg = String.format("El Correo ya se encuentra registrado");
			throw new ConflictException(msg);
		};

	}
	
	private void validateInput(User user) throws ConflictException {
		
		if (!RegexValidate.isvalid(user.getEmail(), RegexType.EMAIL)) {
			String msg = String.format("Error de sintaxis en correo");
			throw new ConflictException(msg);
		}
		
		if (!RegexValidate.isvalid(user.getPassword(), RegexType.PASS)) {
			String msg = String.format("la clave debe contener una letra mayuscula, minusculas y dos numeros");
			throw new ConflictException(msg);
		}
		

	}
}
