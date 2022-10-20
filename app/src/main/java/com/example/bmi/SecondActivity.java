package com.example.bmi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    TextView bmi2;

    Button SMS;
    Button phonecall;
    Button email;

    Button smsAccept;
    Button emailAccept;
    Button phoneAccept;

    EditText changeable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String bmiresult = intent.getStringExtra("bmiresult");

        bmi2 = findViewById(R.id.bmi2);
        bmi2.setText(bmiresult);

        SMS = findViewById(R.id.sms);
        phonecall = findViewById(R.id.cellphone);
        email = findViewById(R.id.email);

        smsAccept = findViewById(R.id.smsAccept);
        emailAccept = findViewById(R.id.emailAccept);
        phoneAccept = findViewById(R.id.phonecallAccept);

        smsAccept.setVisibility(View.GONE);
        emailAccept.setVisibility(View.GONE);
        phoneAccept.setVisibility(View.GONE);

        changeable = findViewById(R.id.changeable);

        changeable.setVisibility(View.GONE);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},1);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);


        SMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMS.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                phonecall.setVisibility(View.GONE);
                smsAccept.setVisibility(View.VISIBLE);
                changeable.setVisibility(View.VISIBLE);
                changeable.setHint("Please enter the phone number you want to send the sms to");
            }
        });

        smsAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changeable.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please insert a phone number for the sms", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage("BMI:" + changeable.getText().toString(), null, "Bmi: " + bmiresult, null, null);
                }
            }
        });

        phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMS.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                phonecall.setVisibility(View.GONE);
                phoneAccept.setVisibility(View.VISIBLE);
                changeable.setVisibility(View.VISIBLE);
                changeable.setHint("Please enter the phone number you want to call to");
            }
        });

        phoneAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //makePhoneCall();
                String phoneno = changeable.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneno));
                startActivity(i);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SMS.setVisibility(View.GONE);
                email.setVisibility(View.GONE);
                phonecall.setVisibility(View.GONE);
                emailAccept.setVisibility(View.VISIBLE);
                changeable.setVisibility(View.VISIBLE);
                changeable.setHint("Please enter the email address you want to send the bmi to");
            }
        });
        emailAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String sendto = changeable.getText().toString();
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);

                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{sendto});
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "BMI");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, bmi2.getText().toString());
                    emailIntent.setType("message/rfc822");
                    try{
                        startActivity(Intent.createChooser(emailIntent, "Send bmi in mail"));
                    }
                    catch (android.content.ActivityNotFoundException ex)
                    {
                        Toast.makeText(getApplicationContext(), "There is no email client installed", Toast.LENGTH_LONG).show();
                    }
            }
        });
    }
  /*  private void makePhoneCall(){
        String number = changeable.getText().toString();
        if(number.trim().length()>0)
        {
            if(ContextCompat.checkSelfPermission(SecondActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(SecondActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);
            }
            else
            {
                String dial = "tell:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Please enter the phone number you want to call to", Toast.LENGTH_LONG).show();
        }
    }
*/
    //@Override
  /*  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){
            if(grantResults.length>0 && grantResults[0] ==  PackageManager.PERMISSION_GRANTED){
                makePhoneCall();
            }
            else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }*/
}