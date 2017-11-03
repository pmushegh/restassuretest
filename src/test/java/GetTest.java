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

import java.util.Iterator;

import static com.jayway.restassured.RestAssured.given;
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
        //proxy = startClientAndProxy(1090);
        new MockServerClient("localhost", 1080).when(request().withMethod("GET").withPath("/id=\\b(1[0-3])")).respond(response().withStatusCode(200).withBody("Hello!!"));
        new MockServerClient("localhost", 1080).when(request().withMethod("GET").withPath("/id=2")).respond(response().withStatusCode(200));
    }

    @AfterClass
    public void stopProxy() {
        //proxy.stop();
        mockServer.stop();
    }

    @DataProvider(name = "testIDs")
    public Object[][] createTestIDs() {
        return new Object[][] {{10}, {11}, {12}, {13}, {14}, {15}};
    }

    @Test(dataProvider = "testIDs")
    public void checkTestIDs(int iID) throws Exception {
//        Response resp = get("http://localhost:1080/id=" + Integer.toString(iID));
//
//        Assert.assertEquals(resp.getStatusCode(), 200, "Response status code is incorrect.");

        //given().
        Response resp = get("http://localhost:1080/id=" + Integer.toString(iID));//.then().statusCode(200);
        Assert.assertEquals(resp.getBody().prettyPrint(), "diszhfhd");
    }


    @Test
    public void getRequestFindCapital() throws JSONException {

        // выполняем запрос get для доступа ко всем параметрам ответа
        Response resp = get("http://localhost:1080/id=12");

        Assert.assertEquals(resp.getStatusCode(), 200, "Response status code is incorrect.");

//        JSONArray jsonResponse = new JSONArray(resp.asString());
//
//        // получение параметра capital (столицы Норвегии)
//        String capital = jsonResponse.getJSONObject(0).getString("capital");
//
//        // проверка, что столицей является Осло
//        Assert.assertEquals(capital, "Kiev", "Wrong country capital is found.");
    }

}