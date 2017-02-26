package okdemo.com.cn.myokhttp.okhttps.builder;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okdemo.com.cn.myokhttp.okhttps.request.PostFormRequest;
import okdemo.com.cn.myokhttp.okhttps.request.RequestCall;
import okdemo.com.cn.myokhttp.okhttps.utils.MakeSign;

/**
 * Created by zhy on 15/12/14.
 */
public class PostFormBuilder extends OkHttpRequestBuilder<PostFormBuilder> implements HasParamsable {
    private List<FileInput> files = new ArrayList<>();

    @Override
    public RequestCall build() {
        return new PostFormRequest(url, tag, params, headers, files, id).build();
    }

    public PostFormBuilder files(String key, Map<String, File> files) {
        for (String filename : files.keySet()) {
            this.files.add(new FileInput(key, filename, files.get(filename)));
        }
        return this;
    }

    public PostFormBuilder addFile(String name, String filename, File file) {
        files.add(new FileInput(name, filename, file));
        return this;
    }

    public static class FileInput {
        public String key;
        public String filename;
        public File file;

        public FileInput(String name, String filename, File file) {
            this.key = name;
            this.filename = filename;
            this.file = file;
        }

        @Override
        public String toString() {
            return "FileInput{" +
                    "key='" + key + '\'' +
                    ", filename='" + filename + '\'' +
                    ", file=" + file +
                    '}';
        }
    }


    @Override
    public PostFormBuilder params(Map<String, String> map) {
        map.put("platform", "Android");
        map.put("version", "2.0.0");
        map.put("deviceId", "123456789");
        map.put("pkg_channel", "360");
        Log.i("map====",map.toString());
        params= new HashMap<>();
        for (String key : map.keySet()) {
            params.put(key, map.get(key));
        }
        this.params.put("sign", MakeSign.makeSignNew(map));
        return this;
    }

    @Override
    public PostFormBuilder addParams(String key, String val) {
        if (this.params == null) {
            params = new LinkedHashMap<>();
        }
        params.put(key, val);
        return this;
    }


}
