package org.wicketTutorial;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

public class UsernameValidator extends AbstractValidator<String> {
	List<String> existingUsernames = Arrays.asList("bigJack", "anonymous", "mrSmith");
	
	@Override
	protected void onValidate(IValidatable<String> validatable) {
		String chosenUserName = validatable.getValue();
		
		if(existingUsernames.contains(chosenUserName))
			error(validatable);
	}
	
	@Override
	protected String resourceKey() {
		return "UsernameValidator";
	}
	
	@Override
	protected Map<String, Object> variablesMap(IValidatable<String> validatable) {
		Map<String, Object> map = super.variablesMap(validatable);	
		Random random = new Random();		
		
		map.put("suggestedUserName", validatable.getValue() + random.nextInt());
		
		return map;
	}
}
