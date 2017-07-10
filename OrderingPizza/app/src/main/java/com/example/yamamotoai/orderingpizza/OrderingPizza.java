package com.example.yamamotoai.orderingpizza;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by yamamotoai on 2017-07-10.
 */

public class OrderingPizza extends AppCompatActivity {

    EditText editTextName;
    RadioButton radio1;
    CheckBox checkBox1;
    CheckBox checkBox2;
    CheckBox checkBox3;
    CheckBox checkBox4;
    Button btnOrder;
    RadioGroup radioGroup;
    String pizzaSize = "Regular";
    StringBuilder pizzaTopping = new StringBuilder(100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orderingpizza);

        editTextName = (EditText) findViewById(R.id.editTxt);
        radio1 = (RadioButton) findViewById(R.id.radio1);
        radio1.toggle();
        btnOrder = (Button) findViewById(R.id.btnOrder);
        radioGroup = (RadioGroup) findViewById(R.id.rg);


        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = "Name: " + String.valueOf(editTextName.getText());
                String size = "Size: " + pizzaSize;
                String topping = "Toppings: " + pizzaTopping;

                Context context = getApplicationContext();
                CharSequence text = name + "\n" + size + "\n" + topping;
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked)
                    checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
                    pizzaTopping.append(checkBox1.getText() + "\n");
                break;
            case R.id.checkbox2:
                if (checked)
                    checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
                    pizzaTopping.append(checkBox2.getText() + "\n");
                    break;
            case R.id.checkbox3:
                if (checked)
                    checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
                    pizzaTopping.append(checkBox3.getText() + "\n");
                    break;
            case R.id.checkbox4:
                if (checked)
                    checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
                    pizzaTopping.append(checkBox4.getText() + "\n");
                    break;
                default:
                    break;
        }
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();
        RadioButton rg = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId()));

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                    Log.d("----","radio1");
                    pizzaSize = rg.getText().toString();
                break;
            case R.id.radio2:
                if (checked)
                    Log.d("----","radio2");
                    pizzaSize = rg.getText().toString();
                    break;
            case R.id.radio3:
                if (checked)
                    Log.d("----","radio3");
                    pizzaSize = rg.getText().toString();
                    break;
            default:
                break;
        }
    }

}
