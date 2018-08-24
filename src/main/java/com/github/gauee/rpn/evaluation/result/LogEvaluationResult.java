package com.github.gauee.rpn.evaluation.result;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogEvaluationResult implements EvaluationResult {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogEvaluationResult.class);

    @Override
    public void print(String evaluationResult) {
        LOGGER.info("Result: {}", evaluationResult);
    }
}
