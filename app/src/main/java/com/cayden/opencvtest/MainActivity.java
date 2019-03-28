package com.cayden.opencvtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
/**
 * Created by caydencui on 2019/3/27.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private Button btn_1;
    private Button btn_2;
    private ImageView imageView;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = (Button)findViewById(R.id.button_1);
        imageView = (ImageView)findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.people);
        imageView.setImageBitmap(bitmap);
        btn_1.setOnClickListener(this);

        btn_2 = (Button)findViewById(R.id.button_2);
        btn_2.setOnClickListener(this);
    }
    public void showImage(){
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.people);
        imageView.setImageBitmap(bitmap);
    }

    public void gray(){
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        int[] piexls = new int[w*h];
        bitmap.getPixels(piexls,0,w,0,0,w,h);
        int[] resultData =NativeLib.Bitmap2Grey(piexls,w,h);
        Bitmap resultImage = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        resultImage.setPixels(resultData,0,w,0,0,w,h);
        imageView.setImageBitmap(resultImage);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.button_1:showImage();break;
            case R.id.button_2:gray();break;
        }
    }


}
