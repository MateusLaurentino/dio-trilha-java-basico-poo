package application;

import domain.models.internetDetail;
import domain.services.internet;

import java.util.Scanner;

public class internetApp implements internet {
    private final Scanner scanner = new Scanner(System.in);
    private static internetDetail internetDetail;

    @Override
    public void page(){
        System.out.printf("Pagina atual %s\n\n", internetDetail.getUrl());
    }

    @Override
    public void go() {
        System.out.println("Informe o link");
        var link = scanner.nextLine();

        if(internetDetail == null)
            internetDetail = new internetDetail();

        internetDetail.setUrl(link);
        System.out.println();
    }

    @Override
    public void reload() {
        System.out.println("Carregando site " + internetDetail.getUrl());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace(System.out);
        }

        System.out.println("Pagina carregada.\n");
    }

    @Override
    public void next() {
        internetDetail.next();
    }

    @Override
    public void before() {
        internetDetail.before();
    }

    @Override
    public void close() {
        internetDetail = null;
    }

    @Override
    public void run() {
        if(internetDetail == null)
            go();

        while (true){
            System.out.println("1 - Pagina Atual");
            System.out.println("2 - Acessar");
            System.out.println("3 - Recarregar");
            System.out.println("4 - Close");

            if (internetDetail.hasBefore() && internetDetail.hasNext()){
                System.out.println("5 - Anterior");
                System.out.println("6 - Proxima");
            }
            else if(internetDetail.hasBefore())
                System.out.println("5 - Anterior");
            else if(internetDetail.hasNext())
                System.out.println("5 - Proxima");

            System.out.println("Selecione uma opção:");
            var option = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            if(option == 1) page();
            else if(option == 2) go();
            else if(option == 3) reload();
            else if(option == 4){
                close();
                break;
            }
            else if (internetDetail.hasBefore() && internetDetail.hasNext() && (option == 5 || option == 6)){
                if(option == 5) before();
                else next();
            }
            else if (internetDetail.hasBefore() && option == 5) before();
            else if (internetDetail.hasNext() && option == 5) next();
            else System.out.println("Opção inválida.");
        }
    }
}
