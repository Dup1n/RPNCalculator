import org.dupin.RPN;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RPNTest {

    private RPN rpn;

    @BeforeEach
    void setUp() {
        rpn = new RPN();
    }

    String stringValueStack(String valueStack, String offsideValue) {
        return "StackRPN{" +
                "stack=[" + valueStack +
                "], offsideValue=" + offsideValue +
                '}';
    }

    @DisplayName("Addition operation: 5 + 5")
    @Test
    void testAddOperation() {
        // Empty RPN Stack
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

        // Add 5 in RPN Stack
        rpn.add(5);
        assertEquals(rpn.toString(), stringValueStack("5.0, 0.0, 0.0", "0.0"));

        // Addition 5 with the 5 in the RPN Stack (empty stack again)
        assertEquals(10, rpn.sum(5));
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

    }

    @DisplayName("Subtraction operation: (3 - (2 - 10))")
    @Test
    void testSubOperation() {
        // Empty RPN Stack
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

        // Add 2 and 3 in RPN Stack
        rpn.add(3);
        rpn.add(2);
        assertEquals(rpn.toString(), stringValueStack("2.0, 3.0, 0.0", "0.0"));

        // Subtraction 10 with 3 and 2 in the RPN Stack (empty stack again)
        assertEquals(11, rpn.sub(rpn.sub(10)));
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

    }

    @DisplayName("Multiplication operation: (2 * (3 * 4))")
    @Test
    void testMulOperation() {
        // Empty RPN Stack
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

        // Add 2 and 3 in RPN Stack
        rpn.add(2);
        rpn.add(3);
        assertEquals(rpn.toString(), stringValueStack("3.0, 2.0, 0.0", "0.0"));

        // Multiply 4 with 3 and 2 in the RPN Stack (empty stack again)
        assertEquals(24, rpn.mul(rpn.mul(4)));
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));

    }

    @DisplayName("Simple equation: (10 - 3) + 22")
    @Test
    void testSimpleEquation() {
        double result = 0.0;

        // Add first part (parenthesis)
        rpn.add(10);
        result = rpn.sub(3);
        assertEquals(7, result);

        // addition result with last part
        rpn.add(result);
        assertEquals(29, rpn.sum(22));
    }

    @DisplayName("Complex equation: ((10 - 4) / 3) * ((1/2 + 5/2) ^ 2)")
    @Test
    void testComplexEquation() {
        double result = 0.0;

        // Add first part: (10 - 4) / 3 (left-hand)
        rpn.add(10);
        result = rpn.sub(4);
        rpn.add(result);
        result = rpn.div(3);
        rpn.add(result);

        assertEquals(2, result);

        // Add second part: (1/2 + 5/2) ^ 2 (right-hand)
        rpn.add(2);
        result = rpn.inverse(2);
        rpn.add(result);
        result = rpn.sum(2.5);
        result = rpn.power(result);
        assertEquals(9, result);

        //Multiply both results
        assertEquals(18, rpn.mul(result));
    }

    @DisplayName("Offside carry the last value on stack")
    @Test
    void testCarryingLastValue() {
        // last value (in offsideValue)
        rpn.add(3);

        rpn.add(2); // 3th value
        rpn.add(2); // 2th value
        rpn.add(2); // 1th value
        assertEquals(rpn.toString(), stringValueStack("2.0, 2.0, 2.0", "3.0"));

        assertEquals(4, rpn.sum(2));
        assertEquals(rpn.toString(), stringValueStack("2.0, 2.0, 3.0", "3.0"));

        assertEquals(4, rpn.sum(2));
        assertEquals(rpn.toString(), stringValueStack("2.0, 3.0, 3.0", "3.0"));

        assertEquals(4, rpn.sum(2));
        assertEquals(rpn.toString(), stringValueStack("3.0, 3.0, 3.0", "3.0"));

        assertEquals(5, rpn.sum(2));
        assertEquals(rpn.toString(), stringValueStack("3.0, 3.0, 3.0", "3.0"));
    }

    @DisplayName("Delete all values on stack")
    @Test
    void testDeleteAllValuesOnStack() {
        // last value (in offsideValue)
        rpn.add(3);

        rpn.add(2); // 3th value
        rpn.add(2); // 2th value
        rpn.add(2); // 1th value
        assertEquals(rpn.toString(), stringValueStack("2.0, 2.0, 2.0", "3.0"));

        rpn.ac();
        assertEquals(rpn.toString(), stringValueStack("0.0, 0.0, 0.0", "0.0"));
    }

    @DisplayName("Swap the result with the first value on stack")
    @Test
    void testSwapValuesOnStack() {
        double result = 23.0;

        rpn.add(10);
        assertEquals(rpn.toString(), stringValueStack("10.0, 0.0, 0.0", "0.0"));

        result = rpn.swap(result);
        assertEquals(rpn.toString(), stringValueStack("23.0, 0.0, 0.0", "0.0"));
        assertEquals(10, result);
    }
}
