package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   TextView Title; //ooly limhok
    Button btn;
    EditText weight;
    EditText height;
    TextView BMI;
    ImageView image;
    TextView weighttype;
    Button nextpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weight = (EditText) findViewById(R.id.weight);
        height = (EditText) findViewById(R.id.height);
        btn = (Button) findViewById(R.id.compute);
        BMI = (TextView) findViewById(R.id.BMI);
        btn.setOnClickListener(this::onClick1);
        weighttype = (TextView) findViewById(R.id.weighttype);
        image = (ImageView) findViewById(R.id.image1);
        nextpage = findViewById(R.id.nextpage);
        nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(BMI.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Please compute the bmi", Toast.LENGTH_LONG).show();
                }
                else
                {
                    openActivity2();
                }
            }
        });

    }
    public void onClick1(View view)
    {
        if(!weight.getText().toString().isEmpty() && !height.getText().toString().isEmpty())
        {
            if (Double.valueOf(weight.getText().toString()) > 900 || weight.getText().length() == 0 || weight.getText().length() > 999 || weight.getText().toString().isEmpty())
                Toast.makeText(this, "Please enter a valid weight", Toast.LENGTH_LONG).show();
            else if (Double.valueOf(height.getText().toString()) > 3 || height.getText().length() == 0 || height.getText().length() > 4 || height.getText().toString().isEmpty())
                Toast.makeText(this, "Please enter a valid height", Toast.LENGTH_LONG).show();
            else
                BMI.setText(String.valueOf(Double.parseDouble(weight.getText().toString()) / Math.pow(Double.parseDouble(height.getText().toString()), 2)));
            if (Double.valueOf(BMI.getText().toString()) < 18.5) {
                weighttype.setText("Underweight - a risk of Malnutrition");
                image.setImageResource(R.drawable.image1);
            } else if (Double.valueOf(BMI.getText().toString()) >= 18.5 && Double.valueOf(BMI.getText().toString()) < 25) {
                weighttype.setText("Normal weight - no risk");
                image.setImageResource(R.drawable.image2);
            } else if (Double.valueOf(BMI.getText().toString()) >= 25 && Double.valueOf(BMI.getText().toString()) < 30) {
                weighttype.setText("Overweight - the risk is worse if there are concomitant background diseases");
                image.setImageResource(R.drawable.image3);
            } else if (Double.valueOf(BMI.getText().toString()) >= 30 && Double.valueOf(BMI.getText().toString()) < 35) {
                weighttype.setText("Obesity grade 1 - medium risk");
                image.setImageResource(R.drawable.image4);
            } else if (Double.valueOf(BMI.getText().toString()) >= 35 && Double.valueOf(BMI.getText().toString()) < 40) {
                weighttype.setText("Obesity grade 2 - high risk");
                image.setImageResource(R.drawable.image5);
            } else if (Double.valueOf(BMI.getText().toString()) >= 40) {
                weighttype.setText("Obesity grade 3 - very high risk");
                image.setImageResource(R.drawable.image6);
            }
        }
        else
        {
            Toast.makeText(this, "Please enter a valid weight and height", Toast.LENGTH_LONG).show();
        }
    }
        public  void openActivity2()
        {
            String bmiResult = BMI.getText().toString();
            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("bmiresult", bmiResult);
            startActivity(intent);
        }
    }
