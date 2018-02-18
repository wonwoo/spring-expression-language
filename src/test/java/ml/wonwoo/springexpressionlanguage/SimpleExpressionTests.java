package ml.wonwoo.springexpressionlanguage;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleExpressionTests {

  @Test
  public void simple() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("'Hello World'");
    String value = (String) exp.getValue();
    assertThat(value).isEqualTo("Hello World");
  }

  @Test
  public void simpleByte() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("'Hello World'.bytes");
    byte[] bytes = (byte[]) exp.getValue();
    assertThat(new String(bytes)).isEqualTo("Hello World");
  }

  @Test
  public void simpleLength() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("'Hello World'.bytes.length");
    Integer length = exp.getValue(Integer.class);
    assertThat(length).isEqualTo(11);
  }

  @Test
  public void stringLength() {
    ExpressionParser parser = new SpelExpressionParser();
    Expression exp = parser.parseExpression("'Hello World'.length()");
    Integer length = exp.getValue(Integer.class);
    assertThat(length).isEqualTo(11);
  }

}
