package okdemo.com.cn.myokhttp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import okdemo.com.cn.myokhttp.R;
import okdemo.com.cn.myokhttp.okhttps.OkHttpUtils;
import okdemo.com.cn.myokhttp.okhttps.callback.GenericsCallback;
import okdemo.com.cn.myokhttp.okhttps.utils.DES;
import okdemo.com.cn.myokhttp.okhttps.utils.JsonGenericsSerializator;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {
    private TextView mTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dimen);
//        mTv = (TextView) findViewById(R.id.showMessage);
    }

    /**
     * 返回的是json字符串，再次解析
     * @param view
     */
    public void getUser(View view) {
        String url = "http://android.youjing.cn//customer/login.do";
        Map<String, String> params = new HashMap<String, String>();
        try {
            params.put("mobile", "18514235676");
            params.put("password", DES.encryptDES("123456"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        OkHttpUtils.post().url(url).params(params).build().execute(new GenericsCallback<String>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {
                mTv.setText("onError:" + e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                mTv.setText("onResponse:" + response);
            }
        });
    }
}
