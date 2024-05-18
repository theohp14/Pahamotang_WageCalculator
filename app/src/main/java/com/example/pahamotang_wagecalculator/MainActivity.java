package com.example.pahamotang_wagecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
            public class MainActivity extends AppCompatActivity {


                EditText noOfHours, hourlyPay;
                TextView payment, overtime, taxPaym, totalPaym;

                @Override
                protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);

                    //Inputs
                    noOfHours = findViewById(R.id.textview);
                    hourlyPay = findViewById(R.id.textview2);

                    // Outputs
                    payment = findViewById(R.id.payment_out);
                    overtime = findViewById(R.id.overtime_out);
                    taxPaym = findViewById(R.id.tax_out);
                    totalPaym = findViewById(R.id.total_out);
                }

                public void calculation(View view) {
                    try {
                        double workedHours = Double.parseDouble(noOfHours.getText().toString());
                        double hourlyRate = Double.parseDouble(hourlyPay.getText().toString());

                        double paymentP = 0;
                        double overtimeP = 0;
                        double totalP = 0;
                        double tax = 0;

                        if (workedHours <= 40) {
                            paymentP = workedHours * hourlyRate;
                            overtimeP = 0;
                            totalP = paymentP;
                        } else {
                            paymentP = 40 * hourlyRate;
                            overtimeP = (workedHours - 40) * hourlyRate;
                            totalP = paymentP + overtimeP;
                        }

                        tax = totalP * 0.18;

                        payment.setText(String.format("%.2f", paymentP));
                        overtime.setText(String.format("%.2f", overtimeP));
                        totalPaym.setText(String.format("%.2f", totalP));
                        taxPaym.setText(String.format("%.2f", tax));

                    } catch (Exception e) {

                        TextView err = findViewById(R.id.error);
                        err.setText("Opps! Enter numbers to calculate!");
                        payment.setText("");
                        overtime.setText("");
                        totalPaym.setText("");
                        taxPaym.setText("");

                    } finally {
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                TextView err = findViewById(R.id.error);
                                err.setText("");
                            }
                        }, 2000);
                    }
                }
            }
        });
    }
}