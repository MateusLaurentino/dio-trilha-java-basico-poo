package interfaces;

import application.musicApp;
import domain.models.playMedia;

import java.util.Scanner;

public class iPhone {
    private static playMedia playingMusic;
    private static boolean inCall = false;

    public static void main(String[] args) {
        System.out.println("Inicializando...\n");
        System.out.println("Seja Bem Vindo\n");

        var scanner = new Scanner(System.in);
        var using = true;
        while (using){
            System.out.println("Aplicativos:");
            System.out.println("1 - Player");
            System.out.println("2 - Phone");
            System.out.println("3 - Internet");
            System.out.println("4 - Desligar");

            System.out.println("\nSelecione um aplicativo:");
            var option = scanner.nextInt();

            switch (option){
                case 1 -> player();
                case 2 -> phone();
                case 3 -> internet();
                case 4 -> using = false;
                default -> System.out.println("Opção informada é inválida.");
            }

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }

        scanner.close();
    }

    public static void player(){
        if (inCall){
            System.out.println("Não é possivel reproduzir musica enquanto estiver em uma ligação.");
            return;
        }

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        playingMusic = new musicApp(playingMusic).run();
    }

    private static void phone(){

    }

    private static void internet(){

    }
}
