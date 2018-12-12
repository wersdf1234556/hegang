package org.tonzoc.Common;

public class WhereParam {
    private final String name;

    public WhereParam(String name, String operator, String param) {
        this.name = name;
        this.operator = operator;
        this.param = param;
    }

    private final String operator;
    private final String param;

    public String getOperator() {
        return operator;
    }

    public String getParam() {
        return param;
    }

    public String getName() {
        return name;
    }
}
