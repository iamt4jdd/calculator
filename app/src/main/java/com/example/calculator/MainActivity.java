package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    MaterialButton button_squareroot, button_sin, button_inversex, button_2px, button_tan_1, button_cuberoot, button_cos, button_epx, button_xp3;

    MaterialButton button_absolute, button_pi, button_sinh, button_cosh, button_ln, button_tan, button_xp2, button_xfactorial;

    MaterialButton button_xpy;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(buttonBrackOpen, R.id.button_open_bracket);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.button_multiply);
        assignId(buttonPlus, R.id.button_plus);
        assignId(buttonMinus, R.id.button_minus);
        assignId(buttonEquals, R.id.button_equal);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonAC, R.id.button_ac);
        assignId(buttonDot, R.id.button_dot);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            assignId(button_squareroot, R.id.button_square_root);
            assignId(button_inversex, R.id.button_inversex);
            assignId(button_2px, R.id.button_2powerx);
            assignId(button_tan_1, R.id.button_inverse_tan);
            assignId(button_cuberoot, R.id.button_square_root3);
            assignId(button_cos, R.id.button_cos);
            assignId(button_epx, R.id.button_epowerx);
            assignId(button_xp3, R.id.button_xpower3);
            assignId(button_sin, R.id.button_sin);
            assignId(button_sinh, R.id.button_sinh);
            assignId(button_absolute, R.id.button_absolute);
            assignId(button_tan, R.id.button_tan);
            assignId(button_xp2, R.id.button_xpower2);
            assignId(button_xfactorial, R.id.button_xfactorial);
            assignId(button_cosh, R.id.button_cosh);
            assignId(button_pi, R.id.button_pi);
            assignId(button_ln, R.id.button_ln);
            assignId(button_xpy, R.id.button_xpowery);

        }

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try{
            MaterialButton button = (MaterialButton) view;
            String buttonText = button.getText().toString();
            String dataToCalculate = solutionTv.getText().toString();
            switch (buttonText) {
                case "AC":
                    clearAll();
                    break;
                case "=":
                    solutionTv.setText(resultTv.getText());
                    break;
                case "C":
                    clearLast();
                    break;
                case "√":
                    handleSquareRoot(dataToCalculate);
                    break;
                case "∛":
                    handleCubeRoot(dataToCalculate);
                    break;
                case "sin":
                case "cos":
                case "tan⁻¹":
                    handleTrigonometricFunction(buttonText, dataToCalculate);
                    break;
                case "1/x":
                    handleReciprocal(dataToCalculate);
                    break;
                case "2ˣ":
                    handleExponential(dataToCalculate, 2);
                    break;
                case "eˣ":
                    handleExponential(dataToCalculate, Math.E);
                    break;
                case "x²":
                    handleExponentiation(dataToCalculate, 2);
                    break;
                case "x³":
                    handleExponentiation(dataToCalculate, 3);
                    break;
                case "π":
                    handlePi();
                    break;
                case "|x|":
                    handleAbsoluteValue(dataToCalculate);
                    break;
                case "sinh":
                    handleSinh(dataToCalculate);
                    break;
                case "cosh":
                    handleCosh(dataToCalculate);
                    break;
                case "ln":
                    handleNaturalLog(dataToCalculate);
                    break;
                case "tan":
                    handleTan(dataToCalculate);
                    break;
                case "x!":
                    handleFactorial(dataToCalculate);
                    break;
                case "xʸ":
                    handleXToPowerY(dataToCalculate);
                    break;
                default:
                    appendToDataToCalculate(buttonText);
                    break;
            }
            evaluateAndDisplay(buttonText);
        } catch(Exception e)
        {
            Log.d("ERROR!!!", e.toString());
        }
    }

    private void clearAll() {
        solutionTv.setText("");
        resultTv.setText("0");
    }

    private void clearLast() {
        String currentData = solutionTv.getText().toString();
        if (!currentData.isEmpty()) {
            solutionTv.setText(currentData.substring(0, currentData.length() - 1));
            if (solutionTv.getText().equals("")) {
                resultTv.setText("");
            }
        } else {
            resultTv.setText("0");
            System.out.println("Data to calculate is already empty.");
        }
    }

    private void appendToDataToCalculate(String buttonText) {
        String specialCharacters = "√∛sincostan2ˣeˣx²x³tan⁻¹";
        if (!specialCharacters.contains(buttonText)) {
            solutionTv.append(buttonText);
        }
    }



    private void handleTrigonometricFunction(String function, String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double radians = Math.toRadians(number);
        double result;

        switch (function) {
            case "sin":
                result = Math.sin(radians);
                solutionTv.setText("Error: Division by zero");
                break;
            case "cos":
                result = Math.cos(radians);
                break;
            case "tan⁻¹":
                result = Math.atan(number);
                break;
            default:
                result = 0;
                break;
        }
        solutionTv.setText(String.valueOf(result));
    }
    private void handleSquareRoot(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double sqrtResult = Math.sqrt(number);
        solutionTv.setText(String.valueOf(sqrtResult));
    }

    private void handleCubeRoot(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double cbrtResult = Math.cbrt(number);
        solutionTv.setText(String.valueOf(cbrtResult));
    }

    @SuppressLint("SetTextI18n")
    private void handleReciprocal(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        if (number != 0) {
            double result = 1 / number;
            solutionTv.setText(String.valueOf(result));
        } else {
            solutionTv.setText("Error: Division by zero");
        }
    }

    private void handleExponential(String dataToCalculate, double base) {
        double number = Double.parseDouble(dataToCalculate);
        double result = Math.pow(base, number);
        solutionTv.setText(String.valueOf(result));
    }

    private void handleExponentiation(String dataToCalculate, int exponent) {
        double number = Double.parseDouble(dataToCalculate);
        double result = Math.pow(number, exponent);
        solutionTv.setText(String.valueOf(result));
    }

    private void handlePi() {
        double piValue = Math.PI;
        solutionTv.append(String.valueOf(piValue));
    }

    private void handleAbsoluteValue(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double absValue = Math.abs(number);
        solutionTv.setText(String.valueOf(absValue));
    }

    private void handleSinh(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double sinhResult = Math.sinh(number);
        solutionTv.setText(String.valueOf(sinhResult));
    }

    private void handleCosh(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double coshResult = Math.cosh(number);
        solutionTv.setText(String.valueOf(coshResult));
    }

    private void handleNaturalLog(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double lnResult = Math.log(number);
        solutionTv.setText(String.valueOf(lnResult));
    }

    private void handleTan(String dataToCalculate) {
        double number = Double.parseDouble(dataToCalculate);
        double tanResult = Math.tan(number);
        solutionTv.setText(String.valueOf(tanResult));
    }

    private void handleFactorial(String dataToCalculate) {
        int number = Integer.parseInt(dataToCalculate);
        double factorialResult = calculateFactorial(number);
        solutionTv.setText(String.valueOf(factorialResult));
    }

    private void handleXToPowerY(String dataToCalculate){
        solutionTv.append("^");
    }
    private double calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calculateFactorial(n - 1);
        }
    }

    private void evaluateAndDisplay(String buttonText) {
        String dataToCalculate = solutionTv.getText().toString();
        String finalResult = getResult(dataToCalculate);
//        if(buttonText.contains("√")){
//            solutionTv.setText("");
//        }
        if (solutionTv.getText().equals("")) {
            resultTv.setText("");
        }
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }

    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object result = context.evaluateString(scriptable, data, "JavaScript", 1, null);

            // Check if the result is null or undefined
            if (result == null || result == Undefined.instance) {
                return "Err";
            }

            String finalResult = Context.toString(result);
            // Parse the result to a double to apply formatting
            double parsedResult = Double.parseDouble(finalResult);
            // Format the result to have 5 decimal places
            DecimalFormat decimalFormat = new DecimalFormat("#.#####");
            finalResult = decimalFormat.format(parsedResult);

            return finalResult;
        } catch (Exception e) {
            return "Err";
        } finally {
            Context.exit();
        }
    }

}