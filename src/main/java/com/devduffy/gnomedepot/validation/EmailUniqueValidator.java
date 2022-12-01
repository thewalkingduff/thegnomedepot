package com.devduffy.gnomedepot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.service.UserService;

public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {
    public static final Logger LOG = LoggerFactory.getLogger(EmailUniqueValidator.class);
	
	@Autowired
	private UserService userService;

	@Override
	public void initialize(EmailUnique constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if ( StringUtils.isEmpty(value) ) {
			return true;
		}
		
		User user = userService.getUserByEmail(value);
		
		return ( user == null );
	}

   
}
