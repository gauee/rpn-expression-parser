package com.github.gauee.rpn;

import com.github.gauee.rpn.evaluation.result.EvaluationResult;
import com.github.gauee.rpn.expression.source.ExpressionSource;
import com.github.gauee.rpn.operators.Operator;
import com.github.gauee.rpn.operators.OperatorSource;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Deque;
import java.util.LinkedList;

public class RPNEvaluationService {
    private final static NumberFormat nf = new DecimalFormat("#.###");
    private final OperatorSource operatorSource = new OperatorSource();

    public void evaluate(ExpressionSource expressionSource, EvaluationResult evaluationResult) {
        String expression = expressionSource.readExpression();

        Deque<Double> operands = new LinkedList<>();

        for (String token : expression.split(" ")) {
            Operator operator = operatorSource.findOperator(token);
            if (operator != null) {
                Double calculatedValue = operator.calculate(operands.pollLast(), operands.pollLast());
                operands.add(calculatedValue);
            } else {
                operands.add(Double.parseDouble(token));
            }
        }

        String result = nf.format(operands.pollLast());
        evaluationResult.print(result);
    }
}
