package chap4.hotel;

public class Hotel {

    private static final int HEIGHT = 3;
    private static final int WIDTH = 10;
    private String hotelName;
    private Room[][] rooms;

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        rooms = new Room[HEIGHT][WIDTH];
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                rooms[i][j] = new Room();
                rooms[i][j].setId(i + 1, j + 1);
            }
        }
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void searchAll() {
        for (Room[] room : rooms) {
            for (Room item : room) {
                System.out.print(item.getId() + "    ");
            }
            System.out.println();
            for (Room value : room) {
                System.out.print(value.getCustomerName() == null ? "        " : value.getCustomerName() + "\t");
            }
            System.out.println();
            for (int j = 1; j <= 8 * WIDTH; j++)
                System.out.print("-");
            System.out.println();
        }
    }

    public void searchByNo(String roomNo) {
        if (testRoomNo(roomNo)) {
            int height = Integer.parseInt(roomNo.substring(0, 2));
            int width = Integer.parseInt(roomNo.substring(2, 4));
            System.out.println(rooms[height - 1][width - 1].getCustomerName() == null ? "该房间没有客人" : roomNo + ":"
                                + rooms[height - 1][width - 1].getCustomerName());
        } else {
            System.out.println("没有这个房间");
        }
    }

    public int checkIn(String roomNo, String name) {
        if (testRoomNo(roomNo)) {
            int height = Integer.parseInt(roomNo.substring(0, 2));
            int width = Integer.parseInt(roomNo.substring(2, 4));
            if (rooms[height - 1][width - 1].in(name)) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    public int checkout(String roomNo) {
        if (testRoomNo(roomNo)) {
            int height = Integer.parseInt(roomNo.substring(0, 2));
            int width = Integer.parseInt(roomNo.substring(2, 4));
            if (rooms[height - 1][width - 1].out()) {
                return 1;
            } else {
                return 2;
            }
        } else {
            return 3;
        }
    }

    private boolean testRoomNo(String roomNo) {
        int height = Integer.parseInt(roomNo.substring(0, 2));
        int width = Integer.parseInt(roomNo.substring(2, 4));
        return height >= 1 && height <= HEIGHT && width >= 1 && width <= WIDTH;
    }
}
