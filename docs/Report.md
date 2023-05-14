# Report

## (1) Introduzione

## (2) Modello di Dominio

### diagramma
```mermaid
    
    classDiagram
        direction LR
            
        Giocatore "1" -- "0..*" Tentativo : Effettua
        Giocatore "1" -- "0..*" Partita : Svolge
        Giocatore -- Difficolta : Imposta

        Difficolta "1" -- "0..*" Partita : Influenza
        Difficolta <|-- Facile
        Difficolta <|-- Medio
        Difficolta <|-- Difficile 

        Nave <|-- Cacciatorpediniere
        Nave <|-- Incrociatore
        Nave <|-- Corazzata
        Nave <|-- Portaerea
        
        Tentativo <|-- Acqua
        Tentativo <|-- Colpo
        Tentativo <|-- Affondamento
        Tentativo "0..*" -- "1" Partita : Appartiene
        Tentativo "0..1" -- "1" Cella : Colpisce

        Griglia *-- "*" Cella
        Partita "1" -- "1" Griglia : Genera
        Cella "0..*" -- "0..1" Nave : è occupata da
        Colpo "0..*" -- "1" Nave : è subito da
        Affondamento "0..1" -- "1" Nave : è subito da


        class Nave{
            dimensione
        }

        class Difficolta{
            maxFallibili
        }

        class Giocatore{
            nome
        }

        class Partita{
            numFalliti
        }

        class Cella{
            coordinate
        }

        class Griglia{
            dimensione
        }


```
### Glossario degli attributi
|Attributo|Concetto di appartenenza|Descrizione|
|-|-|-|
| coordinate | Cella | coppia che individua univocamente una Cella di una Griglia. |
| dimensione | Griglia | Numero di righe e di celle per riga. Per esempio, `dimensione=10` implica $10^2$ celle. |
| dimensione | Nave | Numero di celle che la nave occupa. |
| maxFallibili | Difficoltà | Numero massimo di tentativi fallibili. |
| nome | Giocatore | Nome del giocatore |
| numFalliti | Partita| Numero corrente di tentativi falliti; Si ottiene contando il numero di istanze di Acqua relative alla Partita. |

### Note
- (Definizione) Tra *Griglia* e *Cella* vi è una **composizione**:
    - Una *Griglia* è composta da più *Celle* (vedi molteplicità `*` nel diagramma)
    - **dipendenza esistenziale**: Una *Cella* esiste solo se parte di una *Griglia*
    - **esclusività**: Una *Cella* può appartenere ad una e una sola *Griglia*
- (Notazione) Si dice fallito un *Tentativo* di tipo *Acqua*.


## (3) Requisiti Specifici
### (3.1) Requisiti funzionali
- (RF1) Il sistema deve permettere di creare una nuova partita.
### (3.2) Requisiti non funzionali


## (7) Manuale Utente

## (9) Analisi Retrospettiva
### (9.1) Sprint 0

