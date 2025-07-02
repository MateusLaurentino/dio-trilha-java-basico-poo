package application;

import domain.models.*;
import domain.services.phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class phoneApp implements phone {
    private final Scanner scanner = new Scanner(System.in);
    private static final List<contact> contacts = new ArrayList<>();
    private static final List<historyCall> callHistory = new ArrayList<>();
    public static call inCall = new call();

    private static final List<voiceMail> voiceMails = new ArrayList<>(Arrays.asList(
            new voiceMail(48901020304L, "Me liga, preciso de ajuda."),
            new voiceMail(48910908046L, "A pensão está atrasada."),
            new voiceMail(48987987923L, "Seu carro estragou o motor."),
            new voiceMail(48998349720L, "A policia levou sua moto pai."),
            new voiceMail(48942098098L, "Estou gravida. Me liga")
    ));

    @Override
    public void call(Optional<contact> contact) {
        if (inCall.isInCall()){
            System.out.println("Uma chamada ja esta em andamento.");
            return;
        }

        if (musicApp.playingMusic != null && musicApp.playingMusic.isPlaying()){
            musicApp.playingMusic.setPlaying();
        }

        if (contact.isEmpty()){
            System.out.println("Informe o numero:");
            var number = scanner.nextLong();
            scanner.nextLine();

            contact = Optional.of(contacts.stream()
                    .filter(x -> x.getNumber() == number)
                    .findFirst()
                    .orElseGet(() -> new contact(Long.toString(number), number)));
        }

        callHistory.add(new historyCall(contact.get()));
        inCall.setInfoContacts(contact.get());
        System.out.println("Chamando...\n");
    }

    @Override
    public void endCall() {
        callHistory.add(new historyCall(inCall.getInfoContacts()));
        inCall.endCall();
        System.out.println("Ligação encerrada.\n");
    }

    @Override
    public void voiceMail() {
        while (true){
            System.out.println();
            for (int i = 1; i <= voiceMails.size(); i++) {
                var voice = voiceMails.get(i -1);
                System.out.printf("%s - %s\n", i, voice.getNumber());
            }

            System.out.printf("%s - %s\n", voiceMails.size() + 1, "Sair");
            System.out.println("Selecione uma mensagem:");
            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == voiceMails.size() + 1) break;
            else if (option <= 0 || option > voiceMails.size() + 1)
                System.out.println("Opção informada é inválida.");
            else showOptionsVoiceMail(voiceMails.get(option - 1));
        }
    }

    @Override
    public void history() {
        while (true){
            for (int i = 1; i <= callHistory.size(); i++) {
                var call = callHistory.get(i - 1);
                System.out.printf("%s - %s (%s) - %s \n", i, call.getName(), call.getNumber(), call.getDate());
            }

            System.out.println("Selecione uma chamada ou 0 para sair.");
            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == 0)
                break;
            if (option > callHistory.size())
                System.out.println("Chamada selecionada é inválida.\n");
            else{
                while (true){
                    var call = callHistory.get(option - 1);
                    System.out.printf("%s (%s) - %s \n", call.getName(), call.getNumber(), call.getDate());
                    System.out.println("1 - Ligar");
                    System.out.println("2 - Remover");
                    System.out.println("Selecione uma opção ou 0 para sair:");

                    var action = scanner.nextInt();
                    scanner.nextLine();
                    if (action == 0)
                        break;
                    else if (action == 1){
                        call(Optional.of(call));
                        showOptionsOnCall();
                        break;
                    }
                    else if (action == 2){
                        callHistory.remove(option - 1);
                        System.out.println("Chamada removida do histórico.\n");
                        break;
                    }
                    else System.out.println("Opção informada é inválida.\n");

                }
            }
        }
    }

    @Override
    public void contacts() {
        while (true){
            System.out.println("0 - Adicionar contato");
            for (int i = 1; i <= contacts.size(); i++) {
                var contact = contacts.get(i - 1);
                System.out.printf("%s - %s (%s)\n", i, contact.getName(), contact.getNumber());
            }
            System.out.printf("%s - Voltar\n", contacts.size() + 1);
            System.out.println("Selecione uma opção:");
            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == 0)
                addContact();
            else if (option == contacts.size() + 1)
                break;
            else if (option > 0 && option <= contacts.size())
                showOptionsContact(contacts.get(option - 1));
            else
                System.out.println("Opção informada é inválida.\n");

        }
    }

    @Override
    public void addContact() {
        System.out.println("Informe o numero:");
        var number = scanner.nextLong();
        scanner.nextLine();

        System.out.println("\nInforme o nome:");
        var name = scanner.nextLine();

        contacts.add(new contact(name, number));
        System.out.println("Contato adicionado com sucesso.\n");
    }

    @Override
    public void deleteContact(contact contact) {
        contacts.remove(contact);
        System.out.println("Contato removido com sucesso.");
    }

    @Override
    public contact editContact(contact contact) {
        contacts.remove(contact);

        System.out.println("Informe o numero:");
        var number = scanner.nextLong();
        scanner.nextLine();

        System.out.println("\nInforme o nome:");
        var name = scanner.nextLine();

        if(number > 0 && name.isBlank()){
            System.out.println("Erro ao editar contato.");
            return  contact;
        }
        else contact = new contact(name, number);

        contacts.add(contact);
        return contact;
    }

    @Override
    public void run() {
        if(inCall != null && inCall.isInCall()) showOptionsOnCall();
        else showOptionsOutsideCall();
    }

    private void showOptionsOnCall() {
        while (true) {
            System.out.println("1 - Encerrar chamada.");
            System.out.println("2 - Home");
            System.out.println("\nSelecione uma opção");
            var option = scanner.nextInt();
            scanner.nextLine();

            if(option == 1){
                endCall();
                break;
            }
            else if (option == 2){
                break;
            }
            else System.out.println("Opção informada é inválida.\n");
        }

        if(!inCall.isInCall())
            showOptionsOutsideCall();
    }

    private void showOptionsOutsideCall() {
        while (true) {
            System.out.println("1 - Ligar");
            System.out.println("2 - Correio de voz");
            System.out.println("3 - Contatos");
            System.out.println("4 - Historico");
            System.out.println("5 - Home");
            System.out.println("Selecione uma opção");

            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1){
                call(Optional.empty());
                break;
            }

            if(option == 5)
                break;

            switch (option) {
                case 2 -> voiceMail();
                case 3 -> contacts();
                case 4 -> history();
                default -> System.out.println("Opçao informada é invalida.\n");
            }
        }

        if(inCall.isInCall())
            showOptionsOnCall();
    }

    private void showOptionsContact(contact contact){
        while (true){
            System.out.printf("Nome = %s, Numero = %s \n", contact.getName(), contact.getNumber());
            System.out.println("1 - Ligar");
            System.out.println("2 - Editar");
            System.out.println("3 - Remover");
            System.out.println("4 - Voltar");
            System.out.println("Selecione uma opção:");
            var option = scanner.nextInt();
            scanner.nextLine();

            if(option == 1){
                call(Optional.of(contact));
                showOptionsOnCall();
                break;
            }
            else if (option == 2){
                contact = editContact(contact);
            }
            else if (option == 3) {
                deleteContact(contact);
                break;
            }
            else if (option == 4) {
                break;
            }
            else System.out.println("Opção inválida.");
        }
    }

    private void showOptionsVoiceMail(voiceMail voiceMail){
        var remove = false;
        while (true) {
            System.out.println();
            System.out.println("1 - Sair");
            System.out.println("2 - Ouvir");
            System.out.println("3 - Deletar");
            System.out.println("Selecione uma opção.");

            var option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1){
                if(remove)voiceMails.remove(voiceMail);
                break;
            }
            else if (option == 2) {
                System.out.println(voiceMail.getMessage());
            }
            else if (option == 3) {
                voiceMails.remove(voiceMail);
                break;
            }
            else System.out.println("Opção informada é inválida.\n");

            remove = option == 2;
        }
    }
}
