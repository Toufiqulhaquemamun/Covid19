package com.example.coronabd.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.coronabd.R;
import com.example.coronabd.manager.StaticDataManager;
import com.example.coronabd.model.CountryReport;
import com.example.coronabd.retorfit.API;
import com.example.coronabd.retorfit.RetrofitInstance;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {


    private API api= RetrofitInstance.getApi();
    public View rootView;
    private RadioGroup relationwithowner,symptomappeardays,immigrantorabroad,infectionplaces,covid19tested;
    private RadioButton relation,symptomdays,immigrant,infectionplace,covidtested;
    private CheckBox fever,drycough,tiredness,breath,pressure,lossmovement,hypertension,heart,asma,diabetics,neuro,alergy;
    private EditText name,age,sex,nid,date;
    private Button submit;
    int mYear ;
    int mMonth;
    int mDay;
    String testDate;
    LinearLayout layoutDetails,layoutDate;
    private RequestBody relationowner;
    private RequestBody symptomduration;
    private RequestBody immiragtionabroad;
    private RequestBody placesinfetion;
    private RequestBody testedcovid;
    private RequestBody commonsymptoms;
    private RequestBody serioussymptoms;
    private RequestBody otherdiseases;
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, null);
        initializeView();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int relationId = relationwithowner.getCheckedRadioButtonId();
                relation = rootView.findViewById(relationId);
                int symptomdaysId = symptomappeardays.getCheckedRadioButtonId();
                symptomdays = rootView.findViewById(symptomdaysId);
                int immigrantId = immigrantorabroad.getCheckedRadioButtonId();
                immigrant = rootView.findViewById(immigrantId);
                int infectionplaceId = infectionplaces.getCheckedRadioButtonId();
                infectionplace = rootView.findViewById(infectionplaceId);
                int covidtestedId = covid19tested.getCheckedRadioButtonId();
                covidtested = rootView.findViewById(covidtestedId);
                StringBuilder commonSymptom = new StringBuilder();
                StringBuilder seriousSymptom = new StringBuilder();
                StringBuilder otherDisease = new StringBuilder();
                if(fever.isChecked()){ commonSymptom.append(fever.getText());}
                if(drycough.isChecked()){commonSymptom.append(drycough.getText());}
                if(tiredness.isChecked()){commonSymptom.append(tiredness.getText());}
                if(breath.isChecked()){seriousSymptom.append(breath.getText());}
                if(pressure.isChecked()){seriousSymptom.append(pressure.getText());}
                if(lossmovement.isChecked()){seriousSymptom.append(lossmovement.getText());}
                if(hypertension.isChecked()){otherDisease.append(hypertension.getText());}
                if(heart.isChecked()){otherDisease.append(heart.getText());}
                if(asma.isChecked()){otherDisease.append(asma.getText());}
                if(diabetics.isChecked()){otherDisease.append(diabetics.getText());}
                if(neuro.isChecked()){otherDisease.append(neuro.getText());}
                if(alergy.isChecked()){otherDisease.append(alergy.getText());}
                Toast.makeText(getContext(),commonSymptom.toString(),Toast.LENGTH_SHORT).show();
                testDate =date.getText().toString().trim();
                if(!relation.equals("")&& !symptomdays.equals("") && !immigrant.equals("") && !infectionplace.equals("")&&!covidtested.equals("")&&!commonSymptom.equals("")&&!seriousSymptom.equals("")&&!otherDisease.equals(""))
                {
                    savetoserver();
                }
            }
        });
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        Calendar myCalendar = Calendar.getInstance();
                        myCalendar.set(Calendar.YEAR, selectedyear);
                        myCalendar.set(Calendar.MONTH, selectedmonth);
                        myCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
                        String myFormat = "yyyy-MM-dd"; //Change as you need
                        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
                        date.setText(sdf.format(myCalendar.getTime()));
                        mDay = selectedday;
                        mMonth = selectedmonth;
                        mYear = selectedyear;
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });
        return rootView;
    }

    private void initializeView()
    {
        relationwithowner = rootView.findViewById(R.id.radioGrouprelation);
        symptomappeardays = rootView.findViewById(R.id.radiogroupsymptompsappeardays);
        immigrantorabroad = rootView.findViewById(R.id.radiogroupimmigrant);
        infectionplaces = rootView.findViewById(R.id.radiogroupInfectionPlace);
        covid19tested = rootView.findViewById(R.id.radiogroupcovid19tested);
        fever = rootView.findViewById(R.id.checkboxfever);
        drycough = rootView.findViewById(R.id.checkboxdrycough);
        tiredness = rootView.findViewById(R.id.checkboxtiredness);
        breath = rootView.findViewById(R.id.checkboxbreathdifficulty);
        pressure = rootView.findViewById(R.id.checkboxchestpain);
        lossmovement = rootView.findViewById(R.id.checkboxlossspeech);
        hypertension = rootView.findViewById(R.id.checkboxhypertension);
        heart = rootView.findViewById(R.id.checkboxheartdisease);
        asma = rootView.findViewById(R.id.checkboxasma);
        diabetics = rootView.findViewById(R.id.checkboxdiabetics);
        neuro = rootView.findViewById(R.id.checkboxneuro);
        alergy = rootView.findViewById(R.id.checkboxcoldalergy);
        name = rootView.findViewById(R.id.editText_username);
        age = rootView.findViewById(R.id.editText_age);
        sex = rootView.findViewById(R.id.editText_sex);
        nid = rootView.findViewById(R.id.editText_nidorbirthno);
        date = rootView.findViewById(R.id.select_date);
        submit = rootView.findViewById(R.id.buttonsymtomp);
        layoutDetails = rootView.findViewById(R.id.lineardetails);
        layoutDate = rootView.findViewById(R.id.lineardate);

    }

    private void savetoserver()
    {
        createRequestBody();
    }

    private void createRequestBody()
    {

    }


    private boolean askForPermission() {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            int hasCallPermission = ContextCompat.checkSelfPermission(getContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE);
            if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {

                showDialog();


                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                    user permanently disable permission message

                }else {
                    // Request for permission
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                            StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
                }

                return false;
            } else {
                // permission granted and calling function working
                return true;
            }
        } else {
            return true;
        }
    }

    private void showDialog() {
        DialogInterface.OnClickListener listener=createListener();
        showMessageOKCancel(listener);
    }


    @NonNull
    private DialogInterface.OnClickListener createListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS);
            }
        };
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case StaticDataManager.REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                   // captureImage();
                } else {
                    // Permission Denied
                    Toast.makeText(getContext(), R.string.permission_permanently_denied, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showMessageOKCancel(DialogInterface.OnClickListener okListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.setMessage(getString(R.string.file_access_message))
                .setPositiveButton(getString(R.string.ok), okListener)
                .setCancelable(false)
                .create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                        ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
            }
        });

        dialog.show();

    }

//    @Override
//    public void onClick(View v) {
//        boolean checked = ((RadioButton)v).isChecked();
//        switch (v.getId())
//        {
//            case R.id.radiobuttonSelf:
//                if(checked){layoutDetails.setVisibility(View.INVISIBLE);}
//                break;
//            case R.id.radiobuttonBrother:
//            case R.id.radiobuttonUnknown:
//            case R.id.radiobuttonNeighbour:
//            case R.id.radiobuttonFather:
//            case R.id.radiobuttonMother:
//            case R.id.radiobuttonSister:
//            case R.id.radiobuttonCousin:
//            case R.id.radiobuttonRelatives:
//                if(checked){layoutDetails.setVisibility(View.VISIBLE);}
//                break;
//            case R.id.radiobuttoncovid19yes:
//                if(checked){layoutDate.setVisibility(View.VISIBLE);}
//                break;
//            case R.id.radiobuttoncovid19no:
//                if(checked){layoutDate.setVisibility(View.INVISIBLE);}
//                break;
//        }
//    }
}
