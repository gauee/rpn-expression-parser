package com.github.gauee.rpn.expression.source;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class SinExpressionSource implements ExpressionSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SinExpressionSource.class);

    public String readExpression() {
        try (Reader reader = new BufferedReader(new InputStreamReader(System.in))) {
            return ((BufferedReader) reader).readLine();
        } catch (IOException e) {
            LOGGER.warn("Unable to read the expression from stdin");
        }
        return StringUtils.EMPTY;
    }
}
