# Report

## (1) Introduzione

## (2) Modello di Dominio

```mermaid
    
    classDiagram
        
        Partita -- Griglia : Genera
        Griglia *-- Cella
        Cella -- Nave : Occupa
        Tentativo -- Cella : Attacca


        Nave <|-- Cacciatorpediniere
        Nave <|-- Incrociata
        Nave <|-- Corazzata
        Nave <|-- Portaerea
        
        Tentativo <|-- Acqua
        Tentativo <|-- Colpo
        Tentativo <|-- Affondamento

        Acqua -- Partita
        Colpo -- Nave : Subisce
        Affondamento -- Nave : Subisce

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

