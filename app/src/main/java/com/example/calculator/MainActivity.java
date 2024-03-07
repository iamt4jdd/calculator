package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.content.res.Configuration;
import android.util.Log;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;


import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonPlus, buttonMinus, buttonEquals;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;

    MaterialButton button_squareroot, button_sin, button_inversex, button_2px, button_tan_1, button_cuberoot,
            button_cos, button_epx, button_xp3;


    MaterialButton button_absolute, button_pi, button_sinh, button_cosh, button_ln, button_tan, button_xp2,
            button_xfactorial;

    MaterialButton button_e, button_log, button_10px, button_inverse_cos, button_percent, button_tanh, button_xpy, 
            button_inverse_sin;


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

            assignId(button_e, R.id.button_e);
            assignId(button_log, R.id.button_log);
            assignId(button_10px, R.id.button_10powerx);
            assignId(button_inverse_cos, R.id.button_inverse_cos);
            assignId(button_percent, R.id.button_percent);
            assignId(button_tanh, R.id.button_tanh);
            assignId(button_inverse_sin, R.id.button_inverse_sin);
            assignId(button_xpy, R.id.button_xpowery);

            assignId(button_xpy, R.id.button_xpowery);
            assignId(button_inverse_sin, R.id.button_inverse_sin);
            assignId(button_tanh, R.id.button_tanh);
        }

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        try {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        switch (buttonText) {
            case "AC":
                clearAll();
                break;
            case "=":
                solutionTv.setText(resultTv.getText());
                solutionTv.setText("");
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
            case "e":
                handleLogaricNeperian();
                break;
            case "log":
                handleLog(dataToCalculate);
                break;
            case "cos⁻¹":
                handleInverseCos(dataToCalculate);
                break;
            case "%":
                handlePercent(dataToCalculate);
                break;
            case "10ˣ":
                handlePowerOfTen(dataToCalculate);
                break;
            case "xʸ":
                handleXToPowerY(dataToCalculate);
                break;
            case "sin⁻¹":
                handleASin();
                break;
            case "tanh":
                handleTanh();
                break;
            default:
                appendToDataToCalculate(buttonText);
                break;

        }
        evaluateAndDisplay(buttonText);
    }catch(Exception e)
        {
            Log.d("ERROR!!!", e.toString());
        }

    private void handleASin() {
        solutionTv.append("sin⁻¹(");
    }

    private void clearAll() {
        solutionTv.setText("");
        resultTv.setText("0");
    }

    private void clearLast() {
        String currentData = solutionTv.getText().toString();
        if (currentData.equals("NaN") || currentData.equals("Infinity") || currentData.equals("Err")) {
            solutionTv.setText("");
            resultTv.setText("");
            // return;
        }
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
        String specialCharacters = "√∛sincostan2ˣeˣx²x³tan⁻¹-()+*/";
        if (Character.isDigit(buttonText.charAt(0)) || specialCharacters.contains(buttonText)) {
            solutionTv.append(buttonText);
        }
    }

    private void handleASin() {
        solutionTv.setText("sin⁻¹(");
    }

    private void handleTrigonometricFunction(String function, String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double result;
            switch (function) {
                case "sin":
                    result = Math.sin(number);
                    break;
                case "cos":
                    result = Math.cos(number);
                    break;
                case "tan⁻¹":
                    result = Math.atan(number);
                    break;
                default:
                    result = 0;
                    break;
            }
            solutionTv.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleSquareRoot(String dataToCalculate) {
        // if (dataToCalculate.isEmpty() ||
        // !dataToCalculate.matches("[+-]?(\\d+\\.?\\d*|\\.\\d+)")) {
        // solutionTv.setText("");
        // return;
        // }
        try {
            double number = Double.parseDouble(dataToCalculate);
            double sqrtResult = Math.sqrt(number);
            solutionTv.setText(String.valueOf(sqrtResult));

        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleCubeRoot(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double cbrtResult = Math.cbrt(number);
            solutionTv.setText(String.valueOf(cbrtResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    @SuppressLint("SetTextI18n")
    private void handleReciprocal(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double reciprocalResult = 1 / number;
            solutionTv.setText(String.valueOf(reciprocalResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleExponential(String dataToCalculate, double base) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double result = Math.pow(base, number);
            solutionTv.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleExponentiation(String dataToCalculate, int exponent) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double result = Math.pow(number, exponent);
            solutionTv.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handlePi() {
        try {
            double piValue = Math.PI;
            solutionTv.append(String.valueOf(piValue));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleAbsoluteValue(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double absValue = Math.abs(number);
            solutionTv.setText(String.valueOf(absValue));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleSinh(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double sinhResult = Math.sinh(number);
            solutionTv.setText(String.valueOf(sinhResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleCosh(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double coshResult = Math.cosh(number);
            solutionTv.setText(String.valueOf(coshResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleNaturalLog(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double lnResult = Math.log(number);
            solutionTv.setText(String.valueOf(lnResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleTan(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double tanResult = Math.tan(number);
            solutionTv.setText(String.valueOf(tanResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleTanh() {
        solutionTv.setText("tanh(");
    }

    private void handleFactorial(String dataToCalculate) {
        try {
            int number = Integer.parseInt(dataToCalculate);
            double factorialResult = calculateFactorial(number);
            solutionTv.setText(String.valueOf(factorialResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleXToPowerY(String dataToCalculate){
        solutionTv.append("^");
    }
    private double calculateFactorial(int n) {
        try {
            if (n < 0) {
                return 0;
            } else if (n == 0 || n == 1) {
                return 1;
            } else {
                return n * calculateFactorial(n - 1);
            }
        } catch (StackOverflowError e) {
            return 0;
        }
    }

    private void handleLogaricNeperian() {
        try {
            double number = Math.E;
            // double logResult = Math.log(number);
            solutionTv.setText(String.valueOf(number));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleLog(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double logResult = Math.log10(number);
            solutionTv.setText(String.valueOf(logResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleInverseCos(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double inverseCosResult = Math.acos(number);
            solutionTv.setText(String.valueOf(inverseCosResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handlePercent(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double percentResult = number / 100;
            solutionTv.setText(String.valueOf(percentResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private void handleTanh() {
        solutionTv.setText("tanh(");
    }

    private void handlePowerOfTen(String dataToCalculate) {
        try {
            double number = Double.parseDouble(dataToCalculate);
            double powerOfTenResult = Math.pow(10, number);
            solutionTv.setText(String.valueOf(powerOfTenResult));
        } catch (NumberFormatException e) {
            resultTv.setText("");
            solutionTv.setText("");
        }
    }

    private String revereString(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            output.append(input.charAt(i));
        }
        return output.toString();
    }

    private void handleXToPowerY(String dataToCalculate) {
        solutionTv.append("^");
    }

    private void evaluateAndDisplay(String buttonText) {
        String dataToCalculate = solutionTv.getText().toString();
        String finalResult = getResult(dataToCalculate);
        // if(buttonText.contains("√")){
        // solutionTv.setText("");
        // }
        if (dataToCalculate.isEmpty()) {
            resultTv.setText("");
        }
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
            // solutionTv.setText("");

        }

    }

    private String revereString(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = input.length() - 1; i >= 0; i--) {
            output.append(input.charAt(i));
        }
        return output.toString();
    }

    String getResult(String data) {
        try {
            Log.d("MATH EQUATION", data);
            if (data.contains("^")) {
                int carectLoc = data.indexOf("^");
                String baseValue = "";
                String exponentValue = "";
                int equaStartLoc;
                int equaEndLoc;
                int i = carectLoc - 1;
                String numbers_dot = "1234567890.";

                while (i >= 0 && numbers_dot.contains(data.substring(i, i + 1))) {
                    baseValue = baseValue.concat(data.substring(i, i + 1));
                    i--;
                }
                equaStartLoc = i + 1;

                i = carectLoc + 1;
                while (i < data.length() && numbers_dot.contains(data.substring(i, i + 1))) {
                    exponentValue = exponentValue.concat(data.substring(i, i + 1));
                    i++;
                }
                equaEndLoc = i - 1;
                if (equaEndLoc != carectLoc) {
                    baseValue = revereString(baseValue);
                    data = data.replace(baseValue + "^" + exponentValue,
                            "Math.pow(" + baseValue + "," + exponentValue + ")");
                }
            }

            if (data.contains("sin⁻¹(")) {
                int sinLoc = data.indexOf("sin⁻¹(");
                String sinValue = "";
                // boolean isValidEquation = false;

                int i = sinLoc + 6;
                String numbers_dot = "1234567890.";
                while (i < data.length() && numbers_dot.contains(data.substring(i, i + 1))) {
                    sinValue = sinValue.concat(data.substring(i, i + 1));
                    i++;
                }

                if (data.substring(i, i + 1).equals(")")) {
                    // isValidEquation = true;
                    data = data.replace("sin⁻¹(" + sinValue + ")", "(Math.asin(" + sinValue + ") * 180 / Math.PI)");
                }

                // if (isValidEquation) {
                // }
            }

            if (data.contains("tanh(")) {
                int tanhLoc = data.indexOf("tanh(");
                String tanhValue = "";
                // boolean isValidEquation = false;

                int i = tanhLoc + 5;
                String numbers_dot = "1234567890.";
                while (i < data.length() && numbers_dot.contains(data.substring(i, i + 1))) {
                    tanhValue = tanhValue.concat(data.substring(i, i + 1));
                    i++;
                }

                if (data.substring(i, i + 1).equals(")")) {
                    data = data.replace("tanh(" + tanhValue + ")", "Math.tanh(" + tanhValue + ")");
                }

            }

            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            Object result = context.evaluateString(scriptable, data, "JavaScript", 1, null);
            Log.d("Equation", result.toString());
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