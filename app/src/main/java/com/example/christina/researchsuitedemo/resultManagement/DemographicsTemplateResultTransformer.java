package com.example.christina.researchsuitedemo.resultManagement;

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

public class DemographicsTemplateResultTransformer implements RSRPFrontEnd {

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
//
//        String gender = extractResult(parameters,"gender");
//        Integer age = extractResult(parameters,"age");
//        String zipcode = extractResult(parameters,"zip_code");

//        String coffee = extractResult(parameters,"coffee");
//        String food = extractResult(parameters,"food");

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
//                coffee,
//                food
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

        StepResult firstStepResult = (StepResult) (parameters.get("headache") != null ? parameters.get("headache") : parameters.get("body_temperature"));

        //StepResult firstStepResult = (StepResult) (parameters.get("coffee") != null ? parameters.get("coffee") : parameters.get("food"));
        //StepResult lastStepResult = (StepResult) (parameters.get("employment") != null ? parameters.get("employment") : parameters.get("gender"));

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
        return DemographicsTemplateResult.TYPE.equals(type);
    }

}
