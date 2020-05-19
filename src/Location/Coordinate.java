package Location;

public class Coordinate
{

    public String longitude;
    public String latitude;
    public String time;
    public String title;
    public String address;
    public String typedes;
    public String typecode;
    public int rating;

    public Coordinate(String lo, String la, String t,String title,String address,String typedes,String typecode,int rating) {
        this.longitude=lo;
        this.latitude=la;
        this.time=t;
        this.title = title;
        this.address = address;
        this.typedes = typedes;
        this.typecode = typecode;
        this.rating = rating;
    }
    public Coordinate() {
        // TODO Auto-generated constructor stub
    }

    public String getTypecode() {
        return typecode;
    }

    public String getTitle(){
        return title;
    }
}