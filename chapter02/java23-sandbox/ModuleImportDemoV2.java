/**
 * @author iuliana.cosmina on 03/09/2024
 * https://openjdk.org/jeps/477
 * Run with:
 * > cd $workspace/chapter02/java23-sandbox
 * >  java --enable-preview --source 23 ModuleImportDemoV2.java
 */
void main(){
    String[] singers = new String[] { "john mayer", "danny o'donoghue", "nina simone" };
    Map<String, String> m =
            Stream.of(singers)
                    .collect(Collectors.toMap(s -> s.toUpperCase().substring(0,1),
                            Function.identity()));
    println(m);
}