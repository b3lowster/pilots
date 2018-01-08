package com.upwork.pilot.service;

import com.upwork.pilot.model.RequestParams;
import com.upwork.pilot.model.ResponseResult;

/**
 * @author Vladimir Tsukanov
 */
public class LoggingService {

    public synchronized RequestParams log(RequestParams params){
        if (!params.isRecurseParameter()) {
            try {
                Thread.sleep(params.getDurationParameter());
            } catch (InterruptedException e) {
                e.printStackTrace();
                params.setResult(ResponseResult.ERROR);
            }
            return params;
        } else {
            if (params.getRecurseLevelParameter() == 0) {
                params.setResult(ResponseResult.OK);
                return params;
            } else {
                return log(new RequestParams(params.getId(), true, params.getDurationParameter(), params.getRecurseLevelParameter()-1, ResponseResult.OK));
            }
        }
    }
}
