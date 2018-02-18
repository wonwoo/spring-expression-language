package ml.wonwoo.springexpressionlanguage;

import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class ContextTests {

  @Test
  public void root() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("name.length() < 10");
    Boolean result = exp.getValue(new Foo("wonwoo"), Boolean.class);
    assertThat(result).isTrue();
  }

  @Test
  public void context() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("name.length() < 10");
    EvaluationContext context = new StandardEvaluationContext(new Foo("wonwoo"));
    Boolean result = exp.getValue(context, Boolean.class);
    assertThat(result).isTrue();
  }

  @Test
  public void contextValue() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("name");
    EvaluationContext context = new StandardEvaluationContext(new Foo("wonwoo"));
    exp.setValue(context, "kevin");
    String result = exp.getValue(context, String.class);
    assertThat(result).isEqualTo("kevin");
  }

  class Foo {
    private String name;

    public Foo(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
