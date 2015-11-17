package com.company.app;
import java.util.ArrayList;
import java.util.List;
public class App
{
    static abstract class Page{  // abstract Product
        @Override
        public String toString(){ return this.getClass().getSimpleName(); }
    }
    static class SkillsPage extends Page{}  // ConcreteProduct
    static class EducationPage extends Page{}  // ConcreteProduct
    static class ExperiencePage extends Page{}  // ConcreteProduct
    static class IntroductionPage extends Page{}  // ConcreteProduct
    static class ResultsPage extends Page{}  // ConcreteProduct
    static class ConclusionPage extends Page{}  // ConcreteProduct
    static class SummaryPage extends Page{}  // ConcreteProduct
    static class BibliographyPage extends Page{}  // ConcreteProduct
    static abstract class Document  // abstract Creator
    {
        public Document(){ this.createPages(); }
        protected List<Page> pages;
        public abstract void createPages();
        @Override
        public String toString(){ return this.getClass().getSimpleName();}
    }
    static class Resume extends Document{  // Concrete Creator
        @Override
        public void createPages() {
            this.pages = new ArrayList<Page>(){{
                add(new SkillsPage());
                add(new EducationPage());
                add(new ExperiencePage());
            }};
        }
    }
    static class Report extends Document{  // Concrete Creator
        @Override
        public void createPages() {
            this.pages = new ArrayList<Page>();
            this.pages.add(new IntroductionPage());
            this.pages.add(new ResultsPage());
            this.pages.add(new ConclusionPage());
            this.pages.add(new SummaryPage());
            this.pages.add(new BibliographyPage());
        }
    }
    public static void main( String[] args )
    {
        List<Document> documents = new ArrayList<Document>();
        documents.add(new Resume());
        documents.add(new Report());
        for(Document document : documents){
            System.out.println(document + "--");
            for(Page page : document.pages){
                System.out.println(" " + page);
            }
            System.out.println("");
        }
    }
}
