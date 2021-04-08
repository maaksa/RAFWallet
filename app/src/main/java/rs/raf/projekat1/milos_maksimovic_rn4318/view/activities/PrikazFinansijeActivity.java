package rs.raf.projekat1.milos_maksimovic_rn4318.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import rs.raf.projekat1.milos_maksimovic_rn4318.R;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Prihod;
import rs.raf.projekat1.milos_maksimovic_rn4318.models.Rashod;
import timber.log.Timber;

public class PrikazFinansijeActivity extends AppCompatActivity {

    public static final String FINANSIJA_PRIHOD_KEY = "finansijaPrihodKey";
    public static final String FINANSIJA_RASHOD_KEY = "finansijaRashodKey";

    private boolean releaseMediaPlayer;

    private TextView naslovTv;
    private TextView kolicinaTv;
    private TextView opisTv;

    private Prihod prihod;
    private Rashod rashod;

    private TextView playerPosition, playerDuration;
    private SeekBar seekBar;
    private ImageView btRew, btPlay, btPause, btFw;

    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;
    private AudioFocusRequest audioFocusRequest;
    private Handler handler = new Handler();
    private Runnable runnable;

    LinearLayout seekBarrLL;
    LinearLayout playButtonLL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_finansije);
        init();
    }

    private void init() {
        initView();
    }

    private void initView() {
        naslovTv = findViewById(R.id.naslovEdPrikazFinansijaActivity);
        kolicinaTv = findViewById(R.id.kolicinaEdPrikazFinansijaActivity);
        opisTv = findViewById(R.id.opisPrikazFinansijeActivityEt);

        playerPosition = findViewById(R.id.player_position);
        playerDuration = findViewById(R.id.player_duration);
        seekBar = findViewById(R.id.seek_bar);
        btRew = findViewById(R.id.bt_rew);
        btPlay = findViewById(R.id.bt_play);
        btPause = findViewById(R.id.bt_pause);
        btFw = findViewById(R.id.bt_fw);

        playButtonLL = findViewById(R.id.playbuttonLinearLayout);
        seekBarrLL = findViewById(R.id.seekBarLinearLayout);

        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            this.prihod = (Prihod) intent.getExtras().getSerializable(FINANSIJA_PRIHOD_KEY);
            if (prihod != null) {
                naslovTv.setText(prihod.getNaslov());
                kolicinaTv.setText(Integer.toString(prihod.getKolicina()));
                if (prihod.getAudioZapis() != null) {
                    opisTv.setVisibility(View.GONE);
                    playButtonLL.setVisibility(View.VISIBLE);
                    seekBarrLL.setVisibility(View.VISIBLE);
                    initPlayerPrihod();
                } else {
                    opisTv.setText(prihod.getOpis());
                }
            } else {
                this.rashod = (Rashod) intent.getExtras().get(FINANSIJA_RASHOD_KEY);
                naslovTv.setText(rashod.getNaslov());
                kolicinaTv.setText(Integer.toString(rashod.getKolicina()));
                if (rashod.getAudioZapis() != null) {
                    opisTv.setVisibility(View.GONE);
                    playButtonLL.setVisibility(View.VISIBLE);
                    seekBarrLL.setVisibility(View.VISIBLE);
                    initPlayerRashod();
                } else {
                    opisTv.setText(rashod.getOpis());
                }
            }
        }
    }


    private void initPlayerPrihod() {
        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(prihod.getAudioZapis()));

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Get duration of media player
        int duration = mediaPlayer.getDuration();
        // Convert millisecond to minute and second
        String sDuration = convertFormat(duration);
        // Set duration on text view
        playerDuration.setText(sDuration);

        releaseMediaPlayer = true;
        initRunnable();
        initListeners();
    }

    private void initPlayerRashod() {
        mediaPlayer = MediaPlayer.create(this, Uri.fromFile(rashod.getAudioZapis()));

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Get duration of media player
        int duration = mediaPlayer.getDuration();
        // Convert millisecond to minute and second
        String sDuration = convertFormat(duration);
        // Set duration on text view
        playerDuration.setText(sDuration);

        releaseMediaPlayer = true;
        initRunnable();
        initListeners();
    }

    private void initRunnable() {
        // Initialize runnable
        runnable = new Runnable() {
            @Override
            public void run() {
                // Set progress on seek bar
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
                // Handler post delay for 0.5 second
                handler.postDelayed(this, 500);
            }
        };
    }

    private void initListeners() {
        btPlay.setOnClickListener(v -> play());

        btPause.setOnClickListener(v -> pause());

        btFw.setOnClickListener(v -> {
            // Get current position of media player
            int currentPosition = mediaPlayer.getCurrentPosition();
            // Get duration of media player
            int durationFw = mediaPlayer.getDuration();
            // Check condition
            if (mediaPlayer.isPlaying() && durationFw != currentPosition) {
                // When media is playing and duration is not equal to current position fast forward for 5 seconds
                currentPosition += 5000;
                // Set current position on text view
                playerPosition.setText(convertFormat(currentPosition));
                // Set progress on seek bar
                mediaPlayer.seekTo(currentPosition);
            }
        });

        btRew.setOnClickListener(v -> {
            // Get current position of media player
            int currentPosition = mediaPlayer.getCurrentPosition();
            // Check condition
            if (mediaPlayer.isPlaying() && currentPosition > 5000) {
                // When media is playing and current position is greater than 5 seconds rewind for 5 seconds
                currentPosition -= 5000;
                // Set current position on text view
                playerPosition.setText(convertFormat(currentPosition));
                // Set progress on seek bar
                mediaPlayer.seekTo(currentPosition);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Check condition
                if (fromUser) {
                    // When drag the seek bar set progress on seek bar
                    mediaPlayer.seekTo(progress);
                }
                // Set current position on text view
                playerPosition.setText(convertFormat(mediaPlayer.getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // We have to handle focus changes
        onAudioFocusChangeListener = focusChange -> {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                case AudioManager.AUDIOFOCUS_LOSS: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a short amount of time.
                    // The AUDIOFOCUS_LOSS case means we've lost audio focus
                    Timber.e("AUDIOFOCUS_LOSS_TRANSIENT or AUDIOFOCUS_LOSS");
                    pause();
                }
                break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK: {
                    // The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                    // our app is allowed to continue playing sound but at a lower volume.
                    Timber.e("AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK");
                    playerDuck(true);
                }
                break;
                case AudioManager.AUDIOFOCUS_GAIN: {
                    // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                    Timber.e("AUDIOFOCUS_GAIN");
                    playerDuck(false);
                    play();
                }
                break;
            }
        };

        mediaPlayer.setOnCompletionListener(mp -> {
            // Hide pause button
            btPause.setVisibility(View.GONE);
            // Show play button
            btPlay.setVisibility(View.VISIBLE);
            // Set media player to initial position
            mediaPlayer.seekTo(0);
        });

        // Description of the audioFocusRequest
        audioFocusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build())
                .setAcceptsDelayedFocusGain(true)
                .setWillPauseWhenDucked(true)
                .setOnAudioFocusChangeListener(onAudioFocusChangeListener)
                .build();

    }

    private void pause() {
        // Hide pause button
        btPause.setVisibility(View.GONE);
        // Show play button
        btPlay.setVisibility(View.VISIBLE);
        // Pause media player
        mediaPlayer.pause();
        // Stop handler
        handler.removeCallbacks(runnable);
    }

    private void play() {
        // Request audio focus
        int result = audioManager.requestAudioFocus(audioFocusRequest);//zatrazimo audio fokus tj uzimamo resurs zvucnika
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            Timber.e("AUDIOFOCUS_REQUEST_GRANTED");
            // Hide play button
            btPlay.setVisibility(View.GONE);
            // Show pause button
            btPause.setVisibility(View.VISIBLE);
            // Start media player
            mediaPlayer.start();
            // Set max on seek bar
            seekBar.setMax(mediaPlayer.getDuration());
            // Start handler
            handler.postDelayed(runnable, 0);
        }
    }

    private void releaseMediaPlayer() {
        // Release resources
        if (mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = null;
        audioManager.abandonAudioFocusRequest(audioFocusRequest);
        // Remove and stop running threads
        handler.removeCallbacks(runnable);
        handler.removeCallbacksAndMessages(null);
        runnable = null;
        handler = null;
    }

    public synchronized void playerDuck(boolean duck) {
        if (mediaPlayer != null) {
            // Reduce the volume when ducking - otherwise play at full volume.
            mediaPlayer.setVolume(duck ? 0.2f : 1.0f, duck ? 0.2f : 1.0f);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Proverite sta se desava sa zvukom kada sklonite komentar i izadjete iz aplikacije sa home button
        //pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (releaseMediaPlayer) {
            releaseMediaPlayer();
        }
    }

    @SuppressLint("DefaultLocale")
    private String convertFormat(int duration) {
        return String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)));
    }
}