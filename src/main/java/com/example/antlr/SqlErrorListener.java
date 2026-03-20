package com.example.antlr;

import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义错误监听器，收集解析错误信息
 */
public class SqlErrorListener extends BaseErrorListener {

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
    }

    public List<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String getErrorsAsString() {
        return String.join("\n", errors);
    }
}
