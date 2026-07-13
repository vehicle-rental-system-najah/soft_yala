package com.vehiclerental.util;

import java.time.LocalDate;

/**
 * Provides the current system date.
 */
public class SystemDateTimeProvider implements DateTimeProvider {

    /**
     * Returns today's date from the system clock.
     *
     * @return the current system date
     */
    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}