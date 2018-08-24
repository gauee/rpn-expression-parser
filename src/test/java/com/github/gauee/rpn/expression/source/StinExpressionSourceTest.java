package com.github.gauee.rpn.expression.source;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class StinExpressionSourceTest {

    private StinExpressionSource expressionSource;

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();

    @Before
    public void setUp() {
        expressionSource = new StinExpressionSource();
    }

    @Test
    public void returnsTheExpression() {
        String expression = "1 2 +";
        systemInMock.provideLines(expression);

        assertThat(expressionSource.readExpression())
                .isEqualTo(expression);
    }

    @Test
    public void returnsAnEmptyStringOnException() {
        systemInMock.throwExceptionOnInputEnd(new IOException("unable to read stdin"));

        assertThat(expressionSource.readExpression())
                .isEqualTo(StringUtils.EMPTY);
    }
}