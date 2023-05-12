# Report

## (1) Introduzione

## (2) Modello di Dominio

```mermaid
    
    classDiagram
        
        Giocatore "1" -- "0..*" Tentativo : Effettuare
        Giocatore "1" -- "0..*" Partita : Svolgere
        Giocatore -- Difficolta : Impostare

        Difficolta "1" -- "0..*" Partita : Influenzare
        Difficolta <|-- Facile
        Difficolta <|-- Medio
        Difficolta <|-- Difficile 

        Nave <|-- Cacciatorpediniere
        Nave <|-- Incrociata
        Nave <|-- Corazzata
        Nave <|-- Portaerea
        
        Tentativo <|-- Acqua
        Tentativo <|-- Colpo
        Tentativo <|-- Affondamento
        Tentativo "0..*" -- "1" Partita : Appartenere
        Tentativo "0..1" -- "1" Cella : Colpire

        Griglia *-- Cella
        Partita -- Griglia : Genera
        Cella "0..*" -- "0..1" Nave : Occupare
        Colpo "0..*" -- "1" Nave : Subire
        Affondamento "0..1" -- "1" Nave : Subire


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

