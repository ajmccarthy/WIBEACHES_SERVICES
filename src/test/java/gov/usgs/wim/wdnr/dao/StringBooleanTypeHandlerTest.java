package gov.usgs.wim.wdnr.dao;

import org.apache.ibatis.type.JdbcType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class StringBooleanTypeHandlerTest {

    private StringBooleanTypeHandler handler;

    @Mock
    private PreparedStatement ps;

    @Mock
    private ResultSet rs;

    @Mock
    private CallableStatement cs;

    @Before
    public void setup() {
        handler = new StringBooleanTypeHandler();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void setParameterTrueTest() throws SQLException {
        handler.setParameter(ps, 0, true, JdbcType.CHAR);
        verify(ps).setString(0, "Y");
    }

    @Test
    public void setParameterFalseTest() throws SQLException {
        handler.setParameter(ps, 0, false, JdbcType.CHAR);
        verify(ps).setString(0, null);
    }

    @Test
    public void setParameterNullTest() throws SQLException {
        handler.setParameter(ps, 0, null, JdbcType.CHAR);
        verify(ps).setString(0, null);
    }

    @Test
    public void getResultStrFalseTest() throws SQLException {
        given(rs.getString(anyString())).willReturn("string");
        assertFalse(handler.getResult(rs, "colname"));
    }

    @Test
    public void getResultStrTrueTest() throws SQLException {
        given(rs.getString(anyString())).willReturn("Y");
        assertTrue(handler.getResult(rs, "colname"));
    }

    @Test
    public void getResultIntFalseTest() throws SQLException {
        given(rs.getString(anyInt())).willReturn("string");
        assertFalse(handler.getResult(rs, 23));
    }

    @Test
    public void getResultIntTrueTest() throws SQLException {
        given(rs.getString(anyInt())).willReturn("Y");
        assertTrue(handler.getResult(rs, 23));
    }

    @Test
    public void getResultCallableFalseTest() throws SQLException {
        given(cs.getString(anyInt())).willReturn("string");
        assertFalse(handler.getResult(cs, 23));
    }

    @Test
    public void getResultCallableTrueTest() throws SQLException {
        given(cs.getString(anyInt())).willReturn("Y");
        assertTrue(handler.getResult(cs, 23));
    }
}
