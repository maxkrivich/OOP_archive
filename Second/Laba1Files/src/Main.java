
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        LinkedList<Tour> buffList = getListFile(new FileInputStream("c:/obj.txt"));
        int n;
        while ((n = getNumber()) != 5) {
            switch (n) {
                case 1: {
                    System.out.println();
                    for (Tour t : buffList) 
                        System.out.println(t);
                    System.out.println();
                    break;
                }
                case 2: {
                    System.out.println("Company:  Country:  Date(L|A) MM/dd/yyyy  Cost:  Counst:");
                    buffList.addLast(new Tour(in.next(), in.next(), in.next(), in.next(), in.nextFloat(), in.nextInt()));
                    break;
                }
                case 3: {
                    System.out.println("Index: ");
                    buffList.remove(in.nextInt());
                    break;
                }
                case 4: {
                    System.out.println("Input index: ");
                    System.out.println("1)company; 2)country; 3)date(31 L|32 A) MM/dd/yyyy; 4)cost;5)counst");
                    int i = in.nextInt(), c = in.nextInt();
                    switch (i) {
                        case 1: {
                            buffList.get(i).setCompanyName(in.next());
                            break;
                        }
                        case 2: {
                            buffList.get(i).setHostCountry(in.next());
                            break;
                        }
                        case 31: {
                            buffList.get(i).setLeaveDate(in.next());
                            break;
                        }
                        case 32: {
                            buffList.get(i).setArrivalDate(in.next());
                            break;
                        }
                        case 4: {
                            buffList.get(i).setCost(in.nextFloat());
                            break;
                        }
                        case 5: {
                            buffList.get(i).setCounst(in.nextInt());
                            break;
                        }
                        default:
                            throw new AssertionError();
                    }
                    break;
                } //case 4
                case 5: {
                    writeFiles(new FileOutputStream("c:/obj.txt", false), buffList);
                    break;
                }
                default:
                    throw new AssertionError();
            }
        }
    }

    private static int getNumber() {
        System.out.println("1. Просмотр информации обо всех имеющихся турах");
        System.out.println("2. Добавление тура");
        System.out.println("3. Удаление тура в заданной позиции");
        System.out.println("4. Редактирование тура в заданной позиции");
        System.out.println("5. Выход");
        return new Scanner(System.in).nextInt();
    }

    private static LinkedList<Tour> getListFile(FileInputStream f) throws Exception {
        ObjectInputStream s = new ObjectInputStream(f);
        LinkedList<Tour> tmp = new LinkedList<Tour>();
        while (f.available() > 0) 
            tmp.add((Tour) s.readObject());
        s.close();
        return tmp;
    }

    private static void writeFiles(FileOutputStream f, LinkedList<Tour> lt) throws Exception {
       ObjectOutputStream s = new ObjectOutputStream(f);
       int i = 0;
        while (i < lt.size()) 
            s.writeObject(i++);
        s.flush();
        s.close();
    }
}
