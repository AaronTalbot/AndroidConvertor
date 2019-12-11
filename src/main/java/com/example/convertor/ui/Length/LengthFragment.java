package com.example.convertor.ui.Length;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.convertor.Length;
import com.example.convertor.R;
import com.example.convertor.SecondActivity;

public class LengthFragment extends Fragment {

    private LengthViewModel lengthViewModel;
    private TextView outPutTextView;
    private EditText inputEditText;
    private Button ConvertButton;
    private Length l;

     RadioButton inputFeet;
     RadioButton inputMeters;
     RadioButton inputCenti;

     RadioButton outPutFeet;
     RadioButton outPutMeter;
     RadioButton outPutCenti;





    RadioGroup fromGroup;
    RadioGroup toGroup;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lengthViewModel =
                ViewModelProviders.of(this).get(LengthViewModel.class);
        View root = inflater.inflate(R.layout.fragment_length, container, false);
        lengthViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        View view = root;

        outPutTextView = root.findViewById(R.id.LengthOutPutTextView);
        inputEditText = root.findViewById(R.id.LengthEditText);
        ConvertButton = root.findViewById(R.id.LengthconvertButton);

        l = new Length();


        outPutCenti = view.findViewById(R.id.OutPutCentimeterRadioButton);
        outPutMeter = view.findViewById(R.id.OutPutMeterRadioButton);
        outPutFeet = view.findViewById(R.id.OutPutFeetRadioButton);


        inputCenti = view.findViewById(R.id.centimetRadioButton);
        inputMeters = view.findViewById(R.id.MetersRadioButton);
        inputFeet = view.findViewById(R.id.FeetRadioButtons);

        fromGroup = view.findViewById(R.id.radioGroupfrom);
        toGroup = view.findViewById(R.id.radioGroupto);



        fromGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             * Disable mirror element when one is clicked. Enable the rest.
             * @param group The group of radio buttons that is affected
             * @param checkedId The id of the element that was clicked
             */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                outPutCenti.setEnabled(true);
                outPutFeet.setEnabled(true);
                outPutMeter.setEnabled(true);

                if (checkedId == inputFeet.getId()) {
                    outPutFeet.setEnabled(false);
                }
                if (checkedId == inputCenti.getId()) {
                    outPutCenti.setEnabled(false);
                }
                if (checkedId == inputMeters.getId()) {
                    outPutMeter.setEnabled(false);
                }
            }
        });

        toGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                inputCenti.setEnabled(true);
                inputFeet.setEnabled(true);
                inputMeters.setEnabled(true);

                if (checkedId == outPutCenti.getId()) {
                    inputCenti.setEnabled(false);
                }
                if (checkedId == outPutFeet.getId()) {
                    inputFeet.setEnabled(false);
                }
                if (checkedId == outPutMeter.getId()) {
                    inputMeters.setEnabled(false);
                }
            }
        });


        ConvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleClick();

            }
        });
        return root;
    }

    private void handleClick() {
        // Verify the input box is not empty
        if (inputEditText.getText().toString().equals("")) {
            inputEditText.setError("Enter a number to convert");
        }
        else{
            try {
                String tempValue = inputEditText.getText().toString();
                convertAndSend(tempValue);
            }
            catch (Exception e) {
                outPutTextView.setError("Something went wrong please try again");
                Log.d("Error", e.getMessage());
            }

        }

        // Verifies the input box is numeric

    }

    private void convertAndSend(String  tempValue) {
        String unit = "";
        int selectedFromId = fromGroup.getCheckedRadioButtonId();
        int selectedToId = toGroup.getCheckedRadioButtonId();
        String tempResult = "";


        // If--then cases to make sure we call the appropriate method on each conversion
        if (selectedFromId == inputCenti.getId() && selectedToId == outPutMeter.getId()) {
            tempResult = l.CMtoMeters(tempValue);
            outPutTextView.setText(tempResult);
        } else if (selectedFromId == inputCenti.getId() && selectedToId == outPutFeet.getId()) {
            tempResult = l.CmToFeeT(inputEditText.getText().toString());
            outPutTextView.setText(tempResult);
        } else if (selectedFromId == inputMeters.getId() && selectedToId == outPutCenti.getId()) {
            tempResult = l.MetersToCM(inputEditText.getText().toString());
            outPutTextView.setText(tempResult);
        } else if (selectedFromId == inputMeters.getId() && selectedToId == outPutFeet.getId()) {
            tempResult = l.MetersToFeet(inputEditText.getText().toString());
            outPutTextView.setText(tempResult);
        } else if (selectedFromId == inputFeet.getId() && selectedToId == outPutCenti.getId()) {
            tempResult = l.FeetToCM(inputEditText.getText().toString());
            outPutTextView.setText(tempResult);
        } else if (selectedFromId == inputFeet.getId() && selectedToId == outPutMeter.getId()) {
            tempResult = l.FeetToMeters(inputEditText.getText().toString());
            outPutTextView.setText(tempResult);
        } else {
            inputEditText.setError("Something went wrong please try again");
            return;
        }
    }
 }
