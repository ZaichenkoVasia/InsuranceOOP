package ua.mycompany.helper.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public final class EmailValidator implements Validator {
    private static final String EMAIL_PATTERN = "[a-zA-Z0-9]{1,}[@]{1}[a-z]{5,}[.]{1}+[a-z]{3}";

    private static final Logger logger = LoggerFactory.getLogger(EmailValidator.class);

    public EmailValidator() {

    }

    @Override
    public boolean validate(final String hex) {
        logger.info("Check email Customer");
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        if (matcher.matches()) {
            logger.info("Email is correct");
        } else {
            logger.error("Email isn`t correct");
        }
        return matcher.matches();
    }
}
