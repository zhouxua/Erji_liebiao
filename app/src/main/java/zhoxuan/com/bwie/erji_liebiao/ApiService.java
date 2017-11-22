package zhoxuan.com.bwie.erji_liebiao;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 周旋
 * 2017/11/22  19:48
 */

public interface ApiService {

    @GET("getCarts?uid=983")
    Observable<CartBean> getNoParams();

}
