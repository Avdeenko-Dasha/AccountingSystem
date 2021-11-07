package Building;

public class Room {
    /**Variable that stores the area of the room*/
    private double squareRoom;

    Room(){ squareRoom = 0; }

    public double getSquareRoom() { return squareRoom; }

    public void setSquareRoom(double squareRoom) { this.squareRoom = squareRoom; }

    public String toString() { return "    Room " + "(square room = " + String.format("%.2f", squareRoom) + ')'; }

    public boolean equals(Object obj) {
        if(!(obj instanceof Room)) return false;
        if(squareRoom == 0 || ((Room) obj).squareRoom == 0) return false;
        if(squareRoom == ((Room) obj).squareRoom) return true;
        else return false;
    }

    public static class BuilderRoom{
        private Room newRoom;

        public BuilderRoom(){
            newRoom = new Room();
        }

        public Room.BuilderRoom setSquareRoom(double squareRoom){
            newRoom.squareRoom = squareRoom;
            return this;
        }

        public Room build(){
            return newRoom;
        }
    }
}
