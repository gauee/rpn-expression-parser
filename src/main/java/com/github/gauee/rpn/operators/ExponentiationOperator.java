package com.github.gauee.rpn.operators;

public class ExponentiationOperator implements Operator {
    @Override
    public Double calculate(Double... args) {
        return Math.pow(args[1], args[0]);
    }
}
