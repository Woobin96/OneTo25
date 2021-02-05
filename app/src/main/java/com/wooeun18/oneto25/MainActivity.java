package com.wooeun18.oneto25;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    AdView adView;


    TextView tv;
    Button btnRetry;
    //버튼 참조변수 25개짜리 배열참조변수
    Button[] btns = new Button[25];

    //현제 눌러야 할 번호
    int num=1;

    //버튼 배경이미지 객체 참조변수
    Drawable btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, "ca-app-pub-7150020580371346~4279811151");

        adView= findViewById(R.id.adview);

        //광고 로드하기
        AdRequest adRequest= new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        tv=findViewById(R.id.tv);
        btnRetry= findViewById(R.id.btn_retry);

        ArrayList<Integer> arr= new ArrayList<>();
        for(int i=1; i<26; i++){
            arr.add(i);
        }

        Collections.shuffle(arr); //숫자를 섞는다 . 배열의 요소 위치 섞어 주는거 .

        for(int i=0; i<btns.length; i++){
            btns[i]= findViewById(R.id.btn01+i);
            btns[i].setText(arr.get(i)+"");
            btns[i].setTag(arr.get(i)+"");
        }

        //배경 이미지 객체(Drawable) 얻어오기
        btnBack= btns[0].getBackground();
    }//onCreate method

    //xml 에서 뷰에 설정한 onClick 속성에 값으로 지정한 메소드 ,
    //이 메소드는 그 뷰가 클릭되면 자동으로 실행됨 - 콜백 메소드 .
    //절대 조건
    //1. 반드시 접근제한자는 public .
    //2. 리턴 타입은 void 만 가능 .
    //3. 라파미터는 View 타입 1개만 가능 .

    public void clickBtn(View v){

        //클릭한 버튼 (v) 의 글씨를 얻어와서
        //현재 눌러야 할 번호(num)와 같은지 비교 .

        //v의 자료형이 View로 되어있어
        //본인이 버튼인지 모름 . 바보 ㅋㅋ 나도 바보 ㅋㅋㅋ...

        //방법 1. 글씨를 얻어오기
           Button btn= (Button)v;
//        String s= btn.getText().toString(); //글씨 바꾸는거 .
//        int n= Integer.parseInt(s);
        //방법 2. 이미지로 되어 있는경우
        //tag 라는 특별한 View 의 멤버변수를 이용
        String s = btn.getTag().toString();
        int n = Integer.parseInt(s);

        if(n==num){
            btn.setText("OK");
            btn.setTextColor(0xffff0000); //ARGB
            btn.setBackground(null); //배경 그림객체(Drawable) 없애기

            num++;//다음 번호로 증가 .
            tv.setText(num+"");
        }

        //게임종료
        if(num>25){
            tv.setText("Clear!");

            //리트라이 버튼을  활성화 .
            btnRetry.setEnabled(true);

        }

    }

    //리트라이 버튼을 클릭했을때 자동으로 실행되는 콜백메소드

    public void clickRetry(View v){
        ArrayList<Integer> arr= new ArrayList<>();
        for(int i=1; i<26; i++){
            arr.add(i);
        }
        Collections.shuffle(arr);

        for(int i=0; i<btns.length; i++){
            btns[i].setText(arr.get(i)+"");
            btns[i].setTag(arr.get(i));
            btns[i].setTextColor(0xffffffff);
            //배경이미지객체 를 설정 .
            btns[i].setBackground(btnBack);
        }

        num=1;
        tv.setText(num+"");

        btnRetry.setEnabled(false);
    }

}//Activity class