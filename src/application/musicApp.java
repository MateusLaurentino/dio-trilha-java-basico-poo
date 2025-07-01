package application;

import domain.models.playMusic;
import domain.models.playMedia;
import domain.services.music;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class musicApp implements music {
    private static final Scanner scanner = new Scanner(System.in);
    public static playMedia playingMusic;
    private static final List<playMusic> musics = Arrays.asList(
            new playMusic(1, "A Maior Honra", "Julliany Souza", 11.22D),
            new playMusic(2, "Deus é quem me fortalece", "Julliany Souza", 6.23D),
            new playMusic(3, "Escape", "Renascer Praise", 7.06D),
            new playMusic(4, "Gratidão", "Gabriel Brito", 5.37D),
            new playMusic(5, "Jesus Sempre Tem Mais", "Gabriel Brito", 4.32D),
            new playMusic(6, "Minha Calmaria", "Gabriel Brito", 3.39D),
            new playMusic(7, "Passa Lá em Casa Jesus", "Kailane Frauches", 4.54D),
            new playMusic(8, "Reascende a chama", "Sued Silva", 7.57D),
            new playMusic(9, "Simplesmente Sobrenatural", "Matheus Trindade", 4.28D)
    );

    @Override
    public void musicPlaying(){
        if(playingMusic == null){
            System.out.println("Nenhuma musica foi escolhida para reproduzir.\n");
            return;
        }
        var musicState = playingMusic.isPlaying() ? "reproduzindo" : "pausada";
        System.out.println("Musica atual - " + playingMusic.getName() + " esta " + musicState + "\n");
    }

    @Override
    public void findArtist() {
        System.out.println("Infome o nome do artista:");
        scanner.nextLine();
        var artistName = scanner.nextLine();

        var filterMusics = musics
                .stream().filter(y -> y.getArtist().toLowerCase().contains(artistName.toLowerCase()))
                .toList();

        if (filterMusics.isEmpty()) {
            System.out.println("Nenhum artista foi encontrado.");
            return;
        }

        selectMusic(filterMusics);
    }

    @Override
    public void playPause() {
        if (playingMusic == null) {
            System.out.println("Escolha uma musica para tocar.\n");
            return;
        }

        playingMusic.setPlaying();

        var statusMusic = playingMusic.isPlaying() ? "esta tocando" : "foi pausada";
        System.out.printf("A musica %s %s. \n\n", playingMusic.getName(), statusMusic);
    }

    @Override
    public void stop() {
        if (playingMusic == null || !playingMusic.isPlaying()) return;

        playingMusic.setPlaying();
        System.out.printf("A musica %s foi pausada. \n\n", playingMusic.getName());
    }

    @Override
    public void find() {
        var ordered = musics
                .stream()
                .sorted(Comparator.comparing(playMusic::getName, String.CASE_INSENSITIVE_ORDER))
                .toList();

        selectMusic(ordered);
    }

    @Override
    public void next() {
        if (playingMusic == null) return;

        Integer identifier = playingMusic.getIdentifierMusic() != musics.size() ? playingMusic.getIdentifierMusic() + 1 : 1;
        select(identifier.describeConstable());
    }

    @Override
    public void previous() {
        if (playingMusic == null) return;

        Integer identifier = playingMusic.getIdentifierMusic() != 1 ? playingMusic.getIdentifierMusic() - 1 : musics.size();
        select(identifier.describeConstable());
    }

    @Override
    public void select(Optional<Integer> identifier) {
        if (identifier.isEmpty()) {
            selectMusic(musics);
            return;
        }

        var music = musics.stream()
                .filter(y -> y.getIdentifier().equals(identifier.get()))
                .findFirst();

        if (music.isEmpty()) {
            System.out.println("Nenhuma musica foi selecionada.\n");
            return;
        }

        playingMusic = new playMedia(music.get());
        System.out.printf("\nReproduzindo a musica: %s\n\n", playingMusic.getName());
    }

    public void run() {
        var using = true;
        while (using) {
            System.out.println("1 - Musica tocando");
            System.out.println("2 - Buscar");
            System.out.println("3 - Buscar Artista");
            System.out.println("4 - Selecionar Musica");
            System.out.println("5 - Pausar/Reproduzir Musica");
            System.out.println("6 - Parar Musica");
            System.out.println("7 - Proxima Musica");
            System.out.println("8 - Musica Anterior");
            System.out.println("9 - Home");
            System.out.println("\nSelecione uma opção:");

            var option = scanner.nextInt();
            System.out.println();
            switch (option) {
                case 1 -> musicPlaying();
                case 2 -> find();
                case 3 -> findArtist();
                case 4 -> select(Optional.empty());
                case 5 -> playPause();
                case 6 -> stop();
                case 7 -> next();
                case 8 -> previous();
                case 9 -> using = false;
                default -> System.out.println("Opção inválida. \n\n");
            }
        }
    }

    private void selectMusic(List<playMusic> listMusic) {
        Optional<playMusic> music = Optional.empty();
        while (music.isEmpty()) {
            showMusics(listMusic);
            System.out.println("Informe o identificador ou 0 para voltar:");
            var identifier = scanner.nextInt();

            if (identifier == 0) {
                System.out.println("\nNenhuma musica foi selecionada.\n");
                break;
            }

            music = listMusic.stream()
                    .filter(y -> y.getIdentifier() == identifier)
                    .findFirst();

            System.out.println("\nA musica informada não existe.\n");
        }
        music.ifPresent(value -> select(value.getIdentifier().describeConstable()));
    }

    private void showMusics(List<playMusic> listMusic) {
        for (var music : listMusic) {
            System.out.printf(
                    "Identificador = %s, Nome = %s, Artista = %s \n",
                    music.getIdentifier(), music.getName(), music.getArtist()
            );
        }

        System.out.println();
    }
}
