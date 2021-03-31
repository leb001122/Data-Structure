package DataStructure.SinglyLinkedList;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File(String.valueOf(args[0]));
            if (!file.exists())
                file.createNewFile();

            StudentList sList = new StudentList();
            readFile(file, sList);
            displayMenu();
            Scanner sc = new Scanner(System.in);
            int menu;

            do
            {
                System.out.print("원하는 기능을 선택하세요. : ");
                menu = sc.nextInt();
                sc.nextLine();
                handleMenu(file, menu, sc, sList);
            }
            while (menu != 4);

            sc.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void displayMenu()
    {

        System.out.println("==========================");
        System.out.println("(1) 새 학생 입력");
        System.out.println("(2) 학생 삭제");
        System.out.println("(3) 학번 순으로 전체 출력");
        System.out.println("(4) 파일에 저장하고 종료");
        System.out.println("==========================");
    }

    public static void handleMenu(File file, int menu, Scanner sc, StudentList sList)
    {
        switch (menu)
        {
            case 1:
                System.out.print("학번, 이름, 동아리들을 입력하세요. : ");
                generateStudent(sList, sc.nextLine());
                break;
            case 2:
                System.out.print("학번을 입력하세요. : ");
                deleteStudent(sc, sList);
                break;
            case 3:
                retrieveAll(sList);
                break;
            case 4:
                saveFile(file, sList);
                break;
            default:
                System.out.println("올바른 메뉴 번호를 입력해주세요.");
        }
    }

    public static void saveFile(File file, StudentList sList)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(String.valueOf(sList));  // why??????????????????????
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readFile(File file, StudentList sList)
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine()) != null)
                generateStudent(sList, line);

            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void retrieveAll(StudentList sList)
    {
        System.out.println(sList);
    }

    public static void deleteStudent(Scanner sc, StudentList sList)
    {
        String id = sc.next();
        Node<Student> node = sList.search(id);
        if (node != null)
            System.out.println(node);
        else
            System.out.println("일치하는 학번이 존재하지 않습니다.");
    }

    public static void generateStudent(StudentList sList, String input)
    {
        try
        {
            String[] array;
            array = input.split(" ");

            // 0번째(학번)가 숫자 형태의 문자열이 아닐 경우 NumberFormatException 발생
            Integer.parseInt(array[0]);

            Student student = new Student(array[0], array[1]);
            sList.add(student); // 만약 중복된 학생이 있다면 DuplicatedStudentException 발생

            String str = "";
            for(int i=2; i<array.length; str+=array[i], i++) {
            }
            student.setClub(new ClubList(str));

        } catch (NumberFormatException e) {
            System.out.println("Warning : 잘못된 학번");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
        }
    }
}

