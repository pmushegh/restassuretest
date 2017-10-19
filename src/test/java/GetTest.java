//package CountriesRestTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static com.jayway.restassured.RestAssured.get;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;

public class GetTest {

    @Test
    public void getRequestFindCapital() throws JSONException {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("http://restcountries.eu/rest/v1/name/ukrain");

        Assert.assertEquals(resp.getStatusCode(), 200, "Response status code is incorrect.");

        JSONArray jsonResponse = new JSONArray(resp.asString());

        // получение параметра capital (столицы Норвегии)
        String capital = jsonResponse.getJSONObject(0).getString("capital");

        // проверка, что столицей является Осло
        Assert.assertEquals(capital, "Kiev", "Wrong country capital is found.");
    }


    @Test
    public void testingSomething() {

    }

}