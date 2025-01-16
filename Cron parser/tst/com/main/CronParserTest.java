import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class CronParserTest {

    @Test
    void testExpandFieldWildcard() {
        List<Integer> result = CronParser.expandField("*", 0, 5);
        assertEquals(List.of(0, 1, 2, 3, 4, 5), result);
    }

    @Test
    void testExpandFieldList() {
        List<Integer> result = CronParser.expandField("1,3,5", 0, 5);
        assertEquals(List.of(1, 3, 5), result);
    }

    @Test
    void testExpandFieldRange() {
        List<Integer> result = CronParser.expandField("2-4", 0, 5);
        assertEquals(List.of(2, 3, 4), result);
    }

    @Test
    void testExpandFieldStep() {
        List<Integer> result = CronParser.expandField("*/2", 0, 5);
        assertEquals(List.of(0, 2, 4), result);
    }

    @Test
    void testExpandFieldSingleValue() {
        List<Integer> result = CronParser.expandField("3", 0, 5);
        assertEquals(List.of(3), result);
    }
}