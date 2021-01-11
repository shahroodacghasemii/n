package com.chatroom.isf.apptestmarker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.SimpleLocationOverlay;

public class MyOverly extends SimpleLocationOverlay {
    //private MyCar myCar = new MyCar();
    private GeoPoint geoPosition = new GeoPoint(0, 0);
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

   /* public myOverlay(Context ctx) {
        super(ctx);
    }

    public myOverlay(Context ctx, MyCar _myCar) {
        super(ctx);
       // this.myCar= _myCar;
        this.paint.setAntiAlias(true);
    }*/

    public MyOverly(Bitmap theIcon) {
        super(theIcon);
    }

    @Override
    public void draw(Canvas c, MapView osmv, boolean shadow) {
        Point mapCenterPoint = new Point();
        osmv.getProjection().toPixels(this.geoPosition, mapCenterPoint);
        c.drawCircle(mapCenterPoint.x, mapCenterPoint.y, 10, this.paint);
    }
}