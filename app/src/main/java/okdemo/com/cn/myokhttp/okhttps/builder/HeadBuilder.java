package okdemo.com.cn.myokhttp.okhttps.builder;

import okdemo.com.cn.myokhttp.okhttps.OkHttpUtils;
import okdemo.com.cn.myokhttp.okhttps.request.OtherRequest;
import okdemo.com.cn.myokhttp.okhttps.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
