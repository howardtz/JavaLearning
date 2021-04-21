package chap4.hotel;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("ヱヴァンゲリヲン");
        System.out.println("欢迎您入住" + hotel.getHotelName() + "酒店");
        Scanner scn = new Scanner(System.in);
        System.out.print("请输入您的指令：");
        String command = scn.next();
        String roomNo;
        while (!command.equalsIgnoreCase("quit")) {
            if (command.equalsIgnoreCase("search")) {
                String para = scn.next();
                if (para.equals("all")) {
                    hotel.searchAll();
                } else {
                    hotel.searchByNo(para);
                }
            } else if (command.equalsIgnoreCase("in")) {
                roomNo = scn.next();
                String name = scn.next();
                int res = hotel.checkIn(roomNo, name);
                if (res == 1) {
                    System.out.println(name + "成功入住！");
                } else if (res == 2) {
                    System.out.println("该房间已有客人！");
                } else if (res == 3) {
                    System.out.println("房间号输入错误");
                }
            } else if (command.equalsIgnoreCase("out")) {
                roomNo = scn.next();
                int res = hotel.checkout(roomNo);
                if (res == 1) {
                    System.out.println(roomNo + "成功退房！");
                } else if (res == 2) {
                    System.out.println("该房间没有客人！");
                } else if (res == 3) {
                    System.out.println("房间号输入错误");
                }
            } else {
                System.out.println("没有该指令");
            }
            System.out.print("请输入您的指令：");
            command = scn.next();
        }
        System.out.println("欢迎您下次光临" + hotel.getHotelName());
        System.exit(0);
    }
}
