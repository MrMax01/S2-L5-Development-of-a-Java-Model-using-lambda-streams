import entities.Book;
import entities.Magazine;
import entities.Periodicity;
import entities.ReadingElement;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<ReadingElement> catalog = new ArrayList<>();
        catalog.add(new Book(1, "Lord Of Rings", 300, 1994, "Tolkien", "fantasy"));
        catalog.add(new Book(2, "Harry Potter", 250, 1997, "Rowling", "fantasy"));
        catalog.add(new Book(3, "Moby Dick ", 400, 1851, "Rowling", "avventura"));
        catalog.add(new Magazine(4, "focus", 20, 1851, Periodicity.SETTIMANALE));
        catalog.add(new Magazine(5, "Panorama", 10, 1851, Periodicity.SETTIMANALE));
        Map<Integer, ReadingElement> catalogMap = catalog.stream()
                .collect(Collectors.toMap(ReadingElement::getISBN, element -> element));
        //printCatalog(catalogMap);
        boolean flag=true;
        Scanner in = new Scanner(System.in);
        int menuChoise;
        while (flag){
            System.out.println("");
            System.out.println("Selezionare una delle opzioni");
            System.out.println("0. Esci dall'applicazione.");
            System.out.println("1. Aggiungi elemento al catalogo.");
            System.out.println("2. Rimuovere un elemento catalogo ");
            System.out.println("3. Rimuovere un elemento catalogo ");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per Autore");
            System.out.println("6. Scarica catalogo");
            try{
                menuChoise= Integer.parseInt(in.nextLine());
                if(menuChoise <0 || menuChoise>6) throw new RuntimeException("Seleziona il numero tra le voci");
                switch (menuChoise){
                    case 0:{
                        System.out.println("USCITO DALL'APPLICAZIONE CON SUCCESSO!");
                        flag=false;
                        break;
                    }
                    case 1:{
                        case1:
                        while(true){
                            int typeCatalog;
                            System.out.println("scegli tipo di catalogo");
                            System.out.println("1. Magazine");
                            System.out.println("2. Book");
                            typeCatalog= Integer.parseInt(in.nextLine());
                            switch(typeCatalog){
                                case 1:{
                                    int ISBN;
                                    String title;
                                    int year;
                                    int pageNumber;
                                    Periodicity periodicity = null;
                                    int periodicityChoise;

                                    ISBN:
                                    while(true){
                                        System.out.println("Inserire codice identificativo intero");
                                        try{
                                            ISBN= Integer.parseInt(in.nextLine());
                                            for (Integer key: catalogMap.keySet()) {
                                                if(key==ISBN) throw new RuntimeException("Codice identificativo esistente");
                                            }
                                            break ISBN;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }
                                    title:
                                    while(true){
                                        System.out.println("Inserire title");
                                        try {
                                            title= in.nextLine();
                                            break title;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }
                                    year:
                                    while(true){
                                        System.out.println("Inserire anno di pubblicazione");
                                        try{
                                            LocalDate today = LocalDate.now();
                                            year = Integer.parseInt(in.nextLine());
                                            if(year > today.getYear() ) throw  new RuntimeException("Il libro non può essere pubblicato nel futuro");
                                            break year;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }

                                    pageNumber:
                                    while(true){
                                        System.out.println("Inserire numero di pagine");
                                        try {
                                            pageNumber= Integer.parseInt(in.nextLine());
                                            if(pageNumber==0) throw  new RuntimeException("pagine minime consetite 1");
                                            break pageNumber;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }
                                    periodicity:
                                    while(true){
                                        System.out.println("Scegliere un opzione:");
                                        System.out.println("1. SETTIMANALE");
                                        System.out.println("2. MENSILE");
                                        System.out.println("3. SEMESTRALE");
                                        try{
                                            periodicityChoise = Integer.parseInt(in.nextLine());
                                            if(periodicityChoise < 1 && periodicityChoise > 3) throw  new RuntimeException("Scegli tra quelli mostrati");
                                            switch (periodicityChoise){
                                                case 1:{
                                                    periodicity= Periodicity.SETTIMANALE;
                                                    break;
                                                }
                                                case 2:{
                                                    periodicity = Periodicity.MENSILE;
                                                    break;
                                                }
                                                case 3:{
                                                    periodicity= Periodicity.SEMESTRALE;
                                                    break;
                                                }
                                            }
                                            break periodicity;
                                        }catch(Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }

                                    try{
                                        ReadingElement update = new Magazine(ISBN, title, pageNumber, year, periodicity);
                                        catalog.add(update);
                                        catalogMap.put(update.getISBN(), update);
                                        System.out.println("elemento aggiunto con SUCCESSO!");
                                        break case1;
                                    }catch(Exception ex){
                                        System.err.println(ex.getMessage());
                                    }


                                }
                                case 2:{
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    case 2:{
                        printCatalog(catalogMap);
                        int ISBN;
                        while(true){
                            System.out.println("Inserire chiave identificativa");
                            try{
                                ISBN= Integer.parseInt(in.nextLine());
                                catalogMap.remove(ISBN);
                                System.out.println("elemento rimosso con successo");
                                break;
                            }catch (Exception ex){
                                System.err.println(ex.getMessage());
                            }
                        }
                        break;
                    }
                    case 3:{
                        break;
                    }
                    case 4:{
                        break;
                    }
                    case 5:{
                        break;
                    }
                    case 6:{
                        break;
                    }

                }
            }catch (Exception ex){
                System.err.println(ex.getMessage());
            }


        }

    }

    public static void printCatalog(Map<Integer, ReadingElement> paper){
        paper.forEach((k, v)->{
            System.out.println(k+":"+v);
        });
    }

}
