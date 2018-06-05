package gov.usgs.wim.wdnr.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import gov.usgs.wim.wdnr.Validation.BeachSeq;
import gov.usgs.wim.wdnr.Validation.MonitorSite;
import gov.usgs.wim.wdnr.Validation.UniqueKey;
import org.hibernate.validator.constraints.Length;

@UniqueKey
@BeachSeq
@MonitorSite

@JsonIgnoreProperties(ignoreUnknown = true)
public class SanitaryData {

    protected ValidationResults validationErrors;

    @JsonAlias("user_name")
    @Length(min=0, max=50)
    private String userName;

    @JsonAlias("user_id")
    @Length(min=0, max=10)
    private String userId;

    @JsonAlias("__favorites")
    @Length(min=0, max=50)
    private String favorites;

    @JsonAlias("__county")
    @Length(min=0, max=50)
    private String county;

    @JsonAlias("__lake")
    @Length(min=0, max=50)
    private String lake;

    @JsonAlias("__beach")
    @Length(min=0, max=50)
    private String beach;

    @JsonAlias("BEACH_SEQ")
    @NotNull(message = "You must provide a beach for this record")
    @Digits(integer=8, fraction=0)
    private String beachSeq;

    @JsonAlias("__site")
    @Length(min=0, max=50)
    private String site;

    @JsonAlias("MONITOR_SITE_SEQ")
    @Digits(integer=8, fraction=0)
    @NotNull
    private String monitorSiteSeq;

    @JsonAlias("ECOLI_SAMPLE_TYPE")
    private Boolean ecoliSampleType;

    @JsonAlias("SAMPLE_DATE_TIME_DISPLAYED")
    @Length(min=0, max=50)
    private String sampleDateTimeDisplayed;

    @JsonAlias("SAMPLE_DATE_TIME")
    @NotNull(message="You must provide a sample date and time for this record")
    private LocalDateTime sampleDateTime;

    @JsonAlias("NO_GULLS")
    @Digits(integer=8, fraction=0)
    private String noGulls;

    @JsonAlias("NO_GEESE")
    @Digits(integer=8, fraction=0)
    private String noGeese;

    @JsonAlias("NO_DOGS")
    @Digits(integer=8, fraction=0)
    private String noDogs;

    @JsonAlias("NO_ANIMALS_OTHER")
    @Digits(integer=8, fraction=0)
    private String noAnimalsOther;

    @JsonAlias("ANIMALS_OTHER_DESC")
    @Length(min=0, max=50)
    private String animalsOtherDesc;

    @JsonAlias("NUM_LOONS")
    @Digits(integer=8, fraction=0)
    private String numLoons;

    @JsonAlias("NUM_HERR_GULLS")
    @Digits(integer=8, fraction=0)
    private String numHerrGulls;

    @JsonAlias("NUM_RING_GULLS")
    @Digits(integer=8, fraction=0)
    private String numRingGulls;

    @JsonAlias("NUM_CORMORANTS")
    @Digits(integer=8, fraction=0)
    private String numCormorants;

    @JsonAlias("NUM_LONGTAIL_DUCKS")
    @Digits(integer=8, fraction=0)
    private String numLongtailDucks;

    @JsonAlias("NUM_SCOTER")
    @Digits(integer=8, fraction=0)
    private String numScoter;

    @JsonAlias("NUM_HORN_GREBE")
    @Digits(integer=8, fraction=0)
    private String numHornGrebe;

    @JsonAlias("NUM_REDNECKED_GREBE")
    @Digits(integer=8, fraction=0)
    private String numRedneckedGrebe;

    @JsonAlias("NUM_DEAD_FISH") //???
    @Digits(integer=8, fraction=0)
    private String numDeadFish;

    @JsonAlias("NUM_OTHER")
    @Digits(integer=8, fraction=0)
    private String numOther;

    @JsonAlias("NUM_OTHER_DESC")
    @Length(min=0, max=50)
    private String numOtherDesc;

    @JsonAlias("FLOAT_STREET_LITTER")
    private Boolean floatStreetLitter;

    @JsonAlias("FLOAT_FOOD")
    private Boolean floatFood;

    @JsonAlias("FLOAT_MEDICAL")
    private Boolean floatMedical;

    @JsonAlias("FLOAT_SEWAGE")
    private Boolean floatSewage;

