 package com.atul.android.weightpicker;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.core.util.Pair;

 import com.atul.android.weightpicker.databinding.ActivityMainBinding;

 import java.text.DecimalFormat;

 import static com.atul.android.weightpicker.WeightPicker.*;

 public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding b;
    private int k,gm,h;
    DecimalFormat dec = new DecimalFormat("#0.00");

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());
        WeightPicker a = new WeightPicker();
        HeightPicker c = new HeightPicker();
        b.pickWeight.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 a.show(MainActivity.this, new OnWeightPickedListener() {
                     @Override
                     public void onWeightPicked(int kg, int g) {

                             b.weightSelected.setVisibility(View.VISIBLE);
                             b.weightSelected.setText("Weight is " + kg + "." + g + " KG");
                             k=kg;
                             gm=g;

                     }

                     @Override
                     public void onWeightPickerCancelled() {
                         Toast.makeText(MainActivity.this,"Select Again",Toast.LENGTH_SHORT).show();
                     }
                 });
             }
         });
        b.pickHeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.show(MainActivity.this, new HeightPicker.OnHeightPickedListener() {
                    @Override
                    public void onHeightPicked(int cms) {
                        b.heightSelected.setVisibility(View.VISIBLE);
                        b.heightSelected.setText("Height is " + cms+ " CM");
                        h=cms;
                    }
                    @Override
                    public void onHeightPickerCancelled() {
                        Toast.makeText(MainActivity.this,"Select Again",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

     public void calculateBMI(View view) {
         String s = k+"."+gm;
         double weight = Double.parseDouble(s);
         double height = (double) h/100;
         double bmi = weight/(Math.pow(height,2));
         b.setBMI.setVisibility(View.VISIBLE);
         b.setBMI.setText(valuePrint(bmi));
         BMICategory bmiCategory = new BMICategory();
         Pair<String, Integer> val= bmiCategory.getCategory(bmi);
         b.condition.setVisibility(View.VISIBLE);
         b.condition.setTextColor(val.second);
         b.condition.setText(val.first);
     }

     private StringBuilder valuePrint(double bmi) {
         StringBuilder stringBuilder = new StringBuilder();
         stringBuilder.append("Your BMI is: ")
                 .append(dec.format(bmi))
                 .append("\n You are: ");
         return stringBuilder;
     }

     public void resetFields(View view) {
         b.setBMI.setVisibility(View.GONE);
         b.heightSelected.setVisibility(View.GONE);
         b.weightSelected.setVisibility(View.GONE);
         b.condition.setVisibility(View.GONE);
     }
 }