package com.devdroid.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    MaterialButton btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    MaterialButton btn_Divide, btn_mul, btn_add, btn_Sub, btn_equal;
    MaterialButton  btn_C, btn_openBracket, btn_closeBracket;
    MaterialButton btn_Dot, btn_AC;
    TextView result_tv;
    TextView solution_tv;
    String data;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        result_tv = findViewById(R.id.result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        // Assign id to those buttons
        assignId(btn_C,R.id.btn_C);
        assignId(btn_openBracket,R.id.btn_openBracket);
        assignId(btn_closeBracket,R.id.btn_closeBracket);
        assignId(btn_Dot,R.id.btn_Dot);
        assignId(btn_AC,R.id.btn_AC);
        assignId(btn_Divide,R.id.btn_Divide);
        assignId(btn_mul,R.id.btn_mul);
        assignId(btn_add,R.id.btn_add);
        assignId(btn_Sub,R.id.btn_Sub);
        assignId(btn_equal,R.id.btn_equal);
        assignId(btn_0,R.id.btn_0);
        assignId(btn_1,R.id.btn_1);
        assignId(btn_2,R.id.btn_2);
        assignId(btn_3,R.id.btn_3);
        assignId(btn_4,R.id.btn_4);
        assignId(btn_5,R.id.btn_5);
        assignId(btn_6,R.id.btn_6);
        assignId(btn_7,R.id.btn_7);
        assignId(btn_8,R.id.btn_8);
        assignId(btn_9,R.id.btn_9);



    }

    // Method to assign Id to Button
    void assignId(MaterialButton btn, int id)
    {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // whatever the button is clicked
        MaterialButton button = (MaterialButton) view;
        //extract the text form the button
        String buttonText = button.getText().toString();

        // calculate the String data
        String dataToCalculate = solution_tv.getText().toString();


        // For AC button
        if (buttonText.equals("AC"))
        {
            solution_tv.setText("");
            result_tv.setText("0");
            return;
        }

        if (buttonText.equals("="))
        {
            solution_tv.setText(result_tv.getText());
            return;
        }

        if (buttonText.equals("C"))
        {
            // it will trim the last character
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }

        else
        {
            dataToCalculate =  dataToCalculate + buttonText;
        }
        solution_tv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);

        if (!finalResult.equals("Err"))
        {
            result_tv.setText(finalResult);
        }

    }

    // to calculate the data
    String getResult(String data)
    {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0"))
            {
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e)
        {
            return "Err";
        }
    }
}