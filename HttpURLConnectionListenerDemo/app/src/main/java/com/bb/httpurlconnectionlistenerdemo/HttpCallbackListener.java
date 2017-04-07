package com.bb.httpurlconnectionlistenerdemo;

/**
 * Created by bb on 2017/4/7.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
