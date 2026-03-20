package com.example.antlr.api.dto;

/**
 * 统一 API 响应体
 */
public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String error;
    private long costMs;
    private long timestamp = System.currentTimeMillis();

    public ApiResponse() {}

    public static <T> ApiResponse<T> ok(T data, long costMs) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = true;
        r.data = data;
        r.costMs = costMs;
        return r;
    }

    public static <T> ApiResponse<T> ok(T data) {
        return ok(data, 0);
    }

    public static <T> ApiResponse<T> fail(String error) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.error = error;
        return r;
    }

    public static <T> ApiResponse<T> fail(String error, long costMs) {
        ApiResponse<T> r = new ApiResponse<>();
        r.success = false;
        r.error = error;
        r.costMs = costMs;
        return r;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public String getError() { return error; }
    public void setError(String error) { this.error = error; }

    public long getCostMs() { return costMs; }
    public void setCostMs(long costMs) { this.costMs = costMs; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}
