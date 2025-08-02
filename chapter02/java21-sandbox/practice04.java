
void main(String... args) {
    if (args.length > 0) {
        System.out.print("Practice04: Hello " + args[0] + "\n");
    } else {
        System.out.println("Practice04: Hello " + staticMethod());
    }
}

/* static method */
static String staticMethod(){
    return "World";
}