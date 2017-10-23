//package CountriesRestTests;

import org.mockserver.client.server.MockServerClient;
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
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class GetTest {

    private ClientAndProxy proxy;
    private ClientAndServer mockServer;


    @BeforeClass
    public void startProxy() {
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);
        new MockServerClient("localhost", 1080).when(request().withMethod("GET").withPath("/see")).respond(response().withStatusCode(201).withBody("Hello!!"));
        new MockServerClient("localhost", 1080).when(request().withMethod("GET").withPath("/sea")).respond(response().withStatusCode(205));
    }

    @AfterClass
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void getRequestFindCapital() throws JSONException {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("http://localhost:1080/see");

        Assert.assertEquals(resp.getStatusCode(), 201, "Response status code is incorrect.");

        Assert.assertEquals(resp.getBody().prettyPrint(), "HELLO!!");

//        JSONArray jsonResponse = new JSONArray(resp.asString());
//
//        // получение параметра capital (столицы Норвегии)
//        String capital = jsonResponse.getJSONObject(0).getString("capital");
//
//        // проверка, что столицей является Осло
//        Assert.assertEquals(capital, "Kiev", "Wrong country capital is found.");
    }

}