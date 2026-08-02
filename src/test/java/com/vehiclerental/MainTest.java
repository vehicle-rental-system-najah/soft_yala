package com.vehiclerental;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    @Test
    void mainShouldCompleteTheRentalDemonstration() {
        Logger logger = Logger.getLogger(Main.class.getName());

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        StreamHandler handler =
                new StreamHandler(output, new SimpleFormatter());

        logger.addHandler(handler);

        try {
            assertDoesNotThrow(() -> Main.main(new String[0]));

            handler.flush();
            String logOutput = output.toString();

            assertTrue(logOutput.contains("Login successful"));
            assertTrue(logOutput.contains("Manager logged out"));
        } finally {
            logger.removeHandler(handler);
            handler.close();
        }
    }
}