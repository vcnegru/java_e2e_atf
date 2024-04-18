package com.atf.template.ui.cucumber.assertions;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.lang.System.lineSeparator;

@Slf4j
public class CustomAssertions {

    private static final String ASSERT_THAT = "Assert that ";
    private static final String ASSERTION_EXPECTED_ACTUAL_MESSAGE = "%s%4$s - [ EXPECTED ]: %s%4$s - [ ACTUAL ]: %s";

    public static <T> void assertThat(final String message, final T actual, final Matcher<? super T> matcher) {
        final String fullMessage = ASSERT_THAT + message;
        final String logMessage = format(ASSERTION_EXPECTED_ACTUAL_MESSAGE, fullMessage, valueOf(matcher), valueOf(actual), lineSeparator());
        try {
            MatcherAssert.assertThat(fullMessage, actual, matcher);
            log.info(logMessage);
        } catch (AssertionError error) {
            log.error(logMessage);
            throw new AssertionError(error.getMessage());
        }
    }
}
