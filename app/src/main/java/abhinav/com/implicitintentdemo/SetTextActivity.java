package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetTextActivity extends AppCompatActivity {
    //private static final int RESULT_OK=1;
    EditText etxt_fname,etxt_mname,etxt_lname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        etxt_fname=(EditText)findViewById(R.id.etxt_fname);
        etxt_mname=(EditText)findViewById(R.id.etxt_mname);
        etxt_lname=(EditText)findViewById(R.id.etxt_lname);

       //onBackPressed();
    }
    /*public void onBackPressed()
    {
        super.onBackPressed();

        Intent intent = new Intent();
        intent.putExtra("Fname", ""+etxt_fname.getText().toString().trim());
        intent.putExtra("Mname", ""+etxt_mname.getText().toString().trim());
        intent.putExtra("Lname", ""+etxt_lname.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }*/
}
