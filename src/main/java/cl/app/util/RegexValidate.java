package cl.app.util;

import java.util.regex.*;

import cl.app.enums.RegexType;
import cl.app.exception.ConflictException;

public class RegexValidate {
	public static boolean isvalid(String value, RegexType type) throws ConflictException {

		String pattern = "";

		if (type.equals(RegexType.EMAIL)) {
			pattern = "^[A-Z a-z 0-9]+([._+-][0-9 a-z A-Z]+)*@[0-9 a-z A-Z]+.[a-z A-Z]{2,3}([.][a-z A-Z]{2})*$";
		} else {
			if (!isAlphaNumeric(value)) {
				String msg = String.format("la clave no soporta caracteres especiales", value);
				throw new ConflictException(msg);
			}
			pattern = "^(?=.*[a-z])(?=(?:.*[A-Z].*){1})(?!(?:.*[A-Z].*){2,})(?=(?:.*[0-9].*){2})(?!(?:.*[0-9].*){3,}).*$";
		}
		Pattern P = Pattern.compile(pattern);
		if (value == null) {
			return false;
		}
		Matcher m = P.matcher(value);
		return m.matches();
	}

	public static boolean isAlphaNumeric(String s) {
		String pattern = "^[a-zA-Z0-9]*$";
		return s.matches(pattern);
	}
}
