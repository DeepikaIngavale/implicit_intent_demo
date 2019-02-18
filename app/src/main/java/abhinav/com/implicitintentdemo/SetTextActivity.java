package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetTextActivity extends AppCompatActivity implements View.OnClickListener
{
   // private static final int SECOND_ACTIVITY_REQUEST_CODE=102;
    Button btn_backpress;
    EditText etxt_fname,etxt_mname,etxt_lname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        etxt_fname=(EditText)findViewById(R.id.etxt_fname);
        etxt_mname=(EditText)findViewById(R.id.etxt_mname);
        etxt_lname=(EditText)findViewById(R.id.etxt_lname);
        btn_backpress=(Button) findViewById(R.id.btn_backpress);

        btn_backpress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent intent = new Intent();
        intent.putExtra("Firstname", ""+etxt_fname.getText().toString().trim());
        intent.putExtra("Middlename", ""+etxt_mname.getText().toString().trim());
        intent.putExtra("Lastname", ""+etxt_lname.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                // Get String data from Intent
                String returnString = data.getStringExtra("Fname");

                // Set text view with string

            }
        }
    }*/
}
