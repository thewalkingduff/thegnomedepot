package com.devduffy.gnomedepot.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.devduffy.gnomedepot.entity.User;
import com.devduffy.gnomedepot.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EmailUniqueValidator implements ConstraintValidator<EmailUnique, String> {
    public static final Logger LOG = LoggerFactory.getLogger(EmailUniqueValidator.class);
	
	// @Autowired
	// private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(EmailUnique constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if ( StringUtils.isEmpty(value) ) {
			return true;
		}	// User user = userService.getUserByEmail(value);
	
		User user = userRepository.findByEmail(value);
		
		return ( user == null );
	}

   
}
