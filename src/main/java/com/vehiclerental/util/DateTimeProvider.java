package com.vehiclerental.util;

import java.time.LocalDate;

/**
 * Provides the current date to the system.
 */
public interface DateTimeProvider {

    /**
     * Returns today's date.
     *
     * @return the current date
     */
    LocalDate today();
}