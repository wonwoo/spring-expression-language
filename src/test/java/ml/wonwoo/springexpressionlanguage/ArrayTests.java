package ml.wonwoo.springexpressionlanguage;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayTests {

  @Test
  public void array() {
    ExpressionParser parser = new SpelExpressionParser();
    Foo foo = new Foo();
    foo.names = Arrays.asList("wonwoo", "kevin");
    StandardEvaluationContext context = new StandardEvaluationContext(foo);
    Expression expression = parser.parseExpression("names[0]");
    String value = expression.getValue(context, String.class);
    assertThat(value).isEqualTo("wonwoo");
  }

  @Test
  public void arrayValue() {
    ExpressionParser parser = new SpelExpressionParser();
    Foo foo = new Foo();
    foo.names = Arrays.asList("wonwoo", "kevin");
    StandardEvaluationContext context = new StandardEvaluationContext(foo);
    parser.parseExpression("names[0]").setValue(context, "test");
    assertThat(foo.names.get(0)).isEqualTo("test");
  }

  @Test
  public void arraysValue() {
    ExpressionParser parser = new SpelExpressionParser();
    Bar bar = new Bar();
    Simple simple = new Simple();
    simple.name = "wonwoo";
    bar.simple = simple;
    StandardEvaluationContext context = new StandardEvaluationContext(bar);
    parser.parseExpression("simple.name").setValue(context, "test");
    assertThat(bar.simple.name).isEqualTo("test");

  }

  class Bar {
    public Simple simple;

    @Override
    public String toString() {
      return "Bar{" +
          "simple=" + simple +
          '}';
    }
  }

  class Simple {
    public String name;

    @Override
    public String toString() {
      return "Simple{" +
          "name='" + name + '\'' +
          '}';
    }
  }


  class Foo {
    public List<String> names = new ArrayList<>();


    @Override
    public String toString() {
      return "Foo{" +
          "names=" + names +
          '}';
    }
  }
}
