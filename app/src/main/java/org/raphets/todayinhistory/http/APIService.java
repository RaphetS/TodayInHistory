package org.raphets.todayinhistory.http;

import org.raphets.todayinhistory.bean.GrilBean;
import org.raphets.todayinhistory.bean.Histroy;
import org.raphets.todayinhistory.bean.Picture;
import org.raphets.todayinhistory.bean.SimpleHistory;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by RaphetS on 2016/10/15.
 */

public interface APIService {

    /**
     * 获取历史列表
     */
    @FormUrlEncoded
    @POST("queryEvent.php")
    Observable<HttpResponse<SimpleHistory>> getHistoryList(@Field("key") String key, @Field("date") String date);


    /**
     * 获取历史详情
     */
    @FormUrlEncoded
    @POST("queryDetail.php")
    Observable<HttpResponse<Histroy<Picture>>> getHitoryDetail(@Field("key") String key, @Field("e_id") String e_id);


    /**
     * 妹纸列表
     */
    @GET("data/福利/{num}/{page}")
    Observable<GrilHttppResponse<List<GrilBean>>> getGirlList(@Path("num") int num, @Path("page") int page);
}
