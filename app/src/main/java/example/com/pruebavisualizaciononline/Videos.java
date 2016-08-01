package example.com.pruebavisualizaciononline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;


/**
 * Clase utilizada para lanzar todos los videos que tenga la aplicacion
 */
public class Videos extends Activity {

    private static ProgressDialog progressDialog;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos);
        System.gc();

         videoView = (VideoView) findViewById(R.id.video_view);

        MediaController mediaController = new MediaController(this);

        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);

        videoView.setVideoURI(Uri.parse("http://www.hdwplayer.com/videos/300.mp4"));

        progressDialog = ProgressDialog.show(this, "", "Loading...", true);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer arg0) {
                progressDialog.dismiss();
                videoView.start();
            }
        });


    }

}