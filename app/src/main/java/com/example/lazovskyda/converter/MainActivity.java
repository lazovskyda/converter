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


    String[][] currencyArray = {
            {"RUR","55","58","1"},
            {"EUR","0.9","1","0.01724"},
            {"USD","1","1.054","0.01818"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! this is my code
        EditText inputValue = (EditText)findViewById(R.id.inputCurrencyNumber);



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



//spinner changing listener
        resultCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Spinner inputCurrency = (Spinner)findViewById(R.id.inputCurrency);
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
//                                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Пора покормить кота!", Toast.LENGTH_SHORT);
//
//                toast.show();
                result(inputCurrency);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        inputCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Spinner resultCurrency = (Spinner)findViewById(R.id.resultCurrency);
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
//                                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Пора покормить кота!", Toast.LENGTH_SHORT);
//
//                toast.show();
                result(resultCurrency);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });





//      instruction for edit text listener
        inputValue.addTextChangedListener(new TextWatcher(){
            EditText inputValue = (EditText)findViewById(R.id.inputCurrencyNumber);
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
//                Toast toast = Toast.makeText(getApplicationContext(),
//                        "Пора покормить кота!", Toast.LENGTH_SHORT);
//
//                toast.show();
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





//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!my code


    public void result(View view){

        String justForTests;
        double justSomeDouble;
        String justSomeString = "0";

        String stringActualRate;
        String nowInputCurrency;
        String nowResultCurrency;

//        switch(view.getId()){
//            case R.id.result:
                TextView resultCurrencyNumber = (TextView)findViewById(R.id.resultCurrencyNumber);
                EditText inputValue = (EditText)findViewById(R.id.inputCurrencyNumber);


                Spinner inputCurrency = (Spinner)findViewById(R.id.inputCurrency);
                Spinner resultCurrency = (Spinner)findViewById(R.id.resultCurrency);

                nowInputCurrency = inputCurrency.getSelectedItem().toString();
                nowResultCurrency = resultCurrency.getSelectedItem().toString();


                justSomeDouble =  getRate(nowInputCurrency, nowResultCurrency);



                if (inputValue.getText().toString().equals(""))
                {
                    justSomeString = "Херня";
                    resultCurrencyNumber.setText(justSomeString);
                }
                else
                {
                    try {
                        stringActualRate = inputValue.getText().toString();
                        inputNumber = Double.parseDouble(stringActualRate);

                        //                    justSomeString = inputValue.getText().toString();
//                    inputNumber = Double.parseDouble(justSomeString);
                        inputNumber = inputNumber/justSomeDouble;

                        justForTests = String.valueOf(inputNumber);
                        //justForTests = inputNumber.toString();

                        resultCurrencyNumber.setText(justForTests);

                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                        justSomeString = "Херня";
                        resultCurrencyNumber.setText(justSomeString);
                        inputValue.setText("");
                    }
                }
//        }
    }
    public double getRate(String firstCurrency , String secondCurrency){
        int i;
//        private int j;
        double actualRate = 0;
        String stringActualRate = "none";

        TextView resultCurrencyNumber = (TextView)findViewById(R.id.resultCurrencyNumber);

        stringActualRate = resultCurrencyNumber.getText().toString();

        for(i = 0; i<currencyArray.length; i++){
            if(firstCurrency.equals(currencyArray[i][0])){

                if (secondCurrency.contentEquals("USD")){
                    stringActualRate = currencyArray[i][1];

                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                }

                else if (secondCurrency.contentEquals("EUR")){
//                    actualRate = (float)currencyArray[i][2];
                    stringActualRate = currencyArray[i][2];
                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                }

                else if (secondCurrency.contentEquals("RUR")){
//                    actualRate = (float)currencyArray[i][3];
                    stringActualRate = currencyArray[i][3];
                    try {
                        actualRate = Double.parseDouble(stringActualRate);
                        System.err.println("Норм");
                    } catch (NumberFormatException e) {
                        System.err.println("Неверный формат строки!");
                    }
                    break;
                }

//                Log.i("Текущая переменная"+stringActualRate);
                //System.out.println("Текущая переменная "+stringActualRate);



            }
//            convertation string value of array to double for calculation


        }
        Log.v(TAG, "justSomeTest"+stringActualRate);
        return actualRate;
    }
}
