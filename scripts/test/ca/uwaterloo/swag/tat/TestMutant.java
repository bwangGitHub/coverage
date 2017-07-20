package ca.uwaterloo.swag.tat;

import org.junit.*;
import static org.junit.Assert.*;

public class TestMutant
{
    @Test
    public void testMutantEquality ()
    {
	Mutant m1 = new Mutant ("class", "mutator", 0);
	Mutant m2 = new Mutant ("class", "mutator", 0);
	assertTrue(m1.equals(m2));
	assertTrue(m1.hashCode() == m2.hashCode());
    }

    @Test
    public void testMutantInequality ()
    {
	Mutant m1 = new Mutant ("class", "mutator", 0);
	Mutant m2 = new Mutant ("lcass", "mutator", 0);
	assertFalse(m1.equals(m2));
    }
}
