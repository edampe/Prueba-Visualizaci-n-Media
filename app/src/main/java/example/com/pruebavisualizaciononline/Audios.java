package example.com.pruebavisualizaciononline;

import android.app.ProgressDialog;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;


/**
 *
 */
public class Audios extends AppCompatActivity implements View.OnClickListener {

    static final String AUDIO_PATH = "https://archive.org/download/animalsounds1/09littleblueheronfishes.mp3";
    MediaPlayer mediaPlayer;
    private int playbackPosition = 0;
    GifImageView _Gif;
    LinearLayout _LinearButtons;
    private Handler _Handler;
    private SeekBar _SeekAudio;
    private TextView _TxtContadorAudio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audios);

        _Handler = new Handler();
        _SeekAudio = (SeekBar) findViewById(R.id.seek_bar_audio);
        _SeekAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                System.out.println("OnProgress");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                System.out.println("onStartTrackingTouch  " + _SeekAudio.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                System.out.println("onStopTrackingTouch   " + _SeekAudio.getProgress());
                mediaPlayer.seekTo(_SeekAudio.getProgress());

            }
        });



        _TxtContadorAudio = (TextView) findViewById(R.id.txt_contador_audio);


        Button _PlayAudio = (Button) findViewById(R.id.btn_play_audio);
        _PlayAudio.setOnClickListener(this);

        Button _PauseAudio = (Button) findViewById(R.id.pausePlayerBtn);
        _PauseAudio.setOnClickListener(this);
        Button _RestartAudio = (Button) findViewById(R.id.restartPlayerBtn);
        _RestartAudio.setOnClickListener(this);
        Button _StopAudio = (Button) findViewById(R.id.stopPlayerBtn);
        _StopAudio.setOnClickListener(this);

        _LinearButtons = (LinearLayout) findViewById(R.id.linear_audio);
        _Gif = (GifImageView) findViewById(R.id.gif);
        prepararAudio();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play_audio:
                System.out.println("play");
                try {
                    playAudio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.pausePlayerBtn:
                System.out.println("pause");
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
                break;
            case R.id.restartPlayerBtn:
                System.out.println("reestart");

                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
                break;
            case R.id.stopPlayerBtn:
                System.out.println("stop");

                /*if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.seekTo(0);
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }*/

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    playbackPosition = 0;
                    _TxtContadorAudio.setText("00:00");
                    prepararAudio();
                }
                break;
        }
    }

    private void prepararAudio() {
        killMediaPlayer();
        _LinearButtons.setVisibility(View.GONE);
        _Gif.setVisibility(View.VISIBLE);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mediaPlayer.setDataSource(AUDIO_PATH);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    _LinearButtons.setVisibility(View.VISIBLE);
                    _Gif.setVisibility(View.GONE);
                    _SeekAudio.setMax(mediaPlayer.getDuration());
                    _SeekAudio.setProgress(0);
                    _TxtContadorAudio.setText(duracionAudio(mediaPlayer.getDuration()));

                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                // El metodo se llama una vez se ha terminado de reproducir el Audio.
                @Override
                public void onCompletion(MediaPlayer mp) {
                    _SeekAudio.setProgress(mediaPlayer.getCurrentPosition() + 1000);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();


    }

    private void playAudio() throws Exception {
        mediaPlayer.start();
        _Handler.post(UpdatePlayTime);


    }

    Runnable UpdatePlayTime = new Runnable() {
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                // Update play time and SeekBar

                _SeekAudio.setProgress(mediaPlayer.getCurrentPosition());
                _TxtContadorAudio.setText(duracionAudio(mediaPlayer.getCurrentPosition() + 1000));

                _Handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String duracionAudio(int _TiempoMilisegundos) {

        int HOUR = 3600000;
        int MINUTE = 60000;
        int _DuaracionMinutos = (_TiempoMilisegundos % HOUR) / MINUTE;
        int SECOND = 1000;
        int _DuracionSegundos = (_TiempoMilisegundos % MINUTE) / SECOND;

        return String.format("%02d:%02d", _DuaracionMinutos, _DuracionSegundos);
    }
}