    @JsonAlias("FLOAT_BLDG_MATERIALS")
    private Boolean floatBldgMaterials;

    @JsonAlias("FLOAT_FISHING")
    private Boolean floatFishing;

    @JsonAlias("FLOAT_OTHER")
    private Boolean floatOther;

    @JsonAlias("FLOAT_OTHER_DESC")
    @Length(min=0, max=50)
    private String floatOtherDesc;

    @JsonAlias("DEBRIS_STREET_LITTER")
    private Boolean debrisStreetLitter;

    @JsonAlias("DEBRIS_FOOD")
    private Boolean debrisFood;

    @JsonAlias("DEBRIS_MEDICAL")
    private Boolean debrisMedical;

    @JsonAlias("DEBRIS_SEWAGE")
    private Boolean debrisSewage;

    @JsonAlias("DEBRIS_BLDG_MATERIALS")
    private Boolean debrisBldgMaterials;

    @JsonAlias("DEBRIS_FISHING")
    private Boolean debrisFishing;

    @JsonAlias("DEBRIS_HOUSEHOLD")
    private Boolean debrisHousehold;

    @JsonAlias("DEBRIS_TAR")
    private Boolean debrisTar;

    @JsonAlias("DEBRIS_OIL")
    private Boolean debrisOil;

    @JsonAlias("DEBRIS_OTHER")
    private Boolean debrisOther;

    @JsonAlias("DEBRIS_OTHER_DESC")
    @Length(min=0, max=255)
    private String debrisOtherDesc;

    @JsonAlias("DEBRIS_AMOUNT")
    @Length(min=0, max=30)
    private String debrisAmount;

    @JsonAlias("NO_IN_WATER")
    @Digits(integer=8, fraction=0)
    private String noInWater;

    @JsonAlias("NUM_OUT_OF_WATER")
    @Digits(integer=8, fraction=0)
    private String numOutOfWater;

    @JsonAlias("NO_PEOPLE_BOATING")
    @Digits(integer=8, fraction=0)
    private String noPeopleBoating;

    @JsonAlias("NO_PEOPLE_FISHING")
    @Digits(integer=8, fraction=0)
    private String noPeopleFishing;

    @JsonAlias("NO_PEOPLE_SURFING")
    @Digits(integer=8, fraction=0)
    private String noPeopleSurfing;

    @JsonAlias("NO_PEOPLE_WINDSURFING")
    @Digits(integer=8, fraction=0)
    private String noPeopleWindsurfing;

    @JsonAlias("NUM_PEOPLE_DIVING")
    @Digits(integer=8, fraction=0)
    private String numPeopleDiving;

    @JsonAlias("NO_PEOPLE_CLAMMING")
    @Digits(integer=8, fraction=0)
    private String noPeopleClamming;

    @JsonAlias("NO_PEOPLE_OTHER")
    @Digits(integer=8, fraction=0)
    private String noPeopleOther;

    @JsonAlias("NO_PEOPLE_OTHER_DESC")
    @Length(min=0, max=50)
    private String noPeopleOtherDesc;

    @JsonAlias("AIR_TEMP")
    @Digits(integer=8, fraction=4)
    private String airTemp;

    @JsonAlias("AIR_UNITS")
    @Length(min=0, max=1)
    private String airUnits;

    @JsonAlias("WIND_SPEED")
    @Digits(integer=8, fraction=4)
    private String windSpeed;

    @JsonAlias("WIND_SPEED_UNITS")
    @Length(min=0, max=30)
    private String windSpeedUnits;

    @JsonAlias("WIND_DIR_DEGREES")
    @Digits(integer=8, fraction=0)
    private String windDirDegrees;

    @JsonAlias("WIND_DIR_DESC")
    @Length(min=0, max=30)
    private String windDirDesc;

    @JsonAlias("WEATHER_DESC")
    @Length(min=0, max=30)
    private String weatherDesc;

    @JsonAlias("RAINFALL_LAST_EVENT")
    @Length(min=0, max=5)
    private String rainfallLastEvent;

    @JsonAlias("RAINFALL")
    @Digits(integer=8, fraction=4)
    private String rainfall;

    @JsonAlias("RAINFALL_UNITS")
    @Length(min=0, max=30)
    private String rainfallUnits;

    @JsonAlias("RAINFALL_STD_DESC")
    @Length(min=0, max=60)
    private String rainfallStdDesc;

