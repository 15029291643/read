package com.example.common.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.Base64;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.common.activity.BaseApplication;
import com.example.common.activity.Show;
import com.example.common.callback.BitmapCallback;
import com.example.common.constant.URL;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/*
 * 图片工具类
 *
 * */
public class ImgUtil {
    // 根据utl返回bitmap
    private static Bitmap bitmap;
    private static String string;

    //对图片进行高斯模糊
    public static Bitmap gaussianBlur(Bitmap original) {
        RenderScript renderScript = RenderScript.create(BaseApplication.getContext());
        Allocation input = Allocation.createFromBitmap(renderScript, original);
        Allocation output = Allocation.createTyped(renderScript, input.getType());
        ScriptIntrinsicBlur scriptIntrinsicBlur = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        scriptIntrinsicBlur.setRadius(25);//模糊半径(radius)越大，性能要求越高，模糊半径不能超过25，所以并不能得到模糊度非常高的图片。
        scriptIntrinsicBlur.setInput(input);
        scriptIntrinsicBlur.forEach(output);
        output.copyTo(original);
        return original;
    }

    // 加载图片的url
    public static void urlToBitmap(String url, BitmapCallback callback) {
        Glide.with(BaseApplication.getContext()).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Bitmap blurredBitmap = ImgUtil.gaussianBlur(resource);
                callback.onResourceReady(blurredBitmap);
            }
        });
    }

    // 将bitmap对象转为string
    public static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //读取图片到ByteArrayOutputStream
        bitmap.compress(Bitmap.CompressFormat.PNG, 40, outputStream); //参数如果为100那么就不压缩
        byte[] bytes = outputStream.toByteArray();
        String strImg = Base64.encodeToString(bytes, Base64.DEFAULT);
        return strImg;
    }

    // 将string对象转为bitmap
    public static Bitmap stringToBitmap(String imgAddress) {
        byte[] input = Base64.decode(imgAddress, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        return bitmap;
    }

    //最终上传的Bitmap保存为File对象
    public static File bitmapToFile(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        File file = new File(Environment.getExternalStorageDirectory() + "/temp.jepg");
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(baos.toByteArray());
            int x = 0;
            byte[] b = new byte[1024 * 100];
            while ((x = is.read(b)) != -1) {
                fos.write(b, 0, x);
            }
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
}
