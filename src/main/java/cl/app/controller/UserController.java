package cl.app.controller;

import cl.app.exception.ConflictException;
import cl.app.model.User;
import cl.app.service.RegisterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Register")
@RestController
@RequestMapping("/api/v1/register")
public class UserController {

	@Autowired
	private RegisterService regService;

	public void setRegisterService() {

	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody User create(@RequestBody User jsonString) throws ConflictException {
		return regService.createUser(jsonString);
	}

}
