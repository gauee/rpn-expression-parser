package com.github.gauee.rpn.expression.source;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class MainArgsExpressionSourceTest {

    @Test
    public void returnAnEmptyStringWhenNoArgsHaveBeenProvided() {
        assertThat(new MainArgsExpressionSource(new String[0]).readExpression())
                .isEqualTo(StringUtils.EMPTY);
    }

    @Test
    public void returnAnEmptyStringWhenMainArgsIsNull() {
        assertThat(new MainArgsExpressionSource(null).readExpression())
                .isEqualTo(StringUtils.EMPTY);
    }

    @Test
    public void returnsTheExpression() {
        String expression = "1 2 +";
        assertThat(new MainArgsExpressionSource(new String[]{expression}).readExpression())
                .isEqualTo(expression);
    }

    @Test
    public void returnsTheFirstExpressionExpression() {
        String expression = "1 2 +";
        assertThat(new MainArgsExpressionSource(new String[]{expression, ""}).readExpression())
                .isEqualTo(expression);
    }

}