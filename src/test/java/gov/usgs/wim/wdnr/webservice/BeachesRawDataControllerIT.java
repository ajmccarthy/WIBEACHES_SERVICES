package gov.usgs.wim.wdnr.webservice;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import gov.usgs.wim.wdnr.spring.CsvDataSetLoader;
import gov.usgs.wim.wdnr.spring.SpringConfig;
import gov.usgs.wim.wdnr.spring.TestSpringConfig;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.json.JSONArray;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONArrayAs;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestSpringConfig.class, SpringConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class BeachesRawDataControllerIT {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void getRawData() throws Exception {
        MvcResult rtn = mockMvc.perform(get("/beachesrawdata"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        JSONArray rtnAsJSONArray = getRtnAsJSONArray(rtn);

        assertEquals(1, rtnAsJSONArray.length());

        assertThat(rtnAsJSONArray,
                sameJSONArrayAs(new JSONArray("[{\"BEACH_SEQ\":\"1\",\"WATER_BODY_TYPE\":\"River\",\"WATERBODY_NAME\":\"Rock\",\"MONITOR_SITE_SEQ\":\"2\",\"BEACH_NAME\":\"First Beach\",\"STATION_NAME\":\"Rockem\",\"COUNTY\":\"Dodge\"}]"))
                        .allowingAnyArrayOrdering());
    }

    public JSONArray getRtnAsJSONArray(MvcResult rtn) throws Exception {
        return new JSONArray(rtn.getResponse().getContentAsString());
    }

}
