package com.example.calculator_mobile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView result, solution;
    MaterialButton btnC, btnAC, btnDot, btnDelete;
    MaterialButton btnDivide, btnMultiply, btnPlus, btnSubtract, btnEqual, btnNegate, btnFraction, btnSquare, btnSquareRoot, btnPercent;
    MaterialButton btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        solution = findViewById(R.id.solution);

        assignId(btnC, R.id.btn_c);
        assignId(btnAC, R.id.btn_ac);
        assignId(btnDot, R.id.btn_dot);
        assignId(btnDelete, R.id.btn_delete);
        assignId(btnFraction, R.id.btn_fraction);
        assignId(btnSquare, R.id.btn_square);
        assignId(btnPercent, R.id.btn_percent);
        assignId(btnSquareRoot, R.id.btn_squareRoot);
        assignId(btnDivide, R.id.btn_divide);
        assignId(btnMultiply, R.id.btn_multiply);
        assignId(btnPlus, R.id.btn_plus);
        assignId(btnSubtract, R.id.btn_subtract);
        assignId(btnNegate, R.id.btn_negate);
        assignId(btnEqual, R.id.btn_result);
        assignId(btn0, R.id.btn_0);
        assignId(btn1, R.id.btn_1);
        assignId(btn2, R.id.btn_2);
        assignId(btn3, R.id.btn_3);
        assignId(btn4, R.id.btn_4);
        assignId(btn5, R.id.btn_5);
        assignId(btn6, R.id.btn_6);
        assignId(btn7, R.id.btn_7);
        assignId(btn8, R.id.btn_8);
        assignId(btn9, R.id.btn_9);
    }

    void assignId(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn = (MaterialButton) v;
        String btnText = btn.getText().toString();
        String dataResult = result.getText().toString();
        String dataSolution = solution.getText().toString();

        if(btnText.equals("AC")){
            solution.setText("");
            result.setText("");
            return;
        }
        if (btnText.equals("=")){
            String finalResult = getResult(dataSolution);
            System.out.println(dataSolution);
            if(!finalResult.equals("Err")){
                result.setText(finalResult);
                dataResult = finalResult;
            }
        }
        else {
            if(!dataResult.equals("") && !dataSolution.equals("")){
                result.setText("");
                dataSolution = "";
            }
        }
        if (btnText.equals("C")){
            dataSolution = dataSolution.substring(0, dataSolution.length() - 1);
        }else{
            dataSolution = dataSolution + btnText;
        }
        if(!dataResult.equals("")){
            if (btnText.equals("+")){
                dataSolution = dataResult + btnText;
            } else if (btnText.equals("-")) {
                dataSolution = dataResult + btnText;
            } else if (btnText.equals("*")) {
                dataSolution = dataResult + btnText;
            } else if (btnText.equals("/")) {
                dataSolution = dataResult + btnText;
            }
        }


        solution.setText(dataSolution);

    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initSafeStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;
        } catch (Exception e){
            return "Err";
        }
    }
}