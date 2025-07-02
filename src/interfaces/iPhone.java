package interfaces;

import application.*;
import domain.models.playMedia;

import java.util.Scanner;

public class iPhone {
    private static phoneApp phone = new phoneApp();
    private static musicApp music = new musicApp();
    private static internetApp internet = new internetApp();

    public static void main(String[] args) {
        System.out.println("Inicializando...\n");
        System.out.println("Seja Bem Vindo\n");

        var scanner = new Scanner(System.in);
        var using = true;
        while (using){
            System.out.println("Aplicativos:\n");
            System.out.println("1 - Player");
            System.out.println("2 - Phone");
            System.out.println("3 - Internet");
            System.out.println("4 - Desligar");

            System.out.println("Selecione um aplicativo:");
            var option = scanner.nextInt();
            System.out.println();

            switch (option){
                case 1 -> player();
                case 2 -> phone();
                case 3 -> internet();
                case 4 -> using = false;
                default -> System.out.println("Opção informada é inválida.");
            }

            System.out.println("\n");
        }

        scanner.close();
    }

    public static void player(){
        if (phoneApp.inCall != null && phoneApp.inCall.isInCall()){
            System.out.println("Não é possivel reproduzir musica enquanto estiver em uma ligação.");
            return;
        }

        music.run();
    }

    private static void phone(){
        phone.run();
    }

    private static void internet(){
        internet.run();
    }
}
