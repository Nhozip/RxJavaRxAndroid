package com.nhozip.aib.rxjavarxandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.nhozip.aib.rxjavarxandroid.model.Auth;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    List<Integer> integers = new ArrayList<>();
    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPass)
    EditText edtPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    private List<Auth> auths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        for (int i = 0; i < 10; i++) {
            integers.add(i);
            auths.add(new Auth("nhozip", i * 10));
        }

        // tim cac so chia het cho 2 trong mang
        // C1
        Observable.fromArray(integers)
                .flatMapIterable(f -> f)
                .subscribe(integer -> {
                    if (integer % 2 == 0) {
                        Log.e("C1 so chia het cho 2", integer + "");
                    }
                });

        //C2
        Observable.just(integers)
                .flatMapIterable(integers1 -> integers1).subscribeOn(Schedulers.computation())
                // the result subscription will happen in the UI Thread
                .observeOn(AndroidSchedulers.mainThread())
                .filter(integer -> integer % 2 == 0)
                .subscribe(integer -> Log.e("C2  so chia het cho 2", integer + ""));

        //forEach
        Observable.just(integers).forEach(integers1 -> Log.e("for", integers1 + ""));

        //ngoài ra còn lấy n phần tử m đầu tien ,cuối cùng,lấy danh sách các phàn tử trùng nhau bỏ quá


        //Change Text

        RxTextView.textChangeEvents(edtSearch)
                .filter(v -> v.text().length() > 3).
                subscribe(kq -> {
                    Log.e("KQ", kq.text().toString());
                });
        btnLogin.setEnabled(false);

        displaySumAge20();

    }

    public void displaySumAge20() {
        Observable.just(auths)
                .flatMapIterable(auths1 -> auths1)
                .filter(auth -> auth.age > 20)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(auth -> {
                    Log.e("sum", auth.toString());

                });


    }

    @OnClick(R.id.btnLogin)
    public void actionLogin() {

    }

}
