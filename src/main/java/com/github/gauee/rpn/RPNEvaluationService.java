package com.github.gauee.rpn;

import com.github.gauee.rpn.evaluation.result.EvaluationResult;
import com.github.gauee.rpn.evaluation.result.StoutEvaluationResult;
import com.github.gauee.rpn.expression.source.ExpressionSource;
import com.github.gauee.rpn.expression.source.MainArgsExpressionSource;
import com.github.gauee.rpn.operators.Operator;
import com.github.gauee.rpn.operators.OperatorSource;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Deque;
import java.util.LinkedList;

public class RPNEvaluationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPNEvaluationService.class);
    private final static NumberFormat NUMBER_FORMAT = new DecimalFormat("#.###");
    private final OperatorSource operatorSource = new OperatorSource();

    public static void main(String[] args) {
        new RPNEvaluationService().evaluate(new MainArgsExpressionSource(args), new StoutEvaluationResult());
    }

    public void evaluate(ExpressionSource expressionSource, EvaluationResult evaluationResult) {
        String expression = expressionSource.readExpression();
        String result = evaluate(expression);
        LOGGER.info("Expression {} evaluated to {}", expression, result);
        evaluationResult.print(result);
    }

    private String evaluate(String expression) {
        Deque<Double> operands = new LinkedList<>();
        for (String token : expression.split(" ")) {
            processToken(operands, token);
        }
        return NUMBER_FORMAT.format(operands.pollLast());
    }

    private void processToken(Deque<Double> operands, String token) {
        Operator operator = operatorSource.findOperator(token);
        if (operator != null) {
            Double calculatedValue = operator.calculate(operands.pollLast(), operands.pollLast());
            operands.add(calculatedValue);
            return;
        }
        if (!NumberUtils.isCreatable(token)) {
            LOGGER.warn("Unexpected token {}, skipping", token);
            return;
        }
        operands.add(Double.parseDouble(token));
    }
}
