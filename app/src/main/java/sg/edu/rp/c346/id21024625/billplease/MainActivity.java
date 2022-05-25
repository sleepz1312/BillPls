package sg.edu.rp.c346.id21024625.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText numAmnt;
    EditText numPax;
    ToggleButton SVC;
    ToggleButton GST;
    EditText Disc;
    RadioGroup PaymentMtd;
    RadioButton Cash;
    RadioButton Paynow;
    EditText phoneNo;
    Button splitBill;
    Button resetAll;
    TextView viewAll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numAmnt = findViewById(R.id.amount);
        numPax = findViewById(R.id.pax);
        SVC = findViewById(R.id.svc);
        GST = findViewById(R.id.gst);
        Disc = findViewById(R.id.discount);
        PaymentMtd = findViewById(R.id.radioGroupPayment);
        Cash = findViewById(R.id.radioButtonCash);
        Paynow = findViewById(R.id.radioButtonPaynow);
        phoneNo = findViewById(R.id.phoneNo);
        splitBill = findViewById(R.id.buttonSplit);
        resetAll = findViewById(R.id.buttonReset);
        viewAll = findViewById(R.id.textViewDisplay);


        resetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numAmnt.setText(" ");
                numPax.setText(" ");
                Disc.setText(" ");
                phoneNo.setText(" ");
            }
        });


        PaymentMtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add your code for the action
                if (Paynow.isChecked()) {
                    phoneNo.setEnabled(true);
                } else {
                    phoneNo.setEnabled(false);
                }
            }
        });

        splitBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(numAmnt.getText().toString().trim().length()!=0 && numPax.getText().toString().trim().length()!=0) {
                        double newAmt = 0.0;
                        if (!SVC.isChecked() && !GST.isChecked()) {
                            newAmt = Double.parseDouble(numAmnt.getText().toString());
                        } else if (SVC.isChecked() && !GST.isChecked()) {
                            newAmt = Double.parseDouble(numAmnt.getText().toString()) * 1.1;
                        } else if (!SVC.isChecked() && GST.isChecked()) {
                            newAmt = Double.parseDouble(numAmnt.getText().toString()) * 1.07;
                        } else {
                            newAmt = Double.parseDouble(numAmnt.getText().toString()) * 1.17;
                        }
//Discount
                        if (Disc.getText().toString().trim().length() != 0) {
                            newAmt *= 1 - Double.parseDouble(Disc.getText().toString()) / 100;
                        }

                        int numPerson = Integer.parseInt(numPax.getText().toString());
                        if (numPerson != 1)
                            viewAll.setText("Total Bill: $" + String.format("%.2f", newAmt) + "Each Pays: $" + String.format("%.2f", newAmt / numPerson));
                        else
                            viewAll.setText("Total Bill: $" + String.format("%.2f", newAmt) + "Each Pays: $" + newAmt);
                    }
                }
            });

    }
}