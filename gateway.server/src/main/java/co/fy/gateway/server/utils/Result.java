package co.fy.gateway.server.utils;

import java.io.Serializable;

/**
 * Created by joker on 9/6/17.
 * controller response result bean
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4339745773959904993L;
    private static final String OKCODE = "200";
    private static final String FAILCODE = "500";
    private static final String UNLOGINFAILCODE = "405";
    private String code;
    private String message;
    private Object body;
    public Result() {
    }

    public static <T> Result<T> ok() {
        return ok ();
    }
    public static <T> Result<T> ok(Object body) {
        Result<T> r = new Result ();
        r.setCode ( "200" );
        r.setMessage ( "" );
        r.setBody (body);
        return r;
    }
    public static <T> Result<T> fail(String message, String code) {
        Result<T> r = new Result ();
        r.setCode ( code );
        r.setMessage ( message );
        r.setBody (null);
        return r;
    }

    public boolean isOk() {
        return this.code != null && "200".equals ( this.code.trim () );
    }

    public boolean isBad() {
        return !this.isOk ();
    }

    public String getCode() {
        return this.code;
    }

    public Result<T> setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getBody() {
        return this.body;
    }

    public Result<T> setBody(Object body) {
        this.body = body;
        return this;
    }
    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", body=" + body +
                '}';
    }
}

