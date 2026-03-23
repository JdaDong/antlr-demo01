package com.example.antlr;

import org.antlr.v4.runtime.*;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义错误监听器，收集解析错误信息
 */
public class SqlErrorListener extends BaseErrorListener {

    private static final Logger log = LoggerFactory.getLogger(SqlErrorListener.class);
    private final List<String> errors = new ArrayList<>();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer,
                            Object offendingSymbol,
                            int line,
                            int charPositionInLine,
                            String msg,
                            RecognitionException e) {
        String error = String.format("行 %d:%d - %s", line, charPositionInLine, msg);
        errors.add(error);
        log.debug("SQL 解析错误: {}", error);
    }

    public List<String> getErrors() {
        return ImmutableList.copyOf(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String getErrorsAsString() {
        return Joiner.on("\n").join(errors);
    }
}
