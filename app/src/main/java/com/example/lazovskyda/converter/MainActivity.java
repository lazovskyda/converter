package com.example.lazovskyda.converter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MyActivity sring tag";
    String currentCurrencyValue = "RUR";
    String resultCurrencyValue = "USD";
    double inputNumber = 0;

    private Spinner inputCurrency;
    private Spinner resultCurrency;
    private EditText inputValue;
    private TextView resultCurrencyNumber;

    private Toast testToast;


    String[][] currencyArray = {
            {"RUR","55","58","1"},
            {"EUR","0.9","1","0.01724"},
            {"USD","1","1.054","0.01818"},
            {"USD","1","1.054","0.01818"}
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //my controls init
        inputValue = (EditText)findViewById(R.id.inputCurrencyNumber);
        inputCurrency = (Spinner)findViewById(R.id.inputCurrency);
        resultCurrency = (Spinner)findViewById(R.id.resultCurrency);
        resultCurrencyNumber = (TextView)findViewById(R.id.resultCurrencyNumber);

        testToast = Toast.makeText(getApplicationContext(),
                "Проверочный тост", Toast.LENGTH_SHORT);



        //init of a dropdown list(array adapter and set first value)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currencyList, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        inputCurrency.setAdapter(adapter);
        if (!currentCurrencyValue.equals(null)) {
            int spinnerPostion = adapter.getPosition(currentCurrencyValue);
            inputCurrency.setSelection(spinnerPostion);
        }

        resultCurrency.setAdapter(adapter);
        if (!resultCurrencyValue.equals(null)) {
            int spinnerPostion = adapter.getPosition(resultCurrencyValue);
            resultCurrency.setSelection(spinnerPostion);
        }



        //spinners changing listener
        resultCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
//                testToast.show();
                result(inputCurrency);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        inputCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                testToast.show();
                result(resultCurrency);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });





        //init input listener
        inputValue.addTextChangedListener(new TextWatcher(){

            @Override
            public void afterTextChanged(Editable s) {
//                testToast.show();
                result(inputValue);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //
    public void result(View view){

        String justForTests;
        double justSomeDouble;
        String justSomeString = "0";

        String stringActualRate;
        String nowInputCurrency;
        String nowResultCurrency;


        nowInputCurrency = inputCurrency.getSelectedItem().toString();
        nowResultCurrency = resultCurrency.getSelectedItem().toString();

        justSomeDouble =  getRate(nowInputCurrency, nowResultCurrency);
        if (inputValue.getText().toString().equals("")) {
            justSomeString = "Херня";
            resultCurrencyNumber.setText(justSomeString);
        } else {
            try {
                stringActualRate = inputValue.getText().toString();
                inputNumber = Double.parseDouble(stringActualRate);
                inputNumber = inputNumber / justSomeDouble;
                justForTests = String.valueOf(inputNumber);
                resultCurrencyNumber.setText(justForTests);
                System.gc();

            } catch (NumberFormatException e) {
                System.err.println("Неверный формат строки!");
                justSomeString = "Херня";
                resultCurrencyNumber.setText(justSomeString);
                inputValue.setText("");
            }
        }
    }


//    detecting right value of rates
    public double getRate(String firstCurrency, String secondCurrency) {
        int i;
        double actualRate = 0;
        String stringActualRate = "none";


        stringActualRate = resultCurrencyNumber.getText().toString();

        for (i = 0; i < currencyArray.length; i++) {
            if (firstCurrency.equals(currencyArray[i][0])) {

                if (secondCurrency.contentEquals("USD")) {
                    stringActualRate = currencyArray[i][1];
                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                } else if (secondCurrency.contentEquals("EUR")) {
                    stringActualRate = currencyArray[i][2];
                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                } else if (secondCurrency.contentEquals("RUR")) {
                    stringActualRate = currencyArray[i][3];
                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                }
            }
        }
        Log.v(TAG, "justSomeTest" + stringActualRate);
        return actualRate;
    }
}
