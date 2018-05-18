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
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == MainActivity.RESULT_OK && requestCode==SECOND_ACTIVITY_REQUEST_CODE ) {

            Toast.makeText(this, "Enter Correct City", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        int id =view.getId();
        String location=Location.getText().toString();
        if (!location.isEmpty()) {
            if (id == R.id.Click) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Key", location);
                startActivityForResult(intent,SECOND_ACTIVITY_REQUEST_CODE);
            }
        }else
            Toast.makeText(this,"Enter Location",Toast.LENGTH_SHORT).show();



    }
}
