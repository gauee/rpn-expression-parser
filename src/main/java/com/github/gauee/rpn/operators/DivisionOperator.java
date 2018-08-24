package com.github.gauee.rpn.operators;

public class DivisionOperator implements Operator {
    @Override
    public Double calculate(Double... args) {
        return args[1] / args[0];
    }
}
