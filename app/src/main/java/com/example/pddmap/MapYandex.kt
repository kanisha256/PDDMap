package com.example.pddmap

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.ComponentActivity
import androidx.compose.ui.graphics.Color
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yandex.mapkit.*
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.*
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.ObjectEvent
import com.yandex.mapkit.map.*
import com.yandex.mapkit.map.Map
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.SearchFactory
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.SearchManagerType
import com.yandex.mapkit.traffic.TrafficLayer
import com.yandex.mapkit.transport.TransportFactory
import com.yandex.mapkit.transport.bicycle.Route
import com.yandex.mapkit.transport.bicycle.Session
import com.yandex.mapkit.user_location.UserLocationLayer
import com.yandex.mapkit.user_location.UserLocationView
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider

class MapYandex : ComponentActivity(),DrivingSession.DrivingRouteListener {
    private val MAPKIT_API_KEY = "ba1e4ee2-a331-4269-8e6b-085954773601"
    private var mapView: MapView? = null
    private val ROUTE_START = Point(55.791412, 49.123678)
    private val ROUTE_END = Point(55.778190, 49.115444)
    private val CENTER = Point(
        (ROUTE_START.latitude+ROUTE_END.latitude)/2,
        (ROUTE_START.longitude+ROUTE_END.longitude)/2
    )
    private var mapObject:MapObjectCollection? = null
    private var drivingRouter: DrivingRouter? = null
    private var drivingSession:DrivingSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map)

        mapView = findViewById(R.id.mapview)
        mapView?.map?.move(CameraPosition(Point(55.791412, 49.123678), 14.0f, 0.0f, 0.0f))

        var mapKit : MapKit = MapKitFactory.getInstance()
        var probki = mapKit.createTrafficLayer(mapView!!.mapWindow)
        probki.isTrafficVisible = true



        drivingRouter = DirectionsFactory.getInstance().createDrivingRouter()
        mapObject = mapView!!.map.mapObjects.addCollection()


        draws5_19_1Mark("55.790748","49.123204")
        draws5_19_1Mark("55.788026","49.122991")
        draws5_19_1Mark("55.786730", "49.119246")
        draws5_19_1Mark("55.785602","49.117428")
        draws5_19_1Mark("55.785211", "49.116904")
        draws5_19_1Mark("55.783392", "49.114610")
        draws5_19_1Mark("55.782346", "49.115483")
        draws5_19_1Mark("55.780115", "49.113134")
        draws5_19_1Mark("55.778141", "49.114983")
        draws5_19_1Mark("55.792075", "49.119739")
        draws5_19_1Mark("55.788738", "49.115277")
        submitRequest()
    }

    private fun draws5_19_1Mark(coord1:String,coord2:String) {
        val view = View(this).apply {
            background = getDrawable(R.drawable.s50)
        }
        mapView!!.map.mapObjects.addPlacemark(
            Point(coord1!!.toDouble(),coord2!!.toDouble()),ViewProvider(view)
        )
    }

    private fun drawsrabota(coord1:String,coord2:String) {
        val view = View(this).apply {
            background = getDrawable(R.drawable.rabota)
        }
        mapView!!.map.mapObjects.addPlacemark(
            Point(coord1!!.toDouble(),coord2!!.toDouble()),ViewProvider(view)
        )
    }

    private fun submitRequest() {
        val drivingOptions = DrivingOptions()
        val vehicleOptions = VehicleOptions()
        val requestPoints:ArrayList<RequestPoint> = ArrayList()
        requestPoints.add(RequestPoint(ROUTE_START,RequestPointType.WAYPOINT,null))
        requestPoints.add(RequestPoint(ROUTE_END,RequestPointType.WAYPOINT,null))
        drivingSession = drivingRouter!!.requestRoutes(requestPoints,drivingOptions,vehicleOptions,this)
    }

    override fun onStop() {
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        mapView!!.onStart()
    }

    override fun onDrivingRoutes(p0: MutableList<DrivingRoute>) {
        for(route in p0){
            mapObject!!.addPolyline(route.geometry)
        }
    }

    override fun onDrivingRoutesError(p0: Error) {
        var errorMessage = "Loh"
        Toast.makeText(this@MapYandex,errorMessage,Toast.LENGTH_SHORT)
    }


}
