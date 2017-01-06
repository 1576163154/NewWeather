package xion.newweather.HttpUrlConnection;

/*
* HttpUrlConnect 回调接口
 */

public interface HUCCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
