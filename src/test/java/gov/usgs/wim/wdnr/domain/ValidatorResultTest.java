package gov.usgs.wim.wdnr.domain;

import org.junit.Test;

public class ValidatorResultTest {

    @Test
    public void ValidatorResultUnitTest() {
        ValidatorResult vr = new ValidatorResult("in", "msg", "val");
        assert(vr.getField().equals("in"));
        assert(vr.getMessage()).equals("msg");

    }

    @Test
    public void testSetField() {
        ValidatorResult vr = new ValidatorResult("in", "msg", "val");
        vr.setField("newfield");
        assert(vr.getField()).equals("newfield");

    }

    @Test
    public void testToString() {
        ValidatorResult vr = new ValidatorResult("in", "msg", "val");
        assert(vr.toString().equals("Field:in - Message:msg - "));

    }

}
