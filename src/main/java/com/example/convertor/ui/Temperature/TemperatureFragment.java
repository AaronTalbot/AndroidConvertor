package com.example.convertor.ui.Temperature;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.convertor.R;
import com.example.convertor.Temperature;

import java.util.ArrayList;

public class TemperatureFragment extends Fragment {

    private TemperatureViewModel temperatureViewModel;
    private Spinner input, output;
    private Button convert;
    private EditText inputET;
    private TextView outPutTV;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        temperatureViewModel =
                ViewModelProviders.of(this).get(TemperatureViewModel.class);
        View root = inflater.inflate(R.layout.fragment_temperature, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        temperatureViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        String[] s = {"Fahrenheit", "Celsius"};
        input = root.findViewById(R.id.inputSpinner);
        output = root.findViewById(R.id.outPutSpinner);

        ArrayList<String> SAR = new ArrayList<String>();
        SAR.add("Fahrenheit");
        SAR.add("Celsius");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, SAR);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        input.setAdapter(adapter);
        output.setAdapter(adapter);

        convert = root.findViewById(R.id.ConvertButtonTemp);
        inputET = root.findViewById(R.id.inputEditTextTemp);
        outPutTV = root.findViewById(R.id.OutPutTextViewTemp);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HandleOnClick();
            }
        });

        return root;
    }

    private void HandleOnClick() {
        if (inputET.getText().toString().equals("")){
            inputET.setError("You must enter a decimal number.");
        }
        else{
            DoStuff();
        }
    }

    private void DoStuff(){
        Temperature T = new Temperature();
        if(input.getSelectedItemPosition() == output.getSelectedItemPosition()){
            Toast.makeText(getActivity(), "Drop down menus cannot be the same.",
                    Toast.LENGTH_SHORT).show();
        }
        else if(input.getSelectedItemPosition() == 1){
            outPutTV.setText(T.CelciusToFaranheit(inputET.getText().toString()));
        }
        else{
            outPutTV.setText(T.FarenheittoCelcius(inputET.getText().toString()));
        }

    }


}