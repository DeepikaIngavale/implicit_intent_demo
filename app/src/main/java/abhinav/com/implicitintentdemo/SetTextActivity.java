package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetTextActivity extends AppCompatActivity implements View.OnClickListener {
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
        intent.putExtra("Fname", ""+etxt_fname.getText().toString().trim());
        intent.putExtra("Mname", ""+etxt_mname.getText().toString().trim());
        intent.putExtra("Lname", ""+etxt_lname.getText().toString().trim());
        setResult(RESULT_OK, intent);
        finish();
    }
}
