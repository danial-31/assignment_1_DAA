import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class DeterministicSelectTest {
    int select(int[] arr, int k) {
        Arrays.sort(arr);
        return arr[k];
    }

    @Test
    void testSelect() {
        int[] arr = {7, 2, 1, 6, 8, 5, 3, 4};
        assertEquals(4, select(arr, 3));
    }
}