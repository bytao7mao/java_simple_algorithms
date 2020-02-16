public class Battlepoint {

    ArrayList<Point> targets = new ArrayList<>();
    public Battlepoint(){
        //create targets to shoot at
        createTargets();
        //display map
        showMap();
        //shoot at three points
        shoot(4, 7);
        shoot(3, 3);
        shoot(9, 2);
        //display map again
        showMap();
    }

    public static void main(String[] args) {
        new Battlepoint();
    }

    private void shoot(int x, int y){
        Point shot = new Point(x,y);
        System.out.println("Firing at (" + x + "," + y + ") ... ");
        if (targets.indexOf(shot) > -1){
            System.out.println("you sank my battlepoint! index: " + targets.indexOf(shot));
            targets.remove(shot);
        } else {
            System.out.println("miss.");
        }
    }
    private void createTargets(){
        Point p1 = new Point(5, 9);
        targets.add(p1);
        Point p2 = new Point(4, 5);
        targets.add(p2);
        Point p3 = new Point(9, 2);
        targets.add(p3);
    }
    private void showMap(){
        System.out.println("\n  1  2  3  4  5  6  7  8  9");
        for (int column = 1; column<10;column++){
            for (int row=1;row<10;row++){
                if(row==1){
                    System.out.print(column+" ");
                }
                System.out.print(" ");
                Point cell = new Point(row, column);
                //if index exist
                if (targets.indexOf(cell) > -1){
                    //a target is at this position
                    System.out.print("X");
                } else {
                    //no target found
                    System.out.print(".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
