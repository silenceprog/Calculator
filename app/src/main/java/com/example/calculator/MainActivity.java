package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    TextView resultField;
    TextView operationField;
    EditText number;
    Double operand = null;
    String lastOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultField = findViewById(R.id.result_field);
        number = (EditText) findViewById(R.id.number);
        operationField = findViewById(R.id.operation_field);


    }

    public void onNumberClick(View view){

        Button button = (Button)view;
        number.append(button.getText());

        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }

    public void onOperationClick(View view) {
        Button button = (Button) view;
        String op = button.getText().toString();
        String text = number.getText().toString();
        if (text.length() > 0) {
            text = text.replace(',', '.');
            try {
                performOperation(Double.valueOf(text), op);
            } catch (NumberFormatException ex) {
                number.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double text, String operation) {

        if (operand == null) {
            operand = text;
            lastOperation = operation;
            switch (lastOperation) {

                case "+/-":
                    operand = -text;
                    break;
                case "%":
                    operand = text/100;
                    break;
                case "C":
                    number.setText("");
                    operationField.setText("");
                    resultField.setText("");
                    break;
            }
        } else {
            if (lastOperation.equals("=")) {
                lastOperation = operation;
            }
            switch (lastOperation) {
                case "=":
                    operand = text;
                    break;
                case "/":
                    if (text == 0) {
                        operand = 0.0;
                    } else {
                        operand /= text;
                    }
                    break;
                case "*":
                    operand *= text;
                    break;
                case "+":
                    operand += text;
                    break;
                case "-":
                    operand -= text;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        number.setText("");
    }

    public void changeCalculator(View view){
        Intent intent = new Intent(MainActivity.this,EnginerCalculator.class);
        startActivity(intent);
    }
}
