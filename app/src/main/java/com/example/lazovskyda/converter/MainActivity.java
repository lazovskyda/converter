package com.example.lazovskyda.converter;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    String currentCurrencyValue = "RUR";
    String resultCurrencyValue = "USD";
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
}
