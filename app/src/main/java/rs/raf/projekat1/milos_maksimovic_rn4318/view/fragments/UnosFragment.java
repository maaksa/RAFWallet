package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class UnosFragment extends Fragment {

    private MediaRecorder mediaRecorder;
    private File fileToSave;
    private UUID uuid;

    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private Spinner spinner;
    private EditText naslovEt;
    private EditText kolicinaEt;
    private Button dodajBtn;
    private ImageView audioIv;
    private ImageView audioRecordingIv;
    private EditText opisEt;
    private CheckBox checkBoxAudio;

    private Chronometer chronometer;

    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;

    public UnosFragment() {
        super(R.layout.fragment_unos);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);

        spinner = view.findViewById(R.id.spinner);
        List<String> categories = new ArrayList<String>();
        categories.add("Prihod");
        categories.add("Rashod");

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.options_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinnerAdapter);

        init(view);
    }

    private void init(View view) {
        initView(view);
        setSpinnerListener(view);
        initListeners();
    }

    private void initAudio() {
        File folder = new File(getActivity().getFilesDir(), "sounds");
        if (!folder.exists()) folder.mkdir();
        initListenersAudio(folder);
    }

    private void setSpinnerListener(View view) {
        Spinner spinner = view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
    }

    private void initView(View view) {
        naslovEt = view.findViewById(R.id.naslovUnosFragmentEt);
        kolicinaEt = view.findViewById(R.id.kolicinaUnosFragmentEt);
        dodajBtn = view.findViewById(R.id.dodajFragmentUnosBtn);
        audioIv = view.findViewById(R.id.audioUnostFragmentIv);
        opisEt = view.findViewById(R.id.opisEtUnosFragment);
        checkBoxAudio = view.findViewById(R.id.checkboxAudioUnosFragment);
        audioRecordingIv = view.findViewById(R.id.audioRecordingUnostFragmentIv);
        chronometer = view.findViewById(R.id.chronometerUnosFragment);
    }

    private void initMediaRecorder(File file) {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(file);
    }

    private void initListenersAudio(File perentFolder) {
        audioIv.setOnClickListener(v -> {
            try {
                audioIv.setVisibility(View.GONE);
                chronometer.setVisibility(View.VISIBLE);
                audioRecordingIv.setVisibility(View.VISIBLE);
                dodajBtn.setVisibility(View.GONE);

                uuid = UUID.randomUUID();
                fileToSave = new File(perentFolder, uuid + ".3gp");
                initMediaRecorder(fileToSave);

                mediaRecorder.prepare();
                mediaRecorder.start();

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        audioRecordingIv.setOnClickListener(v -> {
            audioIv.setVisibility(View.VISIBLE);
            chronometer.setVisibility(View.GONE);
            audioRecordingIv.setVisibility(View.GONE);
            dodajBtn.setVisibility(View.VISIBLE);

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.stop();
        });
    }

    private void initListeners() {
        checkBoxAudio.setOnClickListener(v -> {
            boolean isAudioChecked = checkBoxAudio.isChecked();
            if (isAudioChecked) {
                audioIv.setVisibility(View.VISIBLE);
                opisEt.setVisibility(View.GONE);
            } else {
                audioIv.setVisibility(View.GONE);
                opisEt.setVisibility(View.VISIBLE);
            }
        });
        checkBoxAudio.setOnClickListener(v -> {
            if (checkBoxAudio.isChecked()) {
                if (hasPermissions(getActivity(), PERMISSIONS)) {
                    opisEt.setVisibility(View.GONE);
                    audioIv.setVisibility(View.VISIBLE);
                    initAudio();
                } else {
                    checkBoxAudio.setChecked(false);
                    requestPermissions(PERMISSIONS, PERMISSION_ALL);
                }
            } else {
                opisEt.setVisibility(View.VISIBLE);
                audioIv.setVisibility(View.GONE);
            }
        });
        dodajBtn.setOnClickListener(v -> {
            String option = (String) spinner.getSelectedItem();
            String naslov = naslovEt.getText().toString();
            String opis = opisEt.getText().toString();

            if (checkBoxAudio.isChecked()) {
                if (naslov.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Sva polja morate popuniti", Toast.LENGTH_SHORT).show();
                } else if (fileToSave == null) {
                    Toast.makeText(getActivity(), "Niste uneli audio opis", Toast.LENGTH_SHORT).show();
                } else {
                    int kolicina = Integer.parseInt(kolicinaEt.getText().toString());
                    if (option.equals("Prihod")) {
                        prihodViewModel.addPrihodAudio(naslov, kolicina, opis, fileToSave, uuid);
                    } else {
                        rashodViewModel.addRashodAudio(naslov, kolicina, opis, fileToSave, uuid);
                    }
                    kolicinaEt.setText("");
                    naslovEt.setText("");
                    opisEt.setText("");
                }
            } else {
                if (naslov.isEmpty() || kolicinaEt.getText().toString().isEmpty() || opis.isEmpty()) {
                    Toast.makeText(getActivity(), "Sva polja morate popuniti", Toast.LENGTH_SHORT).show();
                } else {
                    int kolicina = Integer.parseInt(kolicinaEt.getText().toString());
                    if (option.equals("Prihod")) {
                        prihodViewModel.addPrihod(naslov, kolicina, opis);
                    } else {
                        rashodViewModel.addRashod(naslov, kolicina, opis);
                    }
                    kolicinaEt.setText("");
                    naslovEt.setText("");
                    opisEt.setText("");
                }
            }
        });

    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
