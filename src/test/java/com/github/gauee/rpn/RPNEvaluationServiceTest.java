package com.github.gauee.rpn;

import com.github.gauee.rpn.evaluation.result.EvaluationResult;
import com.github.gauee.rpn.expression.source.ExpressionSource;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RPNEvaluationServiceTest {

    @Mock
    private EvaluationResult evaluationResult;
    @Mock
    private ExpressionSource expressionSource;
    @Captor
    private ArgumentCaptor<String> resultArgumentCaptor;

    @Test
    public void calculatesTheExpressionWithSingleAdditionalOperator() {
        when(expressionSource.readExpression()).thenReturn("1 2 +");

        new RPNEvaluationService().evaluate(expressionSource, evaluationResult);

        verify(evaluationResult).print(resultArgumentCaptor.capture());

        assertThat(resultArgumentCaptor.getValue())
                .isEqualTo("3");
    }

    @Test
    public void calculatesTheExpressionWithMultipleAdditionalOperator() {
        when(expressionSource.readExpression()).thenReturn("1 2 + 10 +");

        new RPNEvaluationService().evaluate(expressionSource, evaluationResult);

        verify(evaluationResult).print(resultArgumentCaptor.capture());

        assertThat(resultArgumentCaptor.getValue())
                .isEqualTo("13");
    }
}