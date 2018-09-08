package gof.creational.factorymethod;

import java.util.ArrayList;
import java.util.List;

abstract class Page{  // abstract Product
  @Override
  public String toString(){ return this.getClass().getSimpleName(); }
}
class SkillsPage extends Page{}  // ConcreteProduct
class EducationPage extends Page{}  // ConcreteProduct
class ExperiencePage extends Page{}  // ConcreteProduct
class IntroductionPage extends Page{}  // ConcreteProduct
class ResultsPage extends Page{}  // ConcreteProduct
class ConclusionPage extends Page{}  // ConcreteProduct
class SummaryPage extends Page{}  // ConcreteProduct
class BibliographyPage extends Page{}  // ConcreteProduct
abstract class Document  // abstract Creator
{
  public Document(){ this.createPages(); }
  protected List<Page> pages;
  public abstract void createPages();
  @Override
  public String toString(){ return this.getClass().getSimpleName();}
}
class Resume extends Document{  // Concrete Creator
  @Override
  public void createPages() {
    this.pages = new ArrayList<Page>(){{
      add(new SkillsPage());
      add(new EducationPage());
      add(new ExperiencePage());
    }};
  }
}
class Report extends Document{  // Concrete Creator
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

public class RealWorld {
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
/*
Definition
Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets
a class defer instantiation to subclasses.

output:
Resume--
 SkillsPage
 EducationPage
 ExperiencePage

Report--
 IntroductionPage
 ResultsPage
 ConclusionPage
 SummaryPage
 BibliographyPage
 */
