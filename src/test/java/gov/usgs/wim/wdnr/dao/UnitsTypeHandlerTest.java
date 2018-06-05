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
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class UnitsTypeHandlerTest {

    private UnitsTypeHandler handler;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @Mock
    private CallableStatement cs;

    @Before
    public void setup() {
        handler = new UnitsTypeHandler();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setParameterTest() throws SQLException {
        handler.setParameter(ps, 0, "MPH", JdbcType.CHAR);
        verify(ps).setString(0, "mph");
        handler.setParameter(ps, 0, "FT", JdbcType.CHAR);
        verify(ps).setString(0, "Ft");

    }

    @Test
    public void testConvert() {
        assertEquals("mph", handler.convert("MPH"));
        assertEquals("Ft", handler.convert("FT"));
    }


    @Test
    public void getResultStrTest() throws SQLException {
        given(rs.getString(anyString())).willReturn("string");
        assertEquals("string", handler.getResult(rs, "colname"));
    }

    @Test
    public void getResultIntTest() throws SQLException {
        given(rs.getString(anyInt())).willReturn("string");
        assertEquals("string", handler.getResult(rs, 23));
    }

    @Test
    public void getResultCallableTest() throws SQLException {
        given(cs.getString(anyInt())).willReturn("string");
        assertEquals("string", handler.getResult(cs, 23));
    }


}
