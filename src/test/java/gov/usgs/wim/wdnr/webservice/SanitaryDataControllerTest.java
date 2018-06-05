package gov.usgs.wim.wdnr.webservice;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import gov.usgs.wim.wdnr.dao.StreamingDao;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;

public class SanitaryDataControllerTest {

    @Mock
    private SanitaryDataDao sanitaryDataDao;
    @Mock
    private Validator validator;
    @Mock
    private Authentication auth;
    @Mock
    private SecurityContext securityContext;

    private List<SanitaryData> sd;
    private Set<ConstraintViolation<SanitaryData>> errors;
    private ConstraintViolation<SanitaryData> err;


    private SanitaryData san;
    private SanitaryDataController controller;
    private MockHttpServletResponse response;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        sd = new ArrayList<SanitaryData>();
        controller = new SanitaryDataController(sanitaryDataDao, validator);
        san = new SanitaryData();
        response = new MockHttpServletResponse();
        errors = new HashSet<ConstraintViolation<SanitaryData>>();
        err = ConstraintViolationImpl.forBeanValidation("x", null, null, null, null, null, null, PathImpl.createPathFromString("x"), null, null, null);
    }

    @Test
    public void getUseridDefaultTest() {
        assertEquals(433, controller.getUserid());
    }

    @Test
    public void getUseridTest() {
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);
        Mockito.when(auth.getName()).thenReturn("aName");
        given(sanitaryDataDao.getUserid(anyString())).willReturn(500);
        assertEquals(500, controller.getUserid());
    }

    @Test
    public void checkValsTest() {
        san.setPart1Comments("; ");
        san.setPart4Comments("; ; ; ; ");
        san.setFloatStreetLitter(true);
        san.setFloatOther(false);
        san.setFloatMedical(false);
        san.setFloatBldgMaterials(false);
        san.setFloatFishing(false);
        san.setFloatFood(false);
        san.setFloatSewage(false);
        san.setWindDirDesc("Calm");
        controller.checkVals(san);
        assertNull(san.getPart1Comments());
        assertNull(san.getPart4Comments());
        assertTrue(san.getFloatablesFlag());
        assertNull(san.getWindDirDesc());
        san.setPart1Comments("; ");
        san.setPart4Comments("; ; ; ; ");
        san.setFloatStreetLitter(false);
        san.setFloatOther(true);
        san.setWindDirDesc("Calm");
        san.setWindSpeed("5");
        controller.checkVals(san);
        assertEquals("Calm, no direction", san.getWindDirDesc());
        assertTrue(san.getFloatablesFlag());
    }

    @Test
    public void createTest() {

        controller.create(san, response);
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void checkErrorsTrueTest() {
        assertTrue(controller.checkErrors(sd, response));
    }

    @Test
    public void checkErrorsFalseTest() {
        sd.add(san);
        errors.add(err);
        given(validator.validate(san)).willReturn(errors);
        assertFalse(controller.checkErrors(sd, response));
    }

    @Test
    public void createSanitaryDataTest() throws IOException {
        san.setPart1Comments("; ");
        san.setPart4Comments("; ; ; ; ");
        san.setFloatStreetLitter(true);
        san.setFloatOther(false);
        san.setFloatMedical(false);
        san.setFloatBldgMaterials(false);
        san.setFloatFishing(false);
        san.setFloatFood(false);
        san.setFloatSewage(false);
        san.setWindDirDesc("Calm");
        sd.add(san);
        assertEquals(sd, controller.createSanitaryData(sd, response));
    }




}
