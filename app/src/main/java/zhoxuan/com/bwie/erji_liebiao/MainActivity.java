package zhoxuan.com.bwie.erji_liebiao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    //View
    private ExpandableListView expandableListView;
    List<CartBean.DataBean> groups;
    List<CartBean.DataBean.ListBean> childs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

 getNoParams();

    }
    public void getNoParams() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_PATH).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        //通过动态代理得到网络接口对象
        //http://service.meiyinkeqiu.com/service/ads/cptj
        ApiService apiService = retrofit.create(ApiService.class);
        //得到Observable
        Observable<CartBean> observable = apiService.getNoParams();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CartBean>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this,"onCompleted",Toast.LENGTH_LONG);
                        Log.d("main", "onCompleted: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this,"onCompleted",Toast.LENGTH_LONG);
                        Log.d("main", "onError: ");
                    }

                    @Override
                    public void onNext(CartBean cartBean) {
                       groups = cartBean.getData();
                        for (int i = 0;i<cartBean.getData().size();i++){
                            childs = cartBean.getData().get(i).getList();
                        }

                        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

                        expandableListView.setAdapter(new MyExpandableListView(MainActivity.this,groups,childs));
                    }
                });

    }
}
