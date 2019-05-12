import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.*;

class NewAnnotations {

    // instead of @BeforeClass
    @BeforeAll
    static void initAll() {
    }

    // instead of @Before
    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test"); // actually returns value: Stream.of().map(entry -> fail("should not be called"));
    }

    // instead of @Ignore
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    // New annotation for readability when showing test results in index.html and IDEs.
    @Test
    @DisplayName("this name will be displayed instead of method name ╯°□°）╯")
    void testWithDisplayNameContainingSpecialCharacters() {
        fail("failing to show change of the name");
    }

    // instead of @After
    @AfterEach
    void tearDown() {
    }

    // instead of @AfterClass
    @AfterAll
    static void tearDownAll() {
    }

    @RepeatedTest(value=5, name="{displayName} :: repetition {currentRepetition} of {totalRepetitions}")
    void repeatedTest() {
        System.out.println("This test will be repeated 5 times");
    }
}