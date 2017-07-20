package ca.uwaterloo.swag.tat;

import junit.framework.TestSuite;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTestDatabase
{
    TestDatabase testdb;
    
    @Before
    public void setUp ()
    {
        // TODO: don't hardcode
        try
        {
            testdb = new TestDatabase((TestSuite) ((Class.forName("org.apache.poi.AllPOITests").getMethod("suite")).invoke(null)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            fail("Could not create test database");
        }
    }
    
    /*@Test
    public void testRelationships ()
    {
        int numEquivMutants = mutantdb.getNumberOfEquivalentMutants();
        int numEquivMutants2 = mutantdb.getTotalNumberOfMutants() - mutantdb.getNumberOfKilledMutants();
        assertTrue(numEquivMutants == numEquivMutants2);
	}*/

    @Test
    public void testSize()
    {
	assertTrue(testdb.getTotalNumberOfTests() > 0);
    }
}
