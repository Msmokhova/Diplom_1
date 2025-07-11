package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    @Mock(lenient = true)
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        lenient().when(bun.getName()).thenReturn("Белая булочка");
        lenient().when(bun.getPrice()).thenReturn(100.0f);
    }

    @Test
    void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    void testGetPrice() {
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(200.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(450.0f, burger.getPrice(), 0.0001f);
    }

    @Test
    void testGetReceipt() {
        Locale.setDefault(Locale.US);
        when(bun.getName()).thenReturn("Белая булочка");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Кетчуп");
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("Говядина");
        when(ingredient2.getPrice()).thenReturn(200.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String lineSeparator = System.lineSeparator();
        String expected = "(==== Белая булочка ====)" + lineSeparator +
                "= sauce Кетчуп =" + lineSeparator +
                "= filling Говядина =" + lineSeparator +
                "(==== Белая булочка ====)" + lineSeparator +
                lineSeparator +
                "Price: 450.000000" + lineSeparator;

        assertEquals(expected, burger.getReceipt());
    }
}