package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String str = "";
    String[] operands = new String[]{"+","-","*","/","^"};
    String[] functions = new String[]{"sin","cos","tg","ln","lg","sqrt"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void setText(String result){
        TextView t = (TextView)findViewById(R.id.resultText);
        t.setText(result);
    }
    public void onOneButtonClick(View w){
        str += "1";
        setText(str);
    }
    public void onTwoButtonClick(View w){
        str += "2";
        setText(str);
    }
    public void onThreeButtonClick(View w){
        str += "3";
        setText(str);
    }
    public void onFourButtonClick(View w){
        str += "4";
        setText(str);
    }
    public void onFiveButtonClick(View w){
        str += "5";
        setText(str);
    }
    public void onSixButtonClick(View w){
        str += "6";
        setText(str);
    }
    public void onSevenButtonClick(View w){
        str += "7";
        setText(str);
    }
    public void onEightButtonClick(View w){
        str += "8";
        setText(str);
    }
    public void onNineButtonClick(View w){
        str += "9";
        setText(str);
    }
    public void onZeroButtonClick(View w){
        str += "0";
        setText(str);
    }
    public void onPointButtonClick(View w){
        if(str.length() != 0 && str.charAt(str.length()-1) <= '9' && str.charAt(str.length()-1) >= '0'){
            int k = 1;
            while(str.charAt(str.length() - k) <= '9' && str.charAt(str.length() - k) >= '0'){
                if(k == str.length()){
                    str += ".";
                    setText(str);
                    return;
                }
                k++;
            }
            if(str.charAt(str.length() - k) != '.') {
                str += ".";
                setText(str);
            }
        }
    }
    public void onBreaketsButtonClick(View w){
        int leftBreakets = 0, rightBreakets = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                leftBreakets++;
            }
            else if(str.charAt(i) == ')'){
                rightBreakets++;
            }
        }
        if(str.length() == 0 || str.charAt(str.length() - 1) == '(' || isOperand(str.charAt(str.length() - 1))){
            str += "(";
        }
        else if(leftBreakets > rightBreakets){
            str += ")";
        }
        else{
            str += "*(";
        }
        setText(str);
    }
    public void addOperand(String operand){
        if(str.length() == 0 || str.charAt(str.length() - 1) == '('){
            if(operand == "+" || operand == "-"){
                str += operand;
            }
        }
        else if(isOperand(str.charAt(str.length()-1))){
            str = str.substring(0, str.length() - 1) + operand;
        }
        else{
            str += operand;
        }
        setText(str);
    }
    public void onPlusButtonClick(View w){
        addOperand("+");
    }
    public void onMinusButtonClick(View w){
        addOperand("-");
    }
    public void onMultButtonClick(View w){
        addOperand("*");
    }
    public void onDivButtonClick(View w){
        addOperand("/");
    }
    public void onPowButtonClick(View w){
        addOperand("^");
    }
    public void onDelButtonClick(View w){
        if(str.length() == 0){
            return;
        }
        boolean fl = false;
        int lenghtOfFunction = -1;
        for(String s : functions){
            if(str.endsWith(s + "(")){
                fl = true;
                lenghtOfFunction = s.length() + 1;
            }
        }
        if(fl){
            str = str.substring(0, str.length() - lenghtOfFunction);
        }
        else if(str.endsWith("pi")){
            str = str.substring(0, str.length() - 2);
        }
        else{
            str = str.substring(0, str.length() - 1);
        }
        setText(str);
    }
    public void onClearButtonClick(View w){
        str = "";
        setText(str);
    }
    public boolean isOperand(char operand){
        for(String s : operands){
            if(s.charAt(0) == operand){
                return true;
            }
        }
        return false;
    }
    public void addFunction(String function){
        if(str == "" || str.charAt(str.length()-1) == '(' || isOperand(str.charAt(str.length()-1))){
            str += function + "(";
        }
        else{
            str += "*" + function + "(";
        }
        setText(str);
    }
    public void onSinButtonClick(View w){
        addFunction("sin");
    }
    public void onCosButtonClick(View w){
        addFunction("cos");
    }
    public void onTgButtonClick(View w){
        addFunction("tg");
    }
    public void onLnButtonClick(View w){
        addFunction("ln");
    }
    public void onLgButtonClick(View w){
        addFunction("lg");
    }
    public void onSqrtButtonClick(View w){
        addFunction("sqrt");
    }
    public void onE_ButtonClick(View w){
        if(str.length() == 0 || str.charAt(str.length()-1) == '(' || isOperand(str.charAt(str.length()-1))){
            str += "e";
        }
        else{
            str += "*e";
        }
        setText(str);
    }
    public void onPiButtonClick(View w){
        if(str.length() == 0 || str.charAt(str.length()-1) == '(' || isOperand(str.charAt(str.length()-1))){
            str += "pi";
        }
        else{
            str += "*pi";
        }
        setText(str);
    }
    boolean isFunction(String s){
        for(String it : functions){
            if(it == s){
                return true;
            }
        }
        return false;
    }
    public void onEqualsButtonClick(View w){
        if(str.length() == 0){
            return;
        }
        else if(isOperand(str.charAt(str.length() - 1)) || str.charAt(str.length() - 1) == '('){
            return;
        }
        for(int i = 0; i < str.length() - 1; i++){
            if(str.charAt(i) == '.' && !(str.charAt(i + 1) >= '0' && str.charAt(i + 1) <= '9')){
                str = str.substring(0, i) + str.substring(i + 1);
            }
        }
        if(str.charAt(str.length() - 1) == '.'){
            str = str.substring(0, str.length() - 1);
        }
        ArrayList<String> splString = new ArrayList<String>();
        boolean isNumber = false;
        int start = 0;
        for(int i = 0; i < str.length(); i++){
            if(isNumber){
                if((str.charAt(i) >= '0' && str.charAt(i) <= '9') || str.charAt(i) == '.'){

                }
                else{
                    splString.add(str.substring(start, i));
                    isNumber = false;
                    switch (str.charAt(i)){
                        case '+': splString.add("+"); break;
                        case '-': splString.add("-"); break;
                        case '*': splString.add("*"); break;
                        case '/': splString.add("/"); break;
                        case '^': splString.add("^"); break;
                        case ')': splString.add(")"); break;
                        case '(': splString.add("("); break;
                    }
                }
            }
            else{
                if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                    start = i;
                    isNumber = true;
                }
                else{
                    switch (str.charAt(i)){
                        case '(': splString.add("("); break;
                        case ')': splString.add(")"); break;
                        case 'e': splString.add("" + Math.exp(1)); break;
                        case 'p': splString.add("" + Math.PI); break;
                        case '+': splString.add("+"); break;
                        case '-': splString.add("-"); break;
                        case '*': splString.add("*"); break;
                        case '/': splString.add("/"); break;
                        case '^': splString.add("^"); break;
                        case 's':
                            if(str.charAt(i + 1) == 'i'){
                                splString.add("sin");
                                i += 3;
                            }
                            else{
                                splString.add("sqrt");
                                i += 4;
                            }
                            splString.add("(");
                            break;
                        case 'c':
                            splString.add("cos");
                            splString.add("(");
                            i += 3;
                            break;
                        case 't':
                            splString.add("tg");
                            splString.add("(");
                            i += 2;
                            break;
                        case 'l':
                            if(str.charAt(i + 1) == 'n'){
                                splString.add("ln");
                            }
                            else{
                                splString.add("lg");
                            }
                            splString.add("(");
                            i += 2;
                            break;
                    }
                }
            }
        }
        if(isNumber){
            splString.add(str.substring(start));
        }
        int count = 0;
        for(String s : splString){
            if(s == "("){
                count++;
            }
            else if(s == ")"){
                count--;
            }
        }
        for(int i = 0; i < count; i++){
            splString.add(")");
        }
        while(splString.size() > 1){
            for(int i = 0; i < splString.size(); i++){
                if(splString.get(i).charAt(0) == '(' &&  splString.get(i + 2).charAt(0) == ')'){
                    if(i > 0 && isFunction(splString.get(i - 1))){
                        switch (splString.get(i - 1)){
                            case "sin":
                                splString.set(i - 1, "" + Math.sin(Double.parseDouble(splString.get(i + 1))));
                                break;
                            case "cos":
                                splString.set(i - 1, "" + Math.cos(Double.parseDouble(splString.get(i + 1))));
                                break;
                            case "tg":
                                splString.set(i - 1, "" + Math.tan(Double.parseDouble(splString.get(i + 1))));
                                break;
                            case "sqrt":
                                splString.set(i - 1, "" + Math.sqrt(Double.parseDouble(splString.get(i + 1))));
                                break;
                            case "ln":
                                splString.set(i - 1, "" + Math.log(Double.parseDouble(splString.get(i + 1))));
                                break;
                            case "lg":
                                splString.set(i - 1, "" + Math.log10(Double.parseDouble(splString.get(i + 1))));
                                break;
                        }
                        splString.remove(i + 2);
                        splString.remove(i + 1);
                        splString.remove(i);
                    }
                    else{
                        splString.remove(i);
                        splString.remove(i + 1);
                    }
                }
                else if(splString.get(i).equals("-")
                        && (i == 0 || splString.get(i - 1).charAt(0) == '(')
                        && (i + 2 >= splString.size() || splString.get(i + 2).charAt(0) != '(')){
                    splString.remove(i);
                    splString.set(i, "" + Double.parseDouble(splString.get(i)) * (-1));
                }
                else if(isOperand(splString.get(i).charAt(0)) && (i > 0 && splString.get(i - 1).charAt(0) <= '9' && splString.get(i - 1).charAt(0) >= '0')
                        && (i + 1 < splString.size() && splString.get(i + 1).charAt(0) <= '9' && splString.get(i + 1).charAt(0) >= '0')){
                    char operand = splString.get(i).charAt(0);
                    switch(operand){
                        case '^':
                            splString.set(i - 1, "" + Math.pow(Double.parseDouble(splString.get(i - 1)), Double.parseDouble(splString.get(i + 1))));
                            splString.remove(i);
                            splString.remove(i);
                            break;
                        case '*':
                            if(i + 2 >= splString.size() || (splString.get(i + 2).charAt(0) != '^')) {
                                splString.set(i - 1, "" + (Double.parseDouble(splString.get(i - 1)) * Double.parseDouble(splString.get(i + 1))));
                                splString.remove(i);
                                splString.remove(i);
                            }
                            break;
                        case '/':
                            if(i + 2 >= splString.size() || (splString.get(i + 2).charAt(0) != '^'
                                    && splString.get(i + 2).charAt(0) != '*')) {
                                splString.set(i - 1, "" + (Double.parseDouble(splString.get(i - 1)) / Double.parseDouble(splString.get(i + 1))));
                                splString.remove(i);
                                splString.remove(i);
                            }
                            break;
                        case '+':
                            if(i + 2 >= splString.size() || (splString.get(i + 2).charAt(0) != '^'
                                    && splString.get(i + 2).charAt(0) != '*' && splString.get(i + 2).charAt(0) != '/')) {
                                splString.set(i - 1, "" + (Double.parseDouble(splString.get(i - 1)) + Double.parseDouble(splString.get(i + 1))));
                                splString.remove(i);
                                splString.remove(i);
                            }
                            break;
                        case '-':
                            if(splString.get(i).equals("-") && i + 2 >= splString.size() || (splString.get(i + 2).charAt(0) != '^'
                                    && splString.get(i + 2).charAt(0) != '*' && splString.get(i + 2).charAt(0) != '/')) {
                                splString.set(i - 1, "" + (Double.parseDouble(splString.get(i - 1)) - Double.parseDouble(splString.get(i + 1))));
                                splString.remove(i);
                                splString.remove(i);
                            }
                            break;
                    }
                }
            }//for end
        }//while end
        double res = Double.parseDouble(splString.get(0));
        if(res == Math.floor(res)){
            str = "" + (int)Math.floor(res);
        }
        else{
            str = "" + res;
        }
        setText(str);
    }
    public void changeRotation(View w){
        View keyboard = findViewById(R.id.extendedKeyboard);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            keyboard.setVisibility(View.INVISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            keyboard.setVisibility(View.VISIBLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
}
