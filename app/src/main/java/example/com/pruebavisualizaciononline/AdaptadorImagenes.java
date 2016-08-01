package example.com.pruebavisualizaciononline;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Proporciona los elementos para la vista del listview
 */
public class AdaptadorImagenes extends ArrayAdapter<ImagenesBin> {

    Context _Context;

    int _LayoutResourceId;

    ArrayList<ImagenesBin> data = new ArrayList<>();

    /**
     * Constructor.
     * @param _Context
     * @param _LayoutResourceId
     * @param _Data
     */
    public AdaptadorImagenes(Context _Context, int _LayoutResourceId,
                             ArrayList<ImagenesBin> _Data) {
        super(_Context, _LayoutResourceId, _Data);
        this._LayoutResourceId = _LayoutResourceId;
        this._Context = _Context;
        this.data = _Data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View _ViewAdapter = convertView;
        ViewHolder holder = null;

        // Se crea la vista si aun no existe.
        if (_ViewAdapter == null) {
            LayoutInflater inflater = ((Activity) _Context).getLayoutInflater();
            _ViewAdapter = inflater.inflate(_LayoutResourceId, parent, false);

            holder = new ViewHolder();

            holder._ImgURL = (ImageView) _ViewAdapter
                    .findViewById(R.id.img);

            _ViewAdapter.setTag(holder);
        } else {
            holder = (ViewHolder) _ViewAdapter.getTag();
        }

        // Consulta de las categorias
        ImagenesBin ConsultaImg = data.get(position);

        String _URL = ConsultaImg._UrlImg;

        //Picasso.with(_Context).load(_URL).into(holder._ImgURL);


        Picasso.with(_Context)
                .load(_URL)
                .placeholder(R.drawable.placeholder_imagen)
                .error(R.drawable.no_img)
                .into(holder._ImgURL);

        //holder._ImgURL.setImageResource(idDrawable);

        return _ViewAdapter;
    }

    static class ViewHolder {

        ImageView _ImgURL;
    }
}