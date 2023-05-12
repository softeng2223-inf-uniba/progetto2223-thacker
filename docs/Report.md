# Report

## (1) Introduzione

## (2) Modello di Dominio

```mermaid
    
    classDiagram
        
        Giocatore "1" -- "0..*" Tentativo : Effettuare
        Giocatore "1" -- "0..*" Partita : Svolge
        Giocatore -- Difficolta : Imposta

        Partita -- Griglia : Genera
        Griglia *-- Cella
        Cella "0..*" -- "0..1" Nave : Occupa
        Tentativo "0..1" -- "1" Cella : Attacca


        Nave <|-- Cacciatorpediniere
        Nave <|-- Incrociata
        Nave <|-- Corazzata
        Nave <|-- Portaerea
        
        Tentativo <|-- Acqua
        Tentativo <|-- Colpo
        Tentativo <|-- Affondamento

        Acqua -- Partita
        Colpo "0..*" -- "1" Nave : Subire
        Affondamento "0..1" -- "1" Nave : Subire

        Difficolta -- Partita
        Difficolta <|-- Facile
        Difficolta <|-- Medio
        Difficolta <|-- Difficile

        class Nave{
            dimensione
        }

        class Difficolta{
            maxFallibili
        }


```

## (3) Requisiti Specifici
### (3.1) Requisiti funzionali
### (3.2) Requisiti non funzionali


## (7) Manuale Utente

## (9) Analisi Retrospettiva
### (9.1) Sprint 0

