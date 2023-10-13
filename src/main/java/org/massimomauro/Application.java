package org.massimomauro;


import org.apache.commons.io.FileUtils;
import org.massimomauro.entities.Book;
import org.massimomauro.entities.Magazine;
import org.massimomauro.entities.Periodicity;
import org.massimomauro.entities.ReadingElement;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {
        File file = new File("src/output.txt");
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
            System.out.println("3. Ricerca per ISBN");
            System.out.println("4. Ricerca per anno di pubblicazione");
            System.out.println("5. Ricerca per Autore");
            System.out.println("6. Salva catalogo");
            System.out.println("7. Carico catalogo salvato");
            try{
                menuChoise= Integer.parseInt(in.nextLine());
                if(menuChoise <0 || menuChoise>7) throw new RuntimeException("Seleziona il numero tra le voci");
                menuChoise:
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
                                            if(title.equals("")) throw new RuntimeException("titolo vuoto non è permesso");
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
                                    break;

                                }
                                case 2:{
                                    int ISBN;
                                    String title;
                                    int year;
                                    int pageNumber;
                                    String author;
                                    String type;
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
                                            if(title.equals("")) throw new RuntimeException("titolo vuoto non è permesso");
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
                                    author:
                                    while(true){
                                        System.out.println("Inserire nome autore");
                                        try {
                                            author= in.nextLine();
                                            if(author.equals("")) author="Anonimo";
                                            break author;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }
                                    type:
                                    while(true){
                                        System.out.println("Inserire categoria");
                                        try {
                                            type= in.nextLine();
                                            if(type.equals(""))throw new RuntimeException("categoria obbligatoria");
                                            break type;
                                        }catch (Exception ex){
                                            System.err.println(ex.getMessage());
                                        }
                                    }
                                    try{
                                        ReadingElement update = new Book(ISBN, title, pageNumber, year, author, type);
                                        catalog.add(update);
                                        catalogMap.put(update.getISBN(), update);
                                        System.out.println("elemento aggiunto con SUCCESSO!");
                                        break case1;
                                    }catch(Exception ex){
                                        System.err.println(ex.getMessage());
                                    }
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
                                int indice=-1;
                                ISBN= Integer.parseInt(in.nextLine());
                                catalogMap.remove(ISBN);

                                for (int i = 0; i < catalog.size(); i++) {
                                    if (catalog.get(i).getISBN()==ISBN) {
                                        indice = i;
                                        break; // Esci dal ciclo una volta trovato l'elemento
                                    }
                                }
                                if(indice != -1){
                                    catalog.remove(indice);
                                }else{
                                    throw new RuntimeException("non trovato nel catalogo");
                                }
                                System.out.println("elemento rimosso con successo");

                                break;
                            }catch (Exception ex){
                                System.err.println(ex.getMessage());
                            }
                        }
                        break;
                    }
                    case 3:{
                        printCatalog(catalogMap);
                        while (true){
                            int ISBN;
                            System.out.println("Inserire codice identifacativo");
                            System.out.println("digita 0 per tornare al menù...");
                            try{
                                ISBN= Integer.parseInt(in.nextLine());
                                if(ISBN==0) break;
                                else if (catalogMap.get(ISBN) != null) {
                                    System.out.println(catalogMap.get(ISBN));
                                }else{
                                    throw new RuntimeException("Nessun elemento trovato!");
                                }
                            }catch (Exception ex){
                                System.err.println(ex.getMessage());
                            }
                        }
                        break;
                    }
                    case 4:{
                        Map <Integer, List<ReadingElement>> catalogMapForYear= catalog.stream().collect(Collectors.groupingBy(ReadingElement::getYear));
                        catalogMapForYear.forEach((k, v)->{
                            System.out.println(k+":"+v);
                        });
                         while (true){
                             int year;
                             System.out.println("Inserire anno di pubblicazione");
                             System.out.println("digita 0 per tornare al menù...");
                             try{
                                 year= Integer.parseInt(in.nextLine());
                                 if(year==0) break;
                                 else if (catalogMapForYear.get(year) != null) {
                                    System.out.println(catalogMapForYear.get(year));
                                 }else{
                                     throw new RuntimeException("Nessun elemento trovato!");
                                 }
                             }catch (Exception ex){
                                 System.err.println(ex.getMessage());
                             }
                         }
                        break;
                    }
                    case 5:{
                        List<Book> bookReadingElement;
                        bookReadingElement = catalog.stream().filter(element->element instanceof Book)
                                .map(element -> new Book(element.getISBN(), element.getTitle(), element.getPageNumber(), element.getYear(), ((Book) element).getAuthor(), ((Book) element).getType())).toList();
                        Map<String, List<Book>> catalogMapForAuthor=bookReadingElement.stream().collect(Collectors.groupingBy(Book::getAuthor));
                        catalogMapForAuthor.forEach((k, v)->{
                            System.out.println(k+":"+v);
                        });
                        while (true){
                            String author;
                            System.out.println("Inserire autore che stai cercando:");
                            System.out.println("digita 'esc' per tornare al menù...");
                            try{
                                author= in.nextLine();
                                if(author.equals("esc")) break;
                                else if (catalogMapForAuthor.get(author) != null) {
                                    System.out.println(catalogMapForAuthor.get(author));
                                }else{
                                    throw new RuntimeException("Nessun elemento trovato!");
                                }
                            }catch (Exception ex){
                                System.err.println(ex.getMessage());
                            }
                        }
                        break;
                    }
                    case 6:{
                        StringBuilder sb = new StringBuilder();
                        for (ReadingElement obj : catalog) {
                            sb.append(obj.toString()).append(System.lineSeparator());
                        }


                        try {

                            FileUtils.writeStringToFile(file, sb.toString(), StandardCharsets.UTF_8, false);

                            System.out.println("Lista di oggetti scritta nel file: " + file.getAbsolutePath());

                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                        break;
                    }
                    case 7:{

                       try{
                           List<ReadingElement> tmp = new ArrayList<>();
                           String lines= FileUtils.readFileToString(file, StandardCharsets.UTF_8);
                           String[] line = lines.split(System.lineSeparator());
                           for (int i = 0; i < line.length ; i++) {
                                line[i]=line[i].trim();
                                if(line[i].startsWith("Book")){
                                   Book book = parseBook(line[i]);
                                   tmp.add(book);
                                }else if (line[i].startsWith("Magazine")) {
                                    Magazine magazine = parseMagazine(line[i]);
                                    tmp.add(magazine);

                                }
                           }
                           System.out.println("caricato con successo");
                                catalog.clear();
                                catalog.addAll(tmp);
                       }catch (IOException e){
                           System.err.println(e.getMessage());
                       }

                        break;
                    }

                }
            }
            catch (Exception ex){
                System.err.println(ex.getMessage());
            }


        }
        in.close();
    }

    public static void printCatalog(Map<Integer, ReadingElement> paper){
        paper.forEach((k, v)->{
            System.out.println(k+":"+v);
        });
    }

    public static Book parseBook(String line) {

        line = line.substring(5, line.length() - 1);
        String[] fields = line.split(", ");


        int ISBN = Integer.parseInt(fields[0].split("=")[1]);
        String title = fields[1].split("=")[1];
        int year = Integer.parseInt(fields[2].split("=")[1]);
        int pageNumber = Integer.parseInt(fields[3].split("=")[1]);
        String author = fields[4].split("=")[1];
        String type = fields[5].split("=")[1];


        return new Book(ISBN, title, year, pageNumber, author, type);
    }
    private static Magazine parseMagazine(String line) {

        line = line.substring(9, line.length() - 1);
        String[] fields = line.split(", ");


        int ISBN = Integer.parseInt(fields[0].split("=")[1]);
        String title = fields[1].split("=")[1];
        int year = Integer.parseInt(fields[2].split("=")[1]);
        int pageNumber = Integer.parseInt(fields[3].split("=")[1]);
        Periodicity periodicity = Periodicity.valueOf(fields[4].split("=")[1]);


        return new Magazine(ISBN, title, year, pageNumber, periodicity);
    }

}