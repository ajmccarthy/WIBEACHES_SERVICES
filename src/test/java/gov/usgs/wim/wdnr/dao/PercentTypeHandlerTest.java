package gov.usgs.wim.wdnr.dao;

import org.apache.ibatis.type.JdbcType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class PercentTypeHandlerTest {

    private PercentTypeHandler handler;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @Mock
    private CallableStatement cs;

    @Before
    public void setup() {
        handler = new PercentTypeHandler();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setParameterTest() throws SQLException {
        handler.setParameter(ps, 0, "None", JdbcType.CHAR);
        verify(ps).setString(0, "NONE");
        handler.setParameter(ps, 0, "1-20%", JdbcType.CHAR);
        verify(ps).setString(0, "LOW");
        handler.setParameter(ps, 0, "21-50%", JdbcType.CHAR);
        verify(ps).setString(0, "MODERATE");
        handler.setParameter(ps, 0, ">50%", JdbcType.CHAR);
        verify(ps).setString(0, "HIGH");
    }

    @Test
    public void testConvertResult() {
        assertNull(handler.convertResult(null));
        assertEquals("None", handler.convertResult("NONE"));
        assertEquals("1-20%", handler.convertResult("LOW"));
        assertEquals("21-50%", handler.convertResult("MODERATE"));
        assertEquals(">50%", handler.convertResult("HIGH"));
    }

    @Test
    public void testConvertParam() {
        assertNull(handler.convertParam(null));
        assertEquals("NONE", handler.convertParam("None"));
        assertEquals("LOW", handler.convertParam("1-20%"));
        assertEquals("MODERATE", handler.convertParam("21-50%"));
        assertEquals("HIGH", handler.convertParam(">50%"));
    }

    @Test
    public void getResultStrTest() throws SQLException {
        given(rs.getString(anyString())).willReturn(null);
        assertNull(handler.getResult(rs, "colname"));
        given(rs.getString(anyString())).willReturn("NONE");
        assertEquals("None", handler.getResult(rs, "colname"));
        given(rs.getString(anyString())).willReturn("LOW");
        assertEquals("1-20%", handler.getResult(rs, "colname"));
        given(rs.getString(anyString())).willReturn("MODERATE");
        assertEquals("21-50%", handler.getResult(rs, "colname"));
        given(rs.getString(anyString())).willReturn("HIGH");
        assertEquals(">50%", handler.getResult(rs, "colname"));
    }


    @Test
    public void getResultIntTest() throws SQLException {
        given(rs.getString(anyInt())).willReturn(null);
        assertNull(handler.getResult(rs, 23));
        given(rs.getString(anyInt())).willReturn("NONE");
        assertEquals("None", handler.getResult(rs, 23));
        given(rs.getString(anyInt())).willReturn("LOW");
        assertEquals("1-20%", handler.getResult(rs, 23));
        given(rs.getString(anyInt())).willReturn("MODERATE");
        assertEquals("21-50%", handler.getResult(rs, 23));
        given(rs.getString(anyInt())).willReturn("HIGH");
        assertEquals(">50%", handler.getResult(rs, 23));
    }

    @Test
    public void getResultCallableTest() throws SQLException {
        given(cs.getString(anyInt())).willReturn(null);
        assertNull(handler.getResult(cs, 23));
        given(cs.getString(anyInt())).willReturn("NONE");
        assertEquals("None", handler.getResult(cs, 23));
        given(cs.getString(anyInt())).willReturn("LOW");
        assertEquals("1-20%", handler.getResult(cs, 23));
        given(cs.getString(anyInt())).willReturn("MODERATE");
        assertEquals("21-50%", handler.getResult(cs, 23));
        given(cs.getString(anyInt())).willReturn("HIGH");
        assertEquals(">50%", handler.getResult(cs, 23));
    }

}
