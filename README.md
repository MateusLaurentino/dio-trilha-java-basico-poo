# POO - Desafio

### Neste desafio, o intuito era implementar tudo oque foi aprendido

Objetivos de implementação:
- Programmação Orientada a Objetos (POO)
- Interfaces
- Listas

---
### Diagrama UML Estruturado do Projeto iPhone

```mermaid
classDiagram
    class phone {
        call(Optional<contact> contact)
        endCall()
        voiceMail()
        history()
        contacts()
        addContact()
        deleteContact(contact contact)
        editContact(contact contact) contact
        run()
    }

    class music {
        musicPlaying()
        findArtist()
        playPause()
        stop()
        find()
        next()
        previous()
        select(Optional<Integer> identifier)
        run()
    }

    class internet {
        page()
        go()
        reload()
        next()
        before()
        close()
        run()
    }

    class phoneApp
    phoneApp implements phone

    class musicApp
    musicApp implements music

    class internetApp
    internetApp implements internet

    class iPhone {
        phoneApp phone
        musicApp music
        internetApp internet
        main(String[] args)
        phone()
        player()
        internet()
    }

    iPhone --> phoneApp
    iPhone --> musicApp
    iPhone --> internetApp

    phoneApp ..|> phone
    musicApp ..|> music
    internetApp ..|> internet
