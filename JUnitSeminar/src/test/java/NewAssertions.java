import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

/*
JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage
 */
class NewAssertions {
    @Test
    void methodCanBePackagePrivate() {
        /* checks the references */
        assertSame("It works!", "It " + "works!");
    }

    @Test
    void optionalMessages() {
        assertEquals(2017, 2017, "The optional assertion message is now the last parameter.");

        // Lazy supplier can be used
        assertTrue("test" == "fail", () -> "Assertion messages can be lazily evaluated");
    }

    @Test
    void logicalTestGrouping() {
        assertAll("grouped assertions",
                () -> assertTrue(true),
                () -> assertTrue(true)
        );

        assertAll("failed grouped assertions",
                () -> assertTrue(true),
                () -> assertTrue(false, "fail explanation message")
        );
    }

    @Test
    void iterableEquals() {
        assertIterableEquals(asList(1, 2, 3), asList(1, 2, 3));
        assertIterableEquals(new HashSet<>(asList(1, 2, 3)), new HashSet<>(asList(1, 2, 3)));
    }

    @Test
    void checkStringsEquals() {
        assertLinesMatch(
                asList("you may compare strings", "even with regex:[a-w] \\d{2}\\.\\d{2}\\.\\d{4}"),
                asList("you may compare strings", "even with regex:a 12.09.2017")
        );
    }

    @Test
    void handlingExceptionsAreEasier() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("error msg");
        });

        assertEquals("error msg", exception.getMessage());
    }
}
