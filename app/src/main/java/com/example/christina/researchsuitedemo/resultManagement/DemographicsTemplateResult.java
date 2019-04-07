package com.example.christina.researchsuitedemo.resultManagement;

/**
 * Created by christinatsangouri on 3/29/18.
 */

import android.support.annotation.Nullable;

import org.researchstack.backbone.result.StepResult;
import org.researchsuite.rsrp.RSRPFrontEndServiceProvider.spi.RSRPFrontEnd;
import org.researchsuite.rsrp.RSRPIntermediateResult;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import android.support.annotation.Nullable;

import org.researchstack.backbone.result.StepResult;
import org.researchsuite.rsrp.RSRPFrontEndServiceProvider.spi.RSRPFrontEnd;
import org.researchsuite.rsrp.RSRPIntermediateResult;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jameskizer on 9/18/17.
 */

public class DemographicsTemplateResult extends RSRPIntermediateResult {

    public static String TYPE = "DemographicsTemplate";

//    private String gender;
//    private Integer age;
//    private String zipcode;

//    private String coffee;
//    private String food;

    private String headache;
    private String cough;
    private String shortnessOfBreath;
    private String cigarettesCount;
    private String bodyTemperature;

    private String fatigue;
    private String nauseaOrVomiting;
    private String appetite;
    private String diarrhea;
    private String bruiseOrBleeding;

    public DemographicsTemplateResult(UUID uuid, String taskIdentifier, UUID taskRunUUID,
                                      String headache, String cough, String shortnessOfBreath,
                                      String cigarettesCount, String bodyTemperature,
                                      String fatigue, String nauseaOrVomiting, String appetite,
                                      String diarrhea, String bruiseOrBleeding) {
        super(TYPE, uuid, taskIdentifier, taskRunUUID);

//        this.coffee = coffee;
//        this.food = food;

//        this.gender = gender;
//        this.age = age;
//        this.zipcode = zipcode;

        this.headache = headache;
        this.cough = cough;
        this.shortnessOfBreath = shortnessOfBreath;
        this.cigarettesCount = cigarettesCount;
        this.bodyTemperature = bodyTemperature;

        this.fatigue = fatigue;
        this.nauseaOrVomiting = nauseaOrVomiting;
        this.appetite = appetite;
        this.diarrhea = diarrhea;
        this.bruiseOrBleeding = bruiseOrBleeding;
    }

//    public String getGender() {
//        return gender;
//    }
//    public Integer getAge(){
//        return age;
//    }
//    public String getZip(){
//        return zipcode;
//    }

//    public String getCoffee() {
//        return coffee;
//    }

//    public String getFood() {
//        return food;
//    }

    public String getHeadache() {
        return this.headache;
    }

    public String getCough() {
        return this.cough;
    }

    public String getShortnessOfBreath() {
        return this.shortnessOfBreath;
    }

    public String getCigarettesCount() {
        return this.cigarettesCount;
    }

    public String getBodyTemperature() {
        return this.bodyTemperature;
    }

    public String getFatigue() {
        return this.fatigue;
    }

    public String getNauseaOrVomiting() {
        return this.nauseaOrVomiting;
    }

    public String getAppetite() {
        return this.appetite;
    }

    public String getDiarrhea() {
        return this.diarrhea;
    }

    public String getBruiseOrBleeding() {
        return this.bruiseOrBleeding;
    }

    public class FrontEndTransformer implements RSRPFrontEnd {

        public FrontEndTransformer() {}

        @Nullable
        public <T> T extractResult(Map<String, Object> parameters, String identifier) {

            Object param = parameters.get(identifier);
            if (param != null && (param instanceof StepResult)) {
                StepResult stepResult = (StepResult)param;
                if (stepResult.getResult() != null) {
                    return (T)stepResult.getResult();
                }
            }
            return null;
        }

        @Nullable
        @Override
        public RSRPIntermediateResult transform(String taskIdentifier, UUID taskRunUUID, Map<String, Object> parameters) {

//            String gender = extractResult(parameters,"gender");
//            Integer age = extractResult(parameters,"age");
//            String zipcode = extractResult(parameters,"zip_code");

//            String coffee = extractResult(parameters,"coffee");
//            String food = extractResult(parameters,"food");

            String headache = extractResult(parameters, "headache");
            String cough = extractResult(parameters, "cough");
            String shortnessOfBreath = extractResult(parameters, "shortness_of_breath");
            String cigarettesCount = extractResult(parameters, "cigarettes_count");
            String bodyTemperature = extractResult(parameters, "body_temperature");

            String fatigue = extractResult(parameters, "fatigue");
            String nauseaOrVomiting = extractResult(parameters, "nausea_or_vomiting");
            String appetite = extractResult(parameters, "appetite");
            String diarrhea = extractResult(parameters, "diarrhea");
            String bruiseOrBleeding = extractResult(parameters, "bruise_or_bleeding");

            DemographicsTemplateResult result = new DemographicsTemplateResult(
                    UUID.randomUUID(),
                    taskIdentifier,
                    taskRunUUID,
                    //coffee,
                    //food
                    headache,
                    cough,
                    shortnessOfBreath,
                    cigarettesCount,
                    bodyTemperature,
                    fatigue,
                    nauseaOrVomiting,
                    appetite,
                    diarrhea,
                    bruiseOrBleeding
            );

            StepResult firstStepResult = (StepResult) (parameters.get("headache") != null ? parameters.get("headache") : parameters.get("bruise_or_bleeding"));

//            StepResult firstStepResult = (StepResult) (parameters.get("coffee") != null ? parameters.get("coffee") : parameters.get("food"));

//            StepResult firstStepResult = (StepResult) (parameters.get("days_on_campus") != null ? parameters.get("days_on_campus") : parameters.get("travel_plans"));
//            StepResult lastStepResult = (StepResult) (parameters.get("travel_plans") != null ? parameters.get("travel_plans") : parameters.get("days_on_campus"));

            if (firstStepResult != null) {
                result.setStartDate(firstStepResult.getStartDate());
            }
            else {
                result.setStartDate(new Date());
            }

            if (firstStepResult != null) {
                result.setEndDate(firstStepResult.getStartDate());
            }
            else {
                result.setEndDate(new Date());
            }

            return result;
        }

        @Override
        public boolean supportsType(String type) {
            return false;
        }

    }
}
