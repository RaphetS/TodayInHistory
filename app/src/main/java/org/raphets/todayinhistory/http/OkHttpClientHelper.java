package org.raphets.todayinhistory.http;

import android.content.Context;

import org.raphets.todayinhistory.BuildConfig;
import org.raphets.todayinhistory.MyApp;
import org.raphets.todayinhistory.common.Constants;
import org.raphets.todayinhistory.utils.Tool;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by matou0289 on 2016/9/27.
 */
public class OkHttpClientHelper {
    private static OkHttpClientHelper instance;

    public static OkHttpClientHelper getInstance() {
        if (instance == null) {
            synchronized (OkHttpClientHelper.class) {
                if (instance == null) {
                    instance = new OkHttpClientHelper();
                }
            }
        }
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        File cacheFile = new File(MyApp.getInstance().getCacheDir(), Constants.PATH_CACHE);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        Cache cache = new Cache(cacheFile, 100 * 1024 * 1024);
        OkHttpClient client = null;
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();
        } else {
            client = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .readTimeout(10000, TimeUnit.MILLISECONDS)
                    .connectTimeout(10000, TimeUnit.MILLISECONDS)
                    .writeTimeout(10000, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .build();
        }
        return client;
    }

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Tool.isNetworkConnected()) {
                int maxAge = 60 * 10; // 在线缓存在10分钟内可读取
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // 离线时缓存保存4周
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    public OkHttpClient getCacheOkHttpClient(Context context) {
        final File baseDir = context.getCacheDir();
        final File cacheDir = new File(baseDir, "HttpResponseCache");
        Cache cache = new Cache(cacheDir, 100 * 1024 * 1024);   //缓存可用大小为10M
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!Tool.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response originalResponse = chain.proceed(request);
                if (Tool.isNetworkConnected()) {
                    int maxAge = 60;                  //在线缓存一分钟
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 4 * 7;     //离线缓存4周
                    return originalResponse.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .build();
                }
            }
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build();
    }
}