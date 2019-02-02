# Transaction object vs no Transaction object

## no transaction object

1,
class TeamMember {
  int id;
  String name;
  List<Document> documents;
  void nominate(Document document);
}

2,
class Document {
  int id;
  String title;
}

### Database

Document table
Document(111, title1)
Document(112, title2)

TeamMember table
TeamMember(111, Tom, [Document(111), Document(112)])

## Transaction object

