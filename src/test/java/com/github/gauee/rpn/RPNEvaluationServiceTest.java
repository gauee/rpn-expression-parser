package com.github.gauee.rpn;

import com.github.gauee.rpn.evaluation.result.EvaluationResult;
import com.github.gauee.rpn.expression.source.ExpressionSource;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(DataProviderRunner.class)
public class RPNEvaluationServiceTest {

    @Mock
    private EvaluationResult evaluationResult;
    @Mock
    private ExpressionSource expressionSource;
    @Captor
    private ArgumentCaptor<String> resultArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider
    public static Object[][] expressions() {
        return new Object[][]{
                {"1 2 +", "3"},
                {"1 2 + 10 +", "13"},
                {"1 2 ) +", "3"},
                {"1 2 -", "-1"},
                {"30 2 - 10 -", "18"},
                {"3 11 5 + -", "-13"},
                {"3 11 + 5 -", "9"},
                {"12 2 3 4 * 10 5 / + * +", "40"},
                {"2 3 11 + 5 - *", "18"},
                {"9 5 3 + 2 4 ^ - +", "1"},
                {"2 7 + 3 / 14 3 - 4 * + 2 /", "23.5"},
                {"15 7 1 1 + - / 3 * 2 1 1 + + -", "5"},
        };
    }

    @Test
    @UseDataProvider("expressions")
    public void calculatesTheRPNExpression(String expression, String result) {
        when(expressionSource.readExpression()).thenReturn(expression);

        new RPNEvaluationService().evaluate(expressionSource, evaluationResult);

        verify(evaluationResult).print(resultArgumentCaptor.capture());

        assertThat(resultArgumentCaptor.getValue())
                .isEqualTo(result);
    }
}