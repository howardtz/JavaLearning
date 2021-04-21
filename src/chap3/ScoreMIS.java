package chap3;

import java.util.Scanner;

public class ScoreMIS {
    static final int STUDENT_NUM=6;
    static final int COURSE_NUM=5;
    static String[] students={"zhang","wang","li","zhao","liu","song"};
    static String[] courses={"C","Java","mySQL","Linux","HTML"};
    static int[][] score= new int[STUDENT_NUM][COURSE_NUM];

    public static void main(String[] args)	{
        initScore();
        show();
        run();
    }

    public static void run(){
        Scanner scn=new Scanner(System.in);
        while(true){
            System.out.print("请输入命令:");
            String command=scn.next();
            if(command.equalsIgnoreCase("avg")){
                String parameter=scn.next();
                avg(parameter);
            }
            else if(command.equalsIgnoreCase("get")){
                String parameter1=scn.next();
                String parameter2=scn.next();
                get(parameter1,parameter2);
            }
            else if(command.equalsIgnoreCase("sort")){
                String parameter=scn.next();
                sortByScore(parameter);
            }
            else if (command.equalsIgnoreCase("exit")){
                System.out.println("退出查询系统");
                System.exit(0);
            }
            else
                System.out.println("输入错误，请重新输入！");
        }
    }

    public static void initScore(){
        for(int i=0; i<score.length; i++)
            for(int j=0; j<score[i].length; j++)
                score[i][j]=(int)(Math.random()*101);
    }

    public static void show(){
        System.out.printf("%-8s", "");
        for (String element : courses)
            System.out.printf("%-8s", element);
        System.out.println();
        for (int i = 0; i < students.length; i++) {
            System.out.printf("%-8s", students[i]);
            for (int j = 0; j < courses.length; j++)
                System.out.printf("%-8d", score[i][j]);
            System.out.println();
        }
    }
    public static void get(String condition1, String condition2) {
        int i_index = -1, j_index = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i].equalsIgnoreCase(condition1))
                i_index = i;
        }
        if (i_index != -1) {
            for (int j = 0; j < courses.length; j++)
                if (courses[j].equalsIgnoreCase(condition2))
                    j_index = j;
        } else {
            System.out.println("没有 " + condition1 + " 这个人");
            return;
        }
        if (j_index != -1) {
            System.out.println(condition1 + "的" + condition2 + "的成绩是：" + score[i_index][j_index]);
        } else {
            System.out.println(condition1 + "没有 " + condition2 + " 这门课程");
            return;
        }
    }

    public static void avg(String condition){
        int i_index=-1,j_index=-1;

        for(int i=0;i<students.length; i++){
            if (students[i].equalsIgnoreCase(condition))
                i_index=i;
        }
        if(i_index!=-1){
            double sum=0;
            for(int j=0; j<score[i_index].length; j++){
                sum=sum+score[i_index][j];
            }
            System.out.printf("%s的平均分是：%.2f\n",condition,sum/COURSE_NUM);
        }else{
            for(int j=0; j<courses.length; j++){
                if (courses[j].equalsIgnoreCase(condition)){
                    j_index=j;
                }
            }
            if (j_index!=-1){
                double sum=0;
                for (int[] ints : score) {
                    sum = sum + ints[j_index];
                }
                System.out.printf("%s的平均分是：%.2f\n",condition,sum/STUDENT_NUM);
            }else{
                System.out.println("你输入的既不是课程名，也不是学生名");
            }
        }
    }

    public static void sortByScore(String sort_condition){
        int i, j, j_index=-1;
        for(j=0; j<courses.length; j++){
            if (courses[j].equalsIgnoreCase(sort_condition)){
                j_index=j;
            }
        }
        if(j_index==-1){
            System.out.println("没有这门课程");
            return;
        }
        int[] score_temp= new int[STUDENT_NUM];
        for(i=0; i<score.length; i++){
            score_temp[i]=score[i][j_index];
        }
        String[] students_temp=new String[STUDENT_NUM];
        for(i=0; i<students.length; i++){
            students_temp[i]=students[i];
        }
        for(int k1=0; k1<score_temp.length-1; k1++){
            for (int k2=0; k2<score_temp.length-k1-1; k2++){
                if (score_temp[k2]>score_temp[k2+1]){
                    int temp=score_temp[k2];
                    score_temp[k2]=score_temp[k2+1];
                    score_temp[k2+1]=temp;
                    String tmp_str=students_temp[k2];
                    students_temp[k2]=students_temp[k2+1];
                    students_temp[k2+1]=tmp_str;
                }
            }
        }
        System.out.printf("%-6s%-6s%-8s\n","名次","姓名",courses[j_index]);
        for(int k=0; k<score_temp.length; k++)
            System.out.printf("%-8d%-8s%-8d\n", k+1,students_temp[k],score_temp[k]);
    }
}
