package com.upwork.pilot.model;

/**
 * @author Vladimir Tsukanov
 */
public class RequestParams {

    private long id;

    private boolean recurseParameter;

    private long durationParameter;

    private int recurseLevelParameter;

    private ResponseResult result;

    public RequestParams() {
    }

    public RequestParams(long id, boolean recurseParameter, long durationParameter, int recurseLevelParameter, ResponseResult result) {
        this.id = id;
        this.recurseParameter = recurseParameter;
        this.durationParameter = durationParameter;
        this.recurseLevelParameter = recurseLevelParameter;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRecurseParameter() {
        return recurseParameter;
    }

    public void setRecurseParameter(boolean recurseParameter) {
        this.recurseParameter = recurseParameter;
    }

    public long getDurationParameter() {
        return durationParameter;
    }

    public void setDurationParameter(long durationParameter) {
        this.durationParameter = durationParameter;
    }

    public int getRecurseLevelParameter() {
        return recurseLevelParameter;
    }

    public void setRecurseLevelParameter(int recurseLevelParameter) {
        this.recurseLevelParameter = recurseLevelParameter;
    }

    public ResponseResult getResult() {
        return result;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", recurseParameter=" + recurseParameter +
                ", durationParameter=" + durationParameter +
                ", recurseLevelParameter=" + recurseLevelParameter +
                ", result=" + result +
                '}';
    }
}
