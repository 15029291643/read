package com.example.search.writer.info.title;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.example.common.activity.BaseActivity;
import com.example.common.activity.Show;
import com.example.common.object.Book;
import com.example.common.session.Session;
import com.example.common.util.ImgUtil;
import com.example.common.util.WriterUtil;
import com.example.search.R;
import com.example.search.writer.Search_WriterFragment;

import java.io.File;

public class Search_TitleActivity extends BaseActivity {
    private static final int TAKE_PHOTO = 11;// 拍照
    private static final int CROP_PHOTO_TAKE = 12;// 裁剪图片
    private static final int CROP_PHOTO_LOCAL = 14;// 裁剪图片
    private static final int LOCAL_CROP = 13;// 本地图库
    private Toolbar toolbar;
    private ImageView img;
    private EditText title;
    private EditText intro;
    private Button writer;
    private Uri imageUri;// 拍照时的图片uri
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_title_activity);
        bindView();
        initData();
        writer.setOnClickListener(view -> {
            String title2 = title.getText().toString();
            String intro2 = intro.getText().toString();
            if (title2.length() == 0 || intro2.length() == 0 || bitmap == null) {
                return;
            }
            // 实现更新功能
            if (Session.bookPosition != Session.ADD) {
                Book book = Session.book;
                book.setTitle(title2);
                book.setIntro(intro2);
                String s = ImgUtil.bitmapToString(bitmap);
                book.setImg(s);
                // 数据更新
                Search_WriterFragment.set(Session.bookPosition, book);
                WriterUtil.update(book);
                Show.toast("更新成功");
                // 添加
            } else {
                Book book = new Book();
                book.setTitle(title2);
                book.setIntro(intro2);
                String s = ImgUtil.bitmapToString(bitmap);
                book.setImg(s);
                // 数据更新
                Search_WriterFragment.add(book);
                WriterUtil.add(book);
                Session.book = book;
                Show.toast("添加成功");
            }
            finish();
        });
        img.setOnClickListener(view -> {
            requestPermission();  //在其中若用户给予权限则请求相机拍照
        });
    }

    private void initData() {
        if (Session.bookPosition != Session.ADD) {
            Book book = Session.book;
            title.setText(book.getTitle());
            intro.setText(book.getIntro());
            bitmap = ImgUtil.stringToBitmap(book.getImg());
            img.setImageBitmap(bitmap);
        }
    }

    private void bindView() {
        toolbar = findViewById(R.id.search_title_toolbar);
        img = findViewById(R.id.search_title_img);
        title = findViewById(R.id.search_title_title);
        intro = findViewById(R.id.search_title_intro);
        writer = findViewById(R.id.search_title_writer);
    }

    //动态请求权限
    private void requestPermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //请求权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
        }
        takePhotoOrSelectPicture();
    }

    private void takePhotoOrSelectPicture() {
        CharSequence[] items = {"拍照", "图库"};// 裁剪items选项

        // 弹出对话框提示用户拍照或者是通过本地图库选择图片
        new AlertDialog.Builder(Search_TitleActivity.this)
                .setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        switch (which) {
                            // 选择了拍照
                            case 0:
                                // 创建文件保存拍照的图片
                                File takePhotoImage = new File(Environment.getExternalStorageDirectory(), "take_photo_image.jpg");
                                try {
                                    // 文件存在，删除文件
                                    if (takePhotoImage.exists()) {
                                        takePhotoImage.delete();
                                    }
                                    // 根据路径名自动的创建一个新的空文件
                                    takePhotoImage.createNewFile();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // 获取图片文件的uri对象
                                if (Build.VERSION.SDK_INT >= 24) {
                                    // 小心authority属性，必须要与
                                    imageUri = FileProvider.getUriForFile(Search_TitleActivity.this,
                                            "com.example.myapplication.MainActivity", takePhotoImage);
                                } else {
                                    imageUri = Uri.fromFile(takePhotoImage);
                                }
                                // 创建Intent，用于启动手机的照相机拍照
                                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                // 指定输出到文件uri中
                                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                                // 启动intent开始拍照
                                startActivityForResult(intent, TAKE_PHOTO);
                                break;
                            // 调用系统图库
                            case 1:

                                // 创建Intent，用于打开手机本地图库选择图片
                                Intent intent1 = new Intent(Intent.ACTION_PICK,
                                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                // 启动intent打开本地图库
                                startActivityForResult(intent1, LOCAL_CROP);
                                break;

                        }

                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case TAKE_PHOTO:// 拍照

                if (resultCode == RESULT_OK) {
                    // 创建intent用于裁剪图片
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    // 设置数据为文件uri，类型为图片格式
                    intent.setDataAndType(imageUri, "image/*");
                    // 允许裁剪
                    intent.putExtra("scale", true);
                    // 指定输出到文件uri中
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    // 启动intent，开始裁剪
                    startActivityForResult(intent, CROP_PHOTO_TAKE);
                }
                break;
            case LOCAL_CROP:// 系统图库

                if (resultCode == RESULT_OK) {
                    // 创建intent用于裁剪图片
                    Intent intent1 = new Intent("com.android.camera.action.CROP");
                    // 获取图库所选图片的uri
                    Uri uri = data.getData();
                    intent1.setDataAndType(uri, "image/*");
                    //  设置裁剪图片的宽高
                    intent1.putExtra("outputX", 150);
                    intent1.putExtra("outputY", 200);
                    // 裁剪后返回数据
                    intent1.putExtra("return-data", true);
                    // 启动intent，开始裁剪
                    startActivityForResult(intent1, CROP_PHOTO_LOCAL);
                }

                break;
            case CROP_PHOTO_TAKE:// 裁剪后展示图片
                if (resultCode == RESULT_OK) {
                    try {
                        // 展示拍照后裁剪的图片
                        if (imageUri != null) {
                            // 创建BitmapFactory.Options对象
                            BitmapFactory.Options option = new BitmapFactory.Options();
                            // 属性设置，用于压缩bitmap对象
                            option.inSampleSize = 2;
                            option.inPreferredConfig = Bitmap.Config.RGB_565;
                            // 根据文件流解析生成Bitmap对象
                            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, option);
                            // 展示图片

                            img.setImageBitmap(bitmap);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CROP_PHOTO_LOCAL:// 裁剪后展示图片
                if (resultCode == RESULT_OK) {
                    try {
                        // 展示图库中选择裁剪后的图片
                        if (data != null) {
                            // 根据返回的data，获取Bitmap对象
                            bitmap = data.getExtras().getParcelable("data");
                            // 展示图片
                            img.setImageBitmap(bitmap);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}