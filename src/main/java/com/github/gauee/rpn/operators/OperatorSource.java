package com.github.gauee.rpn.operators;

import com.github.gauee.rpn.expression.source.SubtractOperator;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class OperatorSource {

    private static final Map<String, Operator> OPERATORS = ImmutableMap.<String, Operator>builder()
            .put("+", new AdditionalOperator())
            .put("-", new SubtractOperator())
            .build();

    public Operator findOperator(String token) {
        return OPERATORS.get(token);
    }
}
