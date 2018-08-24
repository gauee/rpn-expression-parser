package com.github.gauee.rpn.operators;

public class MultiplicationOperator implements Operator {
    @Override
    public Double calculate(Double... args) {
        return args[1] * args[0];
    }
}
