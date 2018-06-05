package gov.usgs.wim.wdnr.Validation;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import gov.usgs.wim.wdnr.spring.TestSpringConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestSpringConfig.class)

public class UniqueKeyValidatorForSanitaryDataTest {


    @Mock
    protected SanitaryDataDao dao;

    private UniqueKeyValidatorForSanitaryData ukv;

    private SanitaryData value;

    @Mock
    private ConstraintValidatorContext context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ukv = new UniqueKeyValidatorForSanitaryData();
        value = new SanitaryData();
        Whitebox.setInternalState(ukv, "dao", dao);
    }

    @Test
    public void isValidTrueTest() {
        assertTrue(ukv.isValid(null, null));
    }

    @Test
    public void isValidFalseTest() {
        value.setMonitorSiteSeq("2");
        value.setSampleDateTime(LocalDateTime.now());
        given(dao.checkUniqueKey(anyMap())).willReturn(1);
        assertFalse(ukv.isValid(value, context));

    }

}
