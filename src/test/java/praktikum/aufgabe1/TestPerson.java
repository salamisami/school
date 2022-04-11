package praktikum.aufgabe1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Testing class for Vector2i
 */
public class TestPerson {

    @Test
    public void testInfectHealthy(){
        Person person = new Person(10, 10, Person.HealthState.HEALTHY);
        Person infectedPerson = new Person(10, 10, Person.HealthState.SICK);
        infectedPerson.infect(person);
        assertEquals(Person.HealthState.SICK, person.getHealthState());
    }
    @Test
    public void testInfectImmune(){
        Person person = new Person(10, 10, Person.HealthState.IMMUNE);
        Person infectedPerson = new Person(10, 10, Person.HealthState.SICK);
        infectedPerson.infect(person);
        assertEquals(Person.HealthState.IMMUNE, person.getHealthState());
    }
    @Test
    public void testHealthyPersonsInfect(){
        Person person = new Person(10, 10, Person.HealthState.HEALTHY);
        Person infectedPerson = new Person(10, 10, Person.HealthState.HEALTHY);
        infectedPerson.infect(person);
        assertEquals(Person.HealthState.HEALTHY, person.getHealthState());
    }

}
