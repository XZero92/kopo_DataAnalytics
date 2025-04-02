package strings;

public class StringBuilderEx {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("Java Programming");
        System.out.println(sb);

        sb.insert(5, "and Python Programming");
        System.out.println(sb);

        sb.delete(17, 28);
        System.out.println(sb);

        sb.replace(9, 15, "C++");
        System.out.println(sb.toString());

        StringBuilder sb2 = new StringBuilder();
        sb2.append("DEF").insert(0, "ABC").delete(3, 4);
        System.out.println(sb2);
    }

}
