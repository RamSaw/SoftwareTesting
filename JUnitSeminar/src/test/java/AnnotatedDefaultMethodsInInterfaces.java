import org.junit.jupiter.api.*;

import java.util.logging.Logger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
interface TestLifecycleLogger {
    Logger logger = Logger.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    default void beforeAllTests() {
        logger.info("Before all tests");
    }

    @AfterAll
    default void afterAllTests() {
        logger.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("About to execute [%s]",
                testInfo.getDisplayName()));
    }

    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        logger.info(() -> String.format("Finished executing [%s]",
                testInfo.getDisplayName()));
    }

}

interface TestInterfaceDynamicTestsDemo {
    Stream<DynamicTest> createTests();

    @TestFactory
    default Stream<DynamicTest> dynamicTestsForStartingWithA() {
        return createTests();
    }

    @Test
    default void test() {
        assertTrue(true);
    }
}

class TestInterfaceDemo implements TestLifecycleLogger, TestInterfaceDynamicTestsDemo {
/*    @Test
    void isEqualValue() {
        assertEquals(1, "a".length(), "is always equal");
    }
*/
    @Override
    public Stream<DynamicTest> createTests() {
        return Stream.of("a car", "a radar", "a mom", "a dad")
                .map(text -> dynamicTest(text, () -> assertTrue(text.startsWith("a "))));
    }


}
