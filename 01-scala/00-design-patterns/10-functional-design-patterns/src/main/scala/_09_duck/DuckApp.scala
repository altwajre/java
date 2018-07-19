package _09_duck

import java.util.StringTokenizer

/*
Dock typing (structural typing)
https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/3d671d38-ffb8-4a93-91d1-72e85b1cd118.xhtml

Example:
https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/daa58fd6-466b-4304-8705-c1bc46b84c84.xhtml

Minimize duplicate code

Dock typing is a term that comes from dynamic languages and it allows us to treat different types of objects
in a similar manner based on a common method they have.

Duck typing
- SentenceParserTokenize.parse and SentenceParserSplit.parse with the same method signature, but connection with each other.
- The method above can be used in duck typing method below
  def printSentenceParts(sentence: String, parser: {def parse(sentence: String): Array[String]}) = {}

Note:
- Overusing duck typing can negatively affect code quality and application performance.
- You should not avoid creating common interfaces in favor of duck typing.
- It should be only used in case when we cannot implement a common interface between different types.
 */
object DuckApp {
  class SentenceParserTokenize {
    def parse(sentence: String): Array[String] = {
      val tokenizer = new StringTokenizer(sentence)
      Iterator.continually({
        val hasMore = tokenizer.hasMoreTokens
        if (hasMore) {
          (hasMore, tokenizer.nextToken())
        } else {
          (hasMore, null)
        }
      }).takeWhile(_._1).map(_._2).toArray
    }
  }

  class SentenceParserSplit {
    def parse(sentence: String): Array[String] =
      sentence.split("\\s")

    def foo() = println("foo")
  }

  def printSentenceParts(sentence: String, parser: {def parse(sentence: String): Array[String]}) =
    parser.parse(sentence).foreach(println)

  def main(args: Array[String]): Unit = {
    tokenizerTest()

    val tokenizerParser = new SentenceParserTokenize
    val splitParser = new SentenceParserSplit

    val sentence = "This is the sentence we will be splitting."

    println("# Using the tokenize parser")
    printSentenceParts(sentence, tokenizerParser)

    println("# Using the split parser")
    printSentenceParts(sentence, splitParser)
  }

  private def tokenizerTest() = {
    println("# tokenizerTest")
    val sentence = "This is the sentence we will be splitting."
    val tokenize = new SentenceParserTokenize
    val strings = tokenize.parse(sentence)
    strings.foreach(println)
  }
}
/*
# tokenizerTest
This
is
the
sentence
we
will
be
splitting.
# Using the tokenize parser
This
is
the
sentence
we
will
be
splitting.
# Using the split parser
This
is
the
sentence
we
will
be
splitting.
 */
