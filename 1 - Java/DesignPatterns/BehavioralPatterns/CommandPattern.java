package DesignPatterns.BehavioralPatterns;

import java.net.CookieHandler;

// command interface
interface Command {
    void save();
}

// receiver the one who does the job
class Excel {
    void downloadExcel() {
        System.out.println("Excel file downloaded");
    }
}

class Pdf {
    void downloadPdf() {
        System.out.println("Pdf file downloaded");
    }
}

class Word {
    void downloadWord() {
        System.out.println("Word file downloaded");
    }
}

// concrete commands
class DownloadExcel implements Command {

    Excel excel;

    DownloadExcel(Excel excel) {
        this.excel = excel;
    }

    @Override
    public void save() {
        excel.downloadExcel();
    }
}

class DownloadPdf implements Command {

    Pdf pdf;

    DownloadPdf(Pdf pdf) {
        this.pdf = pdf;
    }

    @Override
    public void save() {
        pdf.downloadPdf();
    }
}

class DownloadWord implements Command {

    Word word;

    DownloadWord(Word word) {
        this.word = word;
    }

    @Override
    public void save() {
        word.downloadWord();
    }
}

// invoker
class Website {
    Command command;

    Website(Command command) {
        this.command = command;
    }

    void setCommand(Command command) {
        this.command = command;
    }

    void clickSaveButton() {
        command.save();
    }
}

public class CommandPattern {
    public static void main(String[] args) {

        Excel excel = new Excel();
        Pdf pdf = new Pdf();
        Word word = new Word();

        Command excelCommand = new DownloadExcel(excel);
        Command pdfCommand = new DownloadPdf(pdf);
        Command wordCommand = new DownloadWord(word);

        Website website = new Website(excelCommand);
        website.clickSaveButton();

        website.setCommand(pdfCommand);
        website.clickSaveButton();

        website.setCommand(wordCommand);
        website.clickSaveButton();
    }
}
