package Location;

public class Coordinate
{

    String longitude;
    String latitude;
    String time;
    String title;
    String address;
    String typedes;
    String typecode;
    public Coordinate(String lo, String la, String t,String title,String address,String typedes,String typecode) {
        this.longitude=lo;
        this.latitude=la;
        this.time=t;
        this.title = title;
        this.address = address;
        this.typedes = typedes;
        this.typecode = typecode;
    }
    public Coordinate() {
        // TODO Auto-generated constructor stub
    }
}