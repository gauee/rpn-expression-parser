package com.github.gauee.rpn.evaluation.result;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.assertj.core.api.Assertions.assertThat;

public class SoutEvaluationResultTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void printsTheResultToSystemOut() {
        String result = "testEvaluationResult";
        new StoutEvaluationResult().print(result);
        assertThat(systemOutRule.getLogWithNormalizedLineSeparator())
                .isEqualToIgnoringNewLines(result);
    }
}