package gov.usgs.wim.wdnr.webservice;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import gov.usgs.wim.wdnr.domain.ValidationResults;
import gov.usgs.wim.wdnr.domain.ValidatorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import gov.usgs.wim.wdnr.dao.SanitaryDataDao;
import gov.usgs.wim.wdnr.domain.SanitaryData;
import gov.usgs.wim.wdnr.domain.Views;
import io.swagger.annotations.Api;

@Api(tags={"Sanitary Data"})
@RestController
@RequestMapping("/sanitaryData")
public class SanitaryDataController {

    private SanitaryDataDao sDao;
    private Validator validator;
    private Set<ConstraintViolation<SanitaryData>> errors;

    @Autowired
    public SanitaryDataController(final SanitaryDataDao sDao, final Validator validator) {
        this.sDao = sDao;
        this.validator =  validator;
    }


    private static final Logger log = LoggerFactory.getLogger(SanitaryDataController.class);


    public static final String UNKNOWN_USERNAME = "unknown ";


//    @PreAuthorize("hasPermission(#sd, null)")
    @PostMapping()
    @JsonView(Views.Response.class)
    public List<SanitaryData> createSanitaryData(@RequestBody List<SanitaryData> sd, HttpServletResponse response) throws IOException {
        boolean noErrors = checkErrors(sd, response);

        if (noErrors) {
            for (int i = 0; i < sd.size(); i++) {
                checkVals(sd.get(i));
                create(sd.get(i), response);
            }
        }

        return sd;
    }


    protected int getUserid() {
        int userid = 433;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication && !(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            log.debug(username);
            //call our to table to get user id
            // through new dao method
            userid = sDao.getUserid(username);
        }
        return userid;
    }

    public boolean checkErrors(List<SanitaryData> sd, HttpServletResponse response) {
        boolean noErrors = true;
        for (int i = 0; i < sd.size(); i++) {
            errors = validator.validate(sd.get(i));
            sd.get(i).setValidationErrors(errors);
            if (!errors.isEmpty()) {
                noErrors = false;
                response.setStatus(400);
            }
        }
        return noErrors;
    }

    public void create(SanitaryData san, HttpServletResponse response) {
        int userid = getUserid();

            san.setSamplerSeq(userid);
            san.setDataEntrySeq(userid);
            sDao.create(san);
            response.setStatus(HttpStatus.CREATED.value());
    }

    public void checkVals(SanitaryData san) {

        if (san.getFloatStreetLitter() == true
                || san.getFloatFood() == true
                || san.getFloatMedical() == true
                || san.getFloatSewage() == true
                || san.getFloatBldgMaterials() == true
                || san.getFloatFishing() == true
                || san.getFloatOther() == true ) {
            san.setFloatablesFlag(true);
        }

        if (san.getWindSpeed() == null && san.getWindDirDesc().equals("Calm")) {
            san.setWindDirDesc(null);
        }

        else if (san.getWindSpeed() != null && san.getWindDirDesc().equals("Calm")) {
            san.setWindDirDesc("Calm, no direction");
        }

        if (san.getPart1Comments().equals("; ")) {
            san.setPart1Comments(null);
        }

        if (san.getPart4Comments().equals("; ; ; ; ")) {
            san.setPart4Comments(null);
        }
    }



}