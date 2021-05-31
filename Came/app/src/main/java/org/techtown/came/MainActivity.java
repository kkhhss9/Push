package org.techtown.came;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override

    public void onClick(View v) {

        id_view = v.getId();
        if(v.getId() == R.id.btn_signupfinish) {
            /** SharedPreference 환경 변수 사용 **/
            SharedPreferences prefs = getSharedPreferences("login", 0);
            /** prefs.getString() return값이 null이라면 2번째 함수를 대입한다. **/
            String login = prefs.getString("USER_LOGIN", "LOGOUT");
            String facebook_login = prefs.getString("FACEBOOK_LOGIN", "LOGOUT");
            String user_id = prefs.getString("USER_ID","");
            String user_name = prefs.getString("USER_NAME", "");
            String user_password = prefs.getString("USER_PASSWORD", "");
            String user_phone = prefs.getString("USER_PHONE", "");
            String user_email = prefs.getString("USER_EMAIL", "");
            dbmanger.select(user_id,user_name,user_password, user_phone, user_email);
            dbmanger.selectPhoto(user_name, mImageCaptureUri, absoultePath);
            Intent mainIntent = new Intent(SignUpPhotoActivity.this, LoginActivity.class);
            SignUpPhotoActivity.this.startActivity(mainIntent);
            SignUpPhotoActivity.this.finish();
            Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();


        }else if(v.getId() == R.id.btn_UploadPicture) {

            DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    doTakePhotoAction();
                }
            };

            DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {

                @Override

                public void onClick(DialogInterface dialog, int which) {
                    doTakeAlbumAction();
                }
            };

            DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };


            new AlertDialog.Builder(this)

                    .setTitle("업로드할 이미지 선택")
                    .setPositiveButton("사진촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();

        }
        public void doTakePhotoAction() // 카메라 촬영 후 이미지 가져오기

        {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


            // 임시로 사용할 파일의 경로를 생성

            String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";

            mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));


            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);

            startActivityForResult(intent, PICK_FROM_CAMERA);

        }
        public void doTakeAlbumAction() // 앨범에서 이미지 가져오기

        {

            // 앨범 호출

            Intent intent = new Intent(Intent.ACTION_PICK);

            intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

            startActivityForResult(intent, PICK_FROM_ALBUM);

        }

    }

    @Override

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode,resultCode,data);


        if(resultCode != RESULT_OK)

            return;


        switch(requestCode)

        {

            case PICK_FROM_ALBUM:

            {

            }


            case PICK_FROM_CAMERA:

            {

            }

            case CROP_FROM_IMAGE:

            {

            }

        }

    }


    requestCode를 통해 switch문으로 분기합니다.


    private static final int PICK_FROM_CAMERA = 0;

    private static final int PICK_FROM_ALBUM = 1;

    private static final int CROP_FROM_IMAGE = 2;


    PICK_FROM_CAMERA 는 사진을 촬영하고 찍힌 이미지를 처리하는 부분

    PICK_FROM_ALBUM 은 앨범에서 사진을 고르고 이미지를 처리하는 부분

    CROP_FROM_IMAGE 는 이미지를 크롭하는 부분


case PICK_FROM_ALBUM:

    {

        // 이후의 처리가 카메라와 같으므로 일단  break없이 진행합니다.

        // 실제 코드에서는 좀더 합리적인 방법을 선택하시기 바랍니다.

        mImageCaptureUri = data.getData();

        Log.d("SmartWheel",mImageCaptureUri.getPath().toString());

    }


case PICK_FROM_CAMERA:

    {

        // 이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정합니다.

        // 이후에 이미지 크롭 어플리케이션을 호출하게 됩니다.

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(mImageCaptureUri, "image/*");


        // CROP할 이미지를 200*200 크기로 저장

        intent.putExtra("outputX", 200); // CROP한 이미지의 x축 크기

        intent.putExtra("outputY", 200); // CROP한 이미지의 y축 크기

        intent.putExtra("aspectX", 1); // CROP 박스의 X축 비율

        intent.putExtra("aspectY", 1); // CROP 박스의 Y축 비율

        intent.putExtra("scale", true);

        intent.putExtra("return-data", true);

        startActivityForResult(intent, CROP_FROM_iMAGE); // CROP_FROM_CAMERA case문 이동

        break;

    }


case CROP_FROM_iMAGE:

    {

        // 크롭이 된 이후의 이미지를 넘겨 받습니다.

        // 이미지뷰에 이미지를 보여준다거나 부가적인 작업 이후에

        // 임시 파일을 삭제합니다.

        if(resultCode != RESULT_OK) {

            return;

        }


        final Bundle extras = data.getExtras();


        // CROP된 이미지를 저장하기 위한 FILE 경로

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath()+

                "/SmartWheel/"+System.currentTimeMillis()+".jpg";


        if(extras != null)

        {

            Bitmap photo = extras.getParcelable("data"); // CROP된 BITMAP

            iv_UserPhoto.setImageBitmap(photo); // 레이아웃의 이미지칸에 CROP된 BITMAP을 보여줌



            storeCropImage(photo, filePath); // CROP된 이미지를 외부저장소, 앨범에 저장한다.

            absoultePath = filePath;

            break;


        }

        // 임시 파일 삭제

        File f = new File(mImageCaptureUri.getPath());

        if(f.exists())

        {

            f.delete();

        }

    }

}