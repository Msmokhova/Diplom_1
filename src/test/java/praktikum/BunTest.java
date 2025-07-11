package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BunTest {

    @Test
    void testGetName() {
        Bun bun = new Bun("Белая булочка", 100);
        assertEquals("Белая булочка", bun.getName());
    }

    @Test
    void testGetPrice() {
        Bun bun = new Bun("Чёрная булочка", 150);
        assertEquals(150, bun.getPrice());
    }
}