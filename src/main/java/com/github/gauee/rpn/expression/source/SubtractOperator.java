package com.github.gauee.rpn.expression.source;

import com.github.gauee.rpn.operators.Operator;

public class SubtractOperator implements Operator {
    @Override
    public Double calculate(Double... args) {
        return args[1] - args[0];
    }
}
