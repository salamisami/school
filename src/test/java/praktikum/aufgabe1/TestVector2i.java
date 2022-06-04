package praktikum.aufgabe1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Testing class for Vector2i
 */
public class TestVector2i {

  @Test
  public void testAdd() {
    Vector2i a = new Vector2i(1, 2);
    Vector2i b = new Vector2i(3, -2);
    Vector2i result = a.add(b);
    assertEquals(4, result.getX());
    assertEquals(0, result.getY());
  }

  @Test
  public void testDistanceTo() {
    Vector2i a = new Vector2i(1, 1);
    Vector2i b = new Vector2i(3, 3);
    assertEquals(2 * Math.sqrt(2), a.distanceTo(b), 1e-5);
  }
}
