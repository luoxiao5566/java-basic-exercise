import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        Scanner str1=new Scanner(System.in);
        System.out.println("请输入第一个字符串");
        String firstWordList = str1.nextLine();
        System.out.println("请输入第二个字符串");
        Scanner str2=new Scanner(System.in);
        String secondWordList =str2.nextLine();

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        for (String i: result) {
            System.out.println(i);
        }

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        firstWordList=firstWordList.toUpperCase();
        secondWordList=secondWordList.toUpperCase();

        Pattern pattern = Pattern.compile(",");
        Stream<String> firstStringStream = pattern.splitAsStream(firstWordList);
        Stream<String> secondStringStream = pattern.splitAsStream(secondWordList);

        List<String> firstList = firstStringStream.collect(Collectors.toList());
        List<String> secondList = secondStringStream.collect(Collectors.toList());
        //判断是否有连续逗号
        boolean anyMatch = firstList.stream().anyMatch(e ->e=="")||secondList.stream().anyMatch(e ->e=="");
        if (anyMatch){
            throw new RuntimeException("input not valid");
        }

        //判断是否全是字母
        firstList.stream().forEach(str->{
            boolean temp = str.matches("^[a-zA-Z]*");
            if (!temp){
                throw  new RuntimeException("input not valid");
            }
        });

        secondList.stream().forEach(str->{
            boolean temp = str.matches("^[a-zA-Z]*");
            if (!temp){
                throw  new RuntimeException("input not valid");
            }
        });

        List<String> tempList = new ArrayList<>();

        firstList.stream().forEach(str1->{
            secondList.stream().forEach(str2->{
                if (str2.equals(str1)){
                    tempList.add(str2);
                }
            });
        });
        Stream<String> temp=tempList.stream().distinct().sorted();
        List<String> ans = temp.collect(Collectors.toList());
        int sizeAns=ans.size();
        for (int i = 0; i <sizeAns ; i++) {
            ans.set(i,ans.get(i).replace("", " ").trim());
        }
        return ans;
    }
}
