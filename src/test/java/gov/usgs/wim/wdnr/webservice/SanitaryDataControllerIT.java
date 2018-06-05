package gov.usgs.wim.wdnr.webservice;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.usgs.wim.wdnr.spring.CsvDataSetLoader;
import gov.usgs.wim.wdnr.spring.SpringConfig;
import gov.usgs.wim.wdnr.spring.TestSpringConfig;
import org.json.JSONArray;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONArrayAs;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestSpringConfig.class, SpringConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class SanitaryDataControllerIT {

    private MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    @DatabaseSetup("classpath:/testData/postSanitaryData/prePprePost/")
    @ExpectedDatabase(
            table = "sanitary_data_fact",
            value = "classpath:/testResult/createSanitaryData/",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED
    )
    public void postSanitaryData() throws Exception {

        String x = "[{\n" +
                "\"user_name\": \"Hilbilly Hank\",\n" +
                "\"user_id\": \"HbH316\",\n" +
                "\"__favorites\": \"\",\n" +
                "\"__county\": \"Ozaukee\",\n" +
                "\"__lake\": \"Lake Michigan\",\n" +
                "\"__beach\": \"County Road D Boat Launch Beach\",\n" +
                "\"BEACH_SEQ\": \"119\",\n" +
                "\"__site\": \"County Hwy D\",\n" +
                "\"MONITOR_SITE_SEQ\": \"134\",\n" +
                "\"ECOLI_SAMPLE_TYPE\": true,\n" +
                "\"SAMPLE_DATE_TIME_DISPLAYED\": \"2018-04-19T15:27\",\n" +
                "\"SAMPLE_DATE_TIME\": \"2018-04-19T15:30:07.242Z\",\n" +
                "\"NO_GULLS\": \"1\",\n" +
                "\"NO_GEESE\": \"2\",\n" +
                "\"NO_DOGS\": \"3\",\n" +
                "\"NO_ANIMALS_OTHER\": \"4\",\n" +
                "\"ANIMALS_OTHER_DESC\": \"There were a few deer there\",\n" +
                "\"NUM_LOONS\": \"1\",\n" +
                "\"NUM_HERR_GULLS\": \"2\",\n" +
                "\"NUM_RING_GULLS\": \"3\",\n" +
                "\"NUM_CORMORANTS\": \"4\",\n" +
                "\"NUM_LONGTAIL_DUCKS\": \"5\",\n" +
                "\"NUM_SCOTER\": \"6\",\n" +
                "\"NUM_HORN_GREBE\": \"7\",\n" +
                "\"NUM_REDNECKED_GREBE\": \"8\",\n" +
                "\"NUM_DEAD_FISH\": \"5\",\n" +
                "\"NUM_OTHER\": \"10\",\n" +
                "\"NUM_OTHER_DESC\": \"some poacher shot\",\n" +
                "\"FLOAT_STREET_LITTER\": true,\n" +
                "\"FLOAT_FOOD\": true,\n" +
                "\"FLOAT_MEDICAL\": true,\n" +
                "\"FLOAT_SEWAGE\": true,\n" +
                "\"FLOAT_BLDG_MATERIALS\": true,\n" +
                "\"FLOAT_FISHING\": true,\n" +
                "\"FLOAT_OTHER\": true,\n" +
                "\"FLOAT_OTHER_DESC\": \"paper was everywhere\",\n" +
                "\"DEBRIS_STREET_LITTER\": true,\n" +
                "\"DEBRIS_FOOD\": true,\n" +
                "\"DEBRIS_MEDICAL\": true,\n" +
                "\"DEBRIS_SEWAGE\": true,\n" +
                "\"DEBRIS_BLDG_MATERIALS\": true,\n" +
                "\"DEBRIS_FISHING\": true,\n" +
                "\"DEBRIS_HOUSEHOLD\": true,\n" +
                "\"DEBRIS_TAR\": true,\n" +
                "\"DEBRIS_OIL\": true,\n" +
                "\"DEBRIS_OTHER\": true,\n" +
                "\"DEBRIS_OTHER_DESC\": \"paper was really all over the place\",\n" +
                "\"DEBRIS_AMOUNT\": \"21-50%\",\n" +
                "\"DEBRIS_COMMENTS\": \"\",\n" +
                "\"NO_IN_WATER\": \"1\",\n" +
                "\"NUM_OUT_OF_WATER\": \"2\",\n" +
                "\"NO_PEOPLE_BOATING\": \"3\",\n" +
                "\"NO_PEOPLE_FISHING\": \"4\",\n" +
                "\"NO_PEOPLE_SURFING\": \"5\",\n" +
                "\"NO_PEOPLE_WINDSURFING\": \"6\",\n" +
                "\"NUM_PEOPLE_DIVING\": \"7\",\n" +
                "\"NO_PEOPLE_CLAMMING\": \"8\",\n" +
                "\"NO_PEOPLE_OTHER\": \"9\",\n" +
                "\"NO_PEOPLE_OTHER_DESC\": \"some people out there flying drones\",\n" +
                "\"HUMAN_BATHERS_COMMENTS\": \"\",\n" +
                "\"AIR_TEMP\": \"75\",\n" +
                "\"AIR_UNITS\": \"F\",\n" +
                "\"WIND_SPEED\": \"2\",\n" +
                "\"WIND_SPEED_UNITS\": \"MPH\",\n" +
                "\"WIND_DIR_DEGREES\": \"3\",\n" +
                "\"WIND_DIR_DESC\": \"NE\",\n" +
                "\"WEATHER_DESC\": \"Lovely and sunny\",\n" +
                "\"RAINFALL_LAST_EVENT\": \"<72\",\n" +
                "\"RAINFALL\": \"3\",\n" +
                "\"RAINFALL_UNITS\": \"IN\",\n" +
                "\"RAINFALL_STN_DESC\": \"\",\n" +
                "\"WEATHER_COMMENTS\": \"\",\n" +
                "\"WAVE_HEIGHT\": \"1\",\n" +
                "\"WAVE_HEIGHT_UNITS\": \"FT\",\n" +
                "\"EST_ACT_FLAG\": true,\n" +
                "\"WAVE_DIRECTION\": \"E\",\n" +
                "\"WAVE_INTENSITY\": \"Normal\",\n" +
                "\"CURRENT_SPEED\": \"1\",\n" +
                "\"LONGSHORE_CURRENT_UNITS\": \"ft/sec\",\n" +
                "\"SHORELINE_CURRENT_DIR\": \"NE\",\n" +
                "\"WAVES_COMMENTS\": \"\",\n" +
                "\"PH\": \"7.5\",\n" +
                "\"COLOR_CHANGE\": true,\n" +
                "\"COLOR_DESCRIPTION\": \"pH strip got slightly lighter\",\n" +
                "\"ODOR_DESCRIPTION\": \"None\",\n" +
                "\"ODOR_OTHER_DESCRIPTION\": \"\",\n" +
                "\"AVG_WATER_TEMP\": \"68\",\n" +
                "\"AVG_WATER_TEMP_UNITS\": \"F\",\n" +
                "\"CLARITY_DESC\": \"Clear\",\n" +
                "\"NTU\": \"4\",\n" +
                "\"SECCHI_TUBE_CM\": \"60\",\n" +
                "\"WATER_COMMENTS\": \"\",\n" +
                "\"ALGAE_NEARSHORE\": \"1-20%\",\n" +
                "\"ALGAE_ON_BEACH\": \"1-20%\",\n" +
                "\"ALGAE_TYPE_PERIPHYTON\": true,\n" +
                "\"ALGAE_TYPE_GLOBULAR\": true,\n" +
                "\"ALGAE_TYPE_FREEFLOATING\": true,\n" +
                "\"ALGAE_TYPE_OTHER\": true,\n" +
                "\"ALGAE_TYPE_OTHER_DESC\": \"some other type of algae\",\n" +
                "\"ALGAE_COLOR_LT_GREEN\": true,\n" +
                "\"ALGAE_COLOR_BRGHT_GREEN\": true,\n" +
                "\"ALGAE_COLOR_DRK_GREEN\": true,\n" +
                "\"ALGAE_COLOR_YELLOW\": true,\n" +
                "\"ALGAE_COLOR_BROWN\": true,\n" +
                "\"ALGAE_COLOR_OTHER\": true,\n" +
                "\"ALGAE_COLOR_OTHER_DESC\": \"hot pink\",\n" +
                "\"ALGAE_COMMENTS\": \"\",\n" +
                "\"PART_1_COMMENTS\": \"\",\n" +
                "\"PART_2_COMMENTS\": \"\",\n" +
                "\"PART_3_COMMENTS\": \"\",\n" +
                "\"PART_4_COMMENTS\": \"\",\n" +
                "\"submitted\": true,\n" +
                "\"id\": \"811960c6-e107-ebb3-0a99-0fc60f857f72\",\n" +
                "\"date\": \"2018-04-19T20:30:50.700Z\"\n" +
                "}]";
        MvcResult rtn = mockMvc.perform(post("/sanitaryData").content(x).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();

        String y = rtn.getResponse().getContentAsString();
        JSONArray rtnAsJSONArray = getRtnAsJSONArray(rtn);

        assertEquals(1, rtnAsJSONArray.length());

        assertThat(rtnAsJSONArray,
                sameJSONArrayAs(new JSONArray("[{\"validationErrors\":{\"validationErrors\":[]},\"idNo\":\"811960c6-e107-ebb3-0a99-0fc60f857f72\"}]"))
                        .allowingAnyArrayOrdering());

    }

    public JSONArray getRtnAsJSONArray(MvcResult rtn) throws Exception {
        return new JSONArray(rtn.getResponse().getContentAsString());
    }
}
