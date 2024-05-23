package com.qt.commonutils;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

/**
 * This Class has all the Objects related to ExceptionUtils.
 *
 * @author
 */
public class ExceptionUtils {

    /**
     * Handles exceptions thrown by a supplier function and rethrows them with additional information.
     *
     * @param supplier     The supplier function to execute.
     * @param errorMessage The error message to include in case of an exception.
     * @param <T>          The type of result returned by the supplier.
     * @return The result of the supplier function.
     * @throws TimeoutException If a TimeoutException occurs.
     */

    public static <T> T handleException(ExceptionalSupplier<T> supplier, String errorMessage) throws TimeoutException {
        try {
            return supplier.get();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + errorMessage);
            throw e;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element: " + errorMessage);
            throw e;
        } catch (Exception e) {
            System.out.println("Testcase Failed Due to: " + e.getMessage());
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Executes the given runnable and handles exceptions appropriately.
     *
     * @param runnable     The runnable to execute.
     * @param errorMessage The error message to display.
     * @throws TimeoutException If a timeout occurs while waiting for an element.
     */
    public static void handleException(ExceptionalRunnable runnable, String errorMessage) throws TimeoutException {
        try {
            runnable.run();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + errorMessage);
            throw e;
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element: " + errorMessage);
            throw e;
        } catch (Exception e) {
            System.out.println("Failed Due To: " + e.getMessage());
            throw new RuntimeException(errorMessage, e);
        }
    }

    /**
     * Represents a supplier of results, potentially throwing an exception.
     *
     * @param <T> the type of results supplied by this supplier
     */
    @FunctionalInterface
    public interface ExceptionalSupplier<T> {
        /**
         * Gets a result.
         *
         * @return a result
         * @throws Exception if an exception occurs
         */

        T get() throws Exception;
    }

    /**
     * Represents a runnable task that potentially throws an exception.
     */
    @FunctionalInterface

    public interface ExceptionalRunnable {
        /**
         * Executes the runnable task.
         *
         * @throws Exception if an exception occurs
         */
        void run() throws Exception;
    }
}
