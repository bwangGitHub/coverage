package ca.uwaterloo.swag.tat;

import org.junit.*;
import static org.junit.Assert.*;

public class TestUnitTest
{
    @Test
    public void testUnitTestEquality ()
    {
	UnitTest u1 = new UnitTest ("class", "method");
	UnitTest u2 = new UnitTest ("class", "method");
	assertTrue(u1.equals(u2));
	assertTrue(u1.hashCode() == u2.hashCode());
    }

    @Test
    public void testUnitTestInequality ()
    {
	UnitTest u1 = new UnitTest ("class", "method");
	UnitTest u2 = new UnitTest ("lcass", "method");
	assertFalse(u1.equals(u2));
    }
}
