package abhinav.com.implicitintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SetTextActivity extends AppCompatActivity {
    TextView txt_fname,txt_mname,txt_lname;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_text);

        txt_fname=(TextView)findViewById(R.id.txt_fname);
        txt_mname=(TextView)findViewById(R.id.txt_mname);
        txt_lname=(TextView)findViewById(R.id.txt_lname);

        Intent intent=getIntent();
        String name=intent.getStringExtra("Fname");
        String mname=intent.getStringExtra("Mname");
        String lname=intent.getStringExtra("Lname");
        txt_fname.setText(name);
        txt_mname.setText(mname);
        txt_lname.setText(lname);
        Toast.makeText(this, ""+name +" "+mname+" "+lname, Toast.LENGTH_SHORT).show();
    }
}
