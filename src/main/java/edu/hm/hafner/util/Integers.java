package edu.hm.hafner.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Integer utilities.
 *
 * @author Ullrich Hafner
 */
public final class Integers {
    /**
     * Shuffles the specified array values.
     *
     * @param values the array values to shuffle
     * @see Collections#shuffle(List)
     */
    public static void shuffle(final int[] values) {
        Ensure.that(values).isNotNull();

        List<Integer> list = Arrays.asList(ArrayUtils.toObject(values));
        Collections.shuffle(list, new SecureRandom());
        int[] copy = ArrayUtils.toPrimitive(list.toArray(new Integer[values.length]));
        System.arraycopy(copy, 0, values, 0, values.length);
    }

    /**
     * Reads an integer value from the console.
     *
     * @param  format
     *         A format string as described in <a
     *         href="../util/Formatter.html#syntax">Format string syntax</a>
     * @param  args
     *         Arguments referenced by the format specifiers in the format
     *         string.  If there are more arguments than format specifiers, the
     * @return the integer value read
     * @see Formatter
     */
    @SuppressWarnings("PMD.SystemPrintln")
    public static int read(final String format, final Object... args) {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        while (true) {
            try {
                System.out.format(format, args);
                if (!StringUtils.endsWith(format, "\n")) {
                    System.out.println();
                }
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                }
                else {
                    String notAccepted = scanner.next();
                    System.out.format("Der Text '%s' kann nicht in einen Integer umgewandelt werden, bitte nochmal versuchen\n",
                            notAccepted);
                }
            }
            catch (InputMismatchException exception) {
                System.out.format("Der Text kann nicht in einen Integer umgewandelt werden, bitte nochmal versuchen: %s\n",
                        exception.getMessage());
            }
        }
    }


    private Integers() {
        // prevents instantiation
    }
}
