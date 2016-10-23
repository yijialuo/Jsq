package com.example.dell.jsq;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    Button b_0,b_1,b_2,b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_Rkuo,b_Lkuo,b_binary,b_hex,
            b_add,b_sub,b_mul,b_div,b_dian,b_out,b_clear;
    EditText out;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_0= (Button) findViewById(R.id.s0);
        b_1= (Button) findViewById(R.id.s1);
        b_2= (Button) findViewById(R.id.s2);
        b_3= (Button) findViewById(R.id.s3);
        b_4= (Button) findViewById(R.id.s4);
        b_5= (Button) findViewById(R.id.s5);
        b_6= (Button) findViewById(R.id.s6);
        b_7= (Button) findViewById(R.id.s7);
        b_8= (Button) findViewById(R.id.s8);
        b_9= (Button) findViewById(R.id.s9);
        b_Rkuo=(Button) findViewById(R.id.Rkuo);
        b_Lkuo=(Button)findViewById(R.id.lkuo);
        b_add= (Button) findViewById(R.id.add);
        b_sub= (Button) findViewById(R.id.sub);
        b_mul= (Button) findViewById(R.id.mul);
        b_div= (Button) findViewById(R.id.div);
        b_dian= (Button) findViewById(R.id.s_);
        b_out= (Button) findViewById(R.id.res);
        b_clear= (Button) findViewById(R.id.clear);
        b_binary=(Button) findViewById(R.id.binary);
        b_hex=(Button) findViewById(R.id.hex);
        out= (EditText) findViewById(R.id.out);

        b_0.setOnClickListener(this);
        b_1.setOnClickListener(this);
        b_2.setOnClickListener(this);
        b_3.setOnClickListener(this);
        b_4.setOnClickListener(this);
        b_5.setOnClickListener(this);
        b_6.setOnClickListener(this);
        b_7.setOnClickListener(this);
        b_8.setOnClickListener(this);
        b_9.setOnClickListener(this);
        b_Lkuo.setOnClickListener(this);
        b_Rkuo.setOnClickListener(this);
        b_add.setOnClickListener(this);
        b_sub.setOnClickListener(this);
        b_mul.setOnClickListener(this);
        b_div.setOnClickListener(this);
        b_dian.setOnClickListener(this);
        b_out.setOnClickListener(this);
        b_clear.setOnClickListener(this);
        b_binary.setOnClickListener(this);
        b_hex.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=out.getText().toString();
        switch (v.getId()) {
            case R.id.s0:
            case R.id.s1:
            case R.id.s2:
            case R.id.s3:
            case R.id.s4:
            case R.id.s5:
            case R.id.s6:
            case R.id.s7:
            case R.id.s8:
            case R.id.s9:
            case R.id.Rkuo:
            case R.id.lkuo:
            case R.id.add:
            case R.id.mul:
            case R.id.div:
            case R.id.sub:
            case R.id.s_:
                str=str+((Button)v).getText().toString();
                break;
            case R.id.clear:
                if(!str.equals("")&&str!=null&&str.charAt(str.length()-1)==' ')
                    str=str.substring(0,str.indexOf(" "));
                else if(!str.equals("")&&str!=null&&!str.equals("error"))
                    str=str.substring(0,str.length()-1);
                else
                    str="";
                break;
            case R.id.binary:
                if(str!="") {
                    if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/") || str.contains(".")
                            || str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e")
                            || str.contains("f") || str.contains("g") || str.contains("h"))
                        str = "";
                    else
                        str = Integer.toBinaryString(Integer.parseInt(str)).toString();
                }
                break;
            case R.id.hex:
                if(str!="") {
                    if (str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/") || str.contains(".")
                            || str.contains("a") || str.contains("b") || str.contains("c") || str.contains("d") || str.contains("e")
                            || str.contains("f") || str.contains("g") || str.contains("h"))
                        str = "";
                    else
                        str = Integer.toHexString(Integer.parseInt(str)).toString();
                }
                break;
            case R.id.res:
                if(!str.contains("+")&&!str.contains("-")&&!str.contains("*")&&!str.contains("/"))
                    str="";
                else
                    str=Analytic(str);
                break;
        }
        out.setText(str);
    }

    public static String Analytic(String s){
        try {
            //对于括号的处理
            if (s.contains("(")) {
                int x=0;
                int a = s.indexOf("(");
                int b =-1;
                for(int i = a+1;i<s.length();i++){
                    if(s.charAt(i) == '('){
                        x++;
                    }
                    if(s.charAt(i) == ')'){
                        Log.d("zhan","i = "+i);
                        if (x == 0){
                            b = i;
                            break;
                        }else {
                            x--;
                        }
                    }
                }
                String s1 = s.substring(0, a);
                String s2 = s.substring(b + 1, s.length());
                String s3 = Analytic(s.substring(a + 1, b));
                String s4 = s1 + s3 + s2;
                return Analytic(s4);
            } else if (s.lastIndexOf("+") != -1 || s.lastIndexOf("-") != -1) {
                //对于加减号的处理
                if (s.lastIndexOf("+") > s.lastIndexOf("-")) {
                    if (s.lastIndexOf("+") > 1) {
                        if (s.charAt(s.lastIndexOf("+") - 1) == '*' || s.charAt(s.lastIndexOf("+") - 1) == '/') {
                            String s1 = "+" + s.substring(0, s.lastIndexOf("+") - 1);
                            String s2 = s.substring(s.lastIndexOf("+") + 1, s.length());
                            return calculate(Analytic(s1), Analytic(s2), s.substring(s.lastIndexOf("+") - 1, s.lastIndexOf("+")));
                        }
                    }
                    String s1 = s.substring(0, s.lastIndexOf("+"));
                    String s2 = s.substring(s.lastIndexOf("+") + 1, s.length());
                    if (s1.equals(""))
                        return Analytic(s2);
                    return calculate(Analytic(s1), Analytic(s2), "+");
                } else {
                    if (s.lastIndexOf("-") > 1) {
                        if (s.charAt(s.lastIndexOf("-") - 1) == '*' || s.charAt(s.lastIndexOf("-") - 1) == '/') {
                            String s1 = "-" + s.substring(0, s.lastIndexOf("-") - 1);
                            String s2 = s.substring(s.lastIndexOf("-") + 1, s.length());
                            return calculate(Analytic(s1), Analytic(s2), s.substring(s.lastIndexOf("-") - 1, s.lastIndexOf("-")));
                        }
                    }
                    String s1 = s.substring(0, s.lastIndexOf("-"));
                    String s2 = s.substring(s.lastIndexOf("-") + 1, s.length());


                    if (s1.equals(""))
                        s1 = "0";
                    return calculate(Analytic(s1), Analytic(s2), "-");
                }
            } else if (s.lastIndexOf("*") != -1 || s.lastIndexOf("/") != -1) {
                //对乘除号的处理
                if (s.lastIndexOf("*") > s.lastIndexOf("/")) {
                    String s1 = s.substring(0, s.lastIndexOf("*"));
                    String s2 = s.substring(s.lastIndexOf("*") + 1, s.length());
                    return calculate(Analytic(s1), Analytic(s2), "*");
                } else {
                    String s1 = s.substring(0, s.lastIndexOf("/"));
                    String s2 = s.substring(s.lastIndexOf("/") + 1, s.length());
                    return calculate(Analytic(s1), Analytic(s2), "/");
                }
            }
            return s;
        }catch (Exception e){
            Log.d("zhan","运算出错");
            return "";
        }
    }

    public static String calculate(String string_a, String string_b, String character){

        BigDecimal bigDecimal_a = new BigDecimal(string_a);
        BigDecimal bigDecimal_b = new BigDecimal(string_b);
        String bigDecimal_str="";
        switch (character){
            case "+":
                bigDecimal_str = bigDecimal_a.add(bigDecimal_b).toString();
                break;
            case "-":
                bigDecimal_str = bigDecimal_a.subtract(bigDecimal_b).toString();
                break;
            case "*":
                bigDecimal_str = bigDecimal_a.multiply(bigDecimal_b).toString();
                break;
            case "/":
                if(string_b.equals("0")){
                    //Toast.makeText(this, "被除数不能为0", Toast.LENGTH_SHORT).show();
                    return bigDecimal_str;
                    //break;
                }
                bigDecimal_str = bigDecimal_a.divide(bigDecimal_b,10,BigDecimal.ROUND_HALF_UP).toString();
                int n=bigDecimal_str.length();
                while (bigDecimal_str.charAt(n-1) == '0'){
                    n--;
                }
                if(bigDecimal_str.charAt(n-1) == '.')
                    bigDecimal_str = bigDecimal_str.substring(0,n-1);
                else
                    bigDecimal_str = bigDecimal_str.substring(0,n);
        }
        return bigDecimal_str;
    }


}
