package com.bangproject.reactlayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    AppCompatTextView textView;
    AppCompatEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("결과", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        getDpi();


        //TODO: 플래그먼트 + 페이지뷰 추가하기
    }

    void init(){
        textView = findViewById(R.id.tv_01);
        editText = findViewById(R.id.et_01);
    }

    void getDpi() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Log.i("결과", "getDpi : " + metrics.densityDpi + " " + metrics.density);

        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_LOW);
                break;
            case DisplayMetrics.DENSITY_MEDIUM: //160
                Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_MEDIUM);
                break;
            case DisplayMetrics.DENSITY_TV:
            case DisplayMetrics.DENSITY_HIGH:
                Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_HIGH);
                break;
            case DisplayMetrics.DENSITY_280:
            case DisplayMetrics.DENSITY_XHIGH: // 320
                Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_XHIGH);
                break;
            case DisplayMetrics.DENSITY_440:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_440);
                }
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_XXHIGH);
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Log.i("결과", "dpi state : " + DisplayMetrics.DENSITY_XXXHIGH);
                }
                break;
        }


    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("결과", "onConfigurationChanged");

        String text = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            text = Objects.requireNonNull(editText.getText()).toString();
        }
        Log.i("결과", "text[1] : " + text);

        //onConfigurationChanged 가 반응하기 위해선 매니페스트 수정
        //onCreate가 실행되지 않기 때문에 데이터이동에 대해서도 수동으로 해주어야 한다.
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.i("결과", "세로모드");
            setContentView(R.layout.activity_main);
        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("결과", "가로모드");
            setContentView(R.layout.activity_main);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Log.i("결과", "dpi : " + newConfig.densityDpi);
        }

        onDynamicPush();


    }

    void onDynamicPush() {
        String text = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            text = Objects.requireNonNull(editText.getText()).toString();
        }
        Log.i("결과", "text[2] : " + text); // 단지 메모리에 남아있는 이전 객체

        init(); // 새로 객체를 만들어 줘야 한다.
        editText.setText(text);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.i("결과", "onCreateView");

        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("결과", "onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("결과", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("결과", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("결과", "onDestroy");
    }
}
