package com.github.gauee.rpn.operators;

public class AdditionalOperator implements Operator {
    @Override
    public Double calculate(Double... args) {
        return args[0] + args[1];
    }
}
