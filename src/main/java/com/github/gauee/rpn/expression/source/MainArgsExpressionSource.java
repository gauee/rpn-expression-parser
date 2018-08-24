package com.github.gauee.rpn.expression.source;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainArgsExpressionSource implements ExpressionSource {
    private static final Logger LOGGER = LoggerFactory.getLogger(MainArgsExpressionSource.class);
    private final String[] mainArgs;

    public MainArgsExpressionSource(String[] mainArgs) {
        this.mainArgs = mainArgs;
    }

    @Override
    public String readExpression() {
        if (mainArgs == null || mainArgs.length == 0) {
            LOGGER.warn("No expression provided in the main args");
            return StringUtils.EMPTY;
        }
        if (mainArgs.length > 1) {
            LOGGER.warn("More than one expression provided, only first one will be returned {}", mainArgs);
        }
        return mainArgs[0];
    }
}
