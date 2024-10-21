import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main
{

    public static String readfile(String path) throws FileNotFoundException
    {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        String text = scanner.nextLine();
        while (scanner.hasNextLine())
        {
            text = text + "\n" +scanner.nextLine();
        }
        scanner.close();
        return text;
    }

    public static void writefile(String text) throws FileNotFoundException
    {
        File file = new File("C:\\Users\\Home\\IdeaProjects\\Text editor\\src\\output.txt");
        PrintWriter pw = new PrintWriter(file);
        pw.print(text);
        pw.close();
    }

    public static void main(String[] args)
    {
        char[] glasn = {'а','е','я','у','э','ю','ё','й','и','о'};
        String[] lines;
        while (true)
        {
            String default_path = "C:\\Users\\Home\\IdeaProjects\\Text editor\\src\\input.txt";
            Scanner scanner = new Scanner(System.in);
            System.out.println("Укажите путь до файла:");
            String path = scanner.nextLine();
            if (path.isEmpty()){
                path = default_path;
            }
            try {
                String[] separators = {"-","_",",",".","!","&","?","<",">",":",";","/","\\","|","«","»"};
                String text = readfile(path);
                for (int i = 0; i<separators.length; i++)
                {
                    text = text.replace(separators[i], " ");
                }
                lines = text.split("\n");
                break;
            }catch (FileNotFoundException fg)
            {
                System.out.println("Файл не найден");
            }
        }
        int lengthword;
        while (true)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Выберите количество символов: ");
            try
            {
                lengthword = scanner.nextInt();
                break;
            }catch (InputMismatchException fg)
            {
                System.out.println("Введите число");
            }
        }
        String new_text = "";
        for (int i = 0; i<lines.length; i++)
        {
            String[] word = lines[i].split(" ");
            String new_line = "";
            for (int j=0; j<word.length; j++)
            {
                if (word[j].length() == lengthword)
                {
                    String _word = word[j];
                    Boolean delete = true;
                    for (int l=0; l<glasn.length; l++)
                    {
                        if (_word.toLowerCase().charAt(0) == glasn[l])
                        {
                            delete=false;
                            break;
                        }
                    }
                    if (delete){
                        continue;
                    }else
                    {
                        if (j ==0)
                        {
                            new_line = word[j];
                        }else
                        {
                            new_line = new_line + " " + word[j];
                        }
                    }
                }else
                {
                    if (j ==0)
                    {
                        new_line = word[j];
                    }else
                    {
                        new_line = new_line + " " + word[j];
                    }
                }
            }
            if (i == 0)
            {
                new_text = new_line.trim();
            }else
            {
                new_text = new_text + '\n' +new_line.trim();
            }
        }
        try {
            writefile(new_text);
        }catch (FileNotFoundException fg)
        {
            System.out.println("Не удалось записать файл output.txt");
        }
    }

}