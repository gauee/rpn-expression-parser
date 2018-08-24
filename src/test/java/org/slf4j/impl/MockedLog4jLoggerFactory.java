package org.slf4j.impl;

import org.mockito.Mockito;
import org.slf4j.Logger;

public class MockedLog4jLoggerFactory {

    public static Logger createMockedLogger(Class clazz, Log4jLoggerFactory log4jLoggerFactory){
        Logger mockedLogger = Mockito.mock(Logger.class);
        log4jLoggerFactory.loggerMap.put(clazz.getName(), mockedLogger);
        return mockedLogger;
    }


}
