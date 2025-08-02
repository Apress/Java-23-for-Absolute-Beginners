
void main(String... args) {
    if (args.length > 0) {
        System.out.print("Practice03: Hello " + args[0] + "\n");
    } else {
        System.out.println("Practice03: Hello " + staticVariable);
    }
}

/* static variable */
static String staticVariable = "World";