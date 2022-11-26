package cn.geekcity.home.manager.impl;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpResponse;

import java.util.function.Consumer;

import cn.geekcity.home.manager.HomeManager;

public class HomeManagerFakeImpl implements HomeManager {

    private static final int TIMEOUT = 1000 * 3;

    /**
     * 这里用一个HTTP请求模拟异步接口
     */
    @Override
    public <T> void setValue(String key, T value, Consumer<T> onSuccess, Consumer<String> onError) {
        AsyncHttpRequest request = new AsyncHttpGet("http://www.baidu.com" ).setTimeout(TIMEOUT);

        AsyncHttpClient.getDefaultInstance()
                .executeString(request, new AsyncHttpClient.StringCallback() {

                    @Override
                    public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                        if (e != null) {
                            onError.accept(e.toString());
                            return;
                        }

                        onSuccess.accept(value);
                    }
                });
    }
}
