package ca.uwaterloo.swag.tat;

import org.junit.*;
import static org.junit.Assert.*;

public class TestMutantDatabase
{
    MutantDatabase mutantdb;

    @Before
    public void setUp()
    {
        // TODO: don't hardcode
        mutantdb = new MutantDatabase(
                "/home/laura/test-analysis-toolkit/test-subjects/new-apache-poi-results/pi-results//run-pit-err.log");
    }

    /*
     * @Test public void testRelationships () { int numEquivMutants =
     * mutantdb.getNumberOfEquivalentMutants(); int numEquivMutants2 =
     * mutantdb.getTotalNumberOfMutants() - mutantdb.getNumberOfKilledMutants();
     * assertTrue(numEquivMutants == numEquivMutants2); }
     */

    @Test
    public void testSize()
    {
        assertTrue(mutantdb.getTotalNumberOfMutants() > 0);
    }
}
