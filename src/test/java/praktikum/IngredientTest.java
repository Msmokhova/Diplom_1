package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @ParameterizedTest
    @MethodSource("ingredientProvider")
    void constructorAndGetters_ShouldWorkCorrectly(
            IngredientType type, String name, float price) {

        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    // Провайдер тестовых данных
    private static Stream<Arguments> ingredientProvider() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "Соус острый", 50.0f),
                Arguments.of(IngredientType.SAUCE, "Соус сырный", 60.0f),
                Arguments.of(IngredientType.FILLING, "Говядина", 200.0f),
                Arguments.of(IngredientType.FILLING, "Курица", 150.0f),
                Arguments.of(IngredientType.SAUCE, "Специальный соус", 0.0f)
        );
    }

    // Тест для различных значений цены
    @ParameterizedTest
    @ValueSource(floats = {0.0f, 50.0f, 100.0f, Float.MAX_VALUE})
    void getPrice_ShouldReturnCorrectValue(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Соус", price);
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    // Тест для различных названий
    @ParameterizedTest
    @ValueSource(strings = {"", "Соус", "Длинное название ингредиента", "Специальный соус 42!"})
    void getName_ShouldReturnCorrectValue(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, name, 100.0f);
        assertEquals(name, ingredient.getName());
    }
}