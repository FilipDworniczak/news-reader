package pl.fdworniczak.newsreader.rest;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.fdworniczak.newsreader.NewsReaderApplicationTests;
import pl.fdworniczak.newsreader.config.ApplicationConstants;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by filip on 05.01.19
 */

@SpringBootTest({"services.newsapi.url=http://127.0.0.1:8080"})
@AutoConfigureMockMvc
public class NewsControllerTests extends NewsReaderApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8080);

    @Test
    public void testGetNewsSuccess() throws Exception {
        wireMockRule.stubFor(any(urlMatching(ApplicationConstants.NEWS_API_TOP_HEADLINES + ".*"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(getResourceStringValue("/rest/newsApiTopHeadlinesSuccessResponse.json"))));

        MvcResult mvcResult = mockMvc.perform(get("/api/news/pl/technology")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String expectedJson = getResourceStringValue("/rest/getNewsSuccessResponse.json");
        String json = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(expectedJson, json);
    }
}
