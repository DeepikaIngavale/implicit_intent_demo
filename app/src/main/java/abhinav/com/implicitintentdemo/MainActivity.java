package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int IMAGE_CAPTURE=100;
    private static final int RELOAD_IMAGE=101;
    private static final int ADD_DATA=102;
    EditText etxt_email,etxt_phone;
    TextView txt_fname,txt_mname,txt_lname;
    String email,phone;
    ImageView imgv_phone,imgv_email,imgv_pick,imgv_gallery,imgv_camera,imgv_add;
   // Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxt_email=(EditText)findViewById(R.id.etxt_email);
        etxt_phone=(EditText)findViewById(R.id.etxt_phone);
        imgv_phone=(ImageView) findViewById(R.id.imgv_phone);
        imgv_email=(ImageView) findViewById(R.id.imgv_email);
        imgv_pick=(ImageView) findViewById(R.id.imgv_pick);
        imgv_gallery=(ImageView) findViewById(R.id.imgv_gallery);
        imgv_camera=(ImageView) findViewById(R.id.imgv_camera);
        imgv_add=(ImageView) findViewById(R.id.imgv_add);
        txt_fname=(TextView)findViewById(R.id.txt_fname);
        txt_mname=(TextView)findViewById(R.id.txt_mname);
        txt_lname=(TextView)findViewById(R.id.txt_lname);
        //btn_send=(Button) findViewById(R.id.btn_send);

        imgv_email.setOnClickListener(this);
        imgv_phone.setOnClickListener(this);
        imgv_gallery.setOnClickListener(this);
        imgv_camera.setOnClickListener(this);
        imgv_add.setOnClickListener(this);
        //btn_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.imgv_email)
        {
            email = etxt_email.getText().toString().trim();
            boolean valid = isEmailAddressValid(email);
            if(valid)
            {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.setType("text/html");
                sendIntent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to Send");

                startActivity(Intent.createChooser(sendIntent, "Send Email"));
               // startActivity(sendIntent);
                Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
            }
            else
            {
                etxt_email.setError("Invalid email address");
            }

        }

        if(view.getId()==R.id.imgv_phone)
        {
            phone = etxt_phone.getText().toString().trim();
            openDialer();
        }

        if(view.getId()==R.id.imgv_gallery)
        {
            Intent getImageIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(getImageIntent, RELOAD_IMAGE );
        }

        if(view.getId()==R.id.imgv_camera)
        {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, IMAGE_CAPTURE);
        }

        if(view.getId()==R.id.imgv_add)
        {
            Intent intentName=new Intent(this,SetTextActivity.class);
            startActivityForResult(intentName, ADD_DATA);
        }

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RELOAD_IMAGE && resultCode==RESULT_OK)
        {
            try
            {
                Uri selectedImage=data.getData();
                Bitmap selected_img_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                imgv_pick.setImageBitmap(selected_img_bitmap);

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(requestCode==IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                imgv_pick.setImageBitmap(imageBitmap);
        }
        if(requestCode==ADD_DATA)
        {
            if(resultCode==RESULT_OK)
            {
                String name = getIntent().getStringExtra("Fname");
                String mname = getIntent().getStringExtra("Mname");
                String lname = getIntent().getStringExtra("Lname");
                txt_fname.setText(name);
                txt_mname.setText(mname);
                txt_lname.setText(lname);
                Toast.makeText(this, ""+name+" "+mname+" "+lname, Toast.LENGTH_SHORT).show();
            }
        }
    }
   protected void openDialer()
    {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        if (phone.equals(""))
        {
            intent.setData(Uri.parse("tel:" + "+880XXXXXXXXXXXX"));
        }
        else
        {
            intent.setData(Uri.parse("tel:" + phone));
        }
        startActivity(intent);
    }
    public static boolean isEmailAddressValid(String email)
    {
        boolean isEmailValid = false;

        String strExpression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern objPattern = Pattern.compile(strExpression, Pattern.CASE_INSENSITIVE);
        Matcher objMatcher = objPattern.matcher(inputStr);
        if (objMatcher.matches())
        {
            isEmailValid = true;
        }
        return isEmailValid;
    }


}
