package com.yu.seemovie.view.movieitem;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.yu.seemovie.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters


    private ImageView mPicture;


    public AddMovieFragment() {
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
    public static AddMovieFragment newInstance(String param1, String param2) {
        AddMovieFragment fragment = new AddMovieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.unused_add_moive, null);


        mPicture = view.findViewById(R.id.picture);


        mPicture.setOnClickListener(new dialog());


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (resultCode) {
            case 1:
                File tmp = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");

                break;
            case 2:
                if (data == null) {
                    return;
                }

                break;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setPicToView(Intent intent) {
        Bundle bundle = intent.getExtras();
        FileOutputStream b = null;
        String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
        File tmp = new File(Environment.getExternalStorageDirectory() + "/" + name);


    }

    class dialog implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            AlertDialog alertDialog =
                    builder.setTitle("选择图片")
                            .setPositiveButton("拍照", new dialog_onClickListener())
                            .setNegativeButton("相册", new dialog_onClickListener())
                            .create();
            alertDialog.show();

        }

        class dialog_onClickListener implements Dialog.OnClickListener {
            Intent intent;

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();


                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                                Environment
                                        .getExternalStorageDirectory(),
                                "temp.jpg")));
                        startActivityForResult(intent, 1);
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        intent = new Intent(Intent.ACTION_PICK, null);
                        intent.setDataAndType(
                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*"
                        );
                        startActivityForResult(intent, 2);
                        break;

                }

            }
        }

    }
}

