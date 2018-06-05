package gov.usgs.wim.wdnr.Validation;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintValidatorContext;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyMap;

import org.powermock.reflect.Whitebox;

public class MonitorSiteValidatorForSanitaryDataTest {

    @Mock
    protected SanitaryDataDao dao;

    private MonitorSiteValidatorForSanitaryData msv;

    @Mock
    private SanitaryData value;
    @Mock
    private ConstraintValidatorContext context;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        msv = new MonitorSiteValidatorForSanitaryData();
        Whitebox.setInternalState(msv, "dao", dao);
    }

    @Test
    public void isValidTrueTest() {
        assertTrue(msv.isValid(null, null));
    }

    @Test
    public void isValidFalseTest() {
        given(value.getBeachSeq()).willReturn("2");
        given(value.getMonitorSiteSeq()).willReturn("79");
        assertFalse(msv.isValid(value, context));

    }
}
