package com.chatroom.isf.apptestmarker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gridlines.LatLonGridlineOverlay2;

import java.security.cert.CertificateParsingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    Resources res ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_main);
        res =getApplicationContext().getResources();

        map = findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);

        IMapController mapController = map.getController();
        mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(32.8583, 52.2944);
        mapController.setCenter(startPoint);


    /*    LatLonGridlineOverlay2 overlay = new LatLonGridlineOverlay2();
        map.getOverlays().add(overlay);*/

        requestPermissionsIfNecessary(new String[] {
                // if you need to show the current location, uncomment the line below
                // Manifest.permission.ACCESS_FINE_LOCATION,
                // WRITE_EXTERNAL_STORAGE is required in order to show the map
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        });

    //    BitmapDrawable bd= writeOnDrawable(R.id.bubble_image,"1");
        int id = R.drawable.bonuspack_bubble;
        Bitmap b = BitmapFactory.decodeResource(res, id);

        Bitmap bmp1 = BitmapFactory.decodeResource(res, R.drawable.by);
        Bitmap bmp2 = bmp1.copy(bmp1.getConfig(), true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(50);
        Canvas canvas = new Canvas(bmp2);
        canvas.drawText("1", bmp2.getWidth()/2, bmp2.getHeight()/2, paint);

        Marker m = new Marker(map);
        m.setDraggable(true);
        m.setPosition(new GeoPoint(32.8583, 52.2944));
     //   m.setIcon(getApplicationContext().getDrawable(R.drawable.by));
        m.setIcon(new BitmapDrawable(res,bmp2));
        m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER);
        map.getOverlays().add(m);
       /* Bitmap bmp1 = BitmapFactory.decodeResource(res, R.drawable.bonuspack_bubble);
        Bitmap bmp2 = bmp1.copy(bmp1.getConfig(), true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        Canvas canvaBmp2 = new Canvas( bmp2 );
        canvaBmp2.drawText("1", 0, canvaBmp2.getHeight()/2, paint);*/

    /*    Bitmap bmp1 = BitmapFactory.decodeResource(res, R.drawable.bonuspack_bubble);
        Bitmap bmp2 = bmp1.copy(bmp1.getConfig(), true);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);
        Canvas canvas = new Canvas(bmp2);
        canvas.drawText("1", bmp2.getWidth()/2, bmp2.getHeight()/2, paint);
     //   new BitmapDrawable(bm);

        Marker m = new Marker(map);
        m.setDraggable(true);
        m.setPosition(new GeoPoint(32.8583, 52.2944));
        m.setTextLabelBackgroundColor(
                Color.BLACK
        );
        m.setTextLabelForegroundColor(
                Color.WHITE
        );
        m.setTextLabelFontSize(40);
        m.setIcon( new BitmapDrawable(res,bmp2));*/
 //       newItem.setMarkerHotspot(OverlayItem.HotspotPlace.CENTER);
        //m.setTextIcon("7");
       // m.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_TOP);
      //  map.getOverlays().add(m);
  //      Drawable marker = getResources().getDrawable(R.drawable.marker_cluster);
   //     map.invalidate();
    }


    public BitmapDrawable writeOnDrawable(int drawableId, String text){
        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, 0, bm.getHeight()/2, paint);

        return new BitmapDrawable(res,bm);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }
}
