package uk.ac.sheffield.assignment2021.test.forstudents;

import static org.junit.Assert.*;

import org.junit.*;
import uk.ac.sheffield.assignment2021.QueryParser;
import uk.ac.sheffield.assignment2021.codeprovided.Query;
import uk.ac.sheffield.assignment2021.codeprovided.SubQuery;
import uk.ac.sheffield.assignment2021.codeprovided.WineProperty;
import uk.ac.sheffield.assignment2021.codeprovided.WineType;
import uk.ac.sheffield.assignment2021.test.common.TestCommon;

import java.util.List;

public class TestQueryParser {

    @Test
    public void testReadSingleQuery() {

        QueryParser parser = new QueryParser();

        List<Query> queries = parser.readQueries(TestCommon.tokenizeString("select red where qual != 2 and qual != 4 and qual != 6"));

        assertEquals(1, queries.size());
        assertEquals(3, queries.get(0).getSubQueryList().size());

        assertEquals(WineType.RED, queries.get(0).getWineType());
    }

    @SuppressWarnings("unused")
    @Test
    public void testExceptionOnBadQuery() {
        QueryParser parser = new QueryParser();

        try {
            List<Query> queries = parser.readQueries(TestCommon.tokenizeString("select red where qual &= two"));
        } catch (IllegalArgumentException expected) {
            return;
        }
        fail("Expected exception not thrown.");

    }

}
