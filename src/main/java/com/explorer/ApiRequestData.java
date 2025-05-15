package com.explorer;
import java.util.HashMap;
import java.util.Map;
public class ApiRequestData {
    public String method;
    public String url;
    public String body = "";
    public Map<String, String> headers = new HashMap<>();
    public AuthType authType = AuthType.NONE;
    public String tokenOrUser = "";
    public String password = "";
}
