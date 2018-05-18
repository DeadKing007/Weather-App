package dead.weatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditClass extends AppCompatActivity implements View.OnClickListener{

    EditText Location;
    Button Click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);
        Location=findViewById(R.id.Location1);
        Click=findViewById(R.id.Click);
        Click.setOnClickListener(this);




    }



    @Override
    public void onClick(View view) {
        int id =view.getId();
        String location=Location.getText().toString();
        if (!location.isEmpty()) {
            if (id == R.id.Click) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Key", location);
                startActivity(intent);
            }
        }else
            Toast.makeText(this,"Enter Location",Toast.LENGTH_SHORT).show();

        String a=getIntent().getStringExtra("Return");
        if(a=="0") {
            Toast.makeText(this, "Enter Correct City", Toast.LENGTH_SHORT).show();
        }

    }
}
