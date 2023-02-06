package com.homedepot.om.student;

public class ASCIIArt {
    public static void displayArt(String heroClass){
        switch (heroClass){
            case "mage":
                displayMage();
                break;
            case "warlock":
                displayWarlock();
                break;
            case "hunter":
                displayHunter();
                break;
            case "rogue":
                displayRogue();
                break;
            case "warrior":
                displayWarrior();
                break;
            case "paladin":
                displayPaladin();
                break;
            default:
                displayDefault();
                break;
        }
    }
    public static void displayMage() {
        System.out.println("\u001b[38;5;27m                                                   ,--.");
        System.out.println("                                                  /    |");
        System.out.println("                                                 /     |");
        System.out.println("                                           ____,'      |");
        System.out.println("                                        <-'            :");
        System.out.println("                                         `-.__..--'``-,_\\_");
        System.out.println("\u001b[38;5;180m                                            |\u001b[38;5;189mo\u001b[38;5;180m/ \u001b[38;5;189m<o>\u001b[38;5;180m` :,.)_`>");
        System.out.println("\u001b[38;5;180m                                            :/ `     ||/)");
        System.out.println("\u001b[38;5;180m                                            (_.).__,-` |\\");
        System.out.println("\u001b[38;5;189m                                            /( `.``   `\u001b[38;5;180m| :");
        System.out.println("\u001b[38;5;189m                                            \\'`-.)  ` \u001b[38;5;180m ; ;\\\u001b[0m");
    }

    public static void displayWarrior() {
        System.out.println("\u001b[38;5;36m");
        System.out.println("                                           ,,\\\\^//,,");
        System.out.println("                                          {<<< ^ >>>}");
        System.out.println("                                        |\\{ \u001b[38;5;189mO\u001b[38;5;36m  |  \u001b[38;5;189mO\u001b[38;5;36m }/|");
        System.out.println("                                        \\ \\  (x x)  / /");
        System.out.println("                                         \\/\\   =   /\\/");
        System.out.println("                                           |_\u001b[38;5;189mV^^^^\u001b[38;5;36m_|");
        System.out.println("                                             \\_|_/ ");
        System.out.println("\u001b[0m");
    }

    public static void displayWarlock() {
        System.out.println("\u001b[38;5;1m");
        System.out.println("                                            /+XX+\\    ");
        System.out.println("                                           /+X++X+\\");
        System.out.println("                                          /XXX--XXX\\");
        System.out.println("                                         /--     -- \\");
        System.out.println("\u001b[38;5;180m                                       @)\u001b[38;5;189mo\u001b[38;5;180m  (,,)   \u001b[38;5;189mo\u001b[38;5;180m(@");
        System.out.println("\u001b[38;5;180m                                       }} (v-----v) {{");
        System.out.println("\u001b[38;5;189m                                          ((((-))))");
        System.out.println("\u001b[0m");
    }
    public static void displayPaladin() {
        System.out.println("\u001b[38;5;69m");
        System.out.println("                                           /-/-/|^|\\-\\-\\    ");
        System.out.println("                                           // /     \\ \\\\");
        System.out.println("                                         ^{-\u001b[38;5;189mO\u001b[38;5;69m\\\\  ^  //\u001b[38;5;189mO\u001b[38;5;69m-}^");
        System.out.println("                                         \\))  \\ |U| /  ((/");
        System.out.println("                                           { ||     || }");
        System.out.println("                                          { +\u001b[38;5;189mVUUUUUUUV\u001b[38;5;69m+ }");
        System.out.println("                                            {__|_|_|__}");
        System.out.println("\u001b[0m");
    }
    public static void displayRogue() {
        System.out.println("\u001b[38;5;70m");
        System.out.println("                                            ||\\+/||    ");
        System.out.println("                                          { __   __ }");
        System.out.println("                                        /\\   \u001b[38;5;189mO\u001b[38;5;70m   \u001b[38;5;189mO\u001b[38;5;70m   /\\");
        System.out.println("                                        \\ \\ =(   )= / /");
        System.out.println("                                         \\/,   o   ,\\/");
        System.out.println("                                            \\=====/");
        System.out.println("                                         \u001b[38;5;250m+\u001b[38;5;70m___|   |___\u001b[38;5;250m+\u001b[38;5;70m ");
        System.out.println("\u001b[0m");
    }
    public static void displayHunter() {
        System.out.println("\u001b[38;5;127m");
        System.out.println("\u001b[38;5;54m                                          \\\\\\\\\\-/////    ");
        System.out.println("                                          \u001b[38;5;99m|\u001b[38;5;12m====*====\u001b[38;5;99m|");
        System.out.println("                                        |\\[ \u001b[38;5;189mO\u001b[38;5;99m     \u001b[38;5;189mO\u001b[38;5;99m ]/|");
        System.out.println("                                         \\|    )    |/");
        System.out.println("                                           |       |*");
        System.out.println("                                            | =_= |");
        System.out.println("                                             \\\\^//");
        System.out.println("\u001b[0m");
    }
    public static void displayDefault() {
        System.out.println("\u001b[38;5;$180m");
        System.out.println("                                         /= = = = = = \\");
        System.out.println("                                        /~~~~~~~~~~~~~~\\");
        System.out.println("                                       (____=      =____)");
        System.out.println("                                           | \u001b[38;5;189mo\u001b[38;5;$180m   \u001b[38;5;189mo\u001b[38;5;$180m |");
        System.out.println("                                           | (---) |");
        System.out.println("                                           |= = = =|");
        System.out.println("                                           |  ---  |");
        System.out.println("\u001b[0m");
    }
}
