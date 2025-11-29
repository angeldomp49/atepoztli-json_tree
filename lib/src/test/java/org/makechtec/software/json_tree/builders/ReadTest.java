package org.makechtec.software.json_tree.builders;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadTest {

    @Test
    public void testReading() {

        var input = """
                {
                    "contact": {
                        "accountId": 34,
                        "contactId": 45
                    },
                    "withNull": null
                }
                """;

        var inputObject = new JSONObject(input);


        var obj = inputObject.get("withNull");

        assertEquals(JSONObject.NULL, obj);

    }

    @Test
    public void testReadingUnexisting() {

        var input = """
                {
                    "contact": {
                        "accountId": 34,
                        "contactId": 45
                    }
                }
                """;

        var inputObject = new JSONObject(input);

        assertThrows(JSONException.class, () -> inputObject.get("unexisting"));

    }

    @Test
    public void testReadingCast() {

        var input = """
                {
                    "contact": {
                        "accountId": 34,
                        "contactId": 45
                    },
                    "withNull": 500.1
                }
                """;

        var inputObject = new JSONObject(input);


        var obj = inputObject.getBigDecimal("withNull");

        assertEquals(BigDecimal.valueOf(500.1), obj);

    }

    @Test()
    public void testReadingObject() {

        var input = """
                {
                    "contact": {
                        "accountId": 34,
                        "contactId": 45
                    },
                    "message": "hi"
                }
                """;

        var inputObject = new JSONObject(input);

        assertThrows(JSONException.class, () -> inputObject.getJSONObject("message"));

    }

    @Test
    public void testReadingObjectToString() {

        var input = """
                {
                    "contact": {
                        "accountId": 34,
                        "contactId": 45
                    },
                    "message": "hi"
                }
                """;

        var inputObject = new JSONObject(input);


        var obj = inputObject.getJSONObject("contact");

        System.out.println(obj);

    }

}