    @JsonAlias("WAVE_HEIGHT")
    @Digits(integer=8, fraction=4)
    private String waveHeight;

    @JsonAlias("WAVE_HEIGHT_UNITS")
    @Length(min=0, max=30)
    private String waveHeightUnits;

    @JsonAlias("EST_ACT_FLAG")
    private Boolean estActFlag;

    @JsonAlias("WAVE_DIRECTION")
    @Length(min=0, max=10)
    private String waveDirection;

    @JsonAlias("WAVE_INTENSITY")
    @Length(min=0, max=30)
    private String waveIntensity;

    @JsonAlias("CURRENT_SPEED")
    @Digits(integer=8, fraction=0)
    private String currentSpeed;

    @JsonAlias("LONGSHORE_CURRENT_UNITS")
    @Length(min=0, max=30)
    private String longshoreCurrentUnits;

    @JsonAlias("SHORELINE_CURRENT_DIR")
    @Length(min=0, max=30)
    private String shorelineCurrentDir;

    @JsonAlias("PH")
    @Digits(integer=5, fraction=2)
    private String pH;

    @JsonAlias("COLOR_CHANGE")
    private Boolean colorChange;

    @JsonAlias("COLOR_DESCRIPTION")
    @Length(min=0, max=50)
    private String colorDescription;

    @JsonAlias("ODOR_DESCRIPTION")
    @Length(min=0, max=50)
    private String odorDescription;

    @JsonAlias("ODOR_OTHER_DESCRIPTION")
    @Length(min=0, max=50)
    private String odorOtherDescription;

    @JsonAlias("AVG_WATER_TEMP")
    @Digits(integer=7, fraction=2)
    private String avgWaterTemp;

    @JsonAlias("AVG_WATER_TEMP_UNITS")
    @Length(min=0, max=10)
    private String avgWaterTempUnits;

    @JsonAlias("CLARITY_DESC")
    @Length(min=0, max=30)
    private String clarityDesc;

    @JsonAlias("NTU")
    @Digits(integer=10, fraction=2)
    private String ntu;

    @JsonAlias("SECCHI_TUBE_CM")
    @Digits(integer=8, fraction=0)
    private String secchiTubeCm;

    @JsonAlias("ALGAE_NEARSHORE")
    @Length(min=0, max=30)
    private String algaeNearshore;

    @JsonAlias("ALGAE_ON_BEACH")
    @Length(min=0, max=30)
    private String algaeOnBeach;

    @JsonAlias("ALGAE_TYPE_PERIPHYTON")
    private Boolean algaeTypePeriphyton;

    @JsonAlias("ALGAE_TYPE_GLOBULAR")
    private Boolean algaeTypeGlobular;

    @JsonAlias("ALGAE_TYPE_FREEFLOATING")
    private Boolean algaeTypeFreefloating;

    @JsonAlias("ALGAE_TYPE_OTHER")
    private Boolean algaeTypeOther;

    @JsonAlias("ALGAE_TYPE_OTHER_DESC")
    @Length(min=0, max=50)
    private String algaeTypeOtherDesc;

    @JsonAlias("ALGAE_COLOR_LT_GREEN")
    private Boolean algaeColorLtGreen;

    @JsonAlias("ALGAE_COLOR_BRGHT_GREEN") ////????
    private Boolean algaeColorBrightGreen;

    @JsonAlias("ALGAE_COLOR_DRK_GREEN")
    private Boolean algaeColorDrkGreen;

    @JsonAlias("ALGAE_COLOR_YELLOW")
    private Boolean algaeColorYellow;

    @JsonAlias("ALGAE_COLOR_BROWN")
    private Boolean algaeColorBrown;

    @JsonAlias("ALGAE_COLOR_OTHER")
    private Boolean algaeColorOther;

    @JsonAlias("ALGAE_COLOR_OTHER_DESC")
    @Length(min=0, max=50)
    private String algaeColorOtherDesc;

    @JsonAlias("PART_1_COMMENTS")
    @Length(min=0, max=1000) ///???
    private String part1Comments;

    @JsonAlias("PART_2_COMMENTS")
    @Length(min=0, max=1000) ///???
    private String part2Comments;

    @JsonAlias("PART_3_COMMENTS")
    @Length(min=0, max=1000) ///???
    private String part3Comments;

