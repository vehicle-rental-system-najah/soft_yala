package com.vehiclerental.util;

import java.time.LocalDate;

public class SystemDateTimeProvider implements DateTimeProvider {

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}