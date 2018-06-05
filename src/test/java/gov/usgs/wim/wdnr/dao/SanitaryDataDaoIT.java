package gov.usgs.wim.wdnr.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import gov.usgs.wim.wdnr.spring.CsvDataSetLoader;
import gov.usgs.wim.wdnr.spring.TestSpringConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support
        .DependencyInjectionTestExecutionListener;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestSpringConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class SanitaryDataDaoIT {

    public static final String qry = "select beach_seq,monitor_site_seq," +
            "ecoli_sample_type,sample_date_time,no_gulls,no_geese,no_dogs," +
            "no_animals_other,animals_other_desc,num_loons,num_herr_gulls," +
            "num_ring_gulls,num_cormorants,num_longtail_ducks,num_scoter," +
            "num_horn_grebe,num_rednecked_grebe,num_dead_fish,num_other," +
            "num_other_desc,float_street_litter,float_food,float_medical," +
            "float_sewage,float_bldg_materials,float_fishing,float_other," +
            "float_other_desc,debris_street_litter,debris_food," +
            "debris_medical,debris_sewage,debris_bldg_materials," +
            "debris_fishing,debris_household,debris_tar,debris_oil," +
            "debris_other,debris_other_desc,debris_amount,no_in_water," +
            "num_out_of_water,no_people_boating,no_people_fishing," +
            "no_people_surfing,no_people_windsurfing,no_people_diving," +
            "no_people_clamming,no_people_other,no_people_other_desc," +
            "air_temp,air_units,wind_speed,wind_speed_units,wind_dir_degrees," +
            "wind_dir_desc,weather_desc,rainfall_last_event,rainfall," +
            "rainfall_units,rainfall_stn_desc,wave_height,wave_height_units," +
            "est_act_flag,wave_direction,wave_intensity,current_speed," +
            "longshore_current_units,shoreline_current_dir,ph,color_change," +
            "color_description,odor_description,odor_other_description," +
            "avg_water_temp,avg_water_temp_units,clarity_desc,ntu," +
            "secchi_tube_cm,algae_nearshore,algae_on_beach," +
            "algae_type_periphyton,algae_type_globular," +
            "algae_type_freefloating,algae_type_other,algae_type_other_desc," +
            "algae_color_lt_green,algae_color_brght_green," +
            "algae_color_drk_green,algae_color_yellow,algae_color_brown," +
            "algae_color_other,algae_color_other_desc,part_1_comments," +
            "part2_comments,part3_comments,part4_comments," +
            "sampler_seq, data_entry_seq from " +
            "sanitary_data_fact";

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    SanitaryDataDao sanitaryDataDao;

    @Before
    public void setUp() {
        sanitaryDataDao = new SanitaryDataDao(sqlSessionFactory);
    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void getUseridNull() {

        assertNull(sanitaryDataDao.getUserid("testusername"));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void getUserid() {

        assertEquals((Integer) 1234567, sanitaryDataDao.getUserid
                ("testapexname"));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkNonUniqueKey() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("MONITOR_SITE_SEQ", 134);
        filters.put("SAMPLE_DATE_TIME", LocalDateTime.of(2018, 04, 03, 15,
                27, 07, 0));

        assertEquals((Integer) 1, sanitaryDataDao.checkUniqueKey(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkUniqueKey() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("MONITOR_SITE_SEQ", 135);
        filters.put("SAMPLE_DATE_TIME", LocalDateTime.of(2018, 04, 03, 15,
                27, 07, 0));

        assertEquals((Integer) 0, sanitaryDataDao.checkUniqueKey(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkZeroBeachSeq() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("BEACH_SEQ", 5);
        assertEquals((Integer) 0, sanitaryDataDao.checkBeachSeq(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkBeachSeq() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("BEACH_SEQ", 1);
        assertEquals((Integer) 1, sanitaryDataDao.checkBeachSeq(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkZeroMonitorSite() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("MONITOR_SITE_SEQ", 5);
        filters.put("BEACH_SEQ", 6);
        assertEquals((Integer) 0, sanitaryDataDao.checkMonitorSite(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    public void checkMonitorSite() {

        Map<String, Object> filters = new HashMap<String, Object>();
        filters.put("MONITOR_SITE_SEQ", 2);
        filters.put("BEACH_SEQ", 1);
        assertEquals((Integer) 1, sanitaryDataDao.checkMonitorSite(filters));

    }

    @Test
    @DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
    @ExpectedDatabase(
            table = "sanitary_data_fact",
            value = "classpath:/testResult/createSanitaryData/",
            assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED,
            query = qry
    )
    public void testCreate() {
        SanitaryData sd = new SanitaryData();
        sd.setBeachSeq("120");
        sd.setMonitorSiteSeq("135");
        sd.setEcoliSampleType(true);
        sd.setSampleDateTime(LocalDateTime.of(2018, 04, 03, 15, 27, 8));
        sd.setNoGulls("99");
        sd.setNoGeese("2");
        sd.setNoDogs("3");
        sd.setNoAnimalsOther("98");
        sd.setAnimalsOtherDesc("Abd");
        sd.setNumLoons("5");
        sd.setNumHerrGulls("6");
        sd.setNumRingGulls("7");
        sd.setNumCormorants("8");
        sd.setNumLongtailDucks("9");
        sd.setNumScoter("10");
        sd.setNumHornGrebe("11");
        sd.setNumRedneckedGrebe("12");
        sd.setNumDeadFish("13"); //deadfish
        sd.setNumOther("14");
        sd.setNumOtherDesc("deg");
        sd.setFloatStreetLitter(true);
        sd.setFloatFood(true);
        sd.setFloatMedical(true);
        sd.setFloatSewage(true);
        sd.setFloatBldgMaterials(true);
        sd.setFloatFishing(true);
        sd.setFloatOther(true);
        sd.setFloatOtherDesc("ghj");
        sd.setDebrisStreetLitter(true);
        sd.setDebrisFood(true);
        sd.setDebrisMedical(true);
        sd.setDebrisSewage(true);
        sd.setDebrisBldgMaterials(true);
        sd.setDebrisFishing(true);
        sd.setDebrisHousehold(true);
        sd.setDebrisTar(true);
        sd.setDebrisOil(true);
        sd.setDebrisOther(true);
        sd.setDebrisOtherDesc("asde");
        sd.setDebrisAmount("1-20%");
        sd.setNoInWater("15");
        sd.setNumOutOfWater("16");
        sd.setNoPeopleBoating("17");
        sd.setNoPeopleFishing("18");
        sd.setNoPeopleSurfing("19");
        sd.setNoPeopleWindsurfing("20");
        sd.setNoPeopleClamming("22");
        sd.setNoPeopleOther("23");
        sd.setNoPeopleOtherDesc("jk;");
        sd.setAirTemp("66");
        sd.setAirUnits("S");
        sd.setWindSpeed("24");
        sd.setWindSpeedUnits("KPH");
        sd.setWindDirDegrees("25");
        sd.setWindDirDesc("SW");
        sd.setWeatherDesc("XX");
        sd.setRainfallLastEvent(">54");
        sd.setRainfall("26");
        sd.setRainfallUnits("cm");
        sd.setRainfallStdDesc("YY");
        sd.setWaveHeight("27");
        sd.setWaveHeightUnits("meters");
        sd.setEstActFlag(true);
        sd.setWaveDirection("V");
        sd.setWaveIntensity("abnormal");
        sd.setCurrentSpeed("28");
        sd.setLongshoreCurrentUnits("m/sec");
        sd.setShorelineCurrentDir("NW");
        sd.setpH("29");
        sd.setColorChange(true);
        sd.setColorDescription("ugly");
        sd.setOdorDescription("fragrant");
        sd.setOdorOtherDescription("very fragrant");
        sd.setAvgWaterTemp("30");
        sd.setAvgWaterTempUnits("X");
        sd.setClarityDesc("muddy");
        sd.setNtu("31");
        sd.setSecchiTubeCm("32");
        sd.setAlgaeNearShore("1-20%");
        sd.setAlgaeOnBeach("1-20%");
        sd.setAlgaeTypePeriphyton(true);
        sd.setAlgaeTypeGlobular(true);
        sd.setAlgaeTypeFreefloating(true);
        sd.setAlgaeTypeOther(true);
        sd.setAlgaeTypeOtherDesc("lame algae");
        sd.setAlgaeColorLtGreen(true);
        sd.setAlgaeColorBrightGreen(true);
        sd.setAlgaeColorDrkGreen(true);
        sd.setAlgaeColorYellow(true);
        sd.setAlgaeColorBrown(true);
        sd.setAlgaeColorOther(true);
        sd.setAlgaeColorOtherDesc("jazzy");
        sd.setPart1Comments("cmt1");
        sd.setPart2Coments("cmt2");
        sd.setPart3Comments("cmt3");
        sd.setPart4Comments("cmt4");
        sd.setSamplerSeq(33);
        sd.setDataEntrySeq(34);
        sanitaryDataDao.create(sd);
    }
}