    @JsonAlias("PART_4_COMMENTS")
    @Length(min=0, max=1000) ///???
    private String part4Comments;

    @JsonAlias("DATE_ENTERED")
//    @Basic //TODO
    private LocalDateTime dateEntered;

    @JsonAlias("DATE_UPDATED")
//    @Basic //TODO
    private LocalDateTime dateUpdated;

    @JsonAlias("vPages")
    @Length(min=0, max=50)
    private String vPages;

    @JsonAlias("submitted")
    @Length(min=0, max=5)
    private String submitted;

    @JsonView(Views.Response.class)
    @JsonAlias("id")
    private String idNo;

    @JsonAlias("date")
    private LocalDateTime date;

    @Digits(integer=8, fraction=0)
    private int samplerSeq;

    @Digits(integer=8, fraction=0)
    private int dataEntrySeq;

    private Boolean floatablesFlag;


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setLake(String lake) {
        this.lake = lake;
    }

    public void setBeach(String beach) {
        this.beach = beach;
    }

    public void setBeachSeq(String beachSeq) {
        this.beachSeq = beachSeq;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setMonitorSiteSeq(String monitorSiteSeq) {
        this.monitorSiteSeq = monitorSiteSeq;
    }

    public void setEcoliSampleType(Boolean ecoliSampleType) {
        this.ecoliSampleType = ecoliSampleType;
    }

    public void setSampleDateTimeDisplayed(String sampleDateTimeDisplayed) {
        this.sampleDateTimeDisplayed = sampleDateTimeDisplayed;
    }

    public void setSampleDateTime(LocalDateTime sampleDateTime) {
        this.sampleDateTime = sampleDateTime;
    }

    public void setNoGulls(String noGulls) {
        this.noGulls = noGulls;
    }

    public void setNoGeese(String noGeese) {
        this.noGeese = noGeese;
    }

    public void setNoDogs(String noDogs) {
        this.noDogs = noDogs;
    }

    public void setNoAnimalsOther(String noAnimalsOther) {
        this.noAnimalsOther = noAnimalsOther;
    }

    public void setAnimalsOtherDesc(String animalsOtherDesc) {
        this.animalsOtherDesc = animalsOtherDesc;
    }

    public void setNumLoons(String numLoons) {
        this.numLoons = numLoons;
    }

    public void setNumHerrGulls(String numHerrGulls) {
        this.numHerrGulls = numHerrGulls;
    }

    public void setNumRingGulls(String numRingGulls) {
        this.numRingGulls = numRingGulls;
    }

    public void setNumCormorants(String numCormorants) {
        this.numCormorants = numCormorants;
    }

    public void setNumLongtailDucks(String numLongtailDucks) {
        this.numLongtailDucks = numLongtailDucks;
    }

    public void setNumScoter(String numScoter) {
        this.numScoter = numScoter;
    }

    public void setNumHornGrebe(String numHornGrebe) {
        this.numHornGrebe = numHornGrebe;
    }

    public void setNumRedneckedGrebe(String numRedneckedGrebe) {
        this.numRedneckedGrebe = numRedneckedGrebe;
    }

    public void setNumDeadFish(String numDeadFish) {
        this.numDeadFish = numDeadFish;
    }

    public void setNumOther(String numOther) {
        this.numOther = numOther;
    }

    public void setNumOtherDesc(String numOtherDesc) {
        this.numOtherDesc = numOtherDesc;
    }

    public void setFloatStreetLitter(Boolean floatStreetLitter) {
        this.floatStreetLitter = floatStreetLitter;
    }

    public void setFloatFood(Boolean floatFood) {
        this.floatFood = floatFood;
    }

    public void setFloatMedical(Boolean floatMedical) {
        this.floatMedical = floatMedical;
    }

    public void setFloatSewage(Boolean floatSewage) {
        this.floatSewage = floatSewage;
    }

    public void setFloatBldgMaterials(Boolean floatBldgMaterials) {
        this.floatBldgMaterials = floatBldgMaterials;
    }

    public void setFloatFishing(Boolean floatFishing) {
        this.floatFishing = floatFishing;
    }

    public void setFloatOther(Boolean floatOther) {
        this.floatOther = floatOther;
    }

    public void setFloatOtherDesc(String floatOtherDesc) {
        this.floatOtherDesc = floatOtherDesc;
    }

    public void setDebrisStreetLitter(Boolean debrisStreetLitter) {
        this.debrisStreetLitter = debrisStreetLitter;
    }

    public void setDebrisFood(Boolean debrisFood) {
        this.debrisFood = debrisFood;
    }

    public void setDebrisMedical(Boolean debrisMedical) {
        this.debrisMedical = debrisMedical;
    }

    public void setDebrisSewage(Boolean debrisSewage) {
        this.debrisSewage = debrisSewage;
    }

    public void setDebrisBldgMaterials(Boolean debrisBldgMaterials) {
        this.debrisBldgMaterials = debrisBldgMaterials;
    }

    public void setDebrisFishing(Boolean debrisFishing) {
        this.debrisFishing = debrisFishing;
    }

    public void setDebrisHousehold(Boolean debrisHousehold) {
        this.debrisHousehold = debrisHousehold;
    }

    public void setDebrisTar(Boolean debrisTar) {
        this.debrisTar = debrisTar;
    }

    public void setDebrisOil(Boolean debrisOil) {
        this.debrisOil = debrisOil;
    }

    public void setDebrisOther(Boolean debrisOther) {
        this.debrisOther = debrisOther;
    }

    public void setDebrisOtherDesc(String debrisOtherDesc) {
        this.debrisOtherDesc = debrisOtherDesc;
    }

    public void setDebrisAmount(String debrisAmount) {
        this.debrisAmount = debrisAmount;
    }

    public void setNoInWater(String noInWater) {
        this.noInWater = noInWater;
    }

    public void setNumOutOfWater(String numOutOfWater) {
        this.numOutOfWater = numOutOfWater;
    }

    public void setNoPeopleBoating(String noPeopleBoating) {
        this.noPeopleBoating = noPeopleBoating;
    }

    public void setNoPeopleFishing(String noPeopleFishing) {
        this.noPeopleFishing = noPeopleFishing;
    }

    public void setNoPeopleSurfing(String noPeopleSurfing) {
        this.noPeopleSurfing = noPeopleSurfing;
    }

    public void setNoPeopleWindsurfing(String noPeopleWindsurfing) {
        this.noPeopleWindsurfing = noPeopleWindsurfing;
    }

    public void setNumPeopleDiving(String numPeopleDiving) {
        this.numPeopleDiving = numPeopleDiving;
    }

    public void setNoPeopleClamming(String noPeopleClamming) {
        this.noPeopleClamming = noPeopleClamming;
    }

    public void setNoPeopleOther(String noPeopleOther) {
        this.noPeopleOther = noPeopleOther;
    }

    public void setNoPeopleOtherDesc(String noPeopleOtherDesc) {
        this.noPeopleOtherDesc = noPeopleOtherDesc;
    }

    public void setAirTemp(String airTemp) {
        this.airTemp = airTemp;
    }

    public void setAirUnits(String airUnits) {
        this.airUnits = airUnits;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindSpeedUnits(String windSpeedUnits) {
        this.windSpeedUnits = windSpeedUnits;
    }

    public void setWindDirDegrees(String windDirDegrees) {
        this.windDirDegrees = windDirDegrees;
    }

    public void setWindDirDesc(String windDirDesc) {
        this.windDirDesc = windDirDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
    }

    public void setRainfallLastEvent(String rainfallLastEvent) {
        this.rainfallLastEvent = rainfallLastEvent;
    }

    public void setRainfall(String rainfall) {
        this.rainfall = rainfall;
    }

    public void setRainfallUnits(String rainfallUnits) {
        this.rainfallUnits = rainfallUnits;
    }

    public void setRainfallStdDesc(String rainfallStdDesc) {
        this.rainfallStdDesc = rainfallStdDesc;
    }

    public void setWaveHeight(String waveHeight) {
        this.waveHeight = waveHeight;
    }

    public void setWaveHeightUnits(String waveHeightUnits) {
        this.waveHeightUnits = waveHeightUnits;
    }

    public void setEstActFlag(Boolean estActFlag) {
        this.estActFlag = estActFlag;
    }

    public void setWaveDirection(String waveDirection) {
        this.waveDirection = waveDirection;
    }

    public void setWaveIntensity(String waveIntensity) {
        this.waveIntensity = waveIntensity;
    }

    public void setCurrentSpeed(String currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void setLongshoreCurrentUnits(String longshoreCurrentUnits) {
        this.longshoreCurrentUnits = longshoreCurrentUnits;
    }

    public void setShorelineCurrentDir(String shorelineCurrentDir) {
        this.shorelineCurrentDir = shorelineCurrentDir;
    }

    public void setpH(String pH) {
        this.pH = pH;
    }

    public void setColorChange(Boolean colorChange) {
        this.colorChange = colorChange;
    }

    public void setColorDescription(String colorDescription) {
        this.colorDescription = colorDescription;
    }

    public void setOdorDescription(String odorDescription) {
        this.odorDescription = odorDescription;
    }

    public void setOdorOtherDescription(String odorOtherDescription) {
        this.odorOtherDescription = odorOtherDescription;
    }

    public void setAvgWaterTemp(String avgWaterTemp) {
        this.avgWaterTemp = avgWaterTemp;
    }

    public void setAvgWaterTempUnits(String avgWaterTempUnits) {
        this.avgWaterTempUnits = avgWaterTempUnits;
    }

    public void setClarityDesc(String clarityDesc) {
        this.clarityDesc = clarityDesc;
    }

    public void setNtu(String ntu) {
        this.ntu = ntu;
    }

    public void setSecchiTubeCm(String secchiTubeCm) {
        this.secchiTubeCm = secchiTubeCm;
    }

    public void setAlgaeNearShore(String algaeNearShore) {
        this.algaeNearshore = algaeNearShore;
    }

    public void setAlgaeOnBeach(String algaeOnBeach) {
        this.algaeOnBeach = algaeOnBeach;
    }

    public void setAlgaeTypePeriphyton(Boolean algaeTypePeriphyton) {
        this.algaeTypePeriphyton = algaeTypePeriphyton;
    }

    public void setAlgaeTypeGlobular(Boolean algaeTypeGlobular) {
        this.algaeTypeGlobular = algaeTypeGlobular;
    }

    public void setAlgaeTypeFreefloating(Boolean algaeTypeFreefloating) {
        this.algaeTypeFreefloating = algaeTypeFreefloating;
    }

    public void setAlgaeTypeOther(Boolean algaeTypeOther) {
        this.algaeTypeOther = algaeTypeOther;
    }

    public void setAlgaeTypeOtherDesc(String algaeTypeOtherDesc) {
        this.algaeTypeOtherDesc = algaeTypeOtherDesc;
    }

    public void setAlgaeColorLtGreen(Boolean algaeColorLtGreen) {
        this.algaeColorLtGreen = algaeColorLtGreen;
    }

    public void setAlgaeColorBrightGreen(Boolean algaeColorBrightGreen) {
        this.algaeColorBrightGreen = algaeColorBrightGreen;
    }

    public void setAlgaeColorDrkGreen(Boolean algaeColorDrkGreen) {
        this.algaeColorDrkGreen = algaeColorDrkGreen;
    }

    public void setAlgaeColorYellow(Boolean algaeColorYellow) {
        this.algaeColorYellow = algaeColorYellow;
    }

    public void setAlgaeColorBrown(Boolean algaeColorBrown) {
        this.algaeColorBrown = algaeColorBrown;
    }

    public void setAlgaeColorOther(Boolean algaeColorOther) {
        this.algaeColorOther = algaeColorOther;
    }

    public void setAlgaeColorOtherDesc(String algaeColorOtherDesc) {
        this.algaeColorOtherDesc = algaeColorOtherDesc;
    }

    public void setPart1Comments(String part1Comments) {
        this.part1Comments = part1Comments;
    }

    public void setPart2Coments(String part2Comments) {
        this.part2Comments = part2Comments;
    }

    public void setPart3Comments(String part3Comments) {
        this.part3Comments = part3Comments;
    }

    public void setPart4Comments(String part4Comments) {
        this.part4Comments = part4Comments;
    }

    public void setDateEntered(LocalDateTime dateEntered) {
        this.dateEntered = dateEntered;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setvPages(String vPages) {
        this.vPages = vPages;
    }

    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSamplerSeq(int samplerSeq) {
        this.samplerSeq = samplerSeq;
    }

    public void setDataEntrySeq(int dataEntrySeq) {
        this.dataEntrySeq = dataEntrySeq;
    }

    public void setFloatablesFlag(Boolean floatablesFlag) {this.floatablesFlag = floatablesFlag;}

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getFavorites() {
        return favorites;
    }

    public String getCounty() {
        return county;
    }

    public String getLake() {
        return lake;
    }

    public String getBeach() {
        return beach;
    }

    public String getBeachSeq() {
        return beachSeq;
    }

    public String getSite() {
        return site;
    }

    public String getMonitorSiteSeq() {
        return monitorSiteSeq;
    }

    public Boolean getEcoliSampleType() {
        return ecoliSampleType;
    }

    public String getSampleDateTimeDisplayed() {
        return sampleDateTimeDisplayed;
    }

    public LocalDateTime getSampleDateTime() {
        return sampleDateTime;
    }

    public String getNoGulls() {
        return noGulls;
    }

    public String getNoGeese() {
        return noGeese;
    }

    public String getNoDogs() {
        return noDogs;
    }

    public String getNoAnimalsOther() {
        return noAnimalsOther;
    }

    public String getAnimalsOtherDesc() {
        return animalsOtherDesc;
    }

    public String getNumLoons() {
        return numLoons;
    }

    public String getNumHerrGulls() {
        return numHerrGulls;
    }

    public String getNumRingGulls() {
        return numRingGulls;
    }

    public String getNumCormorants() {
        return numCormorants;
    }

    public String getNumLongtailDucks() {
        return numLongtailDucks;
    }

    public String getNumScoter() {
        return numScoter;
    }

    public String getNumHornGrebe() {
        return numHornGrebe;
    }

    public String getNumRedneckedGrebe() {
        return numRedneckedGrebe;
    }

    public String getNumDeadFish() {
        return numDeadFish;
    }

    public String getNumOther() {
        return numOther;
    }

    public String getNumOtherDesc() {
        return numOtherDesc;
    }

    public Boolean getFloatStreetLitter() {
        return floatStreetLitter;
    }

    public Boolean getFloatFood() {
        return floatFood;
    }

    public Boolean getFloatMedical() {
        return floatMedical;
    }

    public Boolean getFloatSewage() {
        return floatSewage;
    }

    public Boolean getFloatBldgMaterials() {
        return floatBldgMaterials;
    }

    public Boolean getFloatFishing() {
        return floatFishing;
    }

    public Boolean getFloatOther() {
        return floatOther;
    }

    public String getFloatOtherDesc() {
        return floatOtherDesc;
    }

    public Boolean getDebrisStreetLitter() {
        return debrisStreetLitter;
    }

    public Boolean getDebrisFood() {
        return debrisFood;
    }

    public Boolean getDebrisMedical() {
        return debrisMedical;
    }

    public Boolean getDebrisSewage() {
        return debrisSewage;
    }

    public Boolean getDebrisBldgMaterials() {
        return debrisBldgMaterials;
    }

    public Boolean getDebrisFishing() {
        return debrisFishing;
    }

    public Boolean getDebrisHousehold() {
        return debrisHousehold;
    }

    public Boolean getDebrisTar() {
        return debrisTar;
    }

    public Boolean getDebrisOil() {
        return debrisOil;
    }

    public Boolean getDebrisOther() {
        return debrisOther;
    }

    public String getDebrisOtherDesc() {
        return debrisOtherDesc;
    }

    public String getDebrisAmount() {
        return debrisAmount;
    }

    public String getNoInWater() {
        return noInWater;
    }

    public String getNumOutOfWater() {
        return numOutOfWater;
    }

    public String getNoPeopleBoating() {
        return noPeopleBoating;
    }

    public String getNoPeopleFishing() {
        return noPeopleFishing;
    }

    public String getNoPeopleSurfing() {
        return noPeopleSurfing;
    }

    public String getNoPeopleWindsurfing() {
        return noPeopleWindsurfing;
    }

    public String getNumPeopleDiving() {
        return numPeopleDiving;
    }

    public String getNoPeopleClamming() {
        return noPeopleClamming;
    }

    public String getNoPeopleOther() {
        return noPeopleOther;
    }

    public String getNoPeopleOtherDesc() {
        return noPeopleOtherDesc;
    }

    public String getAirTemp() {
        return airTemp;
    }

    public String getAirUnits() {
        return airUnits;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindSpeedUnits() {
        return windSpeedUnits;
    }

    public String getWindDirDegrees() {
        return windDirDegrees;
    }

    public String getWindDirDesc() {
        return windDirDesc;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public String getRainfallLastEvent() {
        return rainfallLastEvent;
    }

    public String getRainfall() {
        return rainfall;
    }

    public String getRainfallUnits() {
        return rainfallUnits;
    }

    public String getRainfallStdDesc() {
        return rainfallStdDesc;
    }

    public String getWaveHeight() {
        return waveHeight;
    }

    public String getWaveHeightUnits() {
        return waveHeightUnits;
    }

    public Boolean getEstActFlag() {
        return estActFlag;
    }

    public String getWaveDirection() {
        return waveDirection;
    }

    public String getWaveIntensity() {
        return waveIntensity;
    }

    public String getCurrentSpeed() {
        return currentSpeed;
    }

    public String getLongshoreCurrentUnits() {
        return longshoreCurrentUnits;
    }

    public String getShorelineCurrentDir() {
        return shorelineCurrentDir;
    }

    public String getpH() {
        return pH;
    }

    public Boolean getColorChange() {
        return colorChange;
    }

    public String getColorDescription() {
        return colorDescription;
    }

    public String getOdorDescription() {
        return odorDescription;
    }

    public String getOdorOtherDescription() {
        return odorOtherDescription;
    }

    public String getAvgWaterTemp() {
        return avgWaterTemp;
    }

    public String getAvgWaterTempUnits() {
        return avgWaterTempUnits;
    }

    public String getClarityDesc() {
        return clarityDesc;
    }

    public String getNtu() {
        return ntu;
    }

    public String getSecchiTubeCm() {
        return secchiTubeCm;
    }

    public String getAlgaeNearshore() {
        return algaeNearshore;
    }

    public String getAlgaeOnBeach() {
        return algaeOnBeach;
    }

    public Boolean getAlgaeTypePeriphyton() {
        return algaeTypePeriphyton;
    }

    public Boolean getAlgaeTypeGlobular() {
        return algaeTypeGlobular;
    }

    public Boolean getAlgaeTypeFreefloating() {
        return algaeTypeFreefloating;
    }

    public Boolean getAlgaeTypeOther() {
        return algaeTypeOther;
    }

    public String getAlgaeTypeOtherDesc() {
        return algaeTypeOtherDesc;
    }

    public Boolean getAlgaeColorLtGreen() {
        return algaeColorLtGreen;
    }

    public Boolean getAlgaeColorBrightGreen() {
        return algaeColorBrightGreen;
    }

    public Boolean getAlgaeColorDrkGreen() {
        return algaeColorDrkGreen;
    }

    public Boolean getAlgaeColorYellow() {
        return algaeColorYellow;
    }

    public Boolean getAlgaeColorBrown() {
        return algaeColorBrown;
    }

    public Boolean getAlgaeColorOther() {
        return algaeColorOther;
    }

    public String getAlgaeColorOtherDesc() {
        return algaeColorOtherDesc;
    }

    public String getPart1Comments() {
        return part1Comments;
    }

    public String getPart2Comments() {
        return part2Comments;
    }

    public String getPart3Comments() {
        return part3Comments;
    }

    public String getPart4Comments() {
        return part4Comments;
    }

    public LocalDateTime getDateEntered() {
        return dateEntered;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public String getvPages() {
        return vPages;
    }

    public String getSubmitted() {
        return submitted;
    }

    public String getIdNo() {
        return idNo;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getSamplerSeq() {
        return samplerSeq;
    }

    public int getDataEntrySeq() {
        return dataEntrySeq;
    }

    public Boolean getFloatablesFlag() {return floatablesFlag;}

    @JsonView(Views.Response.class)
    public ValidationResults getValidationErrors() {
        if (null != validationErrors) {
            return validationErrors;
        } else {
            return new ValidationResults();
        }
    }

    public void setValidationErrors(final Set<ConstraintViolation<SanitaryData>> inValidationErrors) {
        validationErrors = new ValidationResults();
        if (null != inValidationErrors) {
            List<ValidatorResult> vResults = new ArrayList<ValidatorResult>();
            for (ConstraintViolation<SanitaryData> vError : inValidationErrors) {
                ValidatorResult vResult = new ValidatorResult(vError.getPropertyPath().toString(), vError.getMessage(),null);
                vResults.add(vResult);
            }

            validationErrors.setValidationErrors(vResults);
        }
    }
}
