package com.example.mipt_praktika2;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtInputText;
    private Spinner spnMetricType;
    private Button btnCalculate;
    private TextView txtResult;

    private TekstoSkaiciavimoLogika calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initializeViews();
        calculator = new TekstoSkaiciavimoLogika();
        setupSpinner();

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performCalculation();
            }
        });
    }

    private void initializeViews() {
        edtInputText = findViewById(R.id.editTextInput);
        spnMetricType = findViewById(R.id.spinnerMetric);
        btnCalculate = findViewById(R.id.button_calculate);
        txtResult = findViewById(R.id.textViewResult);
    }

    private void setupSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.metrics_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMetricType.setAdapter(adapter);
    }

    private void performCalculation() {
        String inputText = edtInputText.getText().toString();

        if (inputText.trim().isEmpty()) {
            Toast.makeText(this, R.string.toast_empty_text, Toast.LENGTH_SHORT).show();
            return;
        }

        String selectedMetric = spnMetricType.getSelectedItem().toString();
        int result = 0;

        if (selectedMetric.equals("Sakiniai")) {
            result = calculator.countSentences(inputText);
        } else if (selectedMetric.equals("Žodžiai")) {
            result = calculator.countWords(inputText);
        } else if (selectedMetric.equals("Skyrybos ženklai")) {
            result = calculator.countPunctuation(inputText);
        } else if (selectedMetric.equals("Skaičiai")) {
            result = calculator.countNumbers(inputText);
        }

        displayResult(selectedMetric, result);
    }

    private void displayResult(String metricName, int count) {
        String resultText = getString(R.string.result_format, metricName, count);
        txtResult.setText(resultText);
    }
}
