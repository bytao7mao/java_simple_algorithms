public class NormalizeText{
public static void main(String[] args) {
String schon = "schön";
String aeou = "áéőű";
CharSequence normalized_string = Normalizer.normalize(aeoul, Normalizer.Form.NFD);
System.out.println(((String) normalized_string).replaceAll("[^\\p{ASCII}]", ""));
}
}
