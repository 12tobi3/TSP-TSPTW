package Model;

public class Customer {
        private int x;
        private int y;
        private String id;
        private Timespan timespane;

        public Customer(int x, int y, String id){
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public Customer(){
            this.x = (int) (Math.random()*800);
            this.y = (int) (Math.random()*400);
        }

        public double distanceToCustomerByEuklid(Customer customer){
            double distanceX = getX() - customer.getX();
            double distanceY = getY() - customer.getY();
            return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));
        }

        public double distanceToCustomerByManhattan(Customer customer){
            double distanceX = Math.abs(getX()-customer.getX());
            double distanceY = Math.abs(getY()-customer.getY());
            return distanceX + distanceY;
        }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timespan getTimespane() {
        return timespane;
    }

    public void setTimespane(Timespan timespane) {
        this.timespane = timespane;
    }
}
