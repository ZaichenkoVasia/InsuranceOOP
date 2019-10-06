package ua.mycompany.Helper.Validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class CourseValidator implements Validator {
    private static final String PHONE_PATTERN = "[1-6]{1}";

    public CourseValidator() {

    }

    @Override
    public boolean validate(final String hex) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }
}
