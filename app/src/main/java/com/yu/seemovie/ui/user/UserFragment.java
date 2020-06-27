package com.yu.seemovie.ui.user;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.yu.seemovie.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserFragment extends Fragment {
    public static final int REQUEST_PERMISSION_CODE = 101, PIC_VIA_CAM = 1, PIC_VIA_MEDIA = 2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Intent intent;

    // TODO: Rename and change types of parameters
    PicChooser picChooser;
    private ImageView mPicture;
    private Button mSave;
    private Button mExit;
    private ImageView mImageView;


    public UserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMovieFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserFragment newInstance(String param1, String param2) {
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_slideshow_my_info, null);


        mSave = view.findViewById(R.id.save);

        mPicture = view.findViewById(R.id.picture);

        mImageView = view.findViewById(R.id.imageView);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigateUp();
                Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
            }
        });

        mPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picChooser = new PicChooser(getContext());
                picChooser.init();

            }
        });


        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            boolean allowAllPermission = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "请允许权限", Toast.LENGTH_SHORT);
                    allowAllPermission = false;
                    break;
                }

            }
            if (allowAllPermission) {
                picChooser.getPicChoose();


            }

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bitmap bitmap = null;
        if (resultCode == RESULT_OK)

            switch (requestCode) {
                case PIC_VIA_CAM:
                    Uri uri;
                    uri = picChooser.uri;


                    try {
                        bitmap = picChooser.getBitmapFormUri(picChooser.uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mImageView.setImageBitmap(bitmap);


                    break;
                case PIC_VIA_MEDIA:
                    if (data != null) {

                        try {
                            bitmap = picChooser.getBitmapFormUri(data.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        mImageView.setImageBitmap(bitmap);

                    }

                    break;
            }

        super.onActivityResult(requestCode, resultCode, data);
    }

    class PicChooserOnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            picChooser = new PicChooser(getContext());
            picChooser.init();

        }
    }

    public class PicChooser {
        public Uri uri = null;
        Context context;

        public PicChooser(Context context) {
            this.context = context;

        }

        public void init() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                if (!checkPermission()) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);
                } else {
                    getPicChoose();
                }

        }

        //创建图片选择窗口
        public void getPicChoose() {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            AlertDialog alertDialog =
                    builder.setTitle("选择图片")
                            .setPositiveButton("拍照", new PicChooser_dialog_onClickListener())
                            .setNegativeButton("相册", new PicChooser_dialog_onClickListener())
                            .create();
            alertDialog.show();
        }

        private boolean checkPermission() {
            boolean haveCameraPermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
            boolean haveFilePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

            return haveCameraPermission && haveFilePermission;

        }

        private void pickupImage() {
            intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"
            );
            startActivityForResult(intent, PIC_VIA_MEDIA);
        }

        private void takePhoto() {
            String path = getContext().getFilesDir().getPath() + File.separator + "images" + File.separator;
            File file = new File(path, "test.jpg");
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();

            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(getContext(), "com.yu.seemovie", file);
            } else {
                Uri.fromFile(file);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, PIC_VIA_CAM);
        }

        public Bitmap getBitmapFormUri(Uri uri) throws IOException {
            InputStream input = context.getContentResolver().openInputStream(uri);

            //这一段代码是不加载文件到内存中也得到bitmap的真是宽高，主要是设置inJustDecodeBounds为true
            BitmapFactory.Options onlyBoundsOptions = new BitmapFactory.Options();
            onlyBoundsOptions.inJustDecodeBounds = true;//不加载到内存
            onlyBoundsOptions.inDither = true;//optional
            onlyBoundsOptions.inPreferredConfig = Bitmap.Config.RGB_565;//optional
            BitmapFactory.decodeStream(input, null, onlyBoundsOptions);
            input.close();
            int originalWidth = onlyBoundsOptions.outWidth;
            int originalHeight = onlyBoundsOptions.outHeight;
            if ((originalWidth == -1) || (originalHeight == -1))
                return null;

            //图片分辨率以480x800为标准
            float hh = 800f;//这里设置高度为800f
            float ww = 480f;//这里设置宽度为480f
            //缩放比，由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
            int be = 1;//be=1表示不缩放
            if (originalWidth > originalHeight && originalWidth > ww) {//如果宽度大的话根据宽度固定大小缩放
                be = (int) (originalWidth / ww);
            } else if (originalWidth < originalHeight && originalHeight > hh) {//如果高度高的话根据宽度固定大小缩放
                be = (int) (originalHeight / hh);
            }
            if (be <= 0)
                be = 1;
            //比例压缩
            BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
            bitmapOptions.inSampleSize = be;//设置缩放比例
            bitmapOptions.inDither = true;
            bitmapOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            input = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input, null, bitmapOptions);
            input.close();

            return compressImage(bitmap);//再进行质量压缩
        }

        public Bitmap compressImage(Bitmap image) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
            int options = 100;
            while (baos.toByteArray().length / 1024 > 100) {  //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                baos.reset();//重置baos即清空baos
                //第一个参数 ：图片格式 ，第二个参数： 图片质量，100为最高，0为最差  ，第三个参数：保存压缩后的数据的流
                image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options，把压缩后的数据存放到baos中
                options -= 10;//每次都减少10
                if (options <= 0)
                    break;
            }
            ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
            Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
            return bitmap;
        }

        class PicChooser_dialog_onClickListener implements Dialog.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        takePhoto();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        pickupImage();
                        break;
                }
            }
        }


    }


}

