package com.github.gauee.rpn.evaluation.result;

import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.apache.logging.slf4j.MockedLog4jLoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LogEvaluationResultTest {

    @Captor
    private ArgumentCaptor<String> logEntryArgumentCaptor;
    @Captor
    private ArgumentCaptor<Object> logArgsArgumentCaptor;

    @Test
    public void printsResultToLog() {
        String result = "testEvaluationResult";

        ILoggerFactory iLoggerFactory = LoggerFactory.getILoggerFactory();
        Logger mockedLogger = null;
        if (iLoggerFactory instanceof Log4jLoggerFactory) {
            mockedLogger = MockedLog4jLoggerFactory.createMockedLogger(LogEvaluationResult.class,
                    (Log4jLoggerFactory) iLoggerFactory);
        }
        assertThat(mockedLogger)
                .isNotNull();
        new LogEvaluationResult().print(result);

        verify(mockedLogger).info(logEntryArgumentCaptor.capture(), logArgsArgumentCaptor.capture());

        assertThat(logEntryArgumentCaptor.getValue())
                .contains("Result: {}");
        assertThat(logArgsArgumentCaptor.getValue())
                .isEqualTo(result);
    }
}