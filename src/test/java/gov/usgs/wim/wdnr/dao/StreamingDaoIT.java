package gov.usgs.wim.wdnr.dao;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

import gov.usgs.wim.wdnr.spring.CsvDataSetLoader;
import gov.usgs.wim.wdnr.spring.TestSpringConfig;

import java.util.ArrayList;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestSpringConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
public class StreamingDaoIT {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	StreamingDao streamingDao;

	private class TestResultHandler implements ResultHandler<Object> {
		public ArrayList<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		@Override
		public void handleResult(ResultContext<?> context) {
			results.add((Map<String, Object>) context.getResultObject());
		}
		public ArrayList<Map<String, Object>> getResults() {
			return results;
		}
	}

	TestResultHandler handler;

	@Before
	public void setUp() {

		streamingDao = new StreamingDao(sqlSessionFactory);
		handler = new TestResultHandler();
	}

	@After
	public void cleanup() {
		handler = null;
	}

	@Test
	@DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
	public void testLastUpdate() {
		assertEquals("1990-10-12T13:01:01", streamingDao.getLastUpdate(StreamingDao.BEACHES_RAW_DATA).toString());
	}

	@Test
	@DatabaseSetup("classpath:/testData/lastUpdate/fromBeachesRaw/")
	public void testStream() {

		streamingDao.stream(StreamingDao.BEACHES_RAW_DATA, handler);
		ArrayList<Map<String, Object>> results = handler.getResults();
		assertEquals(1, results.size());
		System.out.println(results.get(0));

	}



}
