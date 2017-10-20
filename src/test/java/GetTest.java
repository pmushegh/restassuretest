//package CountriesRestTests;

import org.testng.Assert;
import org.testng.annotations.*;
import static com.jayway.restassured.RestAssured.get;

import com.jayway.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;

import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;

public class GetTest {

    private ClientAndProxy proxy;
    private ClientAndServer mockServer;


    @BeforeClass
    public void startProxy() {
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
    }

    @AfterClass
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void getRequestFindCapital() throws JSONException {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("localhost:1080/");

        Assert.assertEquals(resp.getStatusCode(), 200, "Response status code is incorrect.");

        JSONArray jsonResponse = new JSONArray(resp.asString());

        // получение параметра capital (столицы Норвегии)
        String capital = jsonResponse.getJSONObject(0).getString("capital");

        // проверка, что столицей является Осло
        Assert.assertEquals(capital, "Kiev", "Wrong country capital is found.");
    }

}