package Lesson7;

import javax.print.attribute.standard.NumberOfDocuments;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        /*Чтение и проверка номеров документа из файла заданного через консоль, запись валидных и не валидных номеров
        документа в разные файлы-отчёты */
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь к файлу: ");
        String filePath = scanner.nextLine();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("invalidNumOfDoc.txt"));
             BufferedWriter wb = new BufferedWriter(new FileWriter("validNumOfDoc"));
             BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                if (s.length() == 15 && s.matches("[a-zA-Z0-9]*") && (s.startsWith("kontract") || s.startsWith("docnum"))) {
                    //System.out.println(s);
                    wb.write(s);
                    wb.newLine();
                    wb.flush();
                }
                //Проверка строки, она состоит из цифр и букв,длинной в 15 символов, начинается с docnum или kotract
                else {
                    if (!s.matches("[a-zA-Z0-9]*")) {
                        bw.write(s + " contains special symbols");
                        bw.newLine();
                        bw.flush();
                    }
                    if (s.length() != 15) {
                        bw.write(s + " incorrect length ");
                        bw.newLine();
                        bw.flush();
                    }
                    if (!(s.startsWith("kontract") || s.startsWith("docnum"))) {
                        bw.write(s + " incorrect start ");
                        bw.newLine();
                        bw.flush();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

