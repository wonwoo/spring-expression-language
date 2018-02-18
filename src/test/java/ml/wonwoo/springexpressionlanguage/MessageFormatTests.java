package ml.wonwoo.springexpressionlanguage;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageFormatTests {

  @Test
  public void message() {
    String message = "my foo is #{name}, i bar #{age}";
    SpelExpressionParser parser = new SpelExpressionParser();
    Foo foo = new Foo("wonwoo", 33);
    StandardEvaluationContext context = new StandardEvaluationContext(foo);
    Expression expression = parser.parseExpression(message, ParserContext.TEMPLATE_EXPRESSION);
    String value = expression.getValue(context, String.class);
    assertThat(value).isEqualTo("my foo is wonwoo, i bar 33");
  }

  @Test
  public void templateMessage() {
    String message = "my foo is ${name}, i bar ${age}";
    SpelExpressionParser parser = new SpelExpressionParser();
    Foo foo = new Foo("wonwoo", 33);
    StandardEvaluationContext context = new StandardEvaluationContext(foo);
    Expression expression = parser.parseExpression(message, new ParserContext() {
      @Override
      public boolean isTemplate() {
        return true;
      }

      @Override
      public String getExpressionPrefix() {
        return "${";
      }

      @Override
      public String getExpressionSuffix() {
        return "}";
      }
    });
    String value = expression.getValue(context, String.class);
    assertThat(value).isEqualTo("my foo is wonwoo, i bar 33");
  }

  class Foo {
    private String name;
    private int age;

    public Foo(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }
}
