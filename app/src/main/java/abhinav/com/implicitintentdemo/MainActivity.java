package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etxt_email,etxt_phone;
    String email,emailPattern,phone;
    ImageView imgv_phone,imgv_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxt_email=(EditText)findViewById(R.id.etxt_email);
        etxt_phone=(EditText)findViewById(R.id.etxt_phone);
        imgv_phone=(ImageView) findViewById(R.id.imgv_phone);
        imgv_email=(ImageView) findViewById(R.id.imgv_email);

        imgv_email.setOnClickListener(this);
        imgv_phone.setOnClickListener(this);
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
