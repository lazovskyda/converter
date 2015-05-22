package com.example.lazovskyda.converter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    String currentCurrencyValue = "RUR";
    String resultCurrencyValue = "USD";
    int inputNumber = 0;

    String[][] currencyArray = {
            {"RUR","55","58","1"},
            {"EUR","0.9","1","0.01724"},
            {"USD","1","1.054","0.01818"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner inputCurrency = (Spinner)findViewById(R.id.inputCurrency);
        Spinner resultCurrency = (Spinner)findViewById(R.id.resultCurrency);


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

    public void result(View view){

        String justForTests;
        double justSomeDouble;

        String nowInputCurrency;
        String nowResultCurrency;

        switch(view.getId()){
            case R.id.result:
                TextView resultCurrencyNumber = (TextView)findViewById(R.id.resultCurrencyNumber);
                EditText inputText = (EditText)findViewById(R.id.inputCurrencyNumber);


                Spinner inputCurrency = (Spinner)findViewById(R.id.inputCurrency);
                Spinner resultCurrency = (Spinner)findViewById(R.id.resultCurrency);

                nowInputCurrency = inputCurrency.getSelectedItem().toString();
                nowResultCurrency = inputCurrency.getSelectedItem().toString();


                justSomeDouble =  getRate(nowInputCurrency, nowResultCurrency);

                justForTests = String.valueOf(justSomeDouble);

                resultCurrencyNumber.setText(justForTests);
//
//                if(nowInputCurrency.contentEquals("RUR")){
//                    currencyArray
////                    nowResultCurrency = nowInputCurrency/
//
//                    resultCurrencyNumber.setText("12312312");
//                }
//                else {
//                    resultCurrencyNumber.setText("piu-piu");
//                }




//                justForTests = inputText.getText().toString();
//
//                text.setText(justForTests);

//                Log.v("piu-piu");

        }
    }
    public double getRate(String firstCurrency , String secondCurrency){
        int i;
//        private int j;
        double actualRate = 0;

        for(i = 0; i<currencyArray.length; i++){
            if(firstCurrency.equals(currencyArray[i][0])){

                if (secondCurrency.contentEquals("USD")){
                    actualRate = 111.1;
                    break;
                }

                else if (secondCurrency.contentEquals("EUR")){
//                    actualRate = (float)currencyArray[i][2];
                    actualRate = 222.2;
                    break;
                }

                else if (secondCurrency.contentEquals("RUR")){
//                    actualRate = (float)currencyArray[i][3];
                    actualRate = 333.3;
                    break;
                }

            }
        }

        return actualRate;
    }
}
