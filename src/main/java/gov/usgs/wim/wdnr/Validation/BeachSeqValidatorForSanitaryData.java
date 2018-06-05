package gov.usgs.wim.wdnr.Validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeachSeqValidatorForSanitaryData implements ConstraintValidator<BeachSeq, SanitaryData> {

    @Autowired
    private SanitaryDataDao dao;

    @Override
    public void initialize(BeachSeq constraintAnnotation) {
    }

    @Override
    public boolean isValid(SanitaryData value, ConstraintValidatorContext context) {
        boolean valid = true;

        if (null != value && null != context) {
            if (null != value.getBeachSeq()) {
                Map<String, Object> filters = new HashMap<String,Object>();
                filters.put("BEACH_SEQ", value.getBeachSeq());
                int beachSeq = dao.checkBeachSeq(filters);
                if (beachSeq < 1) {
                    valid = false;
                }
            }
        }

        return valid;
    }
}