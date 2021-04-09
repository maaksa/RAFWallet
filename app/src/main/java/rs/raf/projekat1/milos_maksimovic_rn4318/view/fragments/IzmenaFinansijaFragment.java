package rs.raf.projekat1.milos_maksimovic_rn4318.view.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.PrihodViewModel;
import rs.raf.projekat1.milos_maksimovic_rn4318.viewmodels.RashodViewModel;

public class IzmenaFinansijaFragment extends Fragment {

    private MediaRecorder mediaRecorder;
    private File fileToSave;
    private UUID uuid;

    private final int PERMISSION_ALL = 1;
    private final String[] PERMISSIONS = {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String FINANSIJA_PRIHOD_KEY = "finansijaPrihodKey";
    public static final String FINANSIJA_RASHOD_KEY = "finansijaRashodKey";

    private final String IZMENA_PRIHOD_FRAGMENT_TAG = "izmenaPrihodFragment";
    private final String PRIHOD_FRAGMENT_TAG = "prihodFragment";

    private final String IZMENA_RASHOD_FRAGMENT_TAG = "izmenaRashodFragment";
    private final String RASHOD_FRAGMENT_TAG = "rashodFragment";


    private Prihod prihod;
    private Rashod rashod;

    private EditText naslovEt;
    private EditText kolicinaEt;
    private EditText opisEt;

    private ImageView audioIv;
    private ImageView audioRecordingIv;

    private Button odustaniBtn;
    private Button izmeniBtn;

    private Chronometer chronometer;

    private PrihodViewModel prihodViewModel;
    private RashodViewModel rashodViewModel;

    public IzmenaFinansijaFragment() {
        super(R.layout.fragment_izmena_finansije);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prihodViewModel = new ViewModelProvider(requireActivity()).get(PrihodViewModel.class);
        rashodViewModel = new ViewModelProvider(requireActivity()).get(RashodViewModel.class);

        init(view);
    }

    private void init(View view) {
        initView(view);
        initListeners();
    }

    private void initAudio() {
        File folder = new File(getActivity().getFilesDir(), "sounds");
        if (!folder.exists()) folder.mkdir();
        initListenersAudio(folder);
    }

    private void initView(View view) {
        izmeniBtn = view.findViewById(R.id.izmeniBtnIzmaneFinansijeActivity);
        odustaniBtn = view.findViewById(R.id.odustaniBtnIzmaneFinansijeActivity);
        naslovEt = view.findViewById(R.id.naslovEdIzmenaFinansijaActivity);
        kolicinaEt = view.findViewById(R.id.kolicinaEdIzmenaFinansijaActivity);
        audioIv = view.findViewById(R.id.audioIvIzmeniFinanseijFragment);
        opisEt = view.findViewById(R.id.opisIzmeniFinansijeActivity);
        audioRecordingIv = view.findViewById(R.id.audioRecordingIzmeniFinansijeFragment);
        chronometer = view.findViewById(R.id.chronometerIzmenaFinansijeFragment);

        this.prihod = (Prihod) getArguments().getSerializable(FINANSIJA_PRIHOD_KEY);

        //ako je prosledjen prihod kroz fragment
        if (prihod != null) {
            naslovEt.setText(prihod.getNaslov());
            kolicinaEt.setText(Integer.toString(prihod.getKolicina()));
            //ako je prosledjen prihod koji imao audio opis
            if (prihod.getAudioZapis() != null) {
                opisEt.setVisibility(View.GONE);
                audioIv.setVisibility(View.VISIBLE);
                //ako je korisnik uklonio permisije proveravamo
                if (hasPermissions(getActivity(), PERMISSIONS)) {
                    opisEt.setVisibility(View.GONE);
                    audioIv.setVisibility(View.VISIBLE);
                    initAudio();
                } else {
                    requestPermissions(PERMISSIONS, PERMISSION_ALL);
                }
            } else {
                opisEt.setText(prihod.getOpis());
                audioIv.setVisibility(View.GONE);
            }
            //ako je prosledjen rashod kroz fragment
        } else {
            this.rashod = (Rashod) getArguments().getSerializable(FINANSIJA_RASHOD_KEY);
            naslovEt.setText(rashod.getNaslov());
            kolicinaEt.setText(Integer.toString(rashod.getKolicina()));
            //ako je prosledjen rashod koji imao audio opis
            if (rashod.getAudioZapis() != null) {
                opisEt.setVisibility(View.GONE);
                audioIv.setVisibility(View.VISIBLE);
                //ako je korisnik uklonio permisije proveravamo
                if (hasPermissions(getActivity(), PERMISSIONS)) {
                    opisEt.setVisibility(View.GONE);
                    audioIv.setVisibility(View.VISIBLE);
                    initAudio();
                } else {
                    requestPermissions(PERMISSIONS, PERMISSION_ALL);
                }
            } else {
                opisEt.setText(rashod.getOpis());
                audioIv.setVisibility(View.GONE);
            }
        }
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
                izmeniBtn.setVisibility(View.GONE);
                odustaniBtn.setVisibility(View.GONE);

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
            izmeniBtn.setVisibility(View.VISIBLE);
            odustaniBtn.setVisibility(View.VISIBLE);

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.stop();
        });
    }


    private void initListeners() {
        odustaniBtn.setOnClickListener(v -> {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

            //da se vratimo na RV prihod
            if (prihod != null) {
                PrihodFragment prihodFragment = (PrihodFragment) getActivity().getSupportFragmentManager().findFragmentByTag(IZMENA_PRIHOD_FRAGMENT_TAG);
                if (prihodFragment != null && prihodFragment.isVisible()) {
                    transaction.replace(R.id.izmenaFinansijaFragment, new IzmenaFinansijaFragment(), PRIHOD_FRAGMENT_TAG);
                } else {
                    transaction.replace(R.id.izmenaFinansijaFragment, new PrihodFragment(), IZMENA_PRIHOD_FRAGMENT_TAG);
                }
                transaction.commit();
            } else { //da se vratimo RV rashod
                RashodFragment rashodFragment = (RashodFragment) getActivity().getSupportFragmentManager().findFragmentByTag(IZMENA_RASHOD_FRAGMENT_TAG);
                if (rashodFragment != null && rashodFragment.isVisible()) {
                    transaction.replace(R.id.izmenaFinansijaRashodFragment, new IzmenaFinansijaFragment(), RASHOD_FRAGMENT_TAG);
                } else {
                    transaction.replace(R.id.izmenaFinansijaRashodFragment, new RashodFragment(), IZMENA_RASHOD_FRAGMENT_TAG);
                }
                transaction.commit();
            }
        });

        izmeniBtn.setOnClickListener(v -> {
            //ako smo dosli sa prihod fragmenta
            if (prihod != null) {
                String naslov = naslovEt.getText().toString();
                String opis = opisEt.getText().toString();

                //ako je prihod sa audio zapisom
                if (prihod.getAudioZapis() != null) {
                    if (naslov.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Sva polja morate popuniti", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //ako nije napravljena izmena na audio uzimamo stari audio
                        if (fileToSave == null) {
                            fileToSave = prihod.getAudioZapis();
                        }
                        int kolicina = Integer.parseInt(kolicinaEt.getText().toString());
                        prihodViewModel.updatePrihodAudio(prihod.getId(), naslov, kolicina, null, fileToSave);
                        kolicinaEt.setText("");
                        naslovEt.setText("");
                    }
                } else {
                    if (naslov.isEmpty() || opis.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Popunite sva polja", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        prihodViewModel.updatePrihod(prihod.getId(), naslov, Integer.parseInt(kolicinaEt.getText().toString()), opis);
                    }
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                PrihodFragment prihodFragment = (PrihodFragment) getActivity().getSupportFragmentManager().findFragmentByTag(IZMENA_PRIHOD_FRAGMENT_TAG);
                if (prihodFragment != null && prihodFragment.isVisible()) {
                    transaction.replace(R.id.izmenaFinansijaFragment, new IzmenaFinansijaFragment(), PRIHOD_FRAGMENT_TAG);
                } else {
                    transaction.replace(R.id.izmenaFinansijaFragment, new PrihodFragment(), IZMENA_PRIHOD_FRAGMENT_TAG);
                }
                transaction.commit();
            }//ako smo dosli sa rashod fragmenta
            else {
                String naslov = naslovEt.getText().toString();
                String opis = opisEt.getText().toString();

                //ako je rashod sa audio zapisom
                if (rashod.getAudioZapis() != null) {
                    if (naslov.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Sva polja morate popuniti", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //ako nije napravljena izmena na audio uzimamo stari audio
                        if (fileToSave == null) {
                            fileToSave = rashod.getAudioZapis();
                        }
                        int kolicina = Integer.parseInt(kolicinaEt.getText().toString());
                        rashodViewModel.updateRashodAudio(rashod.getId(), naslov, kolicina, null, fileToSave);
                        kolicinaEt.setText("");
                        naslovEt.setText("");
                    }
                } else {
                    if (naslov.isEmpty() || opis.isEmpty() || kolicinaEt.getText().toString().isEmpty()) {
                        Toast.makeText(getActivity(), "Popunite sva polja", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        rashodViewModel.updateRashod(rashod.getId(), naslov, Integer.parseInt(kolicinaEt.getText().toString()), opis);
                    }
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

                RashodFragment rashodFragment = (RashodFragment) getActivity().getSupportFragmentManager().findFragmentByTag(IZMENA_RASHOD_FRAGMENT_TAG);
                if (rashodFragment != null && rashodFragment.isVisible()) {
                    transaction.replace(R.id.izmenaFinansijaRashodFragment, new IzmenaFinansijaFragment(), RASHOD_FRAGMENT_TAG);
                } else {
                    transaction.replace(R.id.izmenaFinansijaRashodFragment, new RashodFragment(), IZMENA_RASHOD_FRAGMENT_TAG);
                }
                transaction.commit();
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
